/*
 * retoure-server
 * retoure-store-aktivshop
 * Copyright (C) 2012-2012 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 24.11.12 18:19
 */

package eu.artofcoding.retoure.store.aktivshop;

import eu.artofcoding.retoure.store.aktivshop.testws.TESTWSStub;

import java.math.BigDecimal;
import java.rmi.RemoteException;

public class TestWs {

    public static void main(String[] args) throws RemoteException {
        TESTWSStub stub = new TESTWSStub("http://62.214.69.250:10010/web/services/TESTWS.TESTWSHttpSoap11Endpoint/");
        TESTWSStub.Testws testws = new TESTWSStub.Testws();
        final TESTWSStub.TESTWSInput testwsInput = new TESTWSStub.TESTWSInput();
        testws.setArgs0(testwsInput);
        testwsInput.set_VNAME("");
        testwsInput.set_NNAME("");
        testwsInput.set_FHLN(/*new BigDecimal[]{}*/null);
        TESTWSStub.TestwsResponse testwsResponse = stub.testws(testws);
        System.out.printf("Hallo, %s %s%n", testwsResponse.get_return().get_VNAME(), testwsResponse.get_return().get_NNAME());
        for (BigDecimal b : testwsResponse.get_return().get_FHLN()) {
            System.out.println(b);
        }
    }

}
