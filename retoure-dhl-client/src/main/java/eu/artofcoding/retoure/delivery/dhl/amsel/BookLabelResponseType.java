
package eu.artofcoding.retoure.delivery.dhl.amsel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Books a Label.
 * 
 * <p>Java-Klasse für BookLabelResponseType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="BookLabelResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{https://amsel.dpwn.net/abholportal/gw/lp/schema/1.0/var3bl}WsResponseType">
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="label" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="issueDate">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="29"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="routingCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="idc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="idcType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="intIdc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="intIdcType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BookLabelResponseType", propOrder = {
    "label"
})
public class BookLabelResponseType
    extends WsResponseType
{

    protected String label;
    @XmlAttribute(name = "issueDate")
    protected String issueDate;
    @XmlAttribute(name = "routingCode")
    protected String routingCode;
    @XmlAttribute(name = "idc")
    protected String idc;
    @XmlAttribute(name = "idcType")
    protected String idcType;
    @XmlAttribute(name = "intIdc")
    protected String intIdc;
    @XmlAttribute(name = "intIdcType")
    protected String intIdcType;

    /**
     * Ruft den Wert der label-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabel() {
        return label;
    }

    /**
     * Legt den Wert der label-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Ruft den Wert der issueDate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueDate() {
        return issueDate;
    }

    /**
     * Legt den Wert der issueDate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueDate(String value) {
        this.issueDate = value;
    }

    /**
     * Ruft den Wert der routingCode-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoutingCode() {
        return routingCode;
    }

    /**
     * Legt den Wert der routingCode-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoutingCode(String value) {
        this.routingCode = value;
    }

    /**
     * Ruft den Wert der idc-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdc() {
        return idc;
    }

    /**
     * Legt den Wert der idc-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdc(String value) {
        this.idc = value;
    }

    /**
     * Ruft den Wert der idcType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdcType() {
        return idcType;
    }

    /**
     * Legt den Wert der idcType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdcType(String value) {
        this.idcType = value;
    }

    /**
     * Ruft den Wert der intIdc-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntIdc() {
        return intIdc;
    }

    /**
     * Legt den Wert der intIdc-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntIdc(String value) {
        this.intIdc = value;
    }

    /**
     * Ruft den Wert der intIdcType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntIdcType() {
        return intIdcType;
    }

    /**
     * Legt den Wert der intIdcType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntIdcType(String value) {
        this.intIdcType = value;
    }

}
