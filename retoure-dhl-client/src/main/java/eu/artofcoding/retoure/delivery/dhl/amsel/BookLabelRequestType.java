
package eu.artofcoding.retoure.delivery.dhl.amsel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Books a Label.
 * 
 * <p>Java-Klasse für BookLabelRequestType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="BookLabelRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{https://amsel.dpwn.net/abholportal/gw/lp/schema/1.0/var3bl}WsRequestType">
 *       &lt;attribute name="portalId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="deliveryName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="customerReference">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="40"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="shipmentReference">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="labelFormat">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="10"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="senderName1">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="senderName2">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="senderCareOfName">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="senderContactPhone">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="20"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="senderContactEmail">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="senderStreet">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="senderStreetNumber">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="7"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="senderHouseName">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="255"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="senderBoxNumber">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="8"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="senderPostalCode">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="8"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="senderCity">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="senderDistrict">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="senderCounty">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="255"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="itemWeight">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="10"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="itemWorth">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="50"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="custom1">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="255"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="custom2">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="255"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="custom3">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="255"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="custom4">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="255"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="custom5">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="255"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="dutyContent1">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="255"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="dutyAmount1">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="255"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="dutyWeight1">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="255"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="dutyWorth1">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="255"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="dutyCurrency1">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="EUR"/>
 *             &lt;enumeration value="SFR"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="dutyContent2">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="255"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="dutyAmount2">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="255"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="dutyWeight2">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="255"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="dutyWorth2">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="255"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="dutyCurrency2">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="EUR"/>
 *             &lt;enumeration value="SFR"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="dutyContent3">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="255"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="dutyAmount3">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="255"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="dutyWeight3">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="255"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="dutyWorth3">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="255"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="dutyCurrency3">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="EUR"/>
 *             &lt;enumeration value="SFR"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="dutyContent4">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="255"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="dutyAmount4">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="255"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="dutyWeight4">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="255"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="dutyWorth4">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="255"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="dutyCurrency4">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="EUR"/>
 *             &lt;enumeration value="SFR"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BookLabelRequestType")
public class BookLabelRequestType
    extends WsRequestType
{

    @XmlAttribute(name = "portalId", required = true)
    protected String portalId;
    @XmlAttribute(name = "deliveryName", required = true)
    protected String deliveryName;
    @XmlAttribute(name = "customerReference")
    protected String customerReference;
    @XmlAttribute(name = "shipmentReference")
    protected String shipmentReference;
    @XmlAttribute(name = "labelFormat")
    protected String labelFormat;
    @XmlAttribute(name = "senderName1")
    protected String senderName1;
    @XmlAttribute(name = "senderName2")
    protected String senderName2;
    @XmlAttribute(name = "senderCareOfName")
    protected String senderCareOfName;
    @XmlAttribute(name = "senderContactPhone")
    protected String senderContactPhone;
    @XmlAttribute(name = "senderContactEmail")
    protected String senderContactEmail;
    @XmlAttribute(name = "senderStreet")
    protected String senderStreet;
    @XmlAttribute(name = "senderStreetNumber")
    protected String senderStreetNumber;
    @XmlAttribute(name = "senderHouseName")
    protected String senderHouseName;
    @XmlAttribute(name = "senderBoxNumber")
    protected String senderBoxNumber;
    @XmlAttribute(name = "senderPostalCode")
    protected String senderPostalCode;
    @XmlAttribute(name = "senderCity")
    protected String senderCity;
    @XmlAttribute(name = "senderDistrict")
    protected String senderDistrict;
    @XmlAttribute(name = "senderCounty")
    protected String senderCounty;
    @XmlAttribute(name = "itemWeight")
    protected String itemWeight;
    @XmlAttribute(name = "itemWorth")
    protected String itemWorth;
    @XmlAttribute(name = "custom1")
    protected String custom1;
    @XmlAttribute(name = "custom2")
    protected String custom2;
    @XmlAttribute(name = "custom3")
    protected String custom3;
    @XmlAttribute(name = "custom4")
    protected String custom4;
    @XmlAttribute(name = "custom5")
    protected String custom5;
    @XmlAttribute(name = "dutyContent1")
    protected String dutyContent1;
    @XmlAttribute(name = "dutyAmount1")
    protected String dutyAmount1;
    @XmlAttribute(name = "dutyWeight1")
    protected String dutyWeight1;
    @XmlAttribute(name = "dutyWorth1")
    protected String dutyWorth1;
    @XmlAttribute(name = "dutyCurrency1")
    protected String dutyCurrency1;
    @XmlAttribute(name = "dutyContent2")
    protected String dutyContent2;
    @XmlAttribute(name = "dutyAmount2")
    protected String dutyAmount2;
    @XmlAttribute(name = "dutyWeight2")
    protected String dutyWeight2;
    @XmlAttribute(name = "dutyWorth2")
    protected String dutyWorth2;
    @XmlAttribute(name = "dutyCurrency2")
    protected String dutyCurrency2;
    @XmlAttribute(name = "dutyContent3")
    protected String dutyContent3;
    @XmlAttribute(name = "dutyAmount3")
    protected String dutyAmount3;
    @XmlAttribute(name = "dutyWeight3")
    protected String dutyWeight3;
    @XmlAttribute(name = "dutyWorth3")
    protected String dutyWorth3;
    @XmlAttribute(name = "dutyCurrency3")
    protected String dutyCurrency3;
    @XmlAttribute(name = "dutyContent4")
    protected String dutyContent4;
    @XmlAttribute(name = "dutyAmount4")
    protected String dutyAmount4;
    @XmlAttribute(name = "dutyWeight4")
    protected String dutyWeight4;
    @XmlAttribute(name = "dutyWorth4")
    protected String dutyWorth4;
    @XmlAttribute(name = "dutyCurrency4")
    protected String dutyCurrency4;

    /**
     * Ruft den Wert der portalId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortalId() {
        return portalId;
    }

    /**
     * Legt den Wert der portalId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortalId(String value) {
        this.portalId = value;
    }

    /**
     * Ruft den Wert der deliveryName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryName() {
        return deliveryName;
    }

    /**
     * Legt den Wert der deliveryName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryName(String value) {
        this.deliveryName = value;
    }

    /**
     * Ruft den Wert der customerReference-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerReference() {
        return customerReference;
    }

    /**
     * Legt den Wert der customerReference-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerReference(String value) {
        this.customerReference = value;
    }

    /**
     * Ruft den Wert der shipmentReference-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipmentReference() {
        return shipmentReference;
    }

    /**
     * Legt den Wert der shipmentReference-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipmentReference(String value) {
        this.shipmentReference = value;
    }

    /**
     * Ruft den Wert der labelFormat-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelFormat() {
        return labelFormat;
    }

    /**
     * Legt den Wert der labelFormat-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelFormat(String value) {
        this.labelFormat = value;
    }

    /**
     * Ruft den Wert der senderName1-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderName1() {
        return senderName1;
    }

    /**
     * Legt den Wert der senderName1-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderName1(String value) {
        this.senderName1 = value;
    }

    /**
     * Ruft den Wert der senderName2-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderName2() {
        return senderName2;
    }

    /**
     * Legt den Wert der senderName2-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderName2(String value) {
        this.senderName2 = value;
    }

    /**
     * Ruft den Wert der senderCareOfName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderCareOfName() {
        return senderCareOfName;
    }

    /**
     * Legt den Wert der senderCareOfName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderCareOfName(String value) {
        this.senderCareOfName = value;
    }

    /**
     * Ruft den Wert der senderContactPhone-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderContactPhone() {
        return senderContactPhone;
    }

    /**
     * Legt den Wert der senderContactPhone-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderContactPhone(String value) {
        this.senderContactPhone = value;
    }

    /**
     * Ruft den Wert der senderContactEmail-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderContactEmail() {
        return senderContactEmail;
    }

    /**
     * Legt den Wert der senderContactEmail-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderContactEmail(String value) {
        this.senderContactEmail = value;
    }

    /**
     * Ruft den Wert der senderStreet-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderStreet() {
        return senderStreet;
    }

    /**
     * Legt den Wert der senderStreet-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderStreet(String value) {
        this.senderStreet = value;
    }

    /**
     * Ruft den Wert der senderStreetNumber-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderStreetNumber() {
        return senderStreetNumber;
    }

    /**
     * Legt den Wert der senderStreetNumber-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderStreetNumber(String value) {
        this.senderStreetNumber = value;
    }

    /**
     * Ruft den Wert der senderHouseName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderHouseName() {
        return senderHouseName;
    }

    /**
     * Legt den Wert der senderHouseName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderHouseName(String value) {
        this.senderHouseName = value;
    }

    /**
     * Ruft den Wert der senderBoxNumber-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderBoxNumber() {
        return senderBoxNumber;
    }

    /**
     * Legt den Wert der senderBoxNumber-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderBoxNumber(String value) {
        this.senderBoxNumber = value;
    }

    /**
     * Ruft den Wert der senderPostalCode-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderPostalCode() {
        return senderPostalCode;
    }

    /**
     * Legt den Wert der senderPostalCode-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderPostalCode(String value) {
        this.senderPostalCode = value;
    }

    /**
     * Ruft den Wert der senderCity-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderCity() {
        return senderCity;
    }

    /**
     * Legt den Wert der senderCity-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderCity(String value) {
        this.senderCity = value;
    }

    /**
     * Ruft den Wert der senderDistrict-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderDistrict() {
        return senderDistrict;
    }

    /**
     * Legt den Wert der senderDistrict-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderDistrict(String value) {
        this.senderDistrict = value;
    }

    /**
     * Ruft den Wert der senderCounty-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderCounty() {
        return senderCounty;
    }

    /**
     * Legt den Wert der senderCounty-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderCounty(String value) {
        this.senderCounty = value;
    }

    /**
     * Ruft den Wert der itemWeight-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemWeight() {
        return itemWeight;
    }

    /**
     * Legt den Wert der itemWeight-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemWeight(String value) {
        this.itemWeight = value;
    }

    /**
     * Ruft den Wert der itemWorth-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemWorth() {
        return itemWorth;
    }

    /**
     * Legt den Wert der itemWorth-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemWorth(String value) {
        this.itemWorth = value;
    }

    /**
     * Ruft den Wert der custom1-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustom1() {
        return custom1;
    }

    /**
     * Legt den Wert der custom1-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustom1(String value) {
        this.custom1 = value;
    }

    /**
     * Ruft den Wert der custom2-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustom2() {
        return custom2;
    }

    /**
     * Legt den Wert der custom2-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustom2(String value) {
        this.custom2 = value;
    }

    /**
     * Ruft den Wert der custom3-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustom3() {
        return custom3;
    }

    /**
     * Legt den Wert der custom3-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustom3(String value) {
        this.custom3 = value;
    }

    /**
     * Ruft den Wert der custom4-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustom4() {
        return custom4;
    }

    /**
     * Legt den Wert der custom4-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustom4(String value) {
        this.custom4 = value;
    }

    /**
     * Ruft den Wert der custom5-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustom5() {
        return custom5;
    }

    /**
     * Legt den Wert der custom5-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustom5(String value) {
        this.custom5 = value;
    }

    /**
     * Ruft den Wert der dutyContent1-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyContent1() {
        return dutyContent1;
    }

    /**
     * Legt den Wert der dutyContent1-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyContent1(String value) {
        this.dutyContent1 = value;
    }

    /**
     * Ruft den Wert der dutyAmount1-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyAmount1() {
        return dutyAmount1;
    }

    /**
     * Legt den Wert der dutyAmount1-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyAmount1(String value) {
        this.dutyAmount1 = value;
    }

    /**
     * Ruft den Wert der dutyWeight1-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyWeight1() {
        return dutyWeight1;
    }

    /**
     * Legt den Wert der dutyWeight1-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyWeight1(String value) {
        this.dutyWeight1 = value;
    }

    /**
     * Ruft den Wert der dutyWorth1-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyWorth1() {
        return dutyWorth1;
    }

    /**
     * Legt den Wert der dutyWorth1-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyWorth1(String value) {
        this.dutyWorth1 = value;
    }

    /**
     * Ruft den Wert der dutyCurrency1-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyCurrency1() {
        return dutyCurrency1;
    }

    /**
     * Legt den Wert der dutyCurrency1-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyCurrency1(String value) {
        this.dutyCurrency1 = value;
    }

    /**
     * Ruft den Wert der dutyContent2-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyContent2() {
        return dutyContent2;
    }

    /**
     * Legt den Wert der dutyContent2-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyContent2(String value) {
        this.dutyContent2 = value;
    }

    /**
     * Ruft den Wert der dutyAmount2-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyAmount2() {
        return dutyAmount2;
    }

    /**
     * Legt den Wert der dutyAmount2-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyAmount2(String value) {
        this.dutyAmount2 = value;
    }

    /**
     * Ruft den Wert der dutyWeight2-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyWeight2() {
        return dutyWeight2;
    }

    /**
     * Legt den Wert der dutyWeight2-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyWeight2(String value) {
        this.dutyWeight2 = value;
    }

    /**
     * Ruft den Wert der dutyWorth2-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyWorth2() {
        return dutyWorth2;
    }

    /**
     * Legt den Wert der dutyWorth2-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyWorth2(String value) {
        this.dutyWorth2 = value;
    }

    /**
     * Ruft den Wert der dutyCurrency2-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyCurrency2() {
        return dutyCurrency2;
    }

    /**
     * Legt den Wert der dutyCurrency2-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyCurrency2(String value) {
        this.dutyCurrency2 = value;
    }

    /**
     * Ruft den Wert der dutyContent3-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyContent3() {
        return dutyContent3;
    }

    /**
     * Legt den Wert der dutyContent3-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyContent3(String value) {
        this.dutyContent3 = value;
    }

    /**
     * Ruft den Wert der dutyAmount3-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyAmount3() {
        return dutyAmount3;
    }

    /**
     * Legt den Wert der dutyAmount3-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyAmount3(String value) {
        this.dutyAmount3 = value;
    }

    /**
     * Ruft den Wert der dutyWeight3-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyWeight3() {
        return dutyWeight3;
    }

    /**
     * Legt den Wert der dutyWeight3-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyWeight3(String value) {
        this.dutyWeight3 = value;
    }

    /**
     * Ruft den Wert der dutyWorth3-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyWorth3() {
        return dutyWorth3;
    }

    /**
     * Legt den Wert der dutyWorth3-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyWorth3(String value) {
        this.dutyWorth3 = value;
    }

    /**
     * Ruft den Wert der dutyCurrency3-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyCurrency3() {
        return dutyCurrency3;
    }

    /**
     * Legt den Wert der dutyCurrency3-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyCurrency3(String value) {
        this.dutyCurrency3 = value;
    }

    /**
     * Ruft den Wert der dutyContent4-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyContent4() {
        return dutyContent4;
    }

    /**
     * Legt den Wert der dutyContent4-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyContent4(String value) {
        this.dutyContent4 = value;
    }

    /**
     * Ruft den Wert der dutyAmount4-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyAmount4() {
        return dutyAmount4;
    }

    /**
     * Legt den Wert der dutyAmount4-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyAmount4(String value) {
        this.dutyAmount4 = value;
    }

    /**
     * Ruft den Wert der dutyWeight4-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyWeight4() {
        return dutyWeight4;
    }

    /**
     * Legt den Wert der dutyWeight4-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyWeight4(String value) {
        this.dutyWeight4 = value;
    }

    /**
     * Ruft den Wert der dutyWorth4-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyWorth4() {
        return dutyWorth4;
    }

    /**
     * Legt den Wert der dutyWorth4-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyWorth4(String value) {
        this.dutyWorth4 = value;
    }

    /**
     * Ruft den Wert der dutyCurrency4-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDutyCurrency4() {
        return dutyCurrency4;
    }

    /**
     * Legt den Wert der dutyCurrency4-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDutyCurrency4(String value) {
        this.dutyCurrency4 = value;
    }

}
