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

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Retention(RUNTIME)
@Target({FIELD, METHOD, PARAMETER, TYPE})
public @interface AmselClient {

    @Nonbinding String portalId() default "OnlineRetoure";

    @Nonbinding String deliveryName() default "Deutschland_Var3";

}
