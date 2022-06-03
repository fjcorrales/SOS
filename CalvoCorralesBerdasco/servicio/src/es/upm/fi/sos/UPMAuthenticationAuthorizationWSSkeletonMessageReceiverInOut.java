
/**
 * UPMAuthenticationAuthorizationWSSkeletonMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
        package es.upm.fi.sos;

        /**
        *  UPMAuthenticationAuthorizationWSSkeletonMessageReceiverInOut message receiver
        */

        public class UPMAuthenticationAuthorizationWSSkeletonMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        UPMAuthenticationAuthorizationWSSkeletonSkeleton skel = (UPMAuthenticationAuthorizationWSSkeletonSkeleton)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(op.getName().getLocalPart())) != null)){


        

            if("removeUser".equals(methodName)){
                
                es.upm.fi.sos.t3.backend.RemoveUserResponse removeUserResponse21 = null;
	                        es.upm.fi.sos.t3.backend.RemoveUser wrappedParam =
                                                             (es.upm.fi.sos.t3.backend.RemoveUser)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    es.upm.fi.sos.t3.backend.RemoveUser.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               removeUserResponse21 =
                                                   
                                                   
                                                         skel.removeUser(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), removeUserResponse21, false, new javax.xml.namespace.QName("http://backend.t3.sos.fi.upm.es",
                                                    "removeUser"));
                                    } else 

            if("addUser".equals(methodName)){
                
                es.upm.fi.sos.t3.backend.AddUserResponse addUserResponse23 = null;
	                        es.upm.fi.sos.t3.backend.AddUser wrappedParam =
                                                             (es.upm.fi.sos.t3.backend.AddUser)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    es.upm.fi.sos.t3.backend.AddUser.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               addUserResponse23 =
                                                   
                                                   
                                                         skel.addUser(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), addUserResponse23, false, new javax.xml.namespace.QName("http://backend.t3.sos.fi.upm.es",
                                                    "addUser"));
                                    } else 

            if("existUser".equals(methodName)){
                
                es.upm.fi.sos.t3.backend.ExistUserResponse existUserResponse25 = null;
	                        es.upm.fi.sos.t3.backend.ExistUser wrappedParam =
                                                             (es.upm.fi.sos.t3.backend.ExistUser)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    es.upm.fi.sos.t3.backend.ExistUser.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               existUserResponse25 =
                                                   
                                                   
                                                         skel.existUser(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), existUserResponse25, false, new javax.xml.namespace.QName("http://backend.t3.sos.fi.upm.es",
                                                    "existUser"));
                                    } else 

            if("login".equals(methodName)){
                
                es.upm.fi.sos.t3.backend.LoginResponse loginResponse27 = null;
	                        es.upm.fi.sos.t3.backend.Login wrappedParam =
                                                             (es.upm.fi.sos.t3.backend.Login)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    es.upm.fi.sos.t3.backend.Login.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               loginResponse27 =
                                                   
                                                   
                                                         skel.login(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), loginResponse27, false, new javax.xml.namespace.QName("http://backend.t3.sos.fi.upm.es",
                                                    "login"));
                                    } else 

            if("changePassword".equals(methodName)){
                
                es.upm.fi.sos.t3.backend.ChangePasswordResponse changePasswordResponse29 = null;
	                        es.upm.fi.sos.t3.backend.ChangePassword wrappedParam =
                                                             (es.upm.fi.sos.t3.backend.ChangePassword)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    es.upm.fi.sos.t3.backend.ChangePassword.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               changePasswordResponse29 =
                                                   
                                                   
                                                         skel.changePassword(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), changePasswordResponse29, false, new javax.xml.namespace.QName("http://backend.t3.sos.fi.upm.es",
                                                    "changePassword"));
                                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
        

        newMsgContext.setEnvelope(envelope);
        }
        }
        catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }
        
        //
            private  org.apache.axiom.om.OMElement  toOM(es.upm.fi.sos.t3.backend.RemoveUser param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.upm.fi.sos.t3.backend.RemoveUser.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.upm.fi.sos.t3.backend.RemoveUserResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.upm.fi.sos.t3.backend.RemoveUserResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.upm.fi.sos.t3.backend.AddUser param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.upm.fi.sos.t3.backend.AddUser.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.upm.fi.sos.t3.backend.AddUserResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.upm.fi.sos.t3.backend.AddUserResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.upm.fi.sos.t3.backend.ExistUser param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.upm.fi.sos.t3.backend.ExistUser.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.upm.fi.sos.t3.backend.ExistUserResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.upm.fi.sos.t3.backend.ExistUserResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.upm.fi.sos.t3.backend.Login param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.upm.fi.sos.t3.backend.Login.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.upm.fi.sos.t3.backend.LoginResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.upm.fi.sos.t3.backend.LoginResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.upm.fi.sos.t3.backend.ChangePassword param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.upm.fi.sos.t3.backend.ChangePassword.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(es.upm.fi.sos.t3.backend.ChangePasswordResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(es.upm.fi.sos.t3.backend.ChangePasswordResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.upm.fi.sos.t3.backend.RemoveUserResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(es.upm.fi.sos.t3.backend.RemoveUserResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private es.upm.fi.sos.t3.backend.RemoveUserResponse wrapremoveUser(){
                                es.upm.fi.sos.t3.backend.RemoveUserResponse wrappedElement = new es.upm.fi.sos.t3.backend.RemoveUserResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.upm.fi.sos.t3.backend.AddUserResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(es.upm.fi.sos.t3.backend.AddUserResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private es.upm.fi.sos.t3.backend.AddUserResponse wrapaddUser(){
                                es.upm.fi.sos.t3.backend.AddUserResponse wrappedElement = new es.upm.fi.sos.t3.backend.AddUserResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.upm.fi.sos.t3.backend.ExistUserResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(es.upm.fi.sos.t3.backend.ExistUserResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private es.upm.fi.sos.t3.backend.ExistUserResponse wrapexistUser(){
                                es.upm.fi.sos.t3.backend.ExistUserResponse wrappedElement = new es.upm.fi.sos.t3.backend.ExistUserResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.upm.fi.sos.t3.backend.LoginResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(es.upm.fi.sos.t3.backend.LoginResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private es.upm.fi.sos.t3.backend.LoginResponse wraplogin(){
                                es.upm.fi.sos.t3.backend.LoginResponse wrappedElement = new es.upm.fi.sos.t3.backend.LoginResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, es.upm.fi.sos.t3.backend.ChangePasswordResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(es.upm.fi.sos.t3.backend.ChangePasswordResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private es.upm.fi.sos.t3.backend.ChangePasswordResponse wrapchangePassword(){
                                es.upm.fi.sos.t3.backend.ChangePasswordResponse wrappedElement = new es.upm.fi.sos.t3.backend.ChangePasswordResponse();
                                return wrappedElement;
                         }
                    


        /**
        *  get the default envelope
        */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
        return factory.getDefaultEnvelope();
        }


        private  java.lang.Object fromOM(
        org.apache.axiom.om.OMElement param,
        java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{

        try {
        
                if (es.upm.fi.sos.t3.backend.RemoveUser.class.equals(type)){
                
                           return es.upm.fi.sos.t3.backend.RemoveUser.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.upm.fi.sos.t3.backend.RemoveUserResponse.class.equals(type)){
                
                           return es.upm.fi.sos.t3.backend.RemoveUserResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.upm.fi.sos.t3.backend.AddUser.class.equals(type)){
                
                           return es.upm.fi.sos.t3.backend.AddUser.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.upm.fi.sos.t3.backend.AddUserResponse.class.equals(type)){
                
                           return es.upm.fi.sos.t3.backend.AddUserResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.upm.fi.sos.t3.backend.ExistUser.class.equals(type)){
                
                           return es.upm.fi.sos.t3.backend.ExistUser.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.upm.fi.sos.t3.backend.ExistUserResponse.class.equals(type)){
                
                           return es.upm.fi.sos.t3.backend.ExistUserResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.upm.fi.sos.t3.backend.Login.class.equals(type)){
                
                           return es.upm.fi.sos.t3.backend.Login.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.upm.fi.sos.t3.backend.LoginResponse.class.equals(type)){
                
                           return es.upm.fi.sos.t3.backend.LoginResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.upm.fi.sos.t3.backend.ChangePassword.class.equals(type)){
                
                           return es.upm.fi.sos.t3.backend.ChangePassword.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (es.upm.fi.sos.t3.backend.ChangePasswordResponse.class.equals(type)){
                
                           return es.upm.fi.sos.t3.backend.ChangePasswordResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
           return null;
        }



    

        /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
        private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
        org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
        returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
        return returnMap;
        }

        private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();
        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }

        }//end of class
    