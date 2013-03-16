
package eu.artofcoding.retoure.store.aktivshop.wsclient.irt01t;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für IRT01Input complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="IRT01Input">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_ERG" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="_ERGT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="_FNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="_RGNR" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="_SBNR" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IRT01Input", propOrder = {
    "erg",
    "ergt",
    "fnr",
    "id",
    "rgnr",
    "sbnr"
})
public class IRT01Input {

    @XmlElementRef(name = "_ERG", namespace = "http://irt01t.wsbeans.iseries/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> erg;
    @XmlElementRef(name = "_ERGT", namespace = "http://irt01t.wsbeans.iseries/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> ergt;
    @XmlElementRef(name = "_FNR", namespace = "http://irt01t.wsbeans.iseries/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> fnr;
    @XmlElementRef(name = "_ID", namespace = "http://irt01t.wsbeans.iseries/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> id;
    @XmlElementRef(name = "_RGNR", namespace = "http://irt01t.wsbeans.iseries/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> rgnr;
    @XmlElementRef(name = "_SBNR", namespace = "http://irt01t.wsbeans.iseries/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> sbnr;

    /**
     * Ruft den Wert der erg-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getERG() {
        return erg;
    }

    /**
     * Legt den Wert der erg-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setERG(JAXBElement<BigDecimal> value) {
        this.erg = value;
    }

    /**
     * Ruft den Wert der ergt-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getERGT() {
        return ergt;
    }

    /**
     * Legt den Wert der ergt-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setERGT(JAXBElement<String> value) {
        this.ergt = value;
    }

    /**
     * Ruft den Wert der fnr-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFNR() {
        return fnr;
    }

    /**
     * Legt den Wert der fnr-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFNR(JAXBElement<String> value) {
        this.fnr = value;
    }

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getID() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setID(JAXBElement<String> value) {
        this.id = value;
    }

    /**
     * Ruft den Wert der rgnr-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getRGNR() {
        return rgnr;
    }

    /**
     * Legt den Wert der rgnr-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setRGNR(JAXBElement<BigDecimal> value) {
        this.rgnr = value;
    }

    /**
     * Ruft den Wert der sbnr-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getSBNR() {
        return sbnr;
    }

    /**
     * Legt den Wert der sbnr-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setSBNR(JAXBElement<BigDecimal> value) {
        this.sbnr = value;
    }

}
