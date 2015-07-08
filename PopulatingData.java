package voldemort.examples;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;




import voldemort.client.ClientConfig;
import voldemort.client.SocketStoreClientFactory;
import voldemort.client.StoreClient;
import voldemort.client.StoreClientFactory;



public class PopulatingData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
        PopulatingData populate = new PopulatingData();
        populate.putData("tcp://localhost:6666","C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg", "image", "1");
        populate.putData("tcp://localhost:6666","C:\\Users\\Public\\Pictures\\Sample Pictures\\Tulips.jpg", "image", "2");
        populate.putData("tcp://localhost:6669","C:\\Users\\Public\\Videos\\Sample Videos\\abc.avi", "video", "3");
        
             
		
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("inflation");
		tags.add("economy");
        populate.putmetinf("tcp://localhost:6666", tags, "Kunal", "27.75 W", "102.3 N", "04:00 AM", "Finance", "actualimageid",1, "1");
        populate.putmetinf("tcp://localhost:6669", tags, "Kunal", "27.75 W", "102.3 N", "04:00 AM", "Finance", "actualvideoid",1, "1");
        populate.putmetinf("tcp://localhost:7001", tags, "Kunal", "27.75 W", "102.3 N", "04:00 AM", "Finance", "actualblogid",1, "1");
        populate.putmetinf("tcp://localhost:7003", tags, "Kunal", "27.75 W", "102.3 N", "04:00 AM", "Finance", "actualassocid",1, "1");
        tags.clear();
        tags.add("programming");
        tags.add("csharp");
        populate.putmetinf("tcp://localhost:6666", tags, "Girish", "27.75 W", "102.3 N", "12:00 AM", "Technology", "actualimageid",3, "3");
        populate.putmetinf("tcp://localhost:6669", tags, "Girish", "27.75 W", "102.3 N", "12:00 AM", "Technology", "actualvideoid",3, "3");
        populate.putmetinf("tcp://localhost:7001", tags, "Girish", "27.75 W", "102.3 N", "12:00 AM", "Technology", "actualblogid",3, "3");
        populate.putmetinf("tcp://localhost:7003", tags, "Girish", "27.75 W", "102.3 N", "12:00 AM", "Technology", "actualassocid",3, "3");
        tags.clear();
        tags.add("programming");
        tags.add("java");
        populate.putmetinf("tcp://localhost:6666", tags, "Kunal", "27.75 W", "102.3 N", "05:00 AM", "Technology", "actualimageid",2, "2");
        populate.putmetinf("tcp://localhost:6669", tags, "Kunal", "27.75 W", "102.3 N", "05:00 AM", "Technology", "actualvideoid",2, "2");
        populate.putmetinf("tcp://localhost:7001", tags, "Kunal", "27.75 W", "102.3 N", "05:00 AM", "Technology", "actualblogid",2, "2");
        populate.putmetinf("tcp://localhost:7003", tags, "Kunal", "27.75 W", "102.3 N", "05:00 AM", "Technology", "actualassocid",2, "2");
        
        
        populate.putindex();
        
        /*populate.putCrap();
        String bootstrapUrl = "tcp://localhost:6666";
	    StoreClientFactory factory = new SocketStoreClientFactory(new ClientConfig().setBootstrapUrls(bootstrapUrl));
		StoreClient<String,Abc> client = factory.getStoreClient("results");
		Abc abc = new Abc();
		abc = client.getValue("abc");
		System.out.println(abc.getId());*/
        /*SearchTo[] searchTo = new SearchBlog().searchData("Kunal");
        
        for(int i=0;i<searchTo.length;i++)
        {
        	System.out.println(searchTo[i].getAuthor());
        	System.out.println(searchTo[i].getImage());
        	System.out.println(searchTo[i].getVideo());
        	System.out.println(searchTo[i].getBlogContent());
        	//System.out.println(searchTo[i].getAssociation()[0]);
        	
        }*/
        

	}
	
	void putData(String bootstrapUrl,String filepath,String storename,String key)
	{
		File image = new File(filepath);
        DataSource ds = new FileDataSource(image);
        DataHandler dh = new DataHandler(ds);
        ByteArrayOutputStream outstream = new ByteArrayOutputStream();
        
        try {
			dh.writeTo(outstream);
			byte[] buff = outstream.toByteArray();
			outstream.close();
		
    	
        StoreClientFactory factory = new SocketStoreClientFactory(new ClientConfig().setBootstrapUrls(bootstrapUrl));
                        
        StoreClient<String,byte[]> client = factory.getStoreClient(storename);
             
        client.put(key,buff);
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void putmetinf(String bootstrapUrl,ArrayList<String> tags,String author,String latitude,String longitude,String time,String type,String storetype,int storetypeid,String indexid)
	{
		 //String bootstrapUrl = "tcp://localhost:6666";
	     StoreClientFactory factory = new SocketStoreClientFactory(new ClientConfig().setBootstrapUrls(bootstrapUrl));
	     StoreClient<String,HashMap<String, Object>> client = factory.getStoreClient("metainf");
	     HashMap<String, Object> json = new HashMap<String, Object>();
	     /*ArrayList<String> tags = new ArrayList<String>();
	     tags.add("programming");
	     tags.add("java");
	     tags.add("csharp");*/
	     try{
	     
	     
	     json.put("author", author);
	     json.put("lattitude", latitude);
	     json.put("longitude", longitude);
	     json.put("time", time);
	     json.put(storetype,storetypeid);
	     json.put("tags",tags);
	     json.put("type", type);
	     
	     }
	     catch(Exception e)
	     {
	    	 
	     }
	     
	        client.put(indexid,json);
	        
	        
	}
	void putindex()
	{
		String bootstrapUrl = "tcp://localhost:6666";
	    StoreClientFactory factory = new SocketStoreClientFactory(new ClientConfig().setBootstrapUrls(bootstrapUrl));
		StoreClient<String,String> client2 = factory.getStoreClient("index");
        client2.put("Kunal", "1:2");
        client2.put("Girish", "3");
        client2.put("27.75 W", "1:2:3");
        client2.put("102.3 N", "1:2:3");
        client2.put("programming", "2:3");
        client2.put("Technology", "2:3");
        client2.put("economy", "1");
        
        bootstrapUrl = "tcp://localhost:6669";
        factory = new SocketStoreClientFactory(new ClientConfig().setBootstrapUrls(bootstrapUrl));
        client2 = factory.getStoreClient("index");
        
        client2.put("Kunal", "1:2");
        client2.put("Girish", "3");
        client2.put("27.75 W", "1:2:3");
        client2.put("102.3 N", "1:2:3");
        client2.put("programming", "2:3");
        client2.put("Technology", "2:3");
        client2.put("economy", "1");
        
        bootstrapUrl = "tcp://localhost:7001";
        factory = new SocketStoreClientFactory(new ClientConfig().setBootstrapUrls(bootstrapUrl));
        client2 = factory.getStoreClient("index");
        
        client2.put("Kunal", "1:2");
        client2.put("Girish", "3");
        client2.put("27.75 W", "1:2:3");
        client2.put("102.3 N", "1:2:3");
        client2.put("programming", "2:3");
        client2.put("Technology", "2:3");
        client2.put("economy", "1");
        
        client2 = factory.getStoreClient("blog");
        client2.put("1", "Hi this is my blog about inflation");
        client2.put("2", "Hi this is my blog abt java prog'g");
        client2.put("3", "Hi this is my blog abt .Net prog'g");
        
        bootstrapUrl = "tcp://localhost:7003";
        factory = new SocketStoreClientFactory(new ClientConfig().setBootstrapUrls(bootstrapUrl));
        client2 = factory.getStoreClient("index");
        
        client2.put("Kunal", "1:2");
        client2.put("Girish", "3");
        client2.put("27.75 W", "1:2:3");
        client2.put("102.3 N", "1:2:3");
        client2.put("programming", "2:3");
        client2.put("Technology", "2:3");
        client2.put("economy", "1");
        
        client2 = factory.getStoreClient("association");
        client2.put("1", "Girish");
        client2.put("2", "Girish");
        client2.put("3", "Kunal:Feddy");
	}
	
	void putCrap()
	{
		String bootstrapUrl = "tcp://localhost:6666";
	    StoreClientFactory factory = new SocketStoreClientFactory(new ClientConfig().setBootstrapUrls(bootstrapUrl));
		StoreClient<String,Abc> client = factory.getStoreClient("results");
		Abc abc = new Abc();
		abc.setId(1);
		client.put("abc", abc);
		
		
		
	}

}
