/*
 * retoure-server
 * retoure-web-demo
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 17.02.13 15:21
 */

package eu.artofcoding.retoure.web.demo;

import eu.artofcoding.beetlejuice.cdm.accounting.Invoice;
import eu.artofcoding.beetlejuice.cdm.store.StoreCustomer;
import eu.artofcoding.beetlejuice.cdm.store.StoreIdent;
import eu.artofcoding.retoure.api.RetoureConstants;
import eu.artofcoding.retoure.api.RetoureException;
import eu.artofcoding.retoure.store.RetoureFacade;
import eu.artofcoding.retoure.store.demo.CheckUserInput;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@Named("login")
@SessionScoped
public class LoginBean implements Serializable {

    @EJB
    private RetoureFacade retoureFacade;

    private String customerIdent = RetoureConstants.CUSTOMER_ALFONS;

    private String invoiceIdent = RetoureConstants.INVOICE_DHL4;

    private String getStoreIdent() {
        // StoreIdent
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        return externalContext.getInitParameter(RetoureConstants.STORE_IDENT);
    }

    private HttpSession getHttpSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        return (HttpSession) externalContext.getSession(true);
    }

    private void setCustomerInSession(StoreCustomer storeCustomer) {
        HttpSession httpSession = getHttpSession();
        httpSession.setAttribute(RetoureConstants.CUSTOMER_IDENT, storeCustomer);
        httpSession.setAttribute(RetoureConstants.INVOICE_IDENT, invoiceIdent);
    }

    public String getCustomerIdent() {
        return customerIdent;
    }

    public StoreCustomer getCustomer() {
        return (StoreCustomer) getHttpSession().getAttribute(RetoureConstants.CUSTOMER_IDENT);
    }

    public void setCustomerIdent(String customerIdent) {
        // KDNR-SBNR
        this.customerIdent = CheckUserInput.checkInvoiceIdent(customerIdent);
    }

    public String getInvoiceIdent() {
        return invoiceIdent;
    }

    public void setInvoiceIdent(String invoiceIdent) {
        this.invoiceIdent = CheckUserInput.checkInvoiceIdent(invoiceIdent);
    }

    public String performLogin() throws RetoureException {
        String storeIdent = getStoreIdent();
        // Navigation case
        String navigationCase;
        // Load data for customer and invoice
        StoreCustomer customer = new StoreCustomer();
        customer.setStoreIdent(new StoreIdent(storeIdent, null));
        customer.setCustomerIdent(customerIdent);
        // Save customer in HTTP session
        setCustomerInSession(customer);
        // Perform login
        retoureFacade.login(customer, invoiceIdent);
        // Return navigation case
        if (customer.isLoginOk()) {
            retoureFacade.fetchInvoice(customer, invoiceIdent);
            Invoice invoice = customer.getInvoices().get(invoiceIdent);
            if (null != invoice) {
                navigationCase = "show-invoice";
            } else {
                invoiceIdent = null;
                navigationCase = "invoice-not-found";
            }
        } else {
            invoiceIdent = null;
            navigationCase = "login-failed";
        }
        return navigationCase;
    }

}