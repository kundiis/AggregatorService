

/**
 * Aggregator3Test.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */
    package ws.async;

    /*
     *  Aggregator3Test Junit test case
    */

    public class Aggregator3Test extends junit.framework.TestCase{

     
        /**
         * Auto generated test method
         */
        public  void testsearch() throws java.lang.Exception{

        ws.async.Aggregator3Stub stub =
                    new ws.async.Aggregator3Stub();//the default implementation should point to the right endpoint

           ws.async.Aggregator3Stub.Search search6=
                                                        (ws.async.Aggregator3Stub.Search)getTestObject(ws.async.Aggregator3Stub.Search.class);
                    // TODO : Fill in the search6 here
                
                        assertNotNull(stub.search(
                        search6));
                  



        }
        
        /**
         * Auto generated test method
         */
        public  void testsearchGoldBox() throws java.lang.Exception{

        ws.async.Aggregator3Stub stub =
                    new ws.async.Aggregator3Stub();//the default implementation should point to the right endpoint

           ws.async.Aggregator3Stub.SearchGoldBox searchGoldBox8=
                                                        (ws.async.Aggregator3Stub.SearchGoldBox)getTestObject(ws.async.Aggregator3Stub.SearchGoldBox.class);
                    // TODO : Fill in the searchGoldBox8 here
                
                        assertNotNull(stub.searchGoldBox(
                        searchGoldBox8));
                  



        }
        
        /**
         * Auto generated test method
         */
        public  void testsearchAppendandPass() throws java.lang.Exception{

        ws.async.Aggregator3Stub stub =
                    new ws.async.Aggregator3Stub();//the default implementation should point to the right endpoint

           ws.async.Aggregator3Stub.SearchAppendandPass searchAppendandPass10=
                                                        (ws.async.Aggregator3Stub.SearchAppendandPass)getTestObject(ws.async.Aggregator3Stub.SearchAppendandPass.class);
                    // TODO : Fill in the searchAppendandPass10 here
                
                        assertNotNull(stub.searchAppendandPass(
                        searchAppendandPass10));
                  



        }
        
        //Create an ADBBean and provide it as the test object
        public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
           return (org.apache.axis2.databinding.ADBBean) type.newInstance();
        }

        
        

    }
    