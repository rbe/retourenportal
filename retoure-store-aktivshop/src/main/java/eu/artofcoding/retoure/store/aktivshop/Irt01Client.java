/*
 * retoure-server
 * retoure-store-aktivshop
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 13.02.13 16:16
 */

package eu.artofcoding.retoure.store.aktivshop;

import com.google.common.base.Strings;
import eu.artofcoding.retoure.api.RetoureException;
import eu.artofcoding.retoure.store.aktivshop.irt01.IRT01Stub;

import java.math.BigDecimal;
import java.rmi.RemoteException;

import static eu.artofcoding.retoure.api.RetoureConstants.EMPTY_STRING;

public class Irt01Client {

    public static void dumpPayload(IRT01Stub.IRT01Input irt01Input) {
        System.out.println(irt01Input.toString());
        System.out.printf("INPUT:  Firmennummer:         len=%02d, FNR= '%s'%n", irt01Input.get_FNR().length(), irt01Input.get_FNR());
        System.out.printf("INPUT:  Kudennummer:          len=%02d, ID=  '%s'%n", irt01Input.get_ID().length(), irt01Input.get_ID());
        System.out.printf("INPUT:  Rechnungsnummer:      len=%02d, RGNR='%s'%n", irt01Input.get_RGNR().toPlainString().length(), irt01Input.get_RGNR());
        System.out.printf("INPUT:  Sachbearbeiternummer: len=%02d, SBNR='%s'%n", irt01Input.get_SBNR().toPlainString().length(), irt01Input.get_SBNR());
        System.out.printf("INPUT:  ERG=%d, ERGT=%s%n", irt01Input.get_ERG().toBigInteger(), irt01Input.get_ERGT());
    }

    public static void dumpPayload(IRT01Stub.IRT01Result irt01Result) {
        System.out.println(irt01Result.toString());
        System.out.printf("RESULT: Firmennummer:         len=%02d, FNR= '%s'%n", irt01Result.get_FNR().length(), irt01Result.get_FNR());
        System.out.printf("RESULT: Kudennummer:          len=%02d, ID=  '%s'%n", irt01Result.get_ID().length(), irt01Result.get_ID());
        System.out.printf("RESULT: Rechnungsnummer:      len=%02d, RGNR='%s'%n", irt01Result.get_RGNR().toPlainString().length(), irt01Result.get_RGNR());
        System.out.printf("RESULT: Sachbearbeiternummer: len=%02d, SBNR='%s'%n", irt01Result.get_SBNR().toPlainString().length(), irt01Result.get_SBNR());
        System.out.printf("RESULT: ERG=%d, ERGT=%s%n", irt01Result.get_ERG().toBigInteger(), irt01Result.get_ERGT());
    }

    public static String makeKundennummer(String kdnr) throws RetoureException {
        if (null == kdnr) {
            throw new RetoureException("No customer number given");
        }
        kdnr = Strings.padStart(kdnr, 8, ' ');
        return kdnr;
    }

    public static String makeSachbearbeiternummer(String sbnr) throws RetoureException {
        if (null == sbnr) {
            throw new RetoureException("No agent given");
        }
        sbnr = Strings.padEnd(sbnr.toUpperCase(), 10, ' ');
        return sbnr;
    }

    private static BigDecimal makeRechnungsnummer(String renr) throws RetoureException {
        try {
            return BigDecimal.valueOf(Long.valueOf(renr));
        } catch (NumberFormatException e) {
            throw new RetoureException(e);
        }
    }

    public static IRT01Stub.IRT01Result call(String kdnr, String agt, String renr) throws RetoureException {
        // Prepare web service input
        final IRT01Stub.IRT01Input irt01Input = new IRT01Stub.IRT01Input();
        // Firmennummer
        irt01Input.set_FNR(String.format("%02d", 1));
        // Kundennummer und Sachbearbeiternummer
        if (null == agt || agt.length() == 0) {
            irt01Input.set_ID(Strings.padEnd(makeKundennummer(kdnr), 30, ' '));
        } else {
            irt01Input.set_ID(Strings.padEnd(String.format("%s-%s", makeKundennummer(kdnr), makeSachbearbeiternummer(agt)), 30, ' '));
        }
        // Rechnungsnummer, max. 9 Stellen
        irt01Input.set_RGNR(makeRechnungsnummer(renr));
        // Sachbearbeiternummer, max. 6 Stellen
        // Wenn Login mit Kundennummer-Sachbearbeiternummer erfolgreich war
        irt01Input.set_SBNR(BigDecimal.ZERO);
        // Ergebnis
        // ERG, max 1 Stelle, Fehler liegt vor wenn ERG == 2, dann ist auch ERGT gesetzt
        // ERGT, max 30 Stellen
        irt01Input.set_ERG(BigDecimal.ZERO);
        irt01Input.set_ERGT(Strings.padEnd(EMPTY_STRING, 30, ' '));
        // Dump input
        dumpPayload(irt01Input);
        // Call web service
        try {
            final IRT01Stub stub = new IRT01Stub("http://62.214.69.250:10010/web/services/IRT01");
            final IRT01Stub.Irt01 irt01 = new IRT01Stub.Irt01();
            irt01.setArgs0(irt01Input);
            IRT01Stub.Irt01Response irt01Response = stub.irt01(irt01);
            if (null != irt01Response) {
                IRT01Stub.IRT01Result irt01Result = irt01Response.get_return();
                return irt01Result;
            } else {
                throw new RetoureException(String.format("Could not login, got no response"));
            }
        } catch (RemoteException e) {
            throw new RetoureException(e);
        }
    }

}
