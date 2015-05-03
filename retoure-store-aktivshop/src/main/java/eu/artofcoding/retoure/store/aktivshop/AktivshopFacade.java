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

import eu.artofcoding.beetlejuice.cdm.accounting.Invoice;
import eu.artofcoding.beetlejuice.cdm.store.Article;
import eu.artofcoding.beetlejuice.cdm.store.ReturnLabel;
import eu.artofcoding.beetlejuice.cdm.store.ReturnReason;
import eu.artofcoding.beetlejuice.cdm.store.StoreCustomer;
import eu.artofcoding.beetlejuice.email.Postman;
import eu.artofcoding.beetlejuice.email.cdi.QPostman;
import eu.artofcoding.beetlejuice.entity.Attachment;
import eu.artofcoding.beetlejuice.entity.Email;
import eu.artofcoding.beetlejuice.entity.MimeType;
import eu.artofcoding.beetlejuice.template.TemplateProcessor;
import eu.artofcoding.retoure.api.RetoureConstants;
import eu.artofcoding.retoure.api.RetoureException;
import eu.artofcoding.retoure.test.TestData;
import eu.artofcoding.retoure.delivery.ReturnLabelClient;
import eu.artofcoding.retoure.delivery.dhl.AmselClient;
import eu.artofcoding.retoure.store.RetoureFacade;
import eu.artofcoding.retoure.store.aktivshop.wsclient.irt01t.IRT01Result;
import eu.artofcoding.retoure.store.aktivshop.wsclient.irt02t.IRT02Result;
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
import java.math.BigDecimal;
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
import static eu.artofcoding.retoure.store.aktivshop.AktivshopConstants.STORE;
import static eu.artofcoding.retoure.store.aktivshop.AktivshopWebserviceHelper.checkIRT02Result;

@Stateless
@Local
public class AktivshopFacade implements RetoureFacade {

    private static final long serialVersionUID = 1L;

    private transient Logger logger;

/*
    @EJB
    private RetoureDAO retoureDAO;
*/

    @Inject
    @AmselClient(portalId = "OnlineRetoure", deliveryName = "Deutschland_Var3")
    private ReturnLabelClient returnLabelClient;

    @Inject
    private TemplateProcessor templateProcessor;

    @Resource(mappedName = "mail/retoure")
    private Session mailSession;

    @Inject
    @QPostman(transportType = SSL_TLS)
    private Postman postman;

    @PostConstruct
    private void postConstruct() {
        logger = Logger.getLogger(AktivshopFacade.class.getName());
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
    public StoreCustomer login(StoreCustomer customer, String ident) throws RetoureException {
        // Check state
        // Already logged in?
        if (customer.isLoginOk()) {
            // Log out and re-login
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(String.format("Customer %s-%s already logged in", customer.getCustomerIdent(), customer.getAgentIdent()));
            }
            // Clear data
            customer.setAgentIdent(null);
            customer.setInvoices(null);
        }
        // Users wants to login, so we assume logged in == false
        customer.setLoginOk(false);
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
                // Agent logging in?
                if (null != customer.getAgentIdent() && customer.getAgentIdent().length() > 0) {
                    IRT01Result irt01Result = IRT01Client.call(customer.getCustomerIdent(), customer.getAgentIdent(), ident);
                    if (irt01Result.getSBNR().getValue().equals(BigDecimal.ZERO)) {
                        // Login invalid
                        customer.setLoginOk(false);
                        // Error message and handle exception
                        logger.warning(String.format("Cannot login customer '%s-%s', ERG='%s' ERGT='%s'", customer.getCustomerIdent(), customer.getAgentIdent(), irt01Result.getERG().getValue(), irt01Result.getERGT().getValue()));
                    } else {
                        // Login ok
                        customer.setLoginOk(true);
                    }
                } else {
                    IRT01Result irt01Result = IRT01Client.call(customer.getCustomerIdent(), null, ident);
                    // ERG != 0
                    if (!irt01Result.getERG().getValue().equals(BigDecimal.ZERO)) {
                        // Login invalid
                        customer.setLoginOk(false);
                        // Error message and handle exception
                        logger.warning(String.format("Cannot login customer '%s', ERG='%s' ERGT='%s'", customer.getCustomerIdent(), irt01Result.getERG().getValue(), irt01Result.getERGT().getValue()));
                    } else if (irt01Result.getERG().getValue().equals(BigDecimal.ZERO)) {
                        // Login ok
                        customer.setLoginOk(true);
                    }
                }
            }
        }
        // Return
        return customer;
    }

    @Override
    public StoreCustomer fetchCustomer(StoreCustomer customer) throws RetoureException {
        // Data was fetched when retrieving data about invoice
        return customer;
    }

    @Override
    public StoreCustomer fetchInvoice(StoreCustomer customer, String invoiceIdent) throws RetoureException {
        // Check state
        if (!customer.isLoginOk()) {
            throw new RetoureException("Customer is not logged in");
        }
        // Call webservice
        if (null != invoiceIdent) {
            switch (invoiceIdent) {
                case RetoureConstants.INVOICE_DHL4:
                    TestData.makeInvoiceDHL004(this, customer, invoiceIdent);
                    break;
                case RetoureConstants.INVOICE_SPED01:
                    TestData.makeInvoiceSPED01(this, customer, invoiceIdent);
                    break;
                default:
                    // Cast agentIdent -> sbnr
                    BigDecimal sbnr = BigDecimal.ZERO;
                    if (null != customer.getAgentIdent()) {
                        sbnr = BigDecimal.valueOf(Long.valueOf(customer.getAgentIdent()));
                    }
                    IRT02Result irt02Result = IRT02Client.call(sbnr, invoiceIdent);
                    if (checkIRT02Result(irt02Result)) {
                        AktivshopWebserviceHelper.readCustomer(customer, irt02Result);
                        AktivshopWebserviceHelper.readArticles(customer, irt02Result);
                    } else {
                        customer.setLoginOk(false);
                    }
                    break;
            }
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
    @Asynchronous
    public Future<StoreCustomer> placeReturn(StoreCustomer customer, String invoiceIdent) throws RetoureException {
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(String.format("placeReturn[customer=%s,invoice=%s]: start async", customer.getCustomerIdent(), invoiceIdent));
        }
        Invoice invoice = customer.getInvoice(invoiceIdent);
        if (null != invoice && null == invoice.getReturnLabel()) {
            // Create label request
            ReturnLabel returnLabel = returnLabelClient.makeLabel(customer);
            invoice.setReturnLabel(returnLabel);
            String base64 = returnLabel.getBase64();
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(String.format("placeReturn[customer=%s,invoice=%s]: stop, got %d bytes", customer.getCustomerIdent(), invoiceIdent, base64.length()));
            }
            // Filename
            String filename = String.format("%s_Retourenaufkleber_%s.pdf", customer.getCustomerIdent(), invoice.getInvoiceIdent());
            returnLabel.setFilename(filename);
            try {
                returnLabel.saveBinary(Paths.get("retoure", STORE, "returnlabel", filename));
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine(String.format("placeReturn[customer=%s,invoice=%s]: Saved return label for invoice", customer.getCustomerIdent(), invoiceIdent));
                }
            } catch (IOException e) {
                throw new RetoureException(e);
            }
        } else if (null != invoice && null != invoice.getReturnLabel()) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning(String.format("placeReturn[customer=%s,invoice=%s]: Return label for invoice already fetched", customer.getCustomerIdent(), invoiceIdent));
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
            logger.info(String.format("sendReturnLabelForInvoiceByMail[customer=%s,invoice=%s]: Sending email to %s", customer.getCustomerIdent(), invoice.getInvoiceIdent(), customer.getEmail()));
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
                    // TODO Provide link to fetch label up to n times (protected through token)
                    byte[] content = Files.readAllBytes(invoice.getReturnLabel().getPath());
                    Attachment attachment = new Attachment(MimeType.PDF, invoice.getReturnLabel().getFilename(), content);
                    email.addAttachment(attachment);
                    // Set recipient
                    email.setToAddress(customer.getEmail());
                    // Send email
                    postman.sendMail(email);
                    if (logger.isLoggable(Level.INFO)) {
                        logger.info(String.format("sendReturnLabelForInvoiceByMail[customer=%s,invoice=%s]: Email sent to %s", customer.getCustomerIdent(), invoice.getInvoiceIdent(), email));
                    }
                } catch (TemplateException e) {
                    if (logger.isLoggable(Level.SEVERE)) {
                        logger.log(Level.SEVERE, String.format("sendReturnLabelForInvoiceByMail[customer=%s,invoice=%s]: Cannot render template", customer.getCustomerIdent(), invoice.getInvoiceIdent()), e);
                    }
                } catch (MessagingException | IOException e) {
                    if (logger.isLoggable(Level.SEVERE)) {
                        logger.log(Level.SEVERE, String.format("sendReturnLabelForInvoiceByMail[customer=%s,invoice=%s]: Cannot send mail", customer.getCustomerIdent(), invoice.getInvoiceIdent()), e);
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
