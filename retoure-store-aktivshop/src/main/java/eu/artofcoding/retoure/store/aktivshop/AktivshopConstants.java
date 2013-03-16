/*
 * retoure-server
 * retoure-store-aktivshop
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 11.02.13 09:16
 */

package eu.artofcoding.retoure.store.aktivshop;

import java.math.BigDecimal;

public class AktivshopConstants {

    public static final String EMPTY_STRING = "".intern();
    public static final String STORE = "aktivshop".intern();

    public static BigDecimal ZERO_SCALE_3 = BigDecimal.ZERO.setScale(3);

    public static final int LENGTH_RGNR = 9;
    public static final int LENGTH_KDNR = 8;
    public static final int LENGTH_SBNR = 10;

}
