/*
 * retoure-server
 * retoure-store-demo
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 11.02.13 09:33
 */

package eu.artofcoding.retoure.store.demo;

import eu.artofcoding.beetlejuice.cdm.accounting.Invoice;
import eu.artofcoding.beetlejuice.cdm.store.*;
import eu.artofcoding.beetlejuice.email.Postman;
import eu.artofcoding.beetlejuice.email.cdi.QPostman;
import eu.artofcoding.beetlejuice.entity.Attachment;
import eu.artofcoding.beetlejuice.entity.Email;
import eu.artofcoding.beetlejuice.entity.MimeType;
import eu.artofcoding.beetlejuice.template.TemplateProcessor;
import eu.artofcoding.retoure.api.RetoureConstants;
import eu.artofcoding.retoure.api.RetoureException;
import eu.artofcoding.retoure.api.RetoureRuntimeException;
import eu.artofcoding.retoure.test.TestData;
import eu.artofcoding.retoure.delivery.ReturnLabelClient;
import eu.artofcoding.retoure.delivery.dhl.AmselClient;
import eu.artofcoding.retoure.store.RetoureFacade;
import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Local;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static eu.artofcoding.beetlejuice.email.cdi.TransportType.SSL_TLS;
import static eu.artofcoding.retoure.store.demo.DemoConstants.STORE;

@Stateless
@Local
public class DemoFacade implements RetoureFacade {

    private static final long serialVersionUID = 1L;

    private Logger logger;

/*
    @EJB
    private RetoureDAO retoureDAO;
*/

    @Inject
    @AmselClient(portalId = "OnlineRetoure", deliveryName = "Deutschland_Var3")
    private ReturnLabelClient returnLabelClient;

    @Inject
    private transient TemplateProcessor templateProcessor;

    @Resource(mappedName = "mail/retoure")
    private transient Session mailSession;

    @Inject
    @QPostman(transportType = SSL_TLS)
    private transient Postman postman;

    @PostConstruct
    private void postConstruct() {
        logger = Logger.getLogger(DemoFacade.class.getName());
        // Initialize TemplateProcessor
        ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        // Templates can reside in different paths due to packaging
        templateProcessor.addTemplateLoader(context, String.format("META-INF/%s/email", STORE));
        templateProcessor.addTemplateLoader(context, String.format("WEB-INF/classes/META-INF/%s/email", STORE));
        templateProcessor.getConfiguration().setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);
        // Postman
        if (null != postman) {
            postman.setSession(mailSession);
        } else {
            logger.log(Level.SEVERE, "No postman, no emails!");
        }
    }

    @Override
    public StoreCustomer login(StoreCustomer customer, String ident) {
        // Check state
        // Already logged in?
        if (customer.isLoginOk()) {
            // TODO Stay logged in or log out and re-login?
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(String.format("Customer %s-%s already logged in", customer.getCustomerIdent(), customer.getAgentIdent()));
            }
            // Clear data
            customer.setAgentIdent(null);
            customer.setInvoices(null);
        }
        // Get identification entered by user
        String enteredCustomerIdent = customer.getCustomerIdent();
        if (null != enteredCustomerIdent) {
            String[] _customerAgentIdent = CheckUserInput.checkCustomerAndAgentIdent(enteredCustomerIdent);
            customer.setCustomerIdent(_customerAgentIdent[0]);
            customer.setAgentIdent(_customerAgentIdent[1]);
            // Test customers
            if (customer.getCustomerIdent().equalsIgnoreCase(RetoureConstants.CUSTOMER_ALFONS)) {
                TestData.makeCustomerALFONS01(this, customer);
                // Login ok
                customer.setLoginOk(true);
            } else if (customer.getCustomerIdent().equalsIgnoreCase(RetoureConstants.CUSTOMER_MANFRED)) {
                TestData.makeCustomerMANFRED1(this, customer);
                // Login ok
                customer.setLoginOk(true);
            } else {
                // Call webservice
/*
                try {
                    // Agent logging in?
                    if (null != customer.getAgentIdent() && customer.getAgentIdent().length() > 0) {
                        IRT01Stub.IRT01Result irt01Result = Irt01Client.call(customer.getCustomerIdent(), customer.getAgentIdent(), ident);
                        if (irt01Result.get_SBNR().equals(BigDecimal.ZERO)) {
                            // TODO Error message and handle exception
                            throw new RetoureException();
                        }
                    } else {
                        IRT01Stub.IRT01Result irt01Result = Irt01Client.call(customer.getCustomerIdent(), null, ident);
                        if (!irt01Result.get_SBNR().equals(BigDecimal.ZERO)) {
                            // TODO Error message and handle exception
                            throw new RetoureException();
                        }
                    }
                    // Login ok
                    customer.setLoginOk(true);
                } catch (RetoureException e) {
                    // Login invalid
                    customer.setLoginOk(false);
                }
*/
            }
        }
        // Return
        return customer;
    }

    @Override
    public StoreCustomer fetchCustomer(StoreCustomer customer) throws RetoureException {
        return customer;
    }

    @Override
    public StoreCustomer fetchInvoice(StoreCustomer customer, String invoiceIdent) {
        // Check state
        if (!customer.isLoginOk()) {
            throw new RetoureRuntimeException("Customer is not logged in");
        }
        if (null != invoiceIdent) {
            switch (invoiceIdent) {
                case RetoureConstants.INVOICE_DHL4:
                    TestData.makeInvoiceDHL004(this, customer, invoiceIdent);
                    break;
                case RetoureConstants.INVOICE_SPED01:
                    TestData.makeInvoiceSPED01(this, customer, invoiceIdent);
                    break;
            }
        }
        // Return
        return customer;
    }

    @Override
    public ReturnReason[] fetchReasons(Article article) {
        List<ReturnReason> returnReasons = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            returnReasons.add(new ReturnReason(i, String.format("Grund %d", i)));
        }
        return returnReasons.toArray(new ReturnReason[returnReasons.size()]);
    }

    public void addArticleToInvoice(StoreCustomer customer, Invoice invoice, Article article) {
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
    public Future<StoreCustomer> placeReturn(StoreCustomer customer, String invoiceIdent) throws RetoureException {
        if (logger.isLoggable(Level.FINE)) {
            logger.fine("placeReturn: start");
        }
        Invoice invoice = customer.getInvoice(invoiceIdent);
        if (null != invoice && null == invoice.getReturnLabel()) {
            // Create label request
            ReturnLabel returnLabel = returnLabelClient.makeLabel(customer);
            invoice.setReturnLabel(returnLabel);
            String base64 = returnLabel.getBase64();
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(String.format("placeReturn: stop, got %d bytes", base64.length()));
            }
            // Filename
            String filename = String.format("%s_Retourenaufkleber_%s.pdf", customer.getCustomerIdent(), invoice.getInvoiceIdent());
            returnLabel.setFilename(filename);
            try {
                returnLabel.saveBinary(Paths.get("retoure", STORE, "returnlabel", filename));
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine(String.format("Saved return label for invoice %s", invoiceIdent));
                }
            } catch (IOException e) {
                throw new RetoureException(e);
            }
        } else if (null != invoice && null != invoice.getReturnLabel()) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning(String.format("Return label for invoice %s already fetched", invoiceIdent));
            }
        } else {
            String format = String.format("Invoice %s not found or no return label", invoiceIdent);
            throw new RetoureException(format);
        }
        // Return
        return new AsyncResult<>(customer);
    }

    @Override
    @Asynchronous
    public Future<Boolean> sendReturnLabelForInvoiceByMail(StoreCustomer customer, Invoice invoice) throws RetoureException {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Sending email to %s", customer.getEmail()));
        }
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
                    // Template returnlabel.html
                    email.setBody(templateProcessor.renderTemplateToString("returnlabel.html", Locale.GERMAN, root));
                    // Add return label
                    // TODO Provide link to fetch label up to 3 times (protected through token)
                    byte[] content = Files.readAllBytes(invoice.getReturnLabel().getPath());
                    Attachment attachment = new Attachment(MimeType.PDF, invoice.getReturnLabel().getFilename(), content);
                    email.addAttachment(attachment);
                    // Set recipient
                    email.setToAddress(customer.getEmail());
                    // Send email
                    postman.sendMail(email);
                    if (logger.isLoggable(Level.INFO)) {
                        logger.info("Email sent to " + email);
                    }
                } catch (TemplateException e) {
                    if (logger.isLoggable(Level.SEVERE)) {
                        logger.log(Level.SEVERE, "Cannot render template", e);
                    }
                } catch (MessagingException | IOException e) {
                    if (logger.isLoggable(Level.SEVERE)) {
                        logger.log(Level.SEVERE, "Cannot send mail", e);
                    }
                }
            } else {
                throw new RetoureException("Cannot send return label, no email address");
            }
        } else {
            throw new RetoureException("No postman, no emails!");
        }
        return new Future<Boolean>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public Boolean get() throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public Boolean get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        };
    }

}
