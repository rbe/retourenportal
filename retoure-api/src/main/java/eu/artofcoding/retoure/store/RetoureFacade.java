/*
 * retoure-server
 * retoure-api
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 11.02.13 09:16
 */

package eu.artofcoding.retoure.store;

import eu.artofcoding.beetlejuice.cdm.accounting.Invoice;
import eu.artofcoding.beetlejuice.cdm.store.Article;
import eu.artofcoding.beetlejuice.cdm.store.ReturnReason;
import eu.artofcoding.beetlejuice.cdm.store.StoreCustomer;
import eu.artofcoding.retoure.api.RetoureException;

import javax.ejb.Local;
import java.io.Serializable;
import java.util.concurrent.Future;

@Local
public interface RetoureFacade extends Serializable {

    /**
     * Ask for valid login and get data for invoice.
     * @param customer {@link eu.artofcoding.beetlejuice.cdm.store.StoreCustomer} w/ customer ident and invoice ident.
     * @param ident    Identification for customer, e.g. a password.
     * @return StoreCustomer
     * @throws RetoureException On exception when loggin in, e.g. asking an external service.
     */
    StoreCustomer login(StoreCustomer customer, String ident) throws RetoureException;

    /**
     * Fetch data about customer.
     * @param customer {@link eu.artofcoding.beetlejuice.cdm.store.StoreCustomer} w/ customer ident and invoice ident.
     * @return StoreCustomer with data like name, address and so on.
     * @throws RetoureException
     */
    StoreCustomer fetchCustomer(StoreCustomer customer) throws RetoureException;

    /**
     * Ask for articles of a certain invoice.
     * @param customer     {@link StoreCustomer}, previously logged in.
     * @param invoiceIdent Identification of invoice to fetch data about.
     * @return {@link StoreCustomer} w/ invoice data, especially articles.
     */
    StoreCustomer fetchInvoice(StoreCustomer customer, String invoiceIdent) throws RetoureException;

    /**
     * Fetch return reasons for an article.
     * @param article {@link Article} to fetch reasons for.
     * @return Array of {@link ReturnReason}s for this article.
     */
    ReturnReason[] fetchReasons(Article article);

    /**
     * Place a return.
     * @param customer {@link StoreCustomer}.
     * @return {@link StoreCustomer}.
     * @throws RetoureException
     */
    Future<StoreCustomer> placeReturn(StoreCustomer customer, String invoiceIdent) throws RetoureException;

    /**
     * @param customer {@link StoreCustomer}.
     * @param invoice  {@link Invoice}.
     * @throws RetoureException
     */
    void sendReturnLabelForInvoiceByMail(StoreCustomer customer, Invoice invoice) throws RetoureException;

}
