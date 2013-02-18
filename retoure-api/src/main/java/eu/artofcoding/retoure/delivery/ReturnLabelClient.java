/*
 * retoure-server
 * retoure-api
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 16.02.13 14:23
 */

package eu.artofcoding.retoure.delivery;

import eu.artofcoding.beetlejuice.cdm.store.ReturnLabel;
import eu.artofcoding.beetlejuice.cdm.store.StoreCustomer;

public interface ReturnLabelClient {

    ReturnLabel makeLabel(StoreCustomer customer);

}
