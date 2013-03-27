
package eu.artofcoding.retoure.store.aktivshop.wsclient.irt01t;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the eu.artofcoding.retoure.store.aktivshop.wsclient.irt01t package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Irt01XMLResponseReturn_QNAME = new QName("http://irt01t.wsbeans.iseries/xsd", "return");
    private final static QName _Irt01XMLArgs0_QNAME = new QName("http://irt01t.wsbeans.iseries/xsd", "args0");
    private final static QName _IRT01InputSBNR_QNAME = new QName("http://irt01t.wsbeans.iseries/xsd", "_SBNR");
    private final static QName _IRT01InputFNR_QNAME = new QName("http://irt01t.wsbeans.iseries/xsd", "_FNR");
    private final static QName _IRT01InputRGNR_QNAME = new QName("http://irt01t.wsbeans.iseries/xsd", "_RGNR");
    private final static QName _IRT01InputERGT_QNAME = new QName("http://irt01t.wsbeans.iseries/xsd", "_ERGT");
    private final static QName _IRT01InputID_QNAME = new QName("http://irt01t.wsbeans.iseries/xsd", "_ID");
    private final static QName _IRT01InputERG_QNAME = new QName("http://irt01t.wsbeans.iseries/xsd", "_ERG");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: eu.artofcoding.retoure.store.aktivshop.wsclient.irt01t
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Irt01XML }
     * 
     */
    public Irt01XML createIrt01XML() {
        return new Irt01XML();
    }

    /**
     * Create an instance of {@link IRT01Input }
     * 
     */
    public IRT01Input createIRT01Input() {
        return new IRT01Input();
    }

    /**
     * Create an instance of {@link Irt01Response }
     * 
     */
    public Irt01Response createIrt01Response() {
        return new Irt01Response();
    }

    /**
     * Create an instance of {@link IRT01Result }
     * 
     */
    public IRT01Result createIRT01Result() {
        return new IRT01Result();
    }

    /**
     * Create an instance of {@link Irt01 }
     * 
     */
    public Irt01 createIrt01() {
        return new Irt01();
    }

    /**
     * Create an instance of {@link Irt01XMLResponse }
     * 
     */
    public Irt01XMLResponse createIrt01XMLResponse() {
        return new Irt01XMLResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt01t.wsbeans.iseries/xsd", name = "return", scope = Irt01XMLResponse.class)
    public JAXBElement<String> createIrt01XMLResponseReturn(String value) {
        return new JAXBElement<String>(_Irt01XMLResponseReturn_QNAME, String.class, Irt01XMLResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IRT01Input }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt01t.wsbeans.iseries/xsd", name = "args0", scope = Irt01XML.class)
    public JAXBElement<IRT01Input> createIrt01XMLArgs0(IRT01Input value) {
        return new JAXBElement<IRT01Input>(_Irt01XMLArgs0_QNAME, IRT01Input.class, Irt01XML.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IRT01Input }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt01t.wsbeans.iseries/xsd", name = "args0", scope = Irt01 .class)
    public JAXBElement<IRT01Input> createIrt01Args0(IRT01Input value) {
        return new JAXBElement<IRT01Input>(_Irt01XMLArgs0_QNAME, IRT01Input.class, Irt01 .class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IRT01Result }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt01t.wsbeans.iseries/xsd", name = "return", scope = Irt01Response.class)
    public JAXBElement<IRT01Result> createIrt01ResponseReturn(IRT01Result value) {
        return new JAXBElement<IRT01Result>(_Irt01XMLResponseReturn_QNAME, IRT01Result.class, Irt01Response.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt01t.wsbeans.iseries/xsd", name = "_SBNR", scope = IRT01Input.class)
    public JAXBElement<BigDecimal> createIRT01InputSBNR(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_IRT01InputSBNR_QNAME, BigDecimal.class, IRT01Input.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt01t.wsbeans.iseries/xsd", name = "_FNR", scope = IRT01Input.class)
    public JAXBElement<String> createIRT01InputFNR(String value) {
        return new JAXBElement<String>(_IRT01InputFNR_QNAME, String.class, IRT01Input.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt01t.wsbeans.iseries/xsd", name = "_RGNR", scope = IRT01Input.class)
    public JAXBElement<BigDecimal> createIRT01InputRGNR(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_IRT01InputRGNR_QNAME, BigDecimal.class, IRT01Input.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt01t.wsbeans.iseries/xsd", name = "_ERGT", scope = IRT01Input.class)
    public JAXBElement<String> createIRT01InputERGT(String value) {
        return new JAXBElement<String>(_IRT01InputERGT_QNAME, String.class, IRT01Input.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt01t.wsbeans.iseries/xsd", name = "_ID", scope = IRT01Input.class)
    public JAXBElement<String> createIRT01InputID(String value) {
        return new JAXBElement<String>(_IRT01InputID_QNAME, String.class, IRT01Input.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt01t.wsbeans.iseries/xsd", name = "_ERG", scope = IRT01Input.class)
    public JAXBElement<BigDecimal> createIRT01InputERG(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_IRT01InputERG_QNAME, BigDecimal.class, IRT01Input.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt01t.wsbeans.iseries/xsd", name = "_SBNR", scope = IRT01Result.class)
    public JAXBElement<BigDecimal> createIRT01ResultSBNR(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_IRT01InputSBNR_QNAME, BigDecimal.class, IRT01Result.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt01t.wsbeans.iseries/xsd", name = "_FNR", scope = IRT01Result.class)
    public JAXBElement<String> createIRT01ResultFNR(String value) {
        return new JAXBElement<String>(_IRT01InputFNR_QNAME, String.class, IRT01Result.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt01t.wsbeans.iseries/xsd", name = "_RGNR", scope = IRT01Result.class)
    public JAXBElement<BigDecimal> createIRT01ResultRGNR(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_IRT01InputRGNR_QNAME, BigDecimal.class, IRT01Result.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt01t.wsbeans.iseries/xsd", name = "_ERGT", scope = IRT01Result.class)
    public JAXBElement<String> createIRT01ResultERGT(String value) {
        return new JAXBElement<String>(_IRT01InputERGT_QNAME, String.class, IRT01Result.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt01t.wsbeans.iseries/xsd", name = "_ID", scope = IRT01Result.class)
    public JAXBElement<String> createIRT01ResultID(String value) {
        return new JAXBElement<String>(_IRT01InputID_QNAME, String.class, IRT01Result.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt01t.wsbeans.iseries/xsd", name = "_ERG", scope = IRT01Result.class)
    public JAXBElement<BigDecimal> createIRT01ResultERG(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_IRT01InputERG_QNAME, BigDecimal.class, IRT01Result.class, value);
    }

}
