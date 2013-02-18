/*
 * retoure-server
 * retoure-store-aktivshop
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 13.02.13 19:53
 */

package eu.artofcoding.retoure.store.aktivshop.irt01;

import eu.artofcoding.retoure.api.RetoureException;
import eu.artofcoding.retoure.store.aktivshop.Irt01Client;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

public class IRT01Test {

    @Test//(expectedExceptions = {RetoureException.class})
    public void testMustFailWithRetoureException() throws RetoureException {
        // Must fail, invalid data
        IRT01Stub.IRT01Result irt01Result = Irt01Client.call("K0246", "AGT345", "12345");
        assertEquals(irt01Result.get_ERG(), BigDecimal.valueOf(2));
    }

    @Test
    public void testCustomer1() throws RetoureException {
        // 1. Kundennummer: 15557568 Rechnungsnummer: 514616
        IRT01Stub.IRT01Result irt01Result = Irt01Client.call("15557568", null, "514616");
        assertEquals(irt01Result.get_SBNR(), BigDecimal.ZERO);
    }

    @Test
    public void testCustomer1WithAgent() throws RetoureException {
        // 1. Kundennummer: 15557568 Rechnungsnummer: 514616
        IRT01Stub.IRT01Result irt01Result = Irt01Client.call("15557568", "FIEGE", "514616");
        assertEquals(irt01Result.get_SBNR(), BigDecimal.TEN);
    }

    @Test
    public void testCustomer2() throws RetoureException {
        // 2. Kundennummer: 15455001 Rechnungsnummer: 518538
        IRT01Stub.IRT01Result irt01Result = Irt01Client.call("15455001", null, "518538");
        assertEquals(irt01Result.get_SBNR(), BigDecimal.ZERO);
    }

    @Test
    public void testCustomer2WithAgent() throws RetoureException {
        // 2. Kundennummer: 15455001 Rechnungsnummer: 518538
        IRT01Stub.IRT01Result irt01Result = Irt01Client.call("15455001", "FIEGE", "518538");
        assertEquals(irt01Result.get_SBNR(), BigDecimal.TEN);
    }

}
