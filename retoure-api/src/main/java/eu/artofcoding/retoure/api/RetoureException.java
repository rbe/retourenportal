/*
 * retoure-server
 * retoure-api
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 11.02.13 09:16
 */

package eu.artofcoding.retoure.api;

public class RetoureException extends Exception {

    public RetoureException() {
        super();
    }

    public RetoureException(String message) {
        super(message);
    }

    public RetoureException(String message, Throwable cause) {
        super(message, cause);
    }

    public RetoureException(Throwable cause) {
        super(cause);
    }

    protected RetoureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
