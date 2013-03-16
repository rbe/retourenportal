/*
 * retoure-server
 * retoure-dhl-client
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 11.03.13 10:40
 */

package eu.artofcoding.retoure.delivery.dhl;

import eu.artofcoding.retoure.delivery.ReturnLabelClient;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;

public class AmselClientFactory {

    @Produces
    @AmselClient
    public ReturnLabelClient createAmselClient(InjectionPoint injectionPoint) {
        Annotated annotated = injectionPoint.getAnnotated();
        AmselClient qAmsel = annotated.getAnnotation(AmselClient.class);
        ReturnLabelClient amselClient = new AmselClientImpl(qAmsel.portalId(), qAmsel.deliveryName());
        return amselClient;
    }

}
