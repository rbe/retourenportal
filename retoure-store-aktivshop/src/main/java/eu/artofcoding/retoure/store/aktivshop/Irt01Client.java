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
import eu.artofcoding.retoure.store.aktivshop.wsclient.irt01t.IRT01Input;
import eu.artofcoding.retoure.store.aktivshop.wsclient.irt01t.IRT01Result;
import eu.artofcoding.retoure.store.aktivshop.wsclient.irt01t.IRT01T;
import eu.artofcoding.retoure.store.aktivshop.wsclient.irt01t.ObjectFactory;

import java.math.BigDecimal;

import static eu.artofcoding.retoure.api.RetoureConstants.EMPTY_STRING;
import static eu.artofcoding.retoure.store.aktivshop.AktivshopWebserviceHelper.*;

public class IRT01Client {

    public static IRT01Result call(String kdnr, String sbnr, String rgnr) throws RetoureException {
        // Prepare web service input
        IRT01Input irt01Input = new IRT01Input();
        ObjectFactory objectFactory = new ObjectFactory();
        // Firmennummer
        irt01Input.setFNR(objectFactory.createIRT01InputFNR(String.format("%02d", 1)));
        // Kundennummer und Sachbearbeiternummer
        if (null == sbnr || sbnr.length() == 0) {
            irt01Input.setID(objectFactory.createIRT01InputID(Strings.padEnd(makeKundennummer(kdnr), 30, ' ')));
        } else {
            String format = String.format("%s-%s", makeKundennummer(kdnr), makeSachbearbeiternummer(sbnr));
            irt01Input.setID(objectFactory.createIRT01InputID(Strings.padEnd(format, 30, ' ')));
        }
        // Rechnungsnummer, max. 9 Stellen
        irt01Input.setRGNR(objectFactory.createIRT01InputRGNR(makeRechnungsnummer(rgnr)));
        // Sachbearbeiternummer, max. 6 Stellen
        // Wenn Login mit Kundennummer-Sachbearbeiternummer erfolgreich war
        irt01Input.setSBNR(objectFactory.createIRT01InputSBNR(BigDecimal.ZERO));
        // Ergebnis
        // ERG, max 1 Stelle, Fehler liegt vor wenn ERG == 2, dann ist auch ERGT gesetzt
        // ERGT, max 30 Stellen
        irt01Input.setERG(objectFactory.createIRT01InputERG(BigDecimal.ZERO));
        irt01Input.setERGT(objectFactory.createIRT01InputERGT(Strings.padEnd(EMPTY_STRING, 30, ' ')));
        // Call web service
        IRT01T irt01t = new IRT01T();
        IRT01Result irt01Result = irt01t.getIRT01THttpSoap11Endpoint().irt01(irt01Input);
        return irt01Result;
    }

}
