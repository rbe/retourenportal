/*
 * retoure-server
 * retoure-web-helper
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 14.02.13 15:59
 */

package eu.artofcoding.retoure.web;

import eu.artofcoding.beetlejuice.cdm.store.Article;

public class ArticleHelper {

    public static void markArticleForReturn(Article a) {
        if (a.getArticleReturn().isReturnable()) {
            a.getArticleReturn().setToBeReturned(true);
            a.getArticleReturn().setReturnCount(1.0f);
//            if (a.getCount() == 1.0f) {
//                a.getArticleReturn().setReturnCount(1.0f);
//            }
        }
    }

    public static void markArticleForReturn(String articleIdent, Article[] articles) {
        // Find article and set to-be-returned flag
        for (Article a : articles) {
            if (a.getArticleIdent().equals(articleIdent)) {
                markArticleForReturn(a);
            }
        }
    }

    public static void unmarkArticleForReturn(Article a) {
        if (a.getArticleReturn().isReturnable()) {
            a.getArticleReturn().setToBeReturned(false);
            a.getArticleReturn().setReturnCount(0.0f);
        }
    }

    public static void unmarkArticleForReturn(String articleIdent, Article[] articles) {
        // Find article and set to-be-returned flag
        for (Article a : articles) {
            if (a.getArticleIdent().equals(articleIdent)) {
                unmarkArticleForReturn(a);
            }
        }
    }

}
