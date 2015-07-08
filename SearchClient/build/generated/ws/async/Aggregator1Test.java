

/**
 * Aggregator1Test.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */
    package ws.async;

    /*
     *  Aggregator1Test Junit test case
    */

    public class Aggregator1Test extends junit.framework.TestCase{

     
        /**
         * Auto generated test method
         */
        public  void testsearch() throws java.lang.Exception{

        ws.async.Aggregator1Stub stub =
                    new ws.async.Aggregator1Stub();//the default implementation should point to the right endpoint

           ws.async.Aggregator1Stub.Search search6=
                                                        (ws.async.Aggregator1Stub.Search)getTestObject(ws.async.Aggregator1Stub.Search.class);
                    // TODO : Fill in the search6 here
                
                        assertNotNull(stub.search(
                        search6));
                  



        }
        
        /**
         * Auto generated test method
         */
        public  void testsearchAppendandPass() throws java.lang.Exception{

        ws.async.Aggregator1Stub stub =
                    new ws.async.Aggregator1Stub();//the default implementation should point to the right endpoint

           ws.async.Aggregator1Stub.SearchAppendandPass searchAppendandPass8=
                                                        (ws.async.Aggregator1Stub.SearchAppendandPass)getTestObject(ws.async.Aggregator1Stub.SearchAppendandPass.class);
                    // TODO : Fill in the searchAppendandPass8 here
                
                        assertNotNull(stub.searchAppendandPass(
                        searchAppendandPass8));
                  



        }
        
        /**
         * Auto generated test method
         */
        public  void testsearchGoldBox() throws java.lang.Exception{

        ws.async.Aggregator1Stub stub =
                    new ws.async.Aggregator1Stub();//the default implementation should point to the right endpoint

           ws.async.Aggregator1Stub.SearchGoldBox searchGoldBox10=
                                                        (ws.async.Aggregator1Stub.SearchGoldBox)getTestObject(ws.async.Aggregator1Stub.SearchGoldBox.class);
                    // TODO : Fill in the searchGoldBox10 here
                
                        assertNotNull(stub.searchGoldBox(
                        searchGoldBox10));
                  



        }
        
        //Create an ADBBean and provide it as the test object
        public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
           return (org.apache.axis2.databinding.ADBBean) type.newInstance();
        }

        
        

    }
    