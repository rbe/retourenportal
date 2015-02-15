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

package eu.artofcoding.retoure.store.aktivshop;

import eu.artofcoding.retoure.api.RetoureException;
import eu.artofcoding.retoure.store.aktivshop.wsclient.irt01t.IRT01Result;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@Ignore
public class IRT01ClientTest {

    /**
     * Must fail, invalid data
     * @throws RetoureException
     */
    @Test
    public void testMustFailWithRetoureException() throws RetoureException {
        IRT01Result irt01Result = IRT01Client.call("K0246", "AGT345", "12345");
        assertEquals(irt01Result.getERG().getValue(), BigDecimal.valueOf(2));
    }

    /**
     * 1. Kundennummer: 15557568 Rechnungsnummer: 514616
     * @throws RetoureException
     */
    @Test
    public void testCustomer1() throws RetoureException {
        IRT01Result irt01Result = IRT01Client.call("15557568", null, "514616");
        assertEquals(irt01Result.getSBNR().getValue(), BigDecimal.ZERO);
    }

    /**
     * 1. Kundennummer: 15557568 Rechnungsnummer: 514616
     * @throws RetoureException
     */
    @Test
    public void testCustomer1WithAgent() throws RetoureException {
        IRT01Result irt01Result = IRT01Client.call("15557568", "FIEGE", "514616");
        AktivshopWebserviceHelper.dumpPayload(irt01Result);
        assertEquals(irt01Result.getSBNR().getValue(), BigDecimal.TEN);
    }

    /**
     * 2. Kundennummer: 15455001 Rechnungsnummer: 518538
     * @throws RetoureException
     */
    @Test
    public void testCustomer2() throws RetoureException {
        IRT01Result irt01Result = IRT01Client.call("15455001", null, "518538");
        assertEquals(irt01Result.getSBNR().getValue(), BigDecimal.ZERO);
    }

    /**
     * 2. Kundennummer: 15455001 Rechnungsnummer: 518538
     * @throws RetoureException
     */
    @Test
    public void testCustomer2WithAgent() throws RetoureException {
        IRT01Result irt01Result = IRT01Client.call("15455001", "FIEGE", "518538");
        assertEquals(irt01Result.getSBNR().getValue(), BigDecimal.TEN);
    }

}
