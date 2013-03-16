/*
 * retoure-server
 * retoure-api
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 02.03.13 13:48
 */

package eu.artofcoding.retoure.api;

import eu.artofcoding.beetlejuice.cdm.Group;
import eu.artofcoding.beetlejuice.cdm.Salutation;
import eu.artofcoding.beetlejuice.cdm.accounting.BankAccount;
import eu.artofcoding.beetlejuice.cdm.accounting.BankAccountType;
import eu.artofcoding.beetlejuice.cdm.accounting.FinanceCompany;
import eu.artofcoding.beetlejuice.cdm.accounting.Invoice;
import eu.artofcoding.beetlejuice.cdm.store.Article;
import eu.artofcoding.beetlejuice.cdm.store.StoreCustomer;
import eu.artofcoding.retoure.store.RetoureFacade;

public class TestData {

    /**
     * Alfons Aktivmann
     */
    public static void makeCustomerALFONS01(RetoureFacade retoureFacade, StoreCustomer customer) {
        // Set customer data
        customer.setSalutation(Salutation.HERR);
        customer.setFirstname("Alfons");
        customer.setLastname("Anspruchsvoll");
        customer.setShippingAddress1("Allerseits Grüne Heide");
        customer.setShippingAddress1StreetNumber("32a");
        customer.setZipcode("48366");
        customer.setCity("Laer-Holthausen");
        customer.setPhone("02550 123456-890");
        customer.setEmail("support@art-of-coding.eu");
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
    public static void makeCustomerMANFRED1(RetoureFacade retoureFacade, StoreCustomer customer) {
        // Set customer data
        customer.setSalutation(Salutation.HERR);
        customer.setFirstname("Manfred");
        customer.setLastname("Mangelware");
        customer.setShippingAddress1("Allerseits Grüne Heide");
        customer.setShippingAddress1StreetNumber("32a");
        customer.setZipcode("48366");
        customer.setCity("Laer");
        customer.setPhone("04124 123456-890");
        customer.setEmail("support@art-of-coding.eu");
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
    public static void makeInvoiceDHL004(RetoureFacade retoureFacade, StoreCustomer customer, String invoiceIdent) {
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
            invoice.addArticle(article);
        }
        customer.addInvoice(invoiceIdent, invoice);
    }

    /**
     * 1 Artikel, Spedition
     */
    public static void makeInvoiceSPED01(RetoureFacade retoureFacade, StoreCustomer customer, String invoiceIdent) {
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

}
