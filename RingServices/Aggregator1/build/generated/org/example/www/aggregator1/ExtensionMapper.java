
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:47 EDT)
 */

            package org.example.www.aggregator1;
            /**
            *  ExtensionMapper class
            */
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://www.example.org/aggregator1/".equals(namespaceURI) &&
                  "SearchTo".equals(typeName)){
                   
                            return  org.example.www.aggregator1.SearchTo.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/aggregator1/".equals(namespaceURI) &&
                  "BlogInfo".equals(typeName)){
                   
                            return  org.example.www.aggregator1.BlogInfo.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/aggregator1/".equals(namespaceURI) &&
                  "BlogData".equals(typeName)){
                   
                            return  org.example.www.aggregator1.BlogData.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    