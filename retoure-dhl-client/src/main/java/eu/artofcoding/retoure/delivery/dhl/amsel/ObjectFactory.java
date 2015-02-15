
package eu.artofcoding.retoure.delivery.dhl.amsel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the eu.artofcoding.retoure.delivery.dhl.amsel package. 
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

    private final static QName _BookLabelRequest_QNAME = new QName("https://amsel.dpwn.net/abholportal/gw/lp/schema/1.0/var3bl", "BookLabelRequest");
    private final static QName _BookLabelResponse_QNAME = new QName("https://amsel.dpwn.net/abholportal/gw/lp/schema/1.0/var3bl", "BookLabelResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: eu.artofcoding.retoure.delivery.dhl.amsel
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BookLabelRequestType }
     * 
     */
    public BookLabelRequestType createBookLabelRequestType() {
        return new BookLabelRequestType();
    }

    /**
     * Create an instance of {@link BookLabelResponseType }
     * 
     */
    public BookLabelResponseType createBookLabelResponseType() {
        return new BookLabelResponseType();
    }

    /**
     * Create an instance of {@link WsRequestType }
     * 
     */
    public WsRequestType createWsRequestType() {
        return new WsRequestType();
    }

    /**
     * Create an instance of {@link WsResponseType }
     * 
     */
    public WsResponseType createWsResponseType() {
        return new WsResponseType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookLabelRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://amsel.dpwn.net/abholportal/gw/lp/schema/1.0/var3bl", name = "BookLabelRequest")
    public JAXBElement<BookLabelRequestType> createBookLabelRequest(BookLabelRequestType value) {
        return new JAXBElement<BookLabelRequestType>(_BookLabelRequest_QNAME, BookLabelRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookLabelResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://amsel.dpwn.net/abholportal/gw/lp/schema/1.0/var3bl", name = "BookLabelResponse")
    public JAXBElement<BookLabelResponseType> createBookLabelResponse(BookLabelResponseType value) {
        return new JAXBElement<BookLabelResponseType>(_BookLabelResponse_QNAME, BookLabelResponseType.class, null, value);
    }

}
