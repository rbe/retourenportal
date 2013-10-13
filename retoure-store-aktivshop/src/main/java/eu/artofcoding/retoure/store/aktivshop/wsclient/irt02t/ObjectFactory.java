
package eu.artofcoding.retoure.store.aktivshop.wsclient.irt02t;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the eu.artofcoding.retoure.store.aktivshop.wsclient.irt02t package. 
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

    private final static QName _Irt02Args0_QNAME = new QName("http://irt02t.wsbeans.iseries/xsd", "args0");
    private final static QName _IRT02InputRGNR_QNAME = new QName("http://irt02t.wsbeans.iseries/xsd", "_RGNR");
    private final static QName _IRT02InputAORT_QNAME = new QName("http://irt02t.wsbeans.iseries/xsd", "_AORT");
    private final static QName _IRT02InputASTR_QNAME = new QName("http://irt02t.wsbeans.iseries/xsd", "_ASTR");
    private final static QName _IRT02InputANAME_QNAME = new QName("http://irt02t.wsbeans.iseries/xsd", "_ANAME");
    private final static QName _IRT02InputALKZ_QNAME = new QName("http://irt02t.wsbeans.iseries/xsd", "_ALKZ");
    private final static QName _IRT02InputFNR_QNAME = new QName("http://irt02t.wsbeans.iseries/xsd", "_FNR");
    private final static QName _IRT02InputSBNR_QNAME = new QName("http://irt02t.wsbeans.iseries/xsd", "_SBNR");
    private final static QName _IRT02InputANAM4_QNAME = new QName("http://irt02t.wsbeans.iseries/xsd", "_ANAM4");
    private final static QName _IRT02InputAANRE_QNAME = new QName("http://irt02t.wsbeans.iseries/xsd", "_AANRE");
    private final static QName _IRT02InputANAM2_QNAME = new QName("http://irt02t.wsbeans.iseries/xsd", "_ANAM2");
    private final static QName _IRT02InputANAM3_QNAME = new QName("http://irt02t.wsbeans.iseries/xsd", "_ANAM3");
    private final static QName _IRT02InputAPLZ_QNAME = new QName("http://irt02t.wsbeans.iseries/xsd", "_APLZ");
    private final static QName _IRT02InputKZRTER_QNAME = new QName("http://irt02t.wsbeans.iseries/xsd", "_KZRTER");
    private final static QName _IRT02InputVATX_QNAME = new QName("http://irt02t.wsbeans.iseries/xsd", "_VATX");
    private final static QName _IRT02InputKZOP_QNAME = new QName("http://irt02t.wsbeans.iseries/xsd", "_KZOP");
    private final static QName _Irt02ResponseReturn_QNAME = new QName("http://irt02t.wsbeans.iseries/xsd", "return");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: eu.artofcoding.retoure.store.aktivshop.wsclient.irt02t
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Irt02 }
     * 
     */
    public Irt02 createIrt02() {
        return new Irt02();
    }

    /**
     * Create an instance of {@link IRT02Input }
     * 
     */
    public IRT02Input createIRT02Input() {
        return new IRT02Input();
    }

    /**
     * Create an instance of {@link Irt02XMLResponse }
     * 
     */
    public Irt02XMLResponse createIrt02XMLResponse() {
        return new Irt02XMLResponse();
    }

    /**
     * Create an instance of {@link Irt02XML }
     * 
     */
    public Irt02XML createIrt02XML() {
        return new Irt02XML();
    }

    /**
     * Create an instance of {@link Irt02Response }
     * 
     */
    public Irt02Response createIrt02Response() {
        return new Irt02Response();
    }

    /**
     * Create an instance of {@link IRT02Result }
     * 
     */
    public IRT02Result createIRT02Result() {
        return new IRT02Result();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IRT02Input }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "args0", scope = Irt02 .class)
    public JAXBElement<IRT02Input> createIrt02Args0(IRT02Input value) {
        return new JAXBElement<IRT02Input>(_Irt02Args0_QNAME, IRT02Input.class, Irt02 .class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_RGNR", scope = IRT02Input.class)
    public JAXBElement<BigDecimal> createIRT02InputRGNR(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_IRT02InputRGNR_QNAME, BigDecimal.class, IRT02Input.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_AORT", scope = IRT02Input.class)
    public JAXBElement<String> createIRT02InputAORT(String value) {
        return new JAXBElement<String>(_IRT02InputAORT_QNAME, String.class, IRT02Input.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_ASTR", scope = IRT02Input.class)
    public JAXBElement<String> createIRT02InputASTR(String value) {
        return new JAXBElement<String>(_IRT02InputASTR_QNAME, String.class, IRT02Input.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_ANAME", scope = IRT02Input.class)
    public JAXBElement<String> createIRT02InputANAME(String value) {
        return new JAXBElement<String>(_IRT02InputANAME_QNAME, String.class, IRT02Input.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_ALKZ", scope = IRT02Input.class)
    public JAXBElement<String> createIRT02InputALKZ(String value) {
        return new JAXBElement<String>(_IRT02InputALKZ_QNAME, String.class, IRT02Input.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_FNR", scope = IRT02Input.class)
    public JAXBElement<String> createIRT02InputFNR(String value) {
        return new JAXBElement<String>(_IRT02InputFNR_QNAME, String.class, IRT02Input.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_SBNR", scope = IRT02Input.class)
    public JAXBElement<BigDecimal> createIRT02InputSBNR(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_IRT02InputSBNR_QNAME, BigDecimal.class, IRT02Input.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_ANAM4", scope = IRT02Input.class)
    public JAXBElement<String> createIRT02InputANAM4(String value) {
        return new JAXBElement<String>(_IRT02InputANAM4_QNAME, String.class, IRT02Input.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_AANRE", scope = IRT02Input.class)
    public JAXBElement<String> createIRT02InputAANRE(String value) {
        return new JAXBElement<String>(_IRT02InputAANRE_QNAME, String.class, IRT02Input.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_ANAM2", scope = IRT02Input.class)
    public JAXBElement<String> createIRT02InputANAM2(String value) {
        return new JAXBElement<String>(_IRT02InputANAM2_QNAME, String.class, IRT02Input.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_ANAM3", scope = IRT02Input.class)
    public JAXBElement<String> createIRT02InputANAM3(String value) {
        return new JAXBElement<String>(_IRT02InputANAM3_QNAME, String.class, IRT02Input.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_APLZ", scope = IRT02Input.class)
    public JAXBElement<String> createIRT02InputAPLZ(String value) {
        return new JAXBElement<String>(_IRT02InputAPLZ_QNAME, String.class, IRT02Input.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_KZRTER", scope = IRT02Input.class)
    public JAXBElement<BigDecimal> createIRT02InputKZRTER(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_IRT02InputKZRTER_QNAME, BigDecimal.class, IRT02Input.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_VATX", scope = IRT02Input.class)
    public JAXBElement<String> createIRT02InputVATX(String value) {
        return new JAXBElement<String>(_IRT02InputVATX_QNAME, String.class, IRT02Input.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_KZOP", scope = IRT02Input.class)
    public JAXBElement<BigDecimal> createIRT02InputKZOP(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_IRT02InputKZOP_QNAME, BigDecimal.class, IRT02Input.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IRT02Result }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "return", scope = Irt02Response.class)
    public JAXBElement<IRT02Result> createIrt02ResponseReturn(IRT02Result value) {
        return new JAXBElement<IRT02Result>(_Irt02ResponseReturn_QNAME, IRT02Result.class, Irt02Response.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IRT02Input }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "args0", scope = Irt02XML.class)
    public JAXBElement<IRT02Input> createIrt02XMLArgs0(IRT02Input value) {
        return new JAXBElement<IRT02Input>(_Irt02Args0_QNAME, IRT02Input.class, Irt02XML.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "return", scope = Irt02XMLResponse.class)
    public JAXBElement<String> createIrt02XMLResponseReturn(String value) {
        return new JAXBElement<String>(_Irt02ResponseReturn_QNAME, String.class, Irt02XMLResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_RGNR", scope = IRT02Result.class)
    public JAXBElement<BigDecimal> createIRT02ResultRGNR(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_IRT02InputRGNR_QNAME, BigDecimal.class, IRT02Result.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_AORT", scope = IRT02Result.class)
    public JAXBElement<String> createIRT02ResultAORT(String value) {
        return new JAXBElement<String>(_IRT02InputAORT_QNAME, String.class, IRT02Result.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_ASTR", scope = IRT02Result.class)
    public JAXBElement<String> createIRT02ResultASTR(String value) {
        return new JAXBElement<String>(_IRT02InputASTR_QNAME, String.class, IRT02Result.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_ANAME", scope = IRT02Result.class)
    public JAXBElement<String> createIRT02ResultANAME(String value) {
        return new JAXBElement<String>(_IRT02InputANAME_QNAME, String.class, IRT02Result.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_ALKZ", scope = IRT02Result.class)
    public JAXBElement<String> createIRT02ResultALKZ(String value) {
        return new JAXBElement<String>(_IRT02InputALKZ_QNAME, String.class, IRT02Result.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_FNR", scope = IRT02Result.class)
    public JAXBElement<String> createIRT02ResultFNR(String value) {
        return new JAXBElement<String>(_IRT02InputFNR_QNAME, String.class, IRT02Result.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_SBNR", scope = IRT02Result.class)
    public JAXBElement<BigDecimal> createIRT02ResultSBNR(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_IRT02InputSBNR_QNAME, BigDecimal.class, IRT02Result.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_ANAM4", scope = IRT02Result.class)
    public JAXBElement<String> createIRT02ResultANAM4(String value) {
        return new JAXBElement<String>(_IRT02InputANAM4_QNAME, String.class, IRT02Result.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_AANRE", scope = IRT02Result.class)
    public JAXBElement<String> createIRT02ResultAANRE(String value) {
        return new JAXBElement<String>(_IRT02InputAANRE_QNAME, String.class, IRT02Result.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_ANAM2", scope = IRT02Result.class)
    public JAXBElement<String> createIRT02ResultANAM2(String value) {
        return new JAXBElement<String>(_IRT02InputANAM2_QNAME, String.class, IRT02Result.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_ANAM3", scope = IRT02Result.class)
    public JAXBElement<String> createIRT02ResultANAM3(String value) {
        return new JAXBElement<String>(_IRT02InputANAM3_QNAME, String.class, IRT02Result.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_APLZ", scope = IRT02Result.class)
    public JAXBElement<String> createIRT02ResultAPLZ(String value) {
        return new JAXBElement<String>(_IRT02InputAPLZ_QNAME, String.class, IRT02Result.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_KZRTER", scope = IRT02Result.class)
    public JAXBElement<BigDecimal> createIRT02ResultKZRTER(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_IRT02InputKZRTER_QNAME, BigDecimal.class, IRT02Result.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_VATX", scope = IRT02Result.class)
    public JAXBElement<String> createIRT02ResultVATX(String value) {
        return new JAXBElement<String>(_IRT02InputVATX_QNAME, String.class, IRT02Result.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://irt02t.wsbeans.iseries/xsd", name = "_KZOP", scope = IRT02Result.class)
    public JAXBElement<BigDecimal> createIRT02ResultKZOP(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_IRT02InputKZOP_QNAME, BigDecimal.class, IRT02Result.class, value);
    }

}
