
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:47 EDT)
 */

            package org.example.www.aggregator4;
            /**
            *  ExtensionMapper class
            */
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://www.example.org/aggregator4/".equals(namespaceURI) &&
                  "BlogData".equals(typeName)){
                   
                            return  org.example.www.aggregator4.BlogData.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/aggregator4/".equals(namespaceURI) &&
                  "SearchTo".equals(typeName)){
                   
                            return  org.example.www.aggregator4.SearchTo.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/aggregator4/".equals(namespaceURI) &&
                  "BlogInfo".equals(typeName)){
                   
                            return  org.example.www.aggregator4.BlogInfo.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    