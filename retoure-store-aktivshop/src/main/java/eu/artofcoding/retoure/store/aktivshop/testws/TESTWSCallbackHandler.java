
/*
 * retoure-server
 * retoure-store-aktivshop
 * Copyright (C) 2012-2012 art of coding UG, http://www.art-of-coding.eu/
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 * rbe, 24.11.12 18:18
 */

/**
 * TESTWSCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package eu.artofcoding.retoure.store.aktivshop.testws;

    /**
     *  TESTWSCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class TESTWSCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public TESTWSCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public TESTWSCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for testws_XML method
            * override this method for handling normal response from testws_XML operation
            */
           public void receiveResulttestws_XML(
                    eu.artofcoding.retoure.store.aktivshop.testws.TESTWSStub.Testws_XMLResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from testws_XML operation
           */
            public void receiveErrortestws_XML(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for testws method
            * override this method for handling normal response from testws operation
            */
           public void receiveResulttestws(
                    eu.artofcoding.retoure.store.aktivshop.testws.TESTWSStub.TestwsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from testws operation
           */
            public void receiveErrortestws(java.lang.Exception e) {
            }
                


    }
    
