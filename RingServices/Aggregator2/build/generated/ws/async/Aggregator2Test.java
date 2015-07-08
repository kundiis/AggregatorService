

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

           org.example.www.aggregator2.SearchAppendandPass searchAppendandPass48=
                                                        (org.example.www.aggregator2.SearchAppendandPass)getTestObject(org.example.www.aggregator2.SearchAppendandPass.class);
                    // TODO : Fill in the searchAppendandPass48 here
                
                        assertNotNull(stub.searchAppendandPass(
                        searchAppendandPass48));
                  



        }
        
        /**
         * Auto generated test method
         */
        public  void testsearch() throws java.lang.Exception{

        ws.async.Aggregator2Stub stub =
                    new ws.async.Aggregator2Stub();//the default implementation should point to the right endpoint

           org.example.www.aggregator2.Search search50=
                                                        (org.example.www.aggregator2.Search)getTestObject(org.example.www.aggregator2.Search.class);
                    // TODO : Fill in the search50 here
                
                        assertNotNull(stub.search(
                        search50));
                  



        }
        
        /**
         * Auto generated test method
         */
        public  void testsearchGoldBox() throws java.lang.Exception{

        ws.async.Aggregator2Stub stub =
                    new ws.async.Aggregator2Stub();//the default implementation should point to the right endpoint

           org.example.www.aggregator2.SearchGoldBox searchGoldBox52=
                                                        (org.example.www.aggregator2.SearchGoldBox)getTestObject(org.example.www.aggregator2.SearchGoldBox.class);
                    // TODO : Fill in the searchGoldBox52 here
                
                        assertNotNull(stub.searchGoldBox(
                        searchGoldBox52));
                  



        }
        
        //Create an ADBBean and provide it as the test object
        public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
           return (org.apache.axis2.databinding.ADBBean) type.newInstance();
        }

        
        

    }
    