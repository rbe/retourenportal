
package eu.artofcoding.retoure.store.aktivshop.wsclient.irt02e;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für IRT02Result complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="IRT02Result">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_ALKZ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="_ANAM2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="_ANAM3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="_ANAM4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="_ANAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="_AORT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="_APLZ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="_ASTR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="_FARG" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="_FARH" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="_FARN" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="_FBE1" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="_FBE2" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="_FMNG" type="{http://www.w3.org/2001/XMLSchema}decimal" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="_FNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="_KZOP" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="_KZRTER" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="_RGNR" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="_SBNR" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="_VATX" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IRT02Result", propOrder = {
    "alkz",
    "anam2",
    "anam3",
    "anam4",
    "aname",
    "aort",
    "aplz",
    "astr",
    "farg",
    "farh",
    "farn",
    "fbe1",
    "fbe2",
    "fmng",
    "fnr",
    "kzop",
    "kzrter",
    "rgnr",
    "sbnr",
    "vatx"
})
public class IRT02Result {

    @XmlElementRef(name = "_ALKZ", namespace = "http://irt02e.wsbeans.iseries/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> alkz;
    @XmlElementRef(name = "_ANAM2", namespace = "http://irt02e.wsbeans.iseries/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> anam2;
    @XmlElementRef(name = "_ANAM3", namespace = "http://irt02e.wsbeans.iseries/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> anam3;
    @XmlElementRef(name = "_ANAM4", namespace = "http://irt02e.wsbeans.iseries/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> anam4;
    @XmlElementRef(name = "_ANAME", namespace = "http://irt02e.wsbeans.iseries/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> aname;
    @XmlElementRef(name = "_AORT", namespace = "http://irt02e.wsbeans.iseries/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> aort;
    @XmlElementRef(name = "_APLZ", namespace = "http://irt02e.wsbeans.iseries/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> aplz;
    @XmlElementRef(name = "_ASTR", namespace = "http://irt02e.wsbeans.iseries/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> astr;
    @XmlElement(name = "_FARG", nillable = true)
    protected List<String> farg;
    @XmlElement(name = "_FARH", nillable = true)
    protected List<String> farh;
    @XmlElement(name = "_FARN", nillable = true)
    protected List<String> farn;
    @XmlElement(name = "_FBE1", nillable = true)
    protected List<String> fbe1;
    @XmlElement(name = "_FBE2", nillable = true)
    protected List<String> fbe2;
    @XmlElement(name = "_FMNG", nillable = true)
    protected List<BigDecimal> fmng;
    @XmlElementRef(name = "_FNR", namespace = "http://irt02e.wsbeans.iseries/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> fnr;
    @XmlElementRef(name = "_KZOP", namespace = "http://irt02e.wsbeans.iseries/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> kzop;
    @XmlElementRef(name = "_KZRTER", namespace = "http://irt02e.wsbeans.iseries/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> kzrter;
    @XmlElementRef(name = "_RGNR", namespace = "http://irt02e.wsbeans.iseries/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> rgnr;
    @XmlElementRef(name = "_SBNR", namespace = "http://irt02e.wsbeans.iseries/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> sbnr;
    @XmlElementRef(name = "_VATX", namespace = "http://irt02e.wsbeans.iseries/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> vatx;

    /**
     * Ruft den Wert der alkz-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getALKZ() {
        return alkz;
    }

    /**
     * Legt den Wert der alkz-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setALKZ(JAXBElement<String> value) {
        this.alkz = value;
    }

    /**
     * Ruft den Wert der anam2-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getANAM2() {
        return anam2;
    }

    /**
     * Legt den Wert der anam2-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setANAM2(JAXBElement<String> value) {
        this.anam2 = value;
    }

    /**
     * Ruft den Wert der anam3-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getANAM3() {
        return anam3;
    }

    /**
     * Legt den Wert der anam3-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setANAM3(JAXBElement<String> value) {
        this.anam3 = value;
    }

    /**
     * Ruft den Wert der anam4-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getANAM4() {
        return anam4;
    }

    /**
     * Legt den Wert der anam4-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setANAM4(JAXBElement<String> value) {
        this.anam4 = value;
    }

    /**
     * Ruft den Wert der aname-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getANAME() {
        return aname;
    }

    /**
     * Legt den Wert der aname-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setANAME(JAXBElement<String> value) {
        this.aname = value;
    }

    /**
     * Ruft den Wert der aort-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAORT() {
        return aort;
    }

    /**
     * Legt den Wert der aort-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAORT(JAXBElement<String> value) {
        this.aort = value;
    }

    /**
     * Ruft den Wert der aplz-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAPLZ() {
        return aplz;
    }

    /**
     * Legt den Wert der aplz-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAPLZ(JAXBElement<String> value) {
        this.aplz = value;
    }

    /**
     * Ruft den Wert der astr-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getASTR() {
        return astr;
    }

    /**
     * Legt den Wert der astr-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setASTR(JAXBElement<String> value) {
        this.astr = value;
    }

    /**
     * Gets the value of the farg property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the farg property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFARG().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFARG() {
        if (farg == null) {
            farg = new ArrayList<String>();
        }
        return this.farg;
    }

    /**
     * Gets the value of the farh property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the farh property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFARH().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFARH() {
        if (farh == null) {
            farh = new ArrayList<String>();
        }
        return this.farh;
    }

    /**
     * Gets the value of the farn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the farn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFARN().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFARN() {
        if (farn == null) {
            farn = new ArrayList<String>();
        }
        return this.farn;
    }

    /**
     * Gets the value of the fbe1 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fbe1 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFBE1().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFBE1() {
        if (fbe1 == null) {
            fbe1 = new ArrayList<String>();
        }
        return this.fbe1;
    }

    /**
     * Gets the value of the fbe2 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fbe2 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFBE2().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFBE2() {
        if (fbe2 == null) {
            fbe2 = new ArrayList<String>();
        }
        return this.fbe2;
    }

    /**
     * Gets the value of the fmng property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fmng property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFMNG().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigDecimal }
     * 
     * 
     */
    public List<BigDecimal> getFMNG() {
        if (fmng == null) {
            fmng = new ArrayList<BigDecimal>();
        }
        return this.fmng;
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
     * Ruft den Wert der kzop-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getKZOP() {
        return kzop;
    }

    /**
     * Legt den Wert der kzop-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setKZOP(JAXBElement<BigDecimal> value) {
        this.kzop = value;
    }

    /**
     * Ruft den Wert der kzrter-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getKZRTER() {
        return kzrter;
    }

    /**
     * Legt den Wert der kzrter-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setKZRTER(JAXBElement<BigDecimal> value) {
        this.kzrter = value;
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

    /**
     * Ruft den Wert der vatx-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getVATX() {
        return vatx;
    }

    /**
     * Legt den Wert der vatx-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setVATX(JAXBElement<String> value) {
        this.vatx = value;
    }

}
