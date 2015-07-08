package ws.async;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import org.example.www.aggregator4.BlogData;
import org.example.www.aggregator4.Search;
import org.example.www.aggregator4.SearchAppendandPass;
import org.example.www.aggregator4.SearchAppendandPassResponse;
import org.example.www.aggregator4.SearchGoldBox;
import org.example.www.aggregator4.SearchGoldBoxResponse;
import org.example.www.aggregator4.SearchResponse;
import org.example.www.aggregator4.SearchTo;

import voldemort.client.ClientConfig;
import voldemort.client.SocketStoreClientFactory;
import voldemort.client.StoreClient;
import voldemort.client.StoreClientFactory;


public class Aggregator4Implementation extends Thread implements Aggregator4SkeletonInterface {
	String bootstrapUrl = new LoadProperties().getBootstrapUrl();
    StoreClientFactory factory = new SocketStoreClientFactory(new ClientConfig().setBootstrapUrls(bootstrapUrl));
	/**
	 * 
	 * For the client to use
	 * takes : a searchKeyword
	 * returns : a searchResponse
	 * type : blocking
	 * 
	 */
	public SearchResponse search(Search search) {
		
		SearchResponse sr = new SearchResponse();
		// block the request wait for all the WS to reply 
		SearchAppendandPass saap = new SearchAppendandPass();	
		// packing
		StoreClient<String,byte[]> client = factory.getStoreClient("results");
		saap.setOriginID(4); // id of the 1st WS
		saap.setKeyword(search.getIn());  // get the key to be searched
		Random random = new Random();
		saap.setUniqueId(random.nextInt());
		// Setting out of bound counter while initializing. Actual value is set in callNextWS.java
		saap.setCounter(9999);
		searchAppendandPass(saap);  
		SearchTo[] st = null;
		BlogData[] blogdata = null;  
		
		
		System.out.println("Association Aggregator Called by client....");
		int i=0;
		// block the request wait for all the WS to reply
		while(i<12)
		{
			i++;
			byte[] outarray = client.getValue("result"+saap.getUniqueId());
			if(outarray == null)
			{
				System.out.println("Waiting for the ring to complete...");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				System.out.println("Raw data "+outarray);
				//res = (Result) toObject(outarray);
				//System.out.println(res.getAuthor()+" Data XXXX");
				st = (SearchTo[]) toObject(outarray);
				byte[] outarray2 = client.getValue("otherresult"+saap.getUniqueId());
				for(int j=0;j<st.length;j++)
				{
					File image = new File(System.getProperty("user.dir")+"/resultstemp/"+saap.getUniqueId()+st[j].getAuthor()+j+".jpg");
			        if(image.exists())
			        {
			        	DataSource ds = new FileDataSource(image);
				        DataHandler dh = new DataHandler(ds);
				        st[j].setImage(dh);
			        }
			        File video = new File(System.getProperty("user.dir")+"/resultstemp/"+saap.getUniqueId()+st[j].getAuthor()+j+".avi");
			        if(video.exists())
			        {
			        	System.out.println("Video Exists as "+video.getPath());
			        	DataSource ds = new FileDataSource(video);
				        DataHandler dh = new DataHandler(ds);
				        st[j].setVideo(dh);
				        video.deleteOnExit();
			        }
					
				}
				if(outarray2 != null)
				{
					blogdata = (BlogData[]) toObject(outarray2);
					for(int j=0;j<blogdata.length;j++)
					{
						File blog = new File(System.getProperty("user.dir")+"/resultstemp/"+saap.getUniqueId()+blogdata[j].getInfo().getAuthor()+j+".txt");
						if(blog.exists())
						{
							System.out.println("Blogs from otherResults Exists as "+blog.getPath());
				        	DataSource ds = new FileDataSource(blog);
					        DataHandler dh = new DataHandler(ds);
					        blogdata[j].setData(dh);
						}
					}
					client.delete("otherresult"+saap.getUniqueId());
				}
				client.delete("result"+saap.getUniqueId());
				break;
			}
		}

		//SearchResponse sr = new SearchResponse();
		sr.setOut(st);
		
		sr.setOtherResults(blogdata);
		return sr ;
	}

	
	/**
	 * For the WS to use make internal calls e.g from WS 1 to WS 2.
	 * 
	 */
	
	public SearchAppendandPassResponse searchAppendandPass(
			SearchAppendandPass searchAppendandPass) {
		
		// check the flags, 
		// if the originator is set to NULL, means this WS is the originator,
		// start a new thread, pack all the data and pass 
		// else if not the originator, then unpack all the data, pack again with the data of the next WS and
		// append more variables as required.
		StoreClient<String,byte[]> client = factory.getStoreClient("results");
		System.out.println("Association Aggregator initiated....");
		SearchAppendandPassResponse saapResponse = new SearchAppendandPassResponse();
		saapResponse.setOut("Request received");
		System.out.println(searchAppendandPass.getCounter());
		if(searchAppendandPass.getCounter() == 0)
		{
			 SearchTo[] st = searchAppendandPass.getIn();
			 BlogData[] blogdata = searchAppendandPass.getOtherResults();
			 if(st == null)
			 {
				 st = new SearchTo[1];
				 st[0] = new SearchTo();
			 }
			 else{
				 for(int i=0;i<st.length;i++)
				 {
					 if(st[i].getImage()!= null)
					 {
						  
						 try{
							 DataHandler dhout = st[i].getImage();
							 FileOutputStream fsout = new FileOutputStream(System.getProperty("user.dir")+"/resultstemp/"+searchAppendandPass.getUniqueId()+st[i].getAuthor()+i+".jpg");
							 dhout.writeTo(fsout);
							 st[i].setImage(null);
						 }
						 catch(Exception e)
						 {
							 e.printStackTrace();
						 }
						 
					 }
					 if(st[i].getVideo()!= null)
					 {
						 try{
							 DataHandler dhout = st[i].getVideo();
							 FileOutputStream fsout = new FileOutputStream(System.getProperty("user.dir")+"/resultstemp/"+searchAppendandPass.getUniqueId()+st[i].getAuthor()+i+".avi");
							 dhout.writeTo(fsout);
							 st[i].setVideo(null);
						 }
						 catch(Exception e)
						 {
							 e.printStackTrace();
						 }
					 }
					 
				 }
			 }
			 int isOtherResultsAvailable = 0;
			 if(blogdata != null)
			 {
				 System.out.println("Entered this loop...");
				 for(int i=0;i<blogdata.length;i++)
				 {
					 try{
						 if(blogdata[i] != null)
						 {
							 DataHandler dhout = blogdata[i].getData();
							 FileOutputStream fsout = new FileOutputStream(System.getProperty("user.dir")+"/resultstemp/"+searchAppendandPass.getUniqueId()+blogdata[i].getInfo().getAuthor()+i+".txt");
							 dhout.writeTo(fsout);
							 blogdata[i].setData(null);
							 isOtherResultsAvailable = 1;
							 
						 }
						 
					 }
					 catch(Exception e)
					 {
						 //System.out.println("No other results found");
						 e.printStackTrace();
					 }
					 
				 }
				 if(isOtherResultsAvailable == 1)
				 {
					 client.put("otherresult"+searchAppendandPass.getUniqueId(), toBytes(blogdata));
				 }
			 }
			 
			 
			 client.put("result"+searchAppendandPass.getUniqueId(), toBytes(st));
			 System.out.println("Wrote results to voldemort");
		}
		else
		{
			createNewThread(searchAppendandPass);
		}
		return saapResponse;
	}

	/**
	 * 
	 * @param search
	 * @return SearchResponse
	 * Used when this WS is used as a Gold Box for other services to fetch information synchronously.
	 */

	private void createNewThread(final SearchAppendandPass searchAppendandPass) {
		Thread t1 = new Thread(this){
			public void run(){			
			// access Voldemort, get Data, append the SOAP and call the next WS
				System.out.println("Thread Started");
				new searchVoldemort().searchData(searchAppendandPass);
				
			}
		};
		t1.start();
	}

	/**
	 * To be used to search the gold box
	 * Required a String to be set as a parameter and returns back data found from the Voldemort
	 */

	public SearchGoldBoxResponse searchGoldBox(SearchGoldBox searchGoldBox) {	
		SearchGoldBoxResponse response = new SearchGoldBoxResponse();
		response.setOut(new SearchAssociation().searchData(searchGoldBox.getIn()));
		return response;
	}
	
	/** Converts an object to array of bytes. 
	* @param bytes the byte array to convert.
	* @return the associated object.
	*/
	private static byte[] toBytes(Object object){
		java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
		
		try{
			java.io.ObjectOutputStream oos = new
			java.io.ObjectOutputStream(baos);
			oos.writeObject(object);
		}
		catch(java.io.IOException ioe){
				
		}
		return baos.toByteArray();
	}

	/** Converts an array of bytes back to its constituent object. 
	* @param bytes the byte array to convert.
	* @return the associated object.
	*/
	private static Object toObject(byte[] bytes){
		Object object = null;
		try{
			object = new java.io.ObjectInputStream(new
			java.io.ByteArrayInputStream(bytes)).readObject();
		}
		catch(java.io.IOException ioe){
			System.out.println(ioe.getMessage());
		}
		catch(java.lang.ClassNotFoundException cnfe){
			System.out.println(cnfe.getMessage());
		}
		return object;
	}

	
}
