

/**
 * Aggregator2Test.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */
    package ws.async;

    /*
     *  Aggregator2Test Junit test case
    */

    public class Aggregator2Test extends junit.framework.TestCase{

     
        /**
         * Auto generated test method
         */
        public  void testsearchAppendandPass() throws java.lang.Exception{

        ws.async.Aggregator2Stub stub =
                    new ws.async.Aggregator2Stub();//the default implementation should point to the right endpoint

           ws.async.Aggregator2Stub.SearchAppendandPass searchAppendandPass6=
                                                        (ws.async.Aggregator2Stub.SearchAppendandPass)getTestObject(ws.async.Aggregator2Stub.SearchAppendandPass.class);
                    // TODO : Fill in the searchAppendandPass6 here
                
                        assertNotNull(stub.searchAppendandPass(
                        searchAppendandPass6));
                  



        }
        
        /**
         * Auto generated test method
         */
        public  void testsearch() throws java.lang.Exception{

        ws.async.Aggregator2Stub stub =
                    new ws.async.Aggregator2Stub();//the default implementation should point to the right endpoint

           ws.async.Aggregator2Stub.Search search8=
                                                        (ws.async.Aggregator2Stub.Search)getTestObject(ws.async.Aggregator2Stub.Search.class);
                    // TODO : Fill in the search8 here
                
                        assertNotNull(stub.search(
                        search8));
                  



        }
        
        /**
         * Auto generated test method
         */
        public  void testsearchGoldBox() throws java.lang.Exception{

        ws.async.Aggregator2Stub stub =
                    new ws.async.Aggregator2Stub();//the default implementation should point to the right endpoint

           ws.async.Aggregator2Stub.SearchGoldBox searchGoldBox10=
                                                        (ws.async.Aggregator2Stub.SearchGoldBox)getTestObject(ws.async.Aggregator2Stub.SearchGoldBox.class);
                    // TODO : Fill in the searchGoldBox10 here
                
                        assertNotNull(stub.searchGoldBox(
                        searchGoldBox10));
                  



        }
        
        //Create an ADBBean and provide it as the test object
        public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
           return (org.apache.axis2.databinding.ADBBean) type.newInstance();
        }

        
        

    }
    