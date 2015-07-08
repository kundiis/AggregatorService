package ws.async;

import java.util.ArrayList;
import java.util.HashMap;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;

import org.example.www.aggregator2.SearchTo;
import voldemort.client.ClientConfig;
import voldemort.client.SocketStoreClientFactory;
import voldemort.client.StoreClient;
import voldemort.client.StoreClientFactory;

public class SearchVideo {

	String bootstrapUrl = new LoadProperties().getBootstrapUrl();
    StoreClientFactory factory = new SocketStoreClientFactory(new ClientConfig().setBootstrapUrls(bootstrapUrl));
	/*
	 * Extracts the video from Voldemort and returns the video in javax.activation.DataHandler format
	 */
	public DataHandler getVideoData(String storename,String key)
	{
		DataHandler dhout = null;
		try{                      
	        StoreClient<String,byte[]> client = factory.getStoreClient(storename);
			byte[] outArray = client.getValue(key);
	        if(outArray != null)
	        {
	        	ByteArrayDataSource dsout = new	ByteArrayDataSource(outArray,"default");
	        	dhout = new DataHandler(dsout);
	        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return dhout;
	}
	/*
	 * Method for searching the index store in Voldemort using the keyword.
	 * Extracts the metadata from metainf store and returns the data in SearchTo[] format.
	 */
	public SearchTo[] searchData(String keyword)
	{
		SearchTo[] searchTo = null;
		
        StoreClient<String,String> client = factory.getStoreClient("index");
        
        String indexvalues = client.getValue(keyword);
        System.out.println("Indexes returned "+indexvalues);
        
        if(indexvalues != null)
        {
        	// getting all the search results from the voldemort and passing 
            String[] indexvalue = indexvalues.split(":");
            searchTo = new SearchTo[indexvalue.length];
            
            for(int i=0;i<indexvalue.length;i++)
            { 	
            	searchTo[i] = new SearchTo();
            	HashMap<String, Object> map = new HashMap<String, Object>();
    	        
    	        StoreClient<String,HashMap<String, Object>> client2 = factory.getStoreClient("metainf");
    			map = client2.getValue(indexvalue[i]);
    			String videoid = map.get("actualvideoid").toString();
    			
    			searchTo[i].setId(Integer.parseInt(indexvalue[i].trim()));
    			searchTo[i].setAuthor((String)map.get("author"));
    			searchTo[i].setTime((String)map.get("time"));
    			searchTo[i].setLatittude((String)map.get("lattitude"));
    			searchTo[i].setLongitude((String)map.get("longitude"));
    			searchTo[i].setType((String) map.get("type"));
    			searchTo[i].setVideo(getVideoData("video",videoid ));			
            }
        }      
        return searchTo;        
	}
}
