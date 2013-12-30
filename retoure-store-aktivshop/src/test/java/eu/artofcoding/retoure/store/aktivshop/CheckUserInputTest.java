/*
 * retoure-server
 * retoure-store-aktivshop
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 14.02.13 15:11
 */

package eu.artofcoding.retoure.store.aktivshop;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheckUserInputTest {

    @Test
    public void testCheckCustomerAndAgentIdent() throws Exception {
        String customerIdentEntered = " 123-AGENT   ";
        String customerIdentOK = "123-AGENT";
        assertEquals(customerIdentOK, CheckUserInput.checkCustomerIdent(customerIdentEntered));
    }

    @Test
    public void testCheckCustomerIdent() throws Exception {
        String customerIdentEntered = "123   ";
        String customerIdentOK = "123";
        assertEquals(customerIdentOK, CheckUserInput.checkCustomerIdent(customerIdentEntered));
    }

    @Test
    public void testCheckAgentIdent() throws Exception {
    }

    @Test
    public void testCheckInvoiceIdent() throws Exception {
    }

}
