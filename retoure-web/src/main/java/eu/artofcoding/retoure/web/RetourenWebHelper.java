/*
 * retoure-server
 * retoure-web
 * Copyright (C) 2012-2012 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 24.11.12 18:22
 */

package eu.artofcoding.retoure.web;

import eu.artofcoding.beetlejuice.cdm.store.StoreIdent;
import eu.artofcoding.retoure.api.RetoureRuntimeException;
import eu.artofcoding.retoure.store.aktivshop.AktivshopConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import static eu.artofcoding.retoure.api.RetourenConstants.STORE_IDENT;

public class RetourenWebHelper implements Serializable {

    private static final Logger logger = Logger.getLogger(RetourenWebHelper.class.getName());

    private HttpSession httpSession;

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public StoreIdent getStoreIdent() {
        // Check state
        if (null == httpSession) {
            throw new RetoureRuntimeException("No HTTP session");
        }
        return (StoreIdent) httpSession.getAttribute(STORE_IDENT);
    }

    public void setStoreIdentFromRequest(HttpServletRequest httpServletRequest) {
        // Check state
        if (null == httpSession) {
            throw new RetoureRuntimeException("No HTTP session");
        }
        // Get storeIdent from session
        StoreIdent storeIdent = (StoreIdent) httpSession.getAttribute(STORE_IDENT);
        String requestURI = httpServletRequest.getRequestURI();
        if (null == storeIdent) {
            Path path = Paths.get(requestURI);
            if (path.endsWith(AktivshopConstants.AKTIVSHOP) || path.getParent().endsWith(AktivshopConstants.AKTIVSHOP)) {
                storeIdent = StoreIdent.AKTIVSHOP;
            }
            httpSession.setAttribute(STORE_IDENT, storeIdent);
            logger.info(String.format("StoreIdent is %s", httpSession.getAttribute(STORE_IDENT)));
        }
    }

}
