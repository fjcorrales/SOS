
/**
 * UPMGeoCachingCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package client;

    /**
     *  UPMGeoCachingCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class UPMGeoCachingCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public UPMGeoCachingCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public UPMGeoCachingCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for removeFollower method
            * override this method for handling normal response from removeFollower operation
            */
           public void receiveResultremoveFollower(
                    client.UPMGeoCachingStub.RemoveFollowerResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeFollower operation
           */
            public void receiveErrorremoveFollower(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getMyTreasuresFound method
            * override this method for handling normal response from getMyTreasuresFound operation
            */
           public void receiveResultgetMyTreasuresFound(
                    client.UPMGeoCachingStub.GetMyTreasuresFoundResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMyTreasuresFound operation
           */
            public void receiveErrorgetMyTreasuresFound(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getMyFollowers method
            * override this method for handling normal response from getMyFollowers operation
            */
           public void receiveResultgetMyFollowers(
                    client.UPMGeoCachingStub.GetMyFollowersResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMyFollowers operation
           */
            public void receiveErrorgetMyFollowers(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getMyTreasuresCreated method
            * override this method for handling normal response from getMyTreasuresCreated operation
            */
           public void receiveResultgetMyTreasuresCreated(
                    client.UPMGeoCachingStub.GetMyTreasuresCreatedResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMyTreasuresCreated operation
           */
            public void receiveErrorgetMyTreasuresCreated(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeUser method
            * override this method for handling normal response from removeUser operation
            */
           public void receiveResultremoveUser(
                    client.UPMGeoCachingStub.RemoveUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeUser operation
           */
            public void receiveErrorremoveUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addFollower method
            * override this method for handling normal response from addFollower operation
            */
           public void receiveResultaddFollower(
                    client.UPMGeoCachingStub.AddFollowerResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addFollower operation
           */
            public void receiveErroraddFollower(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for createTreasure method
            * override this method for handling normal response from createTreasure operation
            */
           public void receiveResultcreateTreasure(
                    client.UPMGeoCachingStub.CreateTreasureResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from createTreasure operation
           */
            public void receiveErrorcreateTreasure(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for findTreasure method
            * override this method for handling normal response from findTreasure operation
            */
           public void receiveResultfindTreasure(
                    client.UPMGeoCachingStub.FindTreasureResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from findTreasure operation
           */
            public void receiveErrorfindTreasure(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addUser method
            * override this method for handling normal response from addUser operation
            */
           public void receiveResultaddUser(
                    client.UPMGeoCachingStub.AddUserResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addUser operation
           */
            public void receiveErroraddUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getMyFollowerTreasuresCreated method
            * override this method for handling normal response from getMyFollowerTreasuresCreated operation
            */
           public void receiveResultgetMyFollowerTreasuresCreated(
                    client.UPMGeoCachingStub.GetMyFollowerTreasuresCreatedResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMyFollowerTreasuresCreated operation
           */
            public void receiveErrorgetMyFollowerTreasuresCreated(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for changePassword method
            * override this method for handling normal response from changePassword operation
            */
           public void receiveResultchangePassword(
                    client.UPMGeoCachingStub.ChangePasswordResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from changePassword operation
           */
            public void receiveErrorchangePassword(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for login method
            * override this method for handling normal response from login operation
            */
           public void receiveResultlogin(
                    client.UPMGeoCachingStub.LoginResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from login operation
           */
            public void receiveErrorlogin(java.lang.Exception e) {
            }
                


    }
    