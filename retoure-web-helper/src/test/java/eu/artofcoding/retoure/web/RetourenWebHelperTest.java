/*
 * retoure-server
 * retoure-web-helper
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 13.02.13 15:09
 */

package eu.artofcoding.retoure.web;

import eu.artofcoding.beetlejuice.api.persistence.GenericEntity;
import eu.artofcoding.beetlejuice.cdm.Base;
import eu.artofcoding.beetlejuice.cdm.Company;
import eu.artofcoding.beetlejuice.cdm.accounting.BankAccount;
import eu.artofcoding.beetlejuice.cdm.store.Store;
import eu.artofcoding.beetlejuice.cdm.store.StoreCustomer;
import eu.artofcoding.beetlejuice.cdm.store.StoreIdent;
import eu.artofcoding.retoure.api.RetoureException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class RetourenWebHelperTest {

    private static final String WEBAPP_SRC = "src/main/webapp";

    @Inject
    private RetourenWebHelper retourenWebHelper;

    @Deployment
    public static WebArchive createDeployment() {
        JavaArchive javaArchive =
                ShrinkWrap.create(JavaArchive.class, "retoure-web-helper.jar").
                        addPackage(RetoureException.class.getPackage()).
                        addPackage(GenericEntity.class.getPackage()).
                        addPackage(Base.class.getPackage()).
                        addPackage(StoreIdent.class.getPackage()).
                        addPackage(Company.class.getPackage()).
                        addPackage(StoreCustomer.class.getPackage()).
                        addPackage(BankAccount.class.getPackage()).
                        addPackage(Store.class.getPackage()).
                        addClass(RetourenWebHelper.class).
                        addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(javaArchive.toString(true));
        WebArchive webArchive =
                ShrinkWrap.create(WebArchive.class, "RetourenWebHelperTest.war").
                        addAsLibrary(javaArchive).
                        addClass(RetourenWebHelperTest.class).
                        addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(webArchive.toString(true));
        return webArchive;
    }

    @Test
    public void testInjection() {
        assertNotNull("retourenWebHelper is null", retourenWebHelper);
    }

}
