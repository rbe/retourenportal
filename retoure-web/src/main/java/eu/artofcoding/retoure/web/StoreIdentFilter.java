/*
 * retoure-server
 * retoure-web
 * Copyright (C) 2012-2012 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 25.11.12 16:21
 */

package eu.artofcoding.retoure.web;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

import static eu.artofcoding.retoure.api.RetourenConstants.STORE_IDENT;

public class StoreIdentFilter implements Filter {

    private static final Logger logger = Logger.getLogger(StoreIdentFilter.class.getName());

    @Inject
    private RetourenWebHelper retourenWebHelper;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        if (null != session) {
            retourenWebHelper.setHttpSession(session);
            // Set store ident just once
            if (null == session.getAttribute(STORE_IDENT)) {
                retourenWebHelper.setStoreIdentFromRequest(httpServletRequest);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }

}
