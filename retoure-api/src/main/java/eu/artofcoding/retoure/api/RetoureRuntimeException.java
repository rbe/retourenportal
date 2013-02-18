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

public class RetoureRuntimeException extends RuntimeException {

    public RetoureRuntimeException() {
        super();
    }

    public RetoureRuntimeException(String message) {
        super(message);
    }

    public RetoureRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RetoureRuntimeException(Throwable cause) {
        super(cause);
    }

    protected RetoureRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
