/*
 * retoure-server
 * retoure-web
 * Copyright (C) 2012-2012 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 27.11.12 15:44
 */

package eu.artofcoding.retoure.web;

import eu.artofcoding.beetlejuice.cdm.Customer;
import eu.artofcoding.beetlejuice.cdm.Salutation;
import eu.artofcoding.beetlejuice.cdm.accounting.BankAccountType;
import eu.artofcoding.beetlejuice.cdm.accounting.FinanceCompany;
import eu.artofcoding.beetlejuice.cdm.accounting.Invoice;
import eu.artofcoding.beetlejuice.cdm.store.Article;
import eu.artofcoding.beetlejuice.cdm.store.ReturnLabel;
import eu.artofcoding.beetlejuice.cdm.store.ReturnReason;
import eu.artofcoding.beetlejuice.web.jsf.FacesHelper;
import eu.artofcoding.beetlejuice.web.jsf.NavigationHelper;
import eu.artofcoding.retoure.api.RetoureException;
import eu.artofcoding.retoure.store.RetoureFacade;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("retoure")
@SessionScoped
public class Retoure implements Serializable {

    private static final Long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(Retoure.class.getName());

    @Inject
    private RetourenWebHelper retourenWebHelper;

    @EJB
    private RetoureFacade retoureFacade;

    @Inject
    private NavigationHelper navigationHelper;

    private String customerIdent = "Alfons";

    private String invoiceIdent = "4ArtikelDHL";

    private Customer customer;

    private boolean allArticlesMarkedForReturn;

    private transient Future<Customer> returnLabelFuture;

    private boolean returnLabelMailed;

    @PostConstruct
    private void postConstruct() {
        // Initialize helper
        FacesHelper facesHelper = FacesHelper.getInstance(FacesContext.getCurrentInstance());
        retourenWebHelper.setHttpSession(facesHelper.getHttpSession());
        /*
        // Initialize TemplateProcessor
        ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        templateProcessor.addTemplateLoader(context, String.format("/resources/retoure/store/%s/email", retourenWebHelper.getStoreIdent().getName()));
        templateProcessor.getConfiguration().setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);
        // Postman
        if (null != postman) {
            postman.setSession(retoureConfiguration.getMailSession());
        } else {
            logger.log(Level.SEVERE, "No postman, no emails!");
        }
        */
        // Navigation
        navigationHelper.setViews(new String[]{"index", "show-invoice", "show-return-reason", "show-customer", "show-returnlabel"});
    }

    //<editor-fold desc="Getter and Setter">

    public Customer getCustomer() {
        return customer;
    }

    public String getCustomerIdent() {
        return customerIdent;
    }

    public void setCustomerIdent(String customerIdent) {
        this.customerIdent = customerIdent;
    }

    public String getInvoiceIdent() {
        return invoiceIdent;
    }

    public void setInvoiceIdent(String invoiceIdent) {
        this.invoiceIdent = invoiceIdent;
    }

    public boolean isAllArticlesMarkedForReturn() {
        return allArticlesMarkedForReturn;
    }

    public boolean isReturnLabelMailed() {
        return returnLabelMailed;
    }

    //</editor-fold>

    //<editor-fold desc="JSF, Navigation Cases">

    /**
     * Process pre-render-view event.
     * E.g. use it to provide a request to managed bean:
     * <p>
     * &lt;f:metadata><br/>
     * &lt;f:event type="preRenderView" listener="#{bean.preRenderView}"/><br/>
     * &lt;/f:metadata>
     * </p>
     */
    public void preRenderView() {
        navigationHelper.findPageIndexByViewId();
    }

    public int getPageCount() {
        return navigationHelper.getViews().length;
    }

    public int getPageIndex() {
        return navigationHelper.getActualViewIndex() + 1;
    }

    public String previousPage() {
        return navigationHelper.previousPage();
    }

    public String nextPage() {
        String outcome = navigationHelper.nextPage();
        // Action
        if (outcome.equals("show-returnlabel")) {
            try {
                makeReturnLabelForInvoice();
            } catch (RetoureException e) {
                // TODO Put return into database or send email to store
                logger.log(Level.SEVERE, "", e);
                // TODO Set error in NavigationHelper  actualPageIndex = -1;
                outcome = "error";
            }
        }
        return outcome;
    }

    public boolean canProceedToNextStep() {
        boolean b = true;
        // show-invoice
        if (navigationHelper.getActualViewId().equals("show-invoice")) {
            if (!(getArticlesMarkedForReturn().length > 0)) {
                b = false;
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine("NO ARTICLES MARKED FOR RETURN");
                }
            }
        }
        return b;
    }

    /*
    public void navigationListener(@Observes NavigationEvent event) {
        logger.info(String.format("Received navigation event %s", event));
    }
    */

    //</editor-fold>

    //<editor-fold desc="Login">

    public String login() {
        // Load data for customer and invoice
        customer = new Customer();
        customer.setStoreIdent(retourenWebHelper.getStoreIdent());
        customer.setCustomerIdent(customerIdent);
        retoureFacade.login(customer);
        // Return next page
        if (customer.isLoginOk()) {
            return fetchInvoice();
        } else {
            invoiceIdent = null;
            return "login-failed";
        }
    }

    //</editor-fold>

    //<editor-fold desc="Invoice">

    public String fetchInvoice() {
        retoureFacade.fetchInvoice(customer, invoiceIdent);
        Invoice invoice = customer.getInvoices().get(invoiceIdent);
        if (null != invoice) {
            return "show-invoice";
        } else {
            return "invoice-not-found";
        }
    }

    public Article[] getArticlesOfInvoice() {
        if (null != customer) {
            List<Article> articles = customer.getInvoice(invoiceIdent).getArticles();
            return articles.toArray(new Article[articles.size()]);
        } else {
            return null;
        }
    }

    //</editor-fold>

    //<editor-fold desc="Articles, mark for return">

    /**
     * Mark all checkboxes.
     * @param allArticlesMarkedForReturn State of checkbox.
     */
    public void setAllArticlesMarkedForReturn(boolean allArticlesMarkedForReturn) {
        this.allArticlesMarkedForReturn = allArticlesMarkedForReturn;
    }

    public void markAllArticlesForReturn(AjaxBehaviorEvent ajaxBehaviorEvent) {
        HtmlSelectBooleanCheckbox checkbox = (HtmlSelectBooleanCheckbox) ajaxBehaviorEvent.getSource();
        for (Article a : getArticlesOfInvoice()) {
            if (a.getArticleReturn().isReturnable()) {
                a.getArticleReturn().setToBeReturned(checkbox.isSelected());
            }
        }
    }

    public void markArticleForReturn(AjaxBehaviorEvent ajaxBehaviorEvent) {
    }

    public void returnCountClickEvent(AjaxBehaviorEvent ajaxBehaviorEvent) {
        // Get articleIdent from f:param/request params
        String articleIdent = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("articleIdent");
        // Find article and set to-be-returned flag
        for (Article a : getArticlesOfInvoice()) {
            if (a.getArticleIdent().equals(articleIdent)) {
                a.getArticleReturn().setToBeReturned(true);
            }
        }
    }

    public Article[] getArticlesMarkedForReturn() {
        if (null != customer) {
            List<Article> returnArticles = customer.getInvoice(invoiceIdent).getReturnArticles();
            return returnArticles.toArray(new Article[returnArticles.size()]);
        } else {
            return null;
        }
    }

    public ReturnReason[] getReturnReasonForArticle(Article article) {
        return retoureFacade.fetchReasons(article);
    }

    public void reasonSelected(AjaxBehaviorEvent ajaxBehaviorEvent) {
        /*
        // Get articleIdent from f:param/request params
        String articleIdent = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("articleIdent");
        HtmlSelectOneRadio radioButton = (HtmlSelectOneRadio) ajaxBehaviorEvent.getSource();
        Object value = radioButton.getValue();
        System.out.println(value);
        */
    }

    //</editor-fold>

    //<editor-fold desc="Customer Data">

    public Salutation[] getSalutation() {
        return Salutation.values();
    }

    public Salutation getSalutationMr() {
        return Salutation.HERR;
    }

    public Salutation getSalutationMs() {
        return Salutation.FRAU;
    }

    public BankAccountType getAccountTypeNone() {
        return BankAccountType.NONE;
    }

    public BankAccountType getAccountTypeCreditNote() {
        return BankAccountType.CREDITNOTE;
    }

    public BankAccountType getAccountTypeBankAccount() {
        return BankAccountType.BANKACCOUNT;
    }

    public BankAccountType getAccountTypePayPal() {
        return BankAccountType.PAYPAL;
    }

    public BankAccountType getAccountTypeCreditcard() {
        return BankAccountType.CREDITCARD;
    }

    public void bankAccountTypeSelected(AjaxBehaviorEvent ajaxBehaviorEvent) {
        /*
        HtmlSelectOneRadio radioButton = (HtmlSelectOneRadio) ajaxBehaviorEvent.getComponent();
        Object value = radioButton.getValue();
        System.out.println(value);
        */
    }

    public boolean accountTypeIsCreditNote() {
        BankAccountType accountType = customer.getBankAccount().getAccountType();
        return null != accountType && accountType.equals(BankAccountType.CREDITNOTE);
    }

    public boolean accountTypeIsBankAccount() {
        BankAccountType accountType = customer.getBankAccount().getAccountType();
        return null != accountType && accountType.equals(BankAccountType.BANKACCOUNT);
    }

    public boolean accountTypeIsPayPal() {
        BankAccountType accountType = customer.getBankAccount().getAccountType();
        return null != accountType && accountType.equals(BankAccountType.PAYPAL);
    }

    public boolean accountTypeIsCreditcard() {
        BankAccountType accountType = customer.getBankAccount().getAccountType();
        return null != accountType && accountType.equals(BankAccountType.CREDITCARD);
    }

    public /*SelectItem[]*/ FinanceCompany[] getFinanceCompanyValues() {
        /*
        SelectItem[] items = new SelectItem[FinanceCompany.values().length];
        int i = 0;
        for (FinanceCompany f : FinanceCompany.values()) {
            items[i++] = new SelectItem(f, f.name());
        }
        return items;
        */
        return FinanceCompany.values();
    }

    public void creditcardFinanceCompanySelected(AjaxBehaviorEvent ajaxBehaviorEvent) {
    }

    //</editor-fold>

    //<editor-fold desc="Return Label">

    /**
     * Was a return label generated already?
     * @return boolean
     */
    public boolean isLabelForInvoiceGenerated() {
        if (null != customer) {
            Invoice invoice = customer.getInvoice(invoiceIdent);
            return null != invoice.getReturnLabel();
        } else {
            return false;
        }
    }

    /**
     * Request return label by executing asynchronous EJB method.
     */
    private void makeReturnLabelForInvoice() throws RetoureException {
        logger.info(String.format("makeReturnLabelForInvoice: Requesting return label for invoice %s", invoiceIdent));
        // Generate return label
        returnLabelFuture = retoureFacade.placeReturn(customer, invoiceIdent);
    }

    private void waitForReturnLabel() throws RetoureException {
        try {
            logger.info(String.format("waitForReturnLabel: waiting for label for invoice %s", invoiceIdent));
            /*Customer customer =*/
            returnLabelFuture.get();
            /*
            if (null != customer) {
                // Must be same instance customer = this.customer;
                Invoice invoice = this.customer.getInvoice(invoiceIdent);
                // Save file
                ReturnLabel returnLabel = invoice.getReturnLabel();
                String client = retourenWebHelper.getStoreIdent().getName();
                String ident = String.format("%s_%s.pdf", this.customer.getCustomerIdent(), invoice.getInvoiceIdent());
                try {
                    returnLabel.saveBinary(Paths.get("retoure", client, "returnlabel", ident));
                    logger.info(String.format("Saved return label for invoice %s", invoiceIdent));
                } catch (IOException e) {
                    throw new RetoureException(e);
                }
                logger.info(String.format("waitForReturnLabel: invoice %s ... done", invoiceIdent));
            } else {
                logger.severe("Got no customer object from RetoureFacade#placeReturn");
            }
            */
        } catch (InterruptedException | ExecutionException e) {
            throw new RetoureException(e);
        }
    }

    public String downloadReturnLabelForInvoice() throws RetoureException {
        FacesHelper facesHelper = FacesHelper.getInstance(FacesContext.getCurrentInstance());
        // Get return label for invoice
        waitForReturnLabel();
        Invoice invoice = customer.getInvoice(invoiceIdent);
        ReturnLabel returnLabel = invoice.getReturnLabel();
        if (null != returnLabel) {
            Path path = returnLabel.getPath();
            String client = retourenWebHelper.getStoreIdent().getName();
            /*
            // URL for external download site
            String url = String.format("http://files.retourenportal.com/store/%s/returnlabel/%s", store, returnLabel.getPath().toString());
            facesHelper.jsfRedirect(url);
            */
            try {
                // Stream return label
                String filename = String.format("%s_Retoure_%s.pdf", client, invoiceIdent);
                facesHelper.download("application/pdf", filename, Files.readAllBytes(path));
            } catch (IOException e) {
                logger.log(Level.SEVERE, String.format("Cannot start download for %s", path.toString()), e);
            }
            return path.toString();
        }
        return null;
    }

    public String mailReturnLabelForInvoice() throws RetoureException {
        String outcome = "error";
        // Get return label for invoice
        waitForReturnLabel();
        Invoice invoice = customer.getInvoice(invoiceIdent);
        try {
            retoureFacade.sendReturnLabelForInvoiceByMail(customer, invoice);
            returnLabelMailed = true;
            outcome = null; // Stay on actual page
        } catch (RetoureException e) {
            logger.log(Level.SEVERE, "", e);
            returnLabelMailed = false;
        }
        return outcome;
    }

    //</editor-fold>

}
