

/**
 * Aggregator4Test.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */
    package ws.async;

    /*
     *  Aggregator4Test Junit test case
    */

    public class Aggregator4Test extends junit.framework.TestCase{

     
        /**
         * Auto generated test method
         */
        public  void testsearchGoldBox() throws java.lang.Exception{

        ws.async.Aggregator4Stub stub =
                    new ws.async.Aggregator4Stub();//the default implementation should point to the right endpoint

           ws.async.Aggregator4Stub.SearchGoldBox searchGoldBox6=
                                                        (ws.async.Aggregator4Stub.SearchGoldBox)getTestObject(ws.async.Aggregator4Stub.SearchGoldBox.class);
                    // TODO : Fill in the searchGoldBox6 here
                
                        assertNotNull(stub.searchGoldBox(
                        searchGoldBox6));
                  



        }
        
        /**
         * Auto generated test method
         */
        public  void testsearch() throws java.lang.Exception{

        ws.async.Aggregator4Stub stub =
                    new ws.async.Aggregator4Stub();//the default implementation should point to the right endpoint

           ws.async.Aggregator4Stub.Search search8=
                                                        (ws.async.Aggregator4Stub.Search)getTestObject(ws.async.Aggregator4Stub.Search.class);
                    // TODO : Fill in the search8 here
                
                        assertNotNull(stub.search(
                        search8));
                  



        }
        
        /**
         * Auto generated test method
         */
        public  void testsearchAppendandPass() throws java.lang.Exception{

        ws.async.Aggregator4Stub stub =
                    new ws.async.Aggregator4Stub();//the default implementation should point to the right endpoint

           ws.async.Aggregator4Stub.SearchAppendandPass searchAppendandPass10=
                                                        (ws.async.Aggregator4Stub.SearchAppendandPass)getTestObject(ws.async.Aggregator4Stub.SearchAppendandPass.class);
                    // TODO : Fill in the searchAppendandPass10 here
                
                        assertNotNull(stub.searchAppendandPass(
                        searchAppendandPass10));
                  



        }
        
        //Create an ADBBean and provide it as the test object
        public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
           return (org.apache.axis2.databinding.ADBBean) type.newInstance();
        }

        
        

    }
    