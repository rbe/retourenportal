
package eu.artofcoding.retoure.delivery.dhl.amsel;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6b21 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "RpPartnerService", targetNamespace = "https://amsel.dpwn.net/abholportal/gw/lp/schema/1.0/var3ws", wsdlLocation = "file:/Users/rbe/project/aktivshop.de/retoure-server/retoure-dhl-client/src/main/resources/wsdl/var3ws.wsdl")
public class RpPartnerService
    extends Service
{

    private final static URL RPPARTNERSERVICE_WSDL_LOCATION;
    private final static WebServiceException RPPARTNERSERVICE_EXCEPTION;
    private final static QName RPPARTNERSERVICE_QNAME = new QName("https://amsel.dpwn.net/abholportal/gw/lp/schema/1.0/var3ws", "RpPartnerService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/Users/rbe/project/aktivshop.de/retoure-server/retoure-dhl-client/src/main/resources/wsdl/var3ws.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        RPPARTNERSERVICE_WSDL_LOCATION = url;
        RPPARTNERSERVICE_EXCEPTION = e;
    }

    public RpPartnerService() {
        super(__getWsdlLocation(), RPPARTNERSERVICE_QNAME);
    }

    public RpPartnerService(WebServiceFeature... features) {
        super(__getWsdlLocation(), RPPARTNERSERVICE_QNAME, features);
    }

    public RpPartnerService(URL wsdlLocation) {
        super(wsdlLocation, RPPARTNERSERVICE_QNAME);
    }

    public RpPartnerService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, RPPARTNERSERVICE_QNAME, features);
    }

    public RpPartnerService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RpPartnerService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns RpPartnerType
     */
    @WebEndpoint(name = "RpPartnerPort")
    public RpPartnerType getRpPartnerPort() {
        return super.getPort(new QName("https://amsel.dpwn.net/abholportal/gw/lp/schema/1.0/var3ws", "RpPartnerPort"), RpPartnerType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns RpPartnerType
     */
    @WebEndpoint(name = "RpPartnerPort")
    public RpPartnerType getRpPartnerPort(WebServiceFeature... features) {
        return super.getPort(new QName("https://amsel.dpwn.net/abholportal/gw/lp/schema/1.0/var3ws", "RpPartnerPort"), RpPartnerType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (RPPARTNERSERVICE_EXCEPTION!= null) {
            throw RPPARTNERSERVICE_EXCEPTION;
        }
        return RPPARTNERSERVICE_WSDL_LOCATION;
    }

}
