package ws.async;

import java.util.ArrayList;
import java.util.HashMap;
import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;
import org.example.www.aggregator3.SearchTo;
import voldemort.client.ClientConfig;
import voldemort.client.SocketStoreClientFactory;
import voldemort.client.StoreClient;
import voldemort.client.StoreClientFactory;

public class SearchBlog {

	String bootstrapUrl = "tcp://localhost:7001";
    StoreClientFactory factory = new SocketStoreClientFactory(new ClientConfig().setBootstrapUrls(bootstrapUrl));
	/*
	 * Method to extract Blog content from the Voldemort store.
	 */
	public String getBlogData(String storename,String key)
	{
		String blogData = "";
		try{                      
	        StoreClient<String,String> client = factory.getStoreClient(storename);
			blogData = client.getValue(key);
	        			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return blogData;
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
        //System.out.println("Indexes returned "+indexvalues);
        
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
    			String blogid = map.get("actualblogid").toString();
    			
    			searchTo[i].setId(Integer.parseInt(indexvalue[i].trim()));
    			searchTo[i].setAuthor((String)map.get("author"));
    			searchTo[i].setTime((String)map.get("time"));
    			searchTo[i].setLatittude((String)map.get("lattitude"));
    			searchTo[i].setLongitude((String)map.get("longitude"));
    			searchTo[i].setType((String) map.get("type"));
    			searchTo[i].setBlogContent(getBlogData("blog",blogid ));
    			
            }
        }
                
        return searchTo;        
	}
}
