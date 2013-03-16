/*
 * retoure-server
 * retoure-web-aktivshop
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 05.03.13 15:01
 */

package eu.artofcoding.retoure.web.aktivshop;

import eu.artofcoding.beetlejuice.cdm.accounting.Invoice;
import eu.artofcoding.beetlejuice.cdm.store.Article;
import eu.artofcoding.beetlejuice.cdm.store.StoreCustomer;
import eu.artofcoding.beetlejuice.web.jsf.RadioButtonData;
import eu.artofcoding.retoure.api.RetoureConstants;
import eu.artofcoding.retoure.api.RetoureException;
import eu.artofcoding.retoure.store.RetoureFacade;
import eu.artofcoding.retoure.web.ArticleHelper;
import org.apache.myfaces.custom.radio.HtmlRadio;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("retoure")
@SessionScoped
public class RetoureBean implements Serializable {

    private Logger logger = Logger.getLogger(RetoureBean.class.getName());

    @EJB
    private RetoureFacade retoureFacade;

    private boolean allArticlesMarkedForReturn;

    private static final String EMPTY_STRING = "".intern();
    private Map<String, String> stage1Selected = new HashMap<>();
    private RadioButtonData[] stage1Values;
    private Map<String, boolean[]> stage2Render;
    private RadioButtonData[][] stage2Values;
    private String stage2Selected;
    private Map<String, Boolean> textareaMandatory;
    private Map<String, String> message;
    private boolean showGeldzurueck;

    private static class Joiner {

        private char separator;

        private boolean skipNulls;

        public static Joiner on(char separator) {
            return new Joiner(separator);
        }

        public Joiner(char separator) {
            this.separator = separator;
        }

        public Joiner skipNulls() {
            this.skipNulls = true;
            return this;
        }

        public String join(Object[] parts) {
            StringBuilder builder = new StringBuilder(50);
            int len = parts.length;
            for (int i = 0; i < len; i++) {
                builder.append(parts);
                if (i < len - 1) {
                    builder.append(separator);
                }
            }
            return builder.toString();
        }
    }

    private transient Joiner joiner = Joiner.on(',').skipNulls();

    private HttpSession getHttpSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        return (HttpSession) externalContext.getSession(true);
    }

    private StoreCustomer getCustomerFromSession() {
        return (StoreCustomer) getHttpSession().getAttribute(RetoureConstants.CUSTOMER_IDENT);
    }

    private String getInvoiceIdentFromSession() {
        return (String) getHttpSession().getAttribute(RetoureConstants.INVOICE_IDENT);
    }

    @PostConstruct
    public void fetchInvoice() {
        stage1Values = new RadioButtonData[]{
                new RadioButtonData("Der Artikel gefällt nicht / entspricht nicht den Erwartungen", "gefalltNicht", EMPTY_STRING, false),
                new RadioButtonData("Der Artikel passt nicht", "passtNicht", EMPTY_STRING, false),
                new RadioButtonData("Falscher Artikel geliefert / Artikel wurde nicht bestellt", "falscherArtikel", EMPTY_STRING, false),
                new RadioButtonData("Mehrere Varianten zur Auswahl bestellt", "mehrereVariantenBestellt", EMPTY_STRING, false),
                new RadioButtonData("Der Artikel ist defekt oder beschädigt", "artikelDefekt", EMPTY_STRING, false),
                new RadioButtonData("Der Artikel wurde versehentlich falsch bestellt", "artikelVersehentlichFalsch", EMPTY_STRING, false),
                new RadioButtonData("Die Lieferung ist unvollständig", "lieferungUnvollstandig", EMPTY_STRING, false),
                new RadioButtonData("Zu lange Lieferzeit / Artikel zu spät geliefert", "dauerLieferung", EMPTY_STRING, false),
                new RadioButtonData("Sonsitges", "sonstiges", EMPTY_STRING, false)
        };
        stage2Values = new RadioButtonData[stage1Values.length][];
        stage2Values[0] = new RadioButtonData[]{
                new RadioButtonData("Was anderes erwartet / missverständlicher Produkttext", "anderesErwartet", EMPTY_STRING, false),
                new RadioButtonData("Die Qualität des Artikels gefällt nicht / was Besseres erwartet", "qualitatGefalltNicht", EMPTY_STRING, false),
                new RadioButtonData("Anleitung nicht verständlich / Aufbau ist zu kompliziert", "anleitungAufbau", EMPTY_STRING, false),
                new RadioButtonData("Bedienung/Handhabung zu kompliziert", "bedienungZuKompliziert", EMPTY_STRING, false),
                new RadioButtonData("Farbe gefällt nicht", "farbeGefalltNicht", EMPTY_STRING, false),
                new RadioButtonData("Material gefällt nicht", "materialGefalltNicht", EMPTY_STRING, false)
        };
        stage2Values[1] = new RadioButtonData[]{
                new RadioButtonData("Artikel zu groß", "artikelZuGross", EMPTY_STRING, false),
                new RadioButtonData("Artikel zu klein", "artikelZuKlein", EMPTY_STRING, false)
        };
        stage2Values[2] = new RadioButtonData[]{
                new RadioButtonData("Falsche Größe", "falscheGrosse", EMPTY_STRING, false),
                new RadioButtonData("Falsche Farbe", "falscheFarbe", EMPTY_STRING, false),
                new RadioButtonData("Falsche Liefermenge", "falscheLiefermenge", EMPTY_STRING, false),
                new RadioButtonData("Doppelte Lieferung", "doppelteLieferung", EMPTY_STRING, false),
                new RadioButtonData("Komplett falscher Artikel", "komplettFalsch", EMPTY_STRING, false)
        };
/*
        stage1Values = new RadioButtonData[]{
                new RadioButtonData("Falscher Artikel geliefert", "falscherArtikel", EMPTY_STRING, false),
                new RadioButtonData("Ware wurde nicht bestellt / falsch bestellt", "wareNichtBestellt", EMPTY_STRING, false),
                new RadioButtonData("Transportschaden", "transportschaden", EMPTY_STRING, false),
                new RadioButtonData("Lieferung unvollständig", "lieferungUnvollstandig", EMPTY_STRING, false),
                new RadioButtonData("Ware defekt", "wareDefekt", EMPTY_STRING, false),
                new RadioButtonData("Ware gefällt nicht / entspricht nicht den Erwartungen", "wareGefalltNicht", EMPTY_STRING, false)
        };
        stage2Values = new RadioButtonData[stage1Values.length][];
        stage2Values[0] = new RadioButtonData[]{
                new RadioButtonData("Falsche Größe", "falscheGrosse", EMPTY_STRING, false),
                new RadioButtonData("Falsche Farbe", "falscheFarbe", EMPTY_STRING, false),
                new RadioButtonData("Falsche Liefermenge", "falscheLiefermenge", EMPTY_STRING, false),
                new RadioButtonData("Doppelte Lieferung", "doppelteLieferung", EMPTY_STRING, false),
                new RadioButtonData("Komplett falscher Artikel", "komplettFalsch", EMPTY_STRING, false)
        };
        stage2Values[1] = new RadioButtonData[]{
                new RadioButtonData("Falsche Liefermenge", "falscheLiefermenge", EMPTY_STRING, false),
                new RadioButtonData("Doppelte Lieferung", "doppelteLieferung", EMPTY_STRING, false)
        };
        stage2Values[2] = new RadioButtonData[]{
                new RadioButtonData("Zu lange Lieferzeit / Ware zu spät geliefert", "langeLieferzeit", EMPTY_STRING, false)
        };
*/
        stage2Render = new HashMap<>();
        textareaMandatory = new HashMap<>();
        message = new HashMap<>();
        // TODO Gründe pro Artikel holen
        //retoureFacade.fetchReasons(article);
    }

    public Article[] getArticlesOfInvoice() {
        Article[] articles = null;
        StoreCustomer customer = getCustomerFromSession();
        if (null != customer) {
            String invoiceIdent = getInvoiceIdentFromSession();
            Invoice invoice = customer.getInvoice(invoiceIdent);
            if (null == invoice && customer.isLoginOk()) {
                try {
                    retoureFacade.fetchInvoice(customer, invoiceIdent);
                    invoice = customer.getInvoice(invoiceIdent);
                } catch (RetoureException e) {
                    logger.log(Level.SEVERE, String.format("Cannot fetch invoice %s", invoiceIdent), e);
                }
            }
            if (null != invoice) { // Was invoice fetched?
                List<Article> _articles = invoice.getArticles();
                articles = invoice.getArticles().toArray(new Article[_articles.size()]);
            }
        }/* else {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning(this + "#getArticlesOfInvoice: Customer == null");
            }
        }*/
        return articles;
    }

    public Article[] getArticlesMarkedForReturn() {
        StoreCustomer customer = getCustomerFromSession();
        if (null != customer) {
            String invoiceIdent = getInvoiceIdentFromSession();
            List<Article> returnArticles = customer.getInvoice(invoiceIdent).getReturnArticles();
            return returnArticles.toArray(new Article[returnArticles.size()]);
        } else {
            return null;
        }
    }

    public boolean isAllArticlesMarkedForReturn() {
        return allArticlesMarkedForReturn;
    }

    /**
     * Mark all checkboxes.
     * @param allArticlesMarkedForReturn State of checkbox.
     */
    public void setAllArticlesMarkedForReturn(boolean allArticlesMarkedForReturn) {
        this.allArticlesMarkedForReturn = allArticlesMarkedForReturn;
    }

    /**
     * <pre>
     * <h:selectBooleanCheckbox valueChangeListener="#{retoure.markAllArticlesForReturn}"
     *     <f:ajax event="click" render="@form"/>
     * </h:selectBooleanCheckbox>
     * </pre>
     * @param valueChangeEvent Value change event.
     */
    public void markAllArticlesForReturn(ValueChangeEvent valueChangeEvent) {
        HtmlSelectBooleanCheckbox checkbox = (HtmlSelectBooleanCheckbox) valueChangeEvent.getSource();
        for (Article a : getArticlesOfInvoice()) {
            Boolean b = (Boolean) valueChangeEvent.getNewValue();
            if (Boolean.TRUE.equals(b)) {
                ArticleHelper.markArticleForReturn(a);
            } else {
                ArticleHelper.unmarkArticleForReturn(a);
            }
        }
    }

    /**
     * <pre>
     * <h:selectBooleanCheckbox valueChangeListener="#{retoure.markOrUnmarkArticleForReturn}">
     *     <f:attribute name="articleIdent" value="#{article.articleIdent}"/>
     *     <f:ajax event="click" render="@form"/>
     * </h:selectBooleanCheckbox>
     * </pre>
     * @param valueChangeEvent Value change event.
     */
    public void markOrUnmarkArticleForReturn(ValueChangeEvent valueChangeEvent) {
        UIInput component = (UIInput) valueChangeEvent.getComponent();
        String articleIdent = (String) component.getAttributes().get("articleIdent");
        // Find article and set to-be-returned flag
        boolean b = (Boolean) valueChangeEvent.getNewValue();
        Article[] articlesOfInvoice = getArticlesOfInvoice();
        if (b) {
            ArticleHelper.markArticleForReturn(articleIdent, articlesOfInvoice);
        } else {
            ArticleHelper.unmarkArticleForReturn(articleIdent, articlesOfInvoice);
        }
        // Set return-all-articles flag
        if (ArticleHelper.areAllArticlesMarkedForReturn(articlesOfInvoice)) {
            setAllArticlesMarkedForReturn(true);
        } else {
            setAllArticlesMarkedForReturn(false);
        }
    }

    public void returnCountClickEvent(AjaxBehaviorEvent ajaxBehaviorEvent) {
        // Get articleIdent from f:param/request params
        String articleIdent = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("articleIdent");
        // Find article and set to-be-returned flag
        ArticleHelper.markArticleForReturn(articleIdent, getArticlesOfInvoice());
    }

    public RadioButtonData[] getStage1Values() {
        return stage1Values;
    }

    public RadioButtonData[][] getStage2Values() {
        return stage2Values;
    }

    public Map<String, String> getStage1Selected() {
        return stage1Selected;
    }

    public void stage1ChangeListener(AjaxBehaviorEvent ajaxBehaviorEvent) {
        HtmlRadio source = (HtmlRadio) ajaxBehaviorEvent.getSource();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String articleIdent = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{article.articleIdent}", String.class);
        boolean[] booleans = stage2Render.get(articleIdent);
        if (null == booleans) {
            booleans = new boolean[stage1Values.length];
            stage2Render.put(articleIdent, booleans);
        }
        // Reset selected index (previous request)
        for (int i = 0; i < booleans.length; i++) {
            booleans[i] = false;
        }
        // Set selected value
        int stage1SelectedIndex = source.getIndex();
        stage1Selected.put(articleIdent, stage1Values[stage1SelectedIndex].getValue());
        booleans[stage1SelectedIndex] = true;
    }

    public Map<String, boolean[]> getStage2Render() {
        return stage2Render;
    }

    public String getStage2Selected() {
        return stage2Selected;
    }

    /**
     * Phase 5
     * @param ajaxBehaviorEvent The event.
     */
    public void stage2AjaxListener(AjaxBehaviorEvent ajaxBehaviorEvent) {
    }

    /**
     * Phase 3
     * @param valueChangeEvent The event.
     */
    public void stage2ValueChangeListener(ValueChangeEvent valueChangeEvent) {
        Object oldValue = (Object) valueChangeEvent.getOldValue();
        Object[] newValue = (Object[]) valueChangeEvent.getNewValue();
        // Case 1 initial setting of value: oldValue is not set and newValue is an array with length > 0
        // Case 2 oldValue is set, allow newValue is not set
        if ((null == oldValue && newValue.length > 0) || null != oldValue) {
            stage2Selected = joiner.join(newValue);
        }
    }

    public Map<String, Boolean> getTextareaMandatory() {
        return textareaMandatory;
    }

    public Map<String, String> getMessage() {
        return message;
    }

    /**
     * Phase 5
     * @param ajaxBehaviorEvent The event.
     */
    public void textareaAjaxListener(AjaxBehaviorEvent ajaxBehaviorEvent) {
        HtmlInputTextarea source = (HtmlInputTextarea) ajaxBehaviorEvent.getSource();
        String value = (String) source.getValue();
        System.out.println(value);
    }

    /**
     * Phase 3
     * @param valueChangeEvent The event.
     */
    public void textareaValueChangeListener(ValueChangeEvent valueChangeEvent) {
        System.out.println(this + "#textareaValueChangeListener: " + valueChangeEvent);
    }

    public boolean canProceedToNextStep() {
        boolean b = true;
        if (getArticlesMarkedForReturn().length == 0) {
            b = false;
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning(String.format("%s#canProceedToNextStep: no", this));
            }
        } else {
            for (Article a : getArticlesMarkedForReturn()) {
                if (a.getArticleReturn().getReturnCount() == 0) {
                    b = false;
                    if (logger.isLoggable(Level.WARNING)) {
                        logger.warning(String.format("Article %s has return count 0", a.getArticleIdent()));
                    }
                    break;
                }
            }
        }
        return b;
    }

    public boolean isShowGeldzurueck() {
        return showGeldzurueck;
    }

    public void geldzurueck() {
        showGeldzurueck = true;
    }

}
