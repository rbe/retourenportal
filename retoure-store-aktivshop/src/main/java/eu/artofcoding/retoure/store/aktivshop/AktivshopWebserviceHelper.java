/*
 * retoure-server
 * retoure-store-aktivshop
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 24.02.13 14:36
 */

package eu.artofcoding.retoure.store.aktivshop;

import com.google.common.base.Predicates;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import eu.artofcoding.beetlejuice.cdm.Group;
import eu.artofcoding.beetlejuice.cdm.Salutation;
import eu.artofcoding.beetlejuice.cdm.accounting.Invoice;
import eu.artofcoding.beetlejuice.cdm.store.Article;
import eu.artofcoding.beetlejuice.cdm.store.ArticleReturn;
import eu.artofcoding.beetlejuice.cdm.store.StoreCustomer;
import eu.artofcoding.retoure.api.RetoureException;
import eu.artofcoding.retoure.store.aktivshop.wsclient.irt01t.IRT01Input;
import eu.artofcoding.retoure.store.aktivshop.wsclient.irt01t.IRT01Result;
import eu.artofcoding.retoure.store.aktivshop.wsclient.irt02t.IRT02Result;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import static eu.artofcoding.retoure.store.aktivshop.AktivshopConstants.EMPTY_STRING;

public class AktivshopWebserviceHelper {

    private static final Logger logger = Logger.getLogger(AktivshopWebserviceHelper.class.getName());

    public static void dumpPayload(IRT01Input irt01Input) throws RetoureException {
        System.out.println(irt01Input.toString());
        System.out.printf("INPUT:  Firmennummer:         len=%02d, FNR= '%s'%n", irt01Input.getFNR().getValue().length(), irt01Input.getFNR().getValue());
        System.out.printf("INPUT:  Kundennummer:         len=%02d, ID=  '%s'%n", irt01Input.getID().getValue().length(), irt01Input.getID().getValue());
        System.out.printf("INPUT:  Rechnungsnummer:      len=%02d, RGNR='%s'%n", irt01Input.getRGNR().getValue().toPlainString().length(), irt01Input.getRGNR().getValue());
        System.out.printf("INPUT:  Sachbearbeiternummer: len=%02d, SBNR='%s'%n", irt01Input.getSBNR().getValue().toPlainString().length(), irt01Input.getSBNR().getValue());
        System.out.printf("INPUT:  ERG=%d, ERGT=%s%n", irt01Input.getERG().getValue().toBigInteger(), irt01Input.getERGT().getValue());
    }

    public static void dumpPayload(IRT01Result irt01Result) throws RetoureException {
        System.out.println(irt01Result.toString());
        System.out.printf("RESULT: Firmennummer:         len=%02d, FNR= '%s'%n", irt01Result.getFNR().getValue().length(), irt01Result.getFNR().getValue());
        System.out.printf("RESULT: Sachbearbeiternummer: len=%02d, SBNR='%s'%n", irt01Result.getSBNR().getValue().toPlainString().length(), irt01Result.getSBNR().getValue());
        System.out.printf("RESULT: Kundennummer:         len=%02d, ID=  '%s'%n", irt01Result.getID().getValue().length(), irt01Result.getID().getValue());
        System.out.printf("RESULT: Rechnungsnummer:      len=%02d, RGNR='%s'%n", irt01Result.getRGNR().getValue().toPlainString().length(), irt01Result.getRGNR().getValue());
        System.out.printf("RESULT: ERG=%d, ERGT=%s%n", irt01Result.getERG().getValue().toBigInteger(), irt01Result.getERGT().getValue());
    }

    public static void dumpPayload(IRT02Result irt02Result) throws RetoureException {
        System.out.println(irt02Result.toString());
        System.out.printf("RESULT: Firmennummer:         len=%02d, FNR=   '%s'%n", irt02Result.getFNR().getValue().length(), irt02Result.getFNR().getValue());
        System.out.printf("RESULT: Rechnungsnummer:      len=%02d, RGNR=  '%s'%n", irt02Result.getRGNR().getValue().toPlainString().length(), irt02Result.getRGNR().getValue());
        System.out.printf("RESULT: Sachbearbeiternummer: len=%02d, SBNR=  '%s'%n", irt02Result.getSBNR().getValue().toPlainString().length(), irt02Result.getSBNR().getValue());
        System.out.printf("RESULT: Kennzeichen Retoure:  len=%02d, KZRTER='%d'%n", irt02Result.getKZRTER().getValue().toPlainString().length(), irt02Result.getKZRTER().getValue().toBigInteger());
        System.out.printf("RESULT: Kennzeichen OP:       len=%02d, KZOP=  '%s'%n", irt02Result.getKZOP().getValue().toPlainString().length(), irt02Result.getKZOP().getValue());
        System.out.printf("RESULT: Versandart:           len=%02d, VATX=  '%s'%n", irt02Result.getVATX().getValue().length(), irt02Result.getVATX().getValue());
        System.out.printf("RESULT: Vollständiger Name:   len=%02d, ANAME= '%s'%n", irt02Result.getANAME().getValue().length(), irt02Result.getANAME().getValue());
        System.out.printf("RESULT: Strasse               len=%02d, ASTR=  '%s'%n", irt02Result.getASTR().getValue().length(), irt02Result.getASTR().getValue());
        System.out.printf("RESULT: PLZ                   len=%02d, APLZ=  '%s'%n", irt02Result.getAPLZ().getValue().length(), irt02Result.getAPLZ().getValue());
        System.out.printf("RESULT: Ort                   len=%02d, AORT=  '%s'%n", irt02Result.getAORT().getValue().length(), irt02Result.getAORT().getValue());
        boolean answerOK = checkIRT02Result(irt02Result);
        System.out.println("RESULT: Antwort ist im Sinne der Länge der Listen: " + (answerOK ? "OK" : "nicht OK"));
        if (answerOK) {
            int lenFARN = irt02Result.getFARN().size();
            for (int i = 0; i < lenFARN; i++) {
                BigDecimal fmng = irt02Result.getFMNG().get(i);
                BigDecimal fmnr = irt02Result.getFMNR().get(i);
                if (fmng.compareTo(BigDecimal.ZERO) == 1) {
                    System.out.printf("RESULT: Artikel %02d                    FARN='%s'%n", i, irt02Result.getFARN().get(i));
                    System.out.printf("RESULT: Artikel %02d                    FARH='%s'%n", i, irt02Result.getFARH().get(i));
                    System.out.printf("RESULT: Artikel %02d                    FARG='%s'%n", i, irt02Result.getFARG().get(i));
                    System.out.printf("RESULT: Artikel %02d                    FMNG='%s'%n", i, fmng.toPlainString());
                    System.out.printf("RESULT: Artikel %02d                    FMNR='%s'%n", i, fmnr.toPlainString());
                }
            }
        } else {
            System.out.println("RESULT: Keine Artikel!");
        }
    }

    public static boolean checkIRT01Result(IRT01Result irt01Result) throws RetoureException {
        return true;
    }

    /**
     * Ergebnis vom Web Service prüfen.
     * FARN = Artikelnummern zu einem Artikel von einer Rechnung.
     * FARH = Hauptgruppen zu einem Artikel von einer Rechnung.
     * FARG = Gruppe zu einem Artikel von einer Rechnung.
     * FBE1 = Bezeichnung 1 zu einem Artikel von einer Rechnung.
     * FBE2 = Bezeichnung 2 zu einem Artikel von einer Rechnung.
     * FMNG = Menge zu einem Artikel von einer Rechnung.
     * @param irt02Result Ergebnis des Web Service IRT02T.
     * @return Daten OK?
     * @throws RetoureException
     */
    public static boolean checkIRT02Result(IRT02Result irt02Result) throws RetoureException {
        return true;
/*
        boolean b = true;
        // Clean out lists, they have always the same size, w/ empty values
        List<String> farn = ImmutableList.copyOf(Iterables.filter(irt02Result.getFARN(), Predicates.not(Predicates.equalTo(EMPTY_STRING))));
        List<String> farh = ImmutableList.copyOf(Iterables.filter(irt02Result.getFARH(), Predicates.not(Predicates.equalTo(EMPTY_STRING))));
        List<String> farg = ImmutableList.copyOf(Iterables.filter(irt02Result.getFARG(), Predicates.not(Predicates.equalTo(EMPTY_STRING))));
        List<String> fbe1 = ImmutableList.copyOf(Iterables.filter(irt02Result.getFBE1(), Predicates.not(Predicates.equalTo(EMPTY_STRING))));
        List<String> fbe2 = ImmutableList.copyOf(Iterables.filter(irt02Result.getFBE2(), Predicates.not(Predicates.equalTo(EMPTY_STRING))));
        List<BigDecimal> fmng = ImmutableList.copyOf(Iterables.filter(irt02Result.getFMNG(), Predicates.not(Predicates.equalTo(ZERO_SCALE_3))));
        // Check length of lists
        int[] arr = {
                farn.size(),
                farh.size(),
                farg.size(),
                fbe1.size(),
                fbe2.size(),
                fmng.size()
        };
        int arrlen = arr.length;
        for (int j = 0; j < arrlen; j++) {
            int x = arr[j];
            for (int i = 0; i < arrlen; i++) {
                int y = arr[i];
                if (x != y) {
                    if (logger.isLoggable(Level.WARNING)) {
                        String _farn = "?";
                        try {
                            _farn = farn.get(i);
                        } catch (IndexOutOfBoundsException e) {
                            _farn = e.getMessage();
                        }
                        System.out.printf("Incorrect values from web service: FARN='%s' Length incorrect, x=%d y=%d%n", _farn, x, y);
                        //logger.warning(String.format("Incorrect values from web service: FARN='%s' Length incorrect, x=%d y=%d%n", _farn, x, y));
                    }
                }
            }
        }
        return b;
*/
    }

    public static StoreCustomer readArticles(StoreCustomer customer, IRT02Result irt02Result) {
        // KZRTER: Kennzeichen Retoure erlaubt? 9=Fehler beim Ermittlung, 2=Retoure zu spät
        // KZOP:   Kennzeichnung Rechnung offen? 0=OP nicht ermittelt, 1=OP noch offen, 2=OP bezahlt
        boolean retoureAllowed = irt02Result.getKZRTER().getValue().equals(BigDecimal.ZERO);
        if (retoureAllowed) {
            // Rechnungsnummer
            String invoiceIdent = irt02Result.getRGNR().getValue().toPlainString();
            // TODO Rechnung bezahlt... wichtig?
            boolean paid = true; //irt02Result.getKZOP().getValue().equals(BigDecimal.ZERO);
            Invoice invoice = new Invoice(invoiceIdent, null, paid, irt02Result.getVATX().getValue(), true, null, null);
            /*
            // Clean out lists, they have always the same size, w/ empty values
            List<String> farn = ImmutableList.copyOf(Iterables.filter(irt02Result.getFARN(), Predicates.not(Predicates.equalTo(EMPTY_STRING))));
            List<String> farh = ImmutableList.copyOf(Iterables.filter(irt02Result.getFARH(), Predicates.not(Predicates.equalTo(EMPTY_STRING))));
            List<String> farg = ImmutableList.copyOf(Iterables.filter(irt02Result.getFARG(), Predicates.not(Predicates.equalTo(EMPTY_STRING))));
            List<String> fbe1 = ImmutableList.copyOf(Iterables.filter(irt02Result.getFBE1(), Predicates.not(Predicates.equalTo(EMPTY_STRING))));
            List<String> fbe2 = ImmutableList.copyOf(Iterables.filter(irt02Result.getFBE2(), Predicates.not(Predicates.equalTo(EMPTY_STRING))));
            List<BigDecimal> fmng = ImmutableList.copyOf(Iterables.filter(irt02Result.getFMNG(), Predicates.not(Predicates.equalTo(ZERO_SCALE_3))));
            List<BigDecimal> fmnr = ImmutableList.copyOf(Iterables.filter(irt02Result.getFMNR(), Predicates.not(Predicates.equalTo(ZERO_SCALE_3))));
            */
            // Get all elements from FARN, where element is not an empty string
            // Size of resulting list is also the number of articles
            List<String> farn = ImmutableList.copyOf(Iterables.filter(irt02Result.getFARN(), Predicates.not(Predicates.equalTo(EMPTY_STRING))));
            List<String> farh = irt02Result.getFARH();
            List<String> farg = irt02Result.getFARG();
            List<String> fbe1 = irt02Result.getFBE1();
            List<String> fbe2 = irt02Result.getFBE2();
            List<BigDecimal> fmng = irt02Result.getFMNG();
            List<BigDecimal> fmnr = irt02Result.getFMNR();
            int arrlen = farn.size();
            Article article;
            for (int i = 0; i < arrlen; i++) {
                // Create article
                String articleIdent = farn.get(i); // Artikelnummer
                Group mainGroup = new Group(farh.get(i), null); // Hauptgruppe
                Group subGroup = new Group(farg.get(i), null); // Gruppe
                String shippingCompany = irt02Result.getVATX().getValue(); // Versandart
                float deliveredCount = fmng.get(i).floatValue(); // Menge geliefert
                float alreadyReturnedCount = fmnr.get(i).floatValue(); // Menge retourniert
                String description1 = fbe1.get(i); // Bezeichnung 1
                String description2 = fbe2.get(i); // Bezeichnung 2
                // Article
                article = new Article(articleIdent, mainGroup, subGroup, shippingCompany, deliveredCount, description1, description2);
                // Article can be returned?
                article.setArticleReturn(new ArticleReturn());
                article.getArticleReturn().setReturnable(retoureAllowed);
                // Already returned
                article.getArticleReturn().setAlreadyReturnedCount(alreadyReturnedCount);
                // Article should be returned through which shipping company?
                int typeLen = 4;
                if (null != shippingCompany && shippingCompany.length() > typeLen) {
                    String shippingType = shippingCompany.substring(shippingCompany.length() - 1 - typeLen);
                    if (shippingType.endsWith("    ")) {
                        // OK
                    } else if (shippingType.endsWith("SPED")) {
                        article.getArticleReturn().setUnreturnableReason("Spedition!");
                    }
                }
                // Add article to invoice
                addArticleToInvoice(customer, invoice, article);
            }
            customer.addInvoice(invoiceIdent, invoice);
        } else {
            customer.setLoginOk(false);
        }
        return customer;
    }

    public static void addArticleToInvoice(StoreCustomer customer, Invoice invoice, Article article) {
        // Agent logged in, unset unreturnable flag
        if (null != customer.getAgentIdent()) {
            ArticleReturn articleReturn = article.getArticleReturn();
            if (!articleReturn.isReturnable()) {
                articleReturn.setReturnable(true);
                articleReturn.setUnreturnableReason(null);
            }
        }
        invoice.addArticle(article);
    }

    public static StoreCustomer readCustomer(StoreCustomer customer, IRT02Result irt02Result) {
        // Anrede
        switch (irt02Result.getAANRE().getValue()) { // Anrede
            case "1":
                customer.setSalutation(Salutation.FRAU);
                break;
            case "2":
                customer.setSalutation(Salutation.HERR);
                break;
            case "3":
                customer.setSalutation(Salutation.FIRMA);
                break;
            default:
                customer.setSalutation(Salutation.UNKNOWN);
        }
        customer.setFullname(irt02Result.getANAME().getValue()); // Vollständiger Name
        customer.setShippingAddress1(irt02Result.getASTR().getValue()); // Straße
        customer.setZipcode(irt02Result.getAPLZ().getValue()); // PLZ
        customer.setCity(irt02Result.getAORT().getValue()); // Ort
        return customer;
    }

    public static String makeKundennummer(String kdnr) throws RetoureException {
        if (null == kdnr) {
            throw new RetoureException("No customer number given");
        }
        kdnr = Strings.padStart(kdnr, 8, ' ');
        return kdnr;
    }

    public static String makeSachbearbeiternummer(String sbnr) throws RetoureException {
        if (null == sbnr) {
            throw new RetoureException("No agent given");
        }
        sbnr = Strings.padEnd(sbnr.toUpperCase(), 10, ' ');
        return sbnr;
    }

    public static BigDecimal makeRechnungsnummer(String renr) throws RetoureException {
        try {
            return BigDecimal.valueOf(Long.valueOf(renr));
        } catch (NumberFormatException e) {
            throw new RetoureException("Invoice number invalid", e);
        }
    }

}
