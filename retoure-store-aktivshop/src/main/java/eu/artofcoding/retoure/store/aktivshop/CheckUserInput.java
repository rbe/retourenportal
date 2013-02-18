/*
 * retoure-server
 * retoure-web-aktivshop
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 14.02.13 12:35
 */

package eu.artofcoding.retoure.store.aktivshop;

import static eu.artofcoding.retoure.store.aktivshop.AktivshopConstants.LENGTH_KDNR;
import static eu.artofcoding.retoure.store.aktivshop.AktivshopConstants.LENGTH_SBNR;

public class CheckUserInput {

    public static String[] checkCustomerAndAgentIdent(String value) {
        String[] ret = new String[]{null, null};
        if (null != value && value.length() > 0) {
            value = checkCustomerIdent(value);
            // Split value
            String[] split = value.split("[-]");
            // Set array values
            if (split.length >= 1) {
                String customerIdent = split[0];
                ret[0] = checkCustomerIdent(customerIdent);
            }
            if (split.length == 2) {
                String agentIdent = split[1];
                ret[1] = checkAgentIdent(agentIdent);
            }
        }
        return ret;
    }

    public static String checkCustomerIdent(String value) {
        if (null != value && value.length() > 0) {
            String _value = value.trim();
            return _value.substring(0, Math.min(_value.length(), LENGTH_KDNR + 1 + LENGTH_SBNR));
        } else {
            return null;
        }
    }

    public static String checkAgentIdent(String value) {
        if (null != value && value.length() > 0) {
            String _value = value.trim();
            return _value.substring(0, Math.min(_value.length(), LENGTH_SBNR));
        } else {
            return null;
        }
    }

    public static String checkInvoiceIdent(String value) {
        if (null != value && value.length() > 0) {
            String _value = value.trim();
            return _value.substring(0, Math.min(_value.length(), LENGTH_KDNR));
        } else {
            return null;
        }
    }

}
