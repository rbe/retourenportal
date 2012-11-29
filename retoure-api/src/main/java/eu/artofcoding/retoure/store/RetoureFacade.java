/*
 * retoure-server
 * retoure-api
 * Copyright (C) 2012-2012 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 27.11.12 15:44
 */

package eu.artofcoding.retoure.store;

import eu.artofcoding.beetlejuice.cdm.Customer;
import eu.artofcoding.beetlejuice.cdm.accounting.Invoice;
import eu.artofcoding.beetlejuice.cdm.store.Article;
import eu.artofcoding.beetlejuice.cdm.store.ReturnReason;
import eu.artofcoding.retoure.api.RetoureException;

import javax.ejb.Local;
import java.io.Serializable;
import java.util.concurrent.Future;

@Local
public interface RetoureFacade extends Serializable {

    /**
     * Ask for valid login and get data for invoice.
     * @param customer {@link Customer} w/ customer ident and invoice ident.
     * @return {@link Customer}.
     */
    Customer login(Customer customer);

    /**
     * Ask for articles of a certain invoice.
     * @param customer     {@link Customer}, previously logged in.
     * @param invoiceIdent Identification of invoice to fetch data about.
     * @return {@link Customer} w/ invoice data, especially articles.
     */
    Customer fetchInvoice(Customer customer, String invoiceIdent);

    /**
     * Fetch return reasons for an article.
     * @param article {@link Article} to fetch reasons for.
     * @return Array of {@link ReturnReason}s for this article.
     */
    ReturnReason[] fetchReasons(Article article);

    /**
     * Place a return.
     * @param customer {@link Customer}.
     * @return {@link Customer}.
     * @throws RetoureException
     */
    Future<Customer> placeReturn(Customer customer, String invoiceIdent) throws RetoureException;

    /**
     * @param customer {@link Customer}.
     * @param invoice  {@link Invoice}.
     * @throws RetoureException
     */
    void sendReturnLabelForInvoiceByMail(Customer customer, Invoice invoice) throws RetoureException;

    /**
     * Add an article to an invoice.
     * @param customer {@link Customer}
     * @param invoice  {@link Invoice}
     * @param article  {@link Article}
     */
    void addArticleToInvoice(Customer customer, Invoice invoice, Article article);

}
