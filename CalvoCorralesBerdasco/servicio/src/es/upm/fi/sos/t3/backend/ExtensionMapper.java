
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

        
            package es.upm.fi.sos.t3.backend;
        
            /**
            *  ExtensionMapper class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://backend.t3.sos.fi.upm.es/xsd".equals(namespaceURI) &&
                  "ChangePasswordResponse".equals(typeName)){
                   
                            return  es.upm.fi.sos.t3.backend.xsd.ChangePasswordResponse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://backend.t3.sos.fi.upm.es/xsd".equals(namespaceURI) &&
                  "LoginResponseBackEnd".equals(typeName)){
                   
                            return  es.upm.fi.sos.t3.backend.xsd.LoginResponseBackEnd.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://backend.t3.sos.fi.upm.es/xsd".equals(namespaceURI) &&
                  "ChangePasswordBackEnd".equals(typeName)){
                   
                            return  es.upm.fi.sos.t3.backend.xsd.ChangePasswordBackEnd.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://backend.t3.sos.fi.upm.es/xsd".equals(namespaceURI) &&
                  "RemoveUser".equals(typeName)){
                   
                            return  es.upm.fi.sos.t3.backend.xsd.RemoveUser.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://backend.t3.sos.fi.upm.es/xsd".equals(namespaceURI) &&
                  "UserBackEnd".equals(typeName)){
                   
                            return  es.upm.fi.sos.t3.backend.xsd.UserBackEnd.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://backend.t3.sos.fi.upm.es/xsd".equals(namespaceURI) &&
                  "ExistUserResponse".equals(typeName)){
                   
                            return  es.upm.fi.sos.t3.backend.xsd.ExistUserResponse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://backend.t3.sos.fi.upm.es/xsd".equals(namespaceURI) &&
                  "LoginBackEnd".equals(typeName)){
                   
                            return  es.upm.fi.sos.t3.backend.xsd.LoginBackEnd.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://backend.t3.sos.fi.upm.es/xsd".equals(namespaceURI) &&
                  "AddUserResponseBackEnd".equals(typeName)){
                   
                            return  es.upm.fi.sos.t3.backend.xsd.AddUserResponseBackEnd.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://backend.t3.sos.fi.upm.es/xsd".equals(namespaceURI) &&
                  "RemoveUserResponse".equals(typeName)){
                   
                            return  es.upm.fi.sos.t3.backend.xsd.RemoveUserResponse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://backend.t3.sos.fi.upm.es/xsd".equals(namespaceURI) &&
                  "Username".equals(typeName)){
                   
                            return  es.upm.fi.sos.t3.backend.xsd.Username.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    