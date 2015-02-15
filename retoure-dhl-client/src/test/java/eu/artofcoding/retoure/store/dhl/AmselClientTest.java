/*
 * retoure-server
 * retoure-dhl-client
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 11.02.13 13:38
 */

package eu.artofcoding.retoure.store.dhl;

import eu.artofcoding.beetlejuice.cdm.store.ReturnLabel;
import eu.artofcoding.beetlejuice.cdm.store.StoreCustomer;
import eu.artofcoding.retoure.delivery.ReturnLabelClient;
import eu.artofcoding.retoure.delivery.dhl.AmselClientImpl;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AmselClientTest {

    private StoreCustomer customer;

    @Before
    public void setUp() throws Exception {
        // Create customer
        customer = new StoreCustomer();
        customer.setFirstname("Ralf");
        customer.setLastname("Bensmann");
        customer.setShippingAddress1("Grüner Weg");
        customer.setShippingAddress1StreetNumber("23a");
        customer.setZipcode("48366");
        customer.setCity("Laer");
        customer.setPhone("02554 912969-0");
    }

    @Test
    public void testMakeLabel() throws Exception {
        // Create label request
        ReturnLabelClient returnLabelClient = new AmselClientImpl("OnlineRetoure", "Deutschland_Var3");
        ReturnLabel returnLabel = returnLabelClient.makeLabel(customer);
        Path labelPath = Paths.get("src/test/data/label.pdf");
        returnLabel.saveBinary(labelPath);
        System.out.println("Wrote label to " + labelPath.toAbsolutePath().toString());
    }

}
