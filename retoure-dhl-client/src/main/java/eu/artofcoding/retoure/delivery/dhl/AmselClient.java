/*
 * retoure-server
 * retoure-dhl-client
 * Copyright (C) 2012-2012 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 25.11.12 19:04
 */

package eu.artofcoding.retoure.delivery.dhl;

import eu.artofcoding.beetlejuice.cdm.Customer;
import eu.artofcoding.beetlejuice.cdm.store.ReturnLabel;
import eu.artofcoding.retoure.delivery.dhl.amsel.BookLabelRequestType;
import eu.artofcoding.retoure.delivery.dhl.amsel.BookLabelResponseType;
import eu.artofcoding.retoure.delivery.dhl.amsel.RpPartnerService;
import eu.artofcoding.retoure.delivery.dhl.amsel.RpPartnerType;

import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AmselClient {

    private static final Logger logger = Logger.getLogger(AmselClient.class.getName());

    public ReturnLabel getBookLabel(BookLabelRequestType bookLabelRequestType) {
        RpPartnerService rpPartnerService = new RpPartnerService();
        RpPartnerType rpPartnerPort = rpPartnerService.getRpPartnerPort();
        // Add security handler to chain
        final Binding binding = ((BindingProvider) rpPartnerPort).getBinding();
        List<Handler> handlerList = binding.getHandlerChain();
        if (handlerList == null) {
            handlerList = new ArrayList<>();
        }
        handlerList.add(new AmselSecurityHandler());
        binding.setHandlerChain(handlerList);
        BookLabelResponseType bookLabelResponseType = rpPartnerPort.bookLabel(bookLabelRequestType);
        return new ReturnLabel(bookLabelResponseType.getIdc(), bookLabelResponseType.getRoutingCode(), bookLabelResponseType.getLabel());
    }

    public ReturnLabel makeLabel(Customer customer) {
        // Create label request
        BookLabelRequestType bookLabelRequestType = new BookLabelRequestType();
        //
        // PArtner
        bookLabelRequestType.setPortalId("OnlineRetoure");
        bookLabelRequestType.setDeliveryName("Deutschland_Var3");
        // com.sun.xml.ws.fault.ServerSOAPFaultException: Client received SOAP Fault from server: Für die angegebene Delivery-ID konnte kein Retourenemfpänger ermittelt werden:  Für die Delivery-ID DELIVERY NAME konnte kein Retourenempfänger ermittelt werden.
        //bookLabelRequestType.setDeliveryName("DELIVERY NAME");
        //bookLabelRequestType.setShipmentReference("Shipment Reference");
        bookLabelRequestType.setCustomerReference("CUST-1234-REF");
        //
        // Sender
        //bookLabelRequestType.setSenderCareOfName("CareofName");
        //bookLabelRequestType.setSenderBoxNumber("12345");
        bookLabelRequestType.setSenderName1(customer.getFullname());
        bookLabelRequestType.setSenderName2("");
        bookLabelRequestType.setSenderContactPhone(customer.getPhone());
        bookLabelRequestType.setSenderStreet(customer.getShippingAddress1());
        bookLabelRequestType.setSenderStreetNumber(customer.getShippingAddress1StreetNumber());
        bookLabelRequestType.setSenderPostalCode(customer.getZipcode());
        bookLabelRequestType.setSenderCity(customer.getCity());
        //
        // Output type PDF
        bookLabelRequestType.setLabelFormat("PDF");
        // Ask webservice to create label
        ReturnLabel returnLabel = getBookLabel(bookLabelRequestType);
        return returnLabel;
    }

}
