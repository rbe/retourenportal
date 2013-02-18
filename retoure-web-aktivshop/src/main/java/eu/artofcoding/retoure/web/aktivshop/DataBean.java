/*
 * retoure-server
 * retoure-web-aktivshop
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 11.02.13 13:15
 */

package eu.artofcoding.retoure.web.aktivshop;

import eu.artofcoding.beetlejuice.cdm.Salutation;
import eu.artofcoding.beetlejuice.cdm.accounting.BankAccountType;
import eu.artofcoding.beetlejuice.cdm.accounting.FinanceCompany;
import eu.artofcoding.beetlejuice.cdm.store.StoreCustomer;
import eu.artofcoding.retoure.api.RetoureConstants;
import eu.artofcoding.retoure.store.RetoureFacade;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named("data")
@RequestScoped
public class DataBean {

    @EJB
    private RetoureFacade retoureFacade;

    private HttpSession getHttpSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        return (HttpSession) externalContext.getSession(true);
    }

    private StoreCustomer getCustomerFromSession() {
        return (StoreCustomer) getHttpSession().getAttribute(RetoureConstants.CUSTOMER_IDENT);
    }

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
        StoreCustomer customer = getCustomerFromSession();
        BankAccountType accountType = customer.getBankAccount().getAccountType();
        return null != accountType && accountType.equals(BankAccountType.CREDITNOTE);
    }

    public boolean accountTypeIsBankAccount() {
        StoreCustomer customer = getCustomerFromSession();
        BankAccountType accountType = customer.getBankAccount().getAccountType();
        return null != accountType && accountType.equals(BankAccountType.BANKACCOUNT);
    }

    public boolean accountTypeIsPayPal() {
        StoreCustomer customer = getCustomerFromSession();
        BankAccountType accountType = customer.getBankAccount().getAccountType();
        return null != accountType && accountType.equals(BankAccountType.PAYPAL);
    }

    public boolean accountTypeIsCreditcard() {
        StoreCustomer customer = getCustomerFromSession();
        BankAccountType accountType = customer.getBankAccount().getAccountType();
        return null != accountType && accountType.equals(BankAccountType.CREDITCARD);
    }

    public FinanceCompany[] getFinanceCompanyValues() {
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

}
