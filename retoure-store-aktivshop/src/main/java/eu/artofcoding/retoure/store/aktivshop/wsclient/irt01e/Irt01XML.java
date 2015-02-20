
package eu.artofcoding.retoure.store.aktivshop.wsclient.irt01e;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="args0" type="{http://irt01e.wsbeans.iseries/xsd}IRT01Input" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "args0"
})
@XmlRootElement(name = "irt01_XML")
public class Irt01XML {

    @XmlElementRef(name = "args0", namespace = "http://irt01e.wsbeans.iseries/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<IRT01Input> args0;

    /**
     * Ruft den Wert der args0-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link IRT01Input }{@code >}
     *     
     */
    public JAXBElement<IRT01Input> getArgs0() {
        return args0;
    }

    /**
     * Legt den Wert der args0-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link IRT01Input }{@code >}
     *     
     */
    public void setArgs0(JAXBElement<IRT01Input> value) {
        this.args0 = value;
    }

}
