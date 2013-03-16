/*
 * retoure-server
 * retoure-dhl-client
 * Copyright (C) 2012-2013 art of coding UG, http://www.art-of-coding.eu
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 11.02.13 09:16
 */

package eu.artofcoding.retoure.delivery.dhl;

import eu.artofcoding.beetlejuice.cdm.store.ReturnLabel;
import eu.artofcoding.beetlejuice.cdm.store.StoreCustomer;
import eu.artofcoding.retoure.delivery.ReturnLabelClient;
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

public class AmselClientImpl implements ReturnLabelClient {

    private static final Logger logger = Logger.getLogger(AmselClientImpl.class.getName());

    private final String portalId;

    private final String deliveryName;

    public AmselClientImpl(String portalId, String deliveryName) {
        this.portalId = portalId;
        this.deliveryName = deliveryName;
    }

    private ReturnLabel getBookLabel(BookLabelRequestType bookLabelRequestType) {
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

    @Override
    public ReturnLabel makeLabel(StoreCustomer customer) {
        // Create label request
        BookLabelRequestType bookLabelRequestType = new BookLabelRequestType();
        //
        // Partner
        bookLabelRequestType.setPortalId(portalId);
        bookLabelRequestType.setDeliveryName(deliveryName);
        //bookLabelRequestType.setDeliveryName("DELIVERY NAME");
        //bookLabelRequestType.setShipmentReference("Shipment Reference");
        //bookLabelRequestType.setCustomerReference("CUST-1234-REF");
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
