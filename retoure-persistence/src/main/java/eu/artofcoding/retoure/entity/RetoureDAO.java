/*
 * retoure-server
 * retoure-persistence
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 11.02.13 09:16
 */

package eu.artofcoding.retoure.entity;

import eu.artofcoding.beetlejuice.cdm.Base;
import eu.artofcoding.beetlejuice.persistence.GenericDAO;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

@Stateless
public class RetoureDAO extends GenericDAO<Base> {

    private static final Logger logger = Logger.getLogger(RetoureDAO.class.getName());

    @PersistenceContext
    private EntityManager em;

    public RetoureDAO() {
        super(null);
    }

    @PostConstruct
    private void postConstruct() {
        logger.info("em=" + em);
        setEntityManager(em);
    }

}
