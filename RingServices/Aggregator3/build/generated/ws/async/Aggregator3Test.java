

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

           org.example.www.aggregator3.Search search48=
                                                        (org.example.www.aggregator3.Search)getTestObject(org.example.www.aggregator3.Search.class);
                    // TODO : Fill in the search48 here
                
                        assertNotNull(stub.search(
                        search48));
                  



        }
        
        /**
         * Auto generated test method
         */
        public  void testsearchGoldBox() throws java.lang.Exception{

        ws.async.Aggregator3Stub stub =
                    new ws.async.Aggregator3Stub();//the default implementation should point to the right endpoint

           org.example.www.aggregator3.SearchGoldBox searchGoldBox50=
                                                        (org.example.www.aggregator3.SearchGoldBox)getTestObject(org.example.www.aggregator3.SearchGoldBox.class);
                    // TODO : Fill in the searchGoldBox50 here
                
                        assertNotNull(stub.searchGoldBox(
                        searchGoldBox50));
                  



        }
        
        /**
         * Auto generated test method
         */
        public  void testsearchAppendandPass() throws java.lang.Exception{

        ws.async.Aggregator3Stub stub =
                    new ws.async.Aggregator3Stub();//the default implementation should point to the right endpoint

           org.example.www.aggregator3.SearchAppendandPass searchAppendandPass52=
                                                        (org.example.www.aggregator3.SearchAppendandPass)getTestObject(org.example.www.aggregator3.SearchAppendandPass.class);
                    // TODO : Fill in the searchAppendandPass52 here
                
                        assertNotNull(stub.searchAppendandPass(
                        searchAppendandPass52));
                  



        }
        
        //Create an ADBBean and provide it as the test object
        public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
           return (org.apache.axis2.databinding.ADBBean) type.newInstance();
        }

        
        

    }
    