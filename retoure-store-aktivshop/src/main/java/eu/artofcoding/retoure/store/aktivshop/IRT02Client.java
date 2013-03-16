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
import eu.artofcoding.retoure.store.aktivshop.wsclient.irt02t.IRT02Input;
import eu.artofcoding.retoure.store.aktivshop.wsclient.irt02t.IRT02Result;
import eu.artofcoding.retoure.store.aktivshop.wsclient.irt02t.IRT02T;
import eu.artofcoding.retoure.store.aktivshop.wsclient.irt02t.ObjectFactory;

import java.math.BigDecimal;

import static eu.artofcoding.retoure.store.aktivshop.AktivshopWebserviceHelper.*;

public class IRT02Client {

    public static IRT02Result call(BigDecimal sbnr, String rgnr) throws RetoureException {
        // Prepare web service input
        IRT02Input irt02Input = new IRT02Input();
        ObjectFactory objectFactory = new ObjectFactory();
        // Fill data
        // Firmennummer
        irt02Input.setFNR(objectFactory.createIRT02InputFNR(String.format("%02d", 1)));
        // Rechnungsnummer
        irt02Input.setRGNR(objectFactory.createIRT02InputRGNR(makeRechnungsnummer(rgnr)));
        // Sachbearbeiter
        irt02Input.setSBNR(objectFactory.createIRT02InputSBNR(sbnr));
        // Output parameters
        irt02Input.setKZRTER(objectFactory.createIRT02InputKZRTER(BigDecimal.ZERO));
        irt02Input.setKZOP(objectFactory.createIRT02InputKZOP(BigDecimal.ZERO));
        // Call web service
        IRT02T irt02 = new IRT02T();
        IRT02Result irt02Result = irt02.getIRT02THttpSoap11Endpoint().irt02(irt02Input);
        return irt02Result;
    }

}
