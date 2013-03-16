/*
 * retoure-server
 * retoure-web-aktivshop
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 06.03.13 13:27
 */

package eu.artofcoding.retoure.web.aktivshop;

import eu.artofcoding.beetlejuice.cdm.store.Article;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "articleDoubleRange")
public class DoubleRangeValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object val) throws ValidatorException {
        Article article = (Article) component.getAttributes().get("article");
        long enteredVal = (Long) val;
        float v = article.getCount() - article.getArticleReturn().getAlreadyReturnedCount();
        if (enteredVal < 1 || enteredVal > v) {
            FacesMessage message = new FacesMessage();
            message.setDetail(String.format("Bitte geben Sie eine korrekte Menge zwischen 1 und %.0f ein!", v));
            message.setSummary("Menge nicht korrekt");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

}
