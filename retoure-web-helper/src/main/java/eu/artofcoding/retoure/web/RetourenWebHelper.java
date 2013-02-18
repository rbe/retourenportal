/*
 * retoure-server
 * retoure-web-helper
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 11.02.13 13:15
 */

package eu.artofcoding.retoure.web;

import eu.artofcoding.beetlejuice.cdm.store.StoreIdent;
import eu.artofcoding.retoure.api.RetoureRuntimeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import static eu.artofcoding.retoure.api.RetoureConstants.STORE_IDENT;

public class RetourenWebHelper implements Serializable {

    private static final long serialVersionUID = 1L;

    private static transient Logger logger = Logger.getLogger(RetourenWebHelper.class.getName());

    private transient HttpSession httpSession;

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public StoreIdent getStoreIdent() {
        // Check state
        if (null == httpSession) {
            throw new RetoureRuntimeException("No HTTP session");
        } else {
            return (StoreIdent) httpSession.getAttribute(STORE_IDENT);
        }
    }

    public StoreIdent getLastPart(HttpServletRequest httpServletRequest) {
        String requestURI = httpServletRequest.getRequestURI();
        Path path = Paths.get(requestURI);
        int e = path.getNameCount() - 1;
        StoreIdent storeIdent = new StoreIdent(path.subpath(e - 1, e).toString(), null);
        return storeIdent;
    }

    public boolean checkStore(HttpServletRequest httpServletRequest, String storeIdent) {
        String requestURI = httpServletRequest.getRequestURI();
        Path path = Paths.get(requestURI);
        return path.endsWith(storeIdent) || path.getParent().endsWith(storeIdent);
    }

    public void setStoreIdentFromRequest(HttpServletRequest httpServletRequest) {
        // Check state
        if (null == httpSession) {
            throw new RetoureRuntimeException("No HTTP session");
        }
        // Get storeIdent from session
        StoreIdent storeIdent = (StoreIdent) httpSession.getAttribute(STORE_IDENT);
        if (null == storeIdent) {
            storeIdent = getLastPart(httpServletRequest);
            httpSession.setAttribute(STORE_IDENT, storeIdent);
            logger.info(String.format("StoreIdent is %s", httpSession.getAttribute(STORE_IDENT)));
        }
    }

}
