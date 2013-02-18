package eu.artofcoding.retoure.web.aktivshop;

import eu.artofcoding.beetlejuice.cdm.store.Article;
import eu.artofcoding.beetlejuice.cdm.store.ReturnReason;
import eu.artofcoding.beetlejuice.cdm.store.StoreCustomer;
import eu.artofcoding.retoure.api.RetoureConstants;
import eu.artofcoding.retoure.store.RetoureFacade;
import eu.artofcoding.retoure.web.ArticleHelper;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("retoure")
@RequestScoped
public class RetoureBean {

    private Logger logger = Logger.getLogger(RetoureBean.class.getName());

    @EJB
    private RetoureFacade retoureFacade;

    private boolean allArticlesMarkedForReturn;

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

    public Article[] getArticlesOfInvoice() {
        StoreCustomer customer = getCustomerFromSession();
        String invoiceIdent = getInvoiceIdentFromSession();
        if (null != customer) {
            List<Article> articles = customer.getInvoice(invoiceIdent).getArticles();
            return articles.toArray(new Article[articles.size()]);
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
     * <h:selectBooleanCheckbox valueChangeListener="#{retoure.markArticleForReturn}">
     *     <f:attribute name="articleIdent" value="#{article.articleIdent}"/>
     *     <f:ajax event="click" render="@form"/>
     * </h:selectBooleanCheckbox>
     * </pre>
     * @param valueChangeEvent Value change event.
     */
    public void markArticleForReturn(ValueChangeEvent valueChangeEvent) {
        UIInput component = (UIInput) valueChangeEvent.getComponent();
        String articleIdent = (String) component.getAttributes().get("articleIdent");
        // Find article and set to-be-returned flag
        ArticleHelper.markArticleForReturn(articleIdent, getArticlesOfInvoice());
    }

    public void returnCountClickEvent(AjaxBehaviorEvent ajaxBehaviorEvent) {
        // Get articleIdent from f:param/request params
        String articleIdent = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("articleIdent");
        // Find article and set to-be-returned flag
        ArticleHelper.markArticleForReturn(articleIdent, getArticlesOfInvoice());
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

    public ReturnReason[] getReturnReasonForArticle(Article article) {
        return retoureFacade.fetchReasons(article);
    }

    public void reasonSelected(AjaxBehaviorEvent ajaxBehaviorEvent) {
        /*
        // Get articleIdent from f:param/request params
        String articleIdent = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("articleIdent");
        HtmlSelectOneRadio radioButton = (HtmlSelectOneRadio) ajaxBehaviorEvent.getSource();
        Object value = radioButton.getValue();
        System.out.println(value);
        */
    }

    public boolean canProceedToNextStep() {
        boolean b = true;
        if (getArticlesMarkedForReturn().length == 0) {
            b = false;
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning("NO ARTICLES MARKED FOR RETURN");
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

}
