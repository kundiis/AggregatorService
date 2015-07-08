

/**
 * MTOMBlogTransferTest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */
    package ws.async;

    /*
     *  MTOMBlogTransferTest Junit test case
    */

    public class MTOMBlogTransferTest extends junit.framework.TestCase{

     
        /**
         * Auto generated test method
         */
        public  void testdownloadBlog() throws java.lang.Exception{

        ws.async.MTOMBlogTransferStub stub =
                    new ws.async.MTOMBlogTransferStub();//the default implementation should point to the right endpoint

           ws.async.MTOMBlogTransferStub.DownloadBlogRequest downloadBlogRequest4=
                                                        (ws.async.MTOMBlogTransferStub.DownloadBlogRequest)getTestObject(ws.async.MTOMBlogTransferStub.DownloadBlogRequest.class);
                    // TODO : Fill in the downloadBlogRequest4 here
                
                        assertNotNull(stub.downloadBlog(
                        downloadBlogRequest4));
                  



        }
        
        /**
         * Auto generated test method
         */
        public  void testuploadBlog() throws java.lang.Exception{

        ws.async.MTOMBlogTransferStub stub =
                    new ws.async.MTOMBlogTransferStub();//the default implementation should point to the right endpoint

           ws.async.MTOMBlogTransferStub.UploadBlog uploadBlog6=
                                                        (ws.async.MTOMBlogTransferStub.UploadBlog)getTestObject(ws.async.MTOMBlogTransferStub.UploadBlog.class);
                    // TODO : Fill in the uploadBlog6 here
                
                        assertNotNull(stub.uploadBlog(
                        uploadBlog6));
                  



        }
        
        //Create an ADBBean and provide it as the test object
        public org.apache.axis2.databinding.ADBBean getTestObject(java.lang.Class type) throws java.lang.Exception{
           return (org.apache.axis2.databinding.ADBBean) type.newInstance();
        }

        
        

    }
    