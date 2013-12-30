/*
 * retoure-server
 * retoure-store-aktivshop
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 24.02.13 16:41
 */

package eu.artofcoding.retoure.store.aktivshop;

import eu.artofcoding.retoure.api.RetoureException;
import eu.artofcoding.retoure.store.aktivshop.wsclient.irt02t.IRT02Result;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 1. Kunde: 15890812 / Rechnung: 7292788
 * 2. Kunde: 15945609 / Rechnung: 7291422
 * KZRTER: Kennzeichen Retoure erlaubt? 9=Fehler beim Ermittlung, 2=Retoure zu spät
 * KZOP:   Kennzeichnung Rechnung offen? 0=OP nicht ermittelt, 1=OP noch offen, 2=OP bezahlt
 */
public class IRT02ClientTest {

    public boolean assertOneOf(int expected, int... list) {
        boolean b = false;
        for (int i : list) {
            if (i == expected) {
                b = true;
                break;
            }
        }
        assert b : "Expected value [" + expected + "] not in list";
        return b;
    }

    public boolean assertNotOneOf(int expected, int... list) {
        boolean b = true;
        for (int i : list) {
            if (i == expected) {
                b = false;
                break;
            }
        }
        assert b : "Expected value [" + expected + "] in list";
        return b;
    }

    @Test
    public void testInvoice() throws RetoureException {
        IRT02Result irt02Result = IRT02Client.call(BigDecimal.ZERO, "7292788");
        // Dump result
        AktivshopWebserviceHelper.dumpPayload(irt02Result);
        // KZRTER
        BigDecimal kzrter = irt02Result.getKZRTER().getValue();
        assertNotNull(kzrter);
        int kzrteri = kzrter.toBigInteger().intValue();
        assertNotOneOf(kzrteri, new int[]{2, 9});
        // KZOP
        BigDecimal kzop = irt02Result.getKZOP().getValue();
        assertNotNull(kzop);
        int kzopi = kzop.toBigInteger().intValue();
        assertOneOf(kzopi, new int[]{0, 1, 2});
    }

    @Test
    public void testInvoiceWithAgent() throws RetoureException {
        IRT02Result irt02Result = IRT02Client.call(BigDecimal.TEN, "7292788");
        // Dump result
        AktivshopWebserviceHelper.dumpPayload(irt02Result);
        // SBNR
        assertEquals(BigDecimal.TEN, irt02Result.getSBNR().getValue());
        // KZRTER
        BigDecimal kzrter = irt02Result.getKZRTER().getValue();
        assertNotNull(kzrter);
        int kzrteri = kzrter.toBigInteger().intValue();
        assertNotOneOf(kzrteri, new int[]{2, 9});
        // KZOP
        BigDecimal kzop = irt02Result.getKZOP().getValue();
        assertNotNull(kzop);
        int kzopi = kzop.toBigInteger().intValue();
        assertOneOf(kzopi, new int[]{0, 1, 2});
    }

    @Test
    public void testInvoiceWithFMNR() throws RetoureException {
        IRT02Result irt02Result = IRT02Client.call(BigDecimal.ZERO, "7292593");
        // Dump result
        AktivshopWebserviceHelper.dumpPayload(irt02Result);
        // SBNR
        assertEquals(BigDecimal.ZERO, irt02Result.getSBNR().getValue());
        // KZRTER
        BigDecimal kzrter = irt02Result.getKZRTER().getValue();
        assertNotNull(kzrter);
        int kzrteri = kzrter.toBigInteger().intValue();
        assertNotOneOf(kzrteri, new int[]{2, 9});
        // KZOP
        BigDecimal kzop = irt02Result.getKZOP().getValue();
        assertNotNull(kzop);
        int kzopi = kzop.toBigInteger().intValue();
        assertOneOf(kzopi, new int[]{0, 1, 2});
    }

}
