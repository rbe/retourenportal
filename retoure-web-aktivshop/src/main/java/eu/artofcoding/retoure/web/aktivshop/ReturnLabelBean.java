/*
 * retoure-server
 * retoure-web-aktivshop
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 15.02.13 08:05
 */

package eu.artofcoding.retoure.web.aktivshop;

import eu.artofcoding.beetlejuice.cdm.accounting.Invoice;
import eu.artofcoding.beetlejuice.cdm.store.ReturnLabel;
import eu.artofcoding.beetlejuice.cdm.store.StoreCustomer;
import eu.artofcoding.beetlejuice.web.jsf.FacesHelper;
import eu.artofcoding.retoure.api.RetoureConstants;
import eu.artofcoding.retoure.api.RetoureException;
import eu.artofcoding.retoure.store.RetoureFacade;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("label")
@RequestScoped
public class ReturnLabelBean {

    private Logger logger = Logger.getLogger(ReturnLabelBean.class.getName());

    @EJB
    private RetoureFacade retoureFacade;

    private boolean returnLabelMailed;

    private HttpSession getHttpSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        return (HttpSession) externalContext.getSession(true);
    }

    private StoreCustomer getCustomerFromSession() {
        return (StoreCustomer) getHttpSession().getAttribute(RetoureConstants.CUSTOMER_IDENT);
    }

    private String getInvoiceIdentFromSession() {
        return (String) getHttpSession().getAttribute(RetoureConstants.INVOICE_IDENT);
    }

    /**
     * Request return label by executing asynchronous EJB method.
     * @return Navigation rule.
     * @throws RetoureException
     */
    public String makeReturnLabelForInvoice() throws RetoureException {
        StoreCustomer customer = getCustomerFromSession();
        String invoiceIdent = getInvoiceIdentFromSession();
        logger.info(String.format("makeReturnLabelForInvoice: Requesting return label for invoice %s", invoiceIdent));
        // Generate return label
        Future<StoreCustomer> returnLabelFuture = retoureFacade.placeReturn(customer, invoiceIdent);
        try {
            returnLabelFuture.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            logger.log(Level.SEVERE, String.format("Did not get return label for invoice %s", invoiceIdent), e);
            // TODO Case 1: We've got the email address, postpone processing of return label and mail it to customer later
            // TODO Case 2: We've NOT got the email address, show I-am-sorry-message
            throw new RetoureException(e);
        }
        return "show-returnlabel";
    }

    /**
     * Stream return label.
     * @return Navigation rule.
     * @throws RetoureException
     */
    public String downloadReturnLabelForInvoice() throws RetoureException {
        StoreCustomer customer = getCustomerFromSession();
        String invoiceIdent = getInvoiceIdentFromSession();
        Invoice invoice = customer.getInvoice(invoiceIdent);
        ReturnLabel returnLabel = invoice.getReturnLabel();
        if (null != returnLabel) {
            Path path = returnLabel.getPath();
            if (null != path) {
                /*
                // URL for external download site
                String url = String.format("http://files.retourenportal.com/store/%s/returnlabel/%s", store, returnLabel.getPath().toString());
                facesHelper.jsfRedirect(url);
                */
                try {
                    // Stream return label
                    FacesHelper.download("application/pdf", invoice.getReturnLabel().getFilename(), Files.readAllBytes(path));
                } catch (IOException e) {
                    logger.log(Level.SEVERE, String.format("Cannot start download for %s", path.toString()), e);
                }
                return null; //path.toString();
            } else {
                return "cannot-find-returnlabel";
            }
        }
        return null;
    }

    /**
     * Send email.
     * @return Navigation rule.
     * @throws RetoureException
     */
    public String mailReturnLabelForInvoice() throws RetoureException {
        String outcome = "cannot-send-email";
        StoreCustomer customer = getCustomerFromSession();
        String invoiceIdent = getInvoiceIdentFromSession();
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

    /**
     * Was a return label generated already?
     * @return boolean
     */
    public boolean isLabelForInvoiceGenerated() {
        StoreCustomer customer = getCustomerFromSession();
        if (null != customer) {
            String invoiceIdent = getInvoiceIdentFromSession();
            Invoice invoice = customer.getInvoice(invoiceIdent);
            return null != invoice.getReturnLabel();
        } else {
            return false;
        }
    }

    /**
     * Was return label mailed succesfully to customer?
     * @return boolean
     */
    public boolean isReturnLabelMailed() {
        return returnLabelMailed;
    }

}
