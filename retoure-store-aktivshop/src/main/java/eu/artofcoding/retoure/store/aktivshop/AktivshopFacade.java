/*
 * retoure-server
 * retoure-store-aktivshop
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 11.02.13 09:33
 */

package eu.artofcoding.retoure.store.aktivshop;

import eu.artofcoding.beetlejuice.cdm.Customer;
import eu.artofcoding.beetlejuice.cdm.Group;
import eu.artofcoding.beetlejuice.cdm.Salutation;
import eu.artofcoding.beetlejuice.cdm.accounting.BankAccount;
import eu.artofcoding.beetlejuice.cdm.accounting.BankAccountType;
import eu.artofcoding.beetlejuice.cdm.accounting.FinanceCompany;
import eu.artofcoding.beetlejuice.cdm.accounting.Invoice;
import eu.artofcoding.beetlejuice.cdm.store.Article;
import eu.artofcoding.beetlejuice.cdm.store.ArticleReturn;
import eu.artofcoding.beetlejuice.cdm.store.ReturnLabel;
import eu.artofcoding.beetlejuice.cdm.store.ReturnReason;
import eu.artofcoding.beetlejuice.email.Postman;
import eu.artofcoding.beetlejuice.email.cdi.QPostman;
import eu.artofcoding.beetlejuice.entity.Attachment;
import eu.artofcoding.beetlejuice.entity.Email;
import eu.artofcoding.beetlejuice.entity.MimeType;
import eu.artofcoding.beetlejuice.template.TemplateProcessor;
import eu.artofcoding.retoure.api.RetoureException;
import eu.artofcoding.retoure.api.RetoureRuntimeException;
import eu.artofcoding.retoure.delivery.dhl.AmselClient;
import eu.artofcoding.retoure.entity.RetoureDAO;
import eu.artofcoding.retoure.store.RetoureFacade;
import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import static eu.artofcoding.beetlejuice.email.cdi.TransportType.SSL_TLS;

@Stateless
public class AktivshopFacade implements RetoureFacade {

    private static final Logger logger = Logger.getLogger(AktivshopFacade.class.getName());

    private static final String STORE = "AKTIVSHOP";

    @EJB
    private RetoureDAO retoureDAO;

    @Inject
    private transient TemplateProcessor templateProcessor;

    // JBoss @Resource(mappedName = "java:/retoure-smtp")
    @Resource(lookup = "mail/retoure")
    private transient Session mailSession;

    @Inject
    @QPostman(transportType = SSL_TLS)
    private transient Postman postman;

    @PostConstruct
    private void postConstruct() {
        // Initialize TemplateProcessor
        ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        // TODO Not found on Glassfish@FreeBSD
        String format = String.format("/resources/retoure/store/%s/email", STORE);
        templateProcessor.addTemplateLoader(context, format);
        templateProcessor.getConfiguration().setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);
        // Postman
        if (null != postman) {
            postman.setSession(mailSession);
        } else {
            logger.log(Level.SEVERE, "No postman, no emails!");
        }
    }

    @Override
    public Customer login(Customer customer) {
        // Check state
        // Already logged in?
        if (customer.isLoginOk()) {
            // TODO Stay logged in or log out and re-login?
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(String.format("Customer %s already logged in", customer));
            }
            // Clear data
            customer.setAgentIdent(null);
            customer.setInvoices(null);
        }
        // TODO Call webservice
        if (true) {
            String[] split = customer.getCustomerIdent().split("[-]");
            String _customer = split[0];
            String _agent = null;
            if (split.length == 2) {
                _agent = split[1];
            }
            if (_customer.equalsIgnoreCase("Alfons")) {
                makeCustomerAlfons(customer);
                // Login ok
                customer.setLoginOk(true);
            } else if (_customer.equalsIgnoreCase("Manfred")) {
                makeCustomerManfred(customer);
                // Login ok
                customer.setLoginOk(true);
            } else {
                // Login invalid
                customer.setLoginOk(false);
            }
            if (null != _agent) {
                customer.setAgentIdent(_agent);
            }
        }
        // Return
        return customer;
    }

    @Override
    public Customer fetchInvoice(Customer customer, String invoiceIdent) {
        // Check state
        if (!customer.isLoginOk()) {
            throw new RetoureRuntimeException("Customer is not logged in");
        }
        // TODO Call webservice
        switch (invoiceIdent) {
            case "4ArtikelDHL":
                makeInvoice4ArtikelDHL(customer, invoiceIdent);
                break;
            case "1ArtikelSpedition":
                makeInvoice1ArtikelSpedition(customer, invoiceIdent);
                break;
        }
        // Return
        return customer;
    }

    @Override
    public ReturnReason[] fetchReasons(Article article) {
        List<ReturnReason> returnReasons = new ArrayList<>();
        // TODO Call webservice
        for (int i = 0; i < 5; i++) {
            returnReasons.add(new ReturnReason(i, String.format("Grund %d", i)));
        }
        return returnReasons.toArray(new ReturnReason[returnReasons.size()]);
    }

    @Override
    public void addArticleToInvoice(Customer customer, Invoice invoice, Article article) {
        // Agent logged in, unset unreturnable flag
        if (null != customer.getAgentIdent()) {
            ArticleReturn articleReturn = article.getArticleReturn();
            if (!articleReturn.isReturnable()) {
                articleReturn.setReturnable(true);
                articleReturn.setUnreturnableReason(null);
            }
        }
        invoice.addArticle(article);
    }

    @Override
    @Asynchronous
    public Future<Customer> placeReturn(Customer customer, String invoiceIdent) throws RetoureException {
        logger.info("placeReturn: start");
        Invoice invoice = customer.getInvoice(invoiceIdent);
        if (null != invoice && null == invoice.getReturnLabel()) {
            // Create label request
            AmselClient amselClient = new AmselClient();
            ReturnLabel returnLabel = amselClient.makeLabel(customer);
            invoice.setReturnLabel(returnLabel);
            String base64 = returnLabel.getBase64();
            logger.info(String.format("placeReturn: stop, got %d bytes", base64.length()));
            String ident = String.format("%s_%s.pdf", customer.getCustomerIdent(), invoice.getInvoiceIdent());
            try {
                returnLabel.saveBinary(Paths.get("retoure", STORE, "returnlabel", ident));
                logger.info(String.format("Saved return label for invoice %s", invoiceIdent));
            } catch (IOException e) {
                throw new RetoureException(e);
            }
        } else if (null != invoice && null != invoice.getReturnLabel()) {
            logger.warning(String.format("Return label for invoice %s already fetched", invoiceIdent));
        } else {
            String format = String.format("No invoice object for %s or no return label", invoiceIdent);
            throw new RetoureException(format);
        }
        // Return
        return new AsyncResult<>(customer);
    }

    @Override
    @Asynchronous
    public void sendReturnLabelForInvoiceByMail(Customer customer, Invoice invoice) throws RetoureException {
        logger.info(String.format("Sending email to %s", customer.getEmail()));
        if (null != postman) {
            if (null != customer.getEmail()) {
                // Data model for template
                SimpleHash root = new SimpleHash();
                root.put("customer", customer);
                root.put("invoice", invoice);
                root.put("returnlabel", invoice.getReturnLabel());
                root.put("retouredatum", new Date());
                try {
                    Email email = new Email();
                    email.setFromAddress(String.format("%s <%s>", "aktivshop Retoure", "retoure@aktivshop.de"));
                    email.setSubject(String.format("Ihre Retoure zur Rechnung Nr. %s", invoice.getInvoiceIdent()));
                    // Render template depending on request locale
                    email.setMimeType(MimeType.HTML);
                    // Template /resources/retoure/store/AKTIVSHOP/email/returnlabel.html
                    email.setBody(templateProcessor.renderTemplateToString("returnlabel.html", Locale.GERMAN, root));
                    // Add return label
                    String filename = String.format("%s_Retourenaufkleber_%s.pdf", STORE, invoice.getInvoiceIdent());
                    byte[] content = Files.readAllBytes(invoice.getReturnLabel().getPath());
                    Attachment attachment = new Attachment(MimeType.PDF, filename, content);
                    email.addAttachment(attachment);
                    // Set recipient
                    email.setToAddress(customer.getEmail());
                    // Send email
                    postman.sendMail(email);
                    logger.info("Email sent to " + email);
                } catch (TemplateException e) {
                    logger.log(Level.SEVERE, "Cannot render template", e);
                } catch (MessagingException | IOException e) {
                    logger.log(Level.SEVERE, "Cannot send mail", e);
                }
            } else {
                throw new RetoureException("Cannot send return label, no email address");
            }
        } else {
            throw new RetoureException("No postman, no emails!");
        }
    }

    //<editor-fold desc="Testdata">

    /**
     * Alfons Aktivmann
     */
    private void makeCustomerAlfons(Customer customer) {
        // Set customer data
        customer.setSalutation(Salutation.HERR);
        customer.setFirstname("Alfons");
        customer.setLastname("Anspruchsvoll");
        customer.setShippingAddress1("Allerseits Grüne Heide");
        customer.setShippingAddress1StreetNumber("32a");
        customer.setZipcode("48333");
        customer.setCity("Rheine-Laer-Holthausen");
        customer.setPhone("02550 123456-890");
        customer.setEmail("ralf@art-of-coding.eu");
        // BankAccount
        BankAccount bankAccount = customer.getBankAccount();
        bankAccount.setAccountHolder("Alfons Anspruchsvoll");
        bankAccount.setAccountNumber("101450450");
        bankAccount.setRoutingCode("40351060");
        // Set account type to CREDITNOTE
        bankAccount.setAccountType(BankAccountType.CREDITNOTE);
    }

    /**
     * Manfred Mangelware
     */
    private void makeCustomerManfred(Customer customer) {
        // Set customer data
        customer.setSalutation(Salutation.HERR);
        customer.setFirstname("Manfred");
        customer.setLastname("Mangelware");
        customer.setShippingAddress1("Allerseits Grüne Heide");
        customer.setShippingAddress1StreetNumber("32a");
        customer.setZipcode("48333");
        customer.setCity("Oldenburg");
        customer.setPhone("04124 123456-890");
        customer.setEmail("ralf@art-of-coding.eu");
        // BankAccount
        BankAccount bankAccount = customer.getBankAccount();
        bankAccount.setAccountHolder("Manfred Mangelware");
        bankAccount.setAccountNumber("5234123456781234");
        // Creditcard
        bankAccount.setFinanceCompany(FinanceCompany.MASTERCARD);
        // Set account type to CREDITNOTE
        bankAccount.setAccountType(BankAccountType.CREDITNOTE);
    }

    /**
     * 4 Artikel, DHL
     */
    private void makeInvoice4ArtikelDHL(Customer customer, String invoiceIdent) {
        // Groups
        Group g01 = new Group("G01", "Gruppe 01");
        Group s22 = new Group("S22", "Subgruppe 22");
        // Invoice
        Invoice invoice = new Invoice(invoiceIdent, new java.util.Date(), true, "DHL", true, null);
        Article article =
                new Article("AR5640",
                        g01, s22, "DHL", 1.0f,
                        "Beschreibung 1 für Artikel", "Beschreibung 2");
        article.getArticleReturn().setReturnable(true);
        invoice.addArticle(article);
        for (int i = 0; i < 3; i++) {
            article =
                    new Article(String.format("AR567%d", i),
                            g01, s22, "DHL", 5.0f + i,
                            String.format("Beschreibung 1 für Artikel %d", i), "Beschreibung 2");
            article.getArticleReturn().setReturnable(true);
            addArticleToInvoice(customer, invoice, article);
        }
        customer.addInvoice(invoiceIdent, invoice);
    }

    /**
     * 1 Artikel, Spedition
     */
    private void makeInvoice1ArtikelSpedition(Customer customer, String invoiceIdent) {
        // Groups
        Group g01 = new Group("G01", "Gruppe 01");
        Group s22 = new Group("S22", "Subgruppe 22");
        // Invoice
        Invoice invoice = new Invoice(invoiceIdent, new java.util.Date(), true, "Spedition", true, null);
        invoice.addArticle(
                new Article("SP9981",
                        g01, s22, "Spedition", 1.0f,
                        "Ein ganz großer schwerer toller Stuhl mit Alles", "So Massage und so..."));
        customer.addInvoice(invoiceIdent, invoice);
    }

    //</editor-fold>

}
