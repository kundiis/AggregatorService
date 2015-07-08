package ws.async;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;

import org.example.www.aggregator4.BlogData;
import org.example.www.aggregator4.SearchAppendandPass;
import org.example.www.aggregator4.SearchTo;

import voldemort.client.ClientConfig;
import voldemort.client.SocketStoreClientFactory;
import voldemort.client.StoreClient;
import voldemort.client.StoreClientFactory;

public class searchVoldemort {

	public void searchData(SearchAppendandPass saap)
	{
		SearchAssociation search = new SearchAssociation();   
		
		SearchTo[] searchResults =search.searchData(saap.getKeyword());
		
		System.out.println("Data fetch complete!");
		
		ws.async.Aggregator1Stub.SearchTo[] passSearchResults = null;
		
		System.out.println("Now packing data to be used for next WS....");
		
		if(searchResults != null)
		{
			int resultsLength = searchResults.length;
			passSearchResults = new ws.async.Aggregator1Stub.SearchTo[searchResults.length];
			if(saap.getIn() == null)
			{
				System.out.println("This is the starting WS");
				
				for (int i =0;i<searchResults.length;i++){
					passSearchResults[i] = new ws.async.Aggregator1Stub.SearchTo();
					
					passSearchResults[i].setAuthor(searchResults[i].getAuthor());
					passSearchResults[i].setId(searchResults[i].getId());
					passSearchResults[i].setLatittude(searchResults[i].getLatittude());
					passSearchResults[i].setLongitude(searchResults[i].getLongitude());
					passSearchResults[i].setTime(searchResults[i].getTime());
					passSearchResults[i].setType(searchResults[i].getType());
					if(searchResults[i].getAssociation() != null)
					{
						System.out.println("Data exists for image");
						passSearchResults[i].setAssociation(searchResults[i].getAssociation());
					}
					
					
				}

			}
			
			else {
				SearchTo[] searchResults2 = saap.getIn();
				int results2Length = searchResults2.length;
				for(int i=0;i<resultsLength;i++)
				{
				
					for(int j=0;j<results2Length;j++)
					{
						System.out.println("Entered the comparison Loop");
						if(searchResults[i].getId() == searchResults2[j].getId())
						{
							
							try{
								passSearchResults[i] = new ws.async.Aggregator1Stub.SearchTo();
								passSearchResults[i].setAuthor(searchResults[i].getAuthor());
								passSearchResults[i].setId(searchResults[i].getId());
								passSearchResults[i].setLatittude(searchResults[i].getLatittude());
								passSearchResults[i].setLongitude(searchResults[i].getLongitude());
								passSearchResults[i].setTime(searchResults[i].getTime());
								passSearchResults[i].setType(searchResults[i].getType());
								
								if(searchResults2[j].getImage() != null)
								{
									System.out.println("Data exists for image");
									passSearchResults[i].setImage(searchResults2[j].getImage());
								}
								if(searchResults2[j].getVideo() != null)
								{
									System.out.println("Data exists for video");
									passSearchResults[i].setVideo(searchResults2[j].getVideo());
								}
								if(searchResults[i].getAssociation() != null)
								{
									System.out.println("Data exists for associations");
									passSearchResults[i].setAssociation(searchResults[i].getAssociation());
								}
								if(searchResults2[j].getBlogContent() != null)
								{
									System.out.println("Data exists for blog");
									passSearchResults[i].setBlogContent(searchResults2[j].getBlogContent());
								}
					
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
										
						}
					}
				}
				
				
				System.out.println("This contains data from prev WS");
				
			}
		}
		ws.async.Aggregator1Stub.BlogData[] blogdata = null;
        if(saap.getOtherResults() != null)
        {
        	blogdata = extractOtherResults(saap.getOtherResults());
        }
		
        new callNextWS().callNext(saap, passSearchResults,blogdata);
        
	}
	
	private ws.async.Aggregator1Stub.BlogData[] extractOtherResults(BlogData[] blogdata)
	{
		ws.async.Aggregator1Stub.BlogData[] blogdataOutput = new ws.async.Aggregator1Stub.BlogData[blogdata.length];
		
		try{
			
			for(int i=0;i<blogdata.length;i++)
			{
				
				if(blogdata[i] != null)
				{
					
					blogdataOutput[i] = new ws.async.Aggregator1Stub.BlogData();
					ws.async.Aggregator1Stub.BlogInfo bloginfoOutput = new ws.async.Aggregator1Stub.BlogInfo();
					
					
					bloginfoOutput.setAuthor(blogdata[i].getInfo().getAuthor());
					
					bloginfoOutput.setBlogId(blogdata[i].getInfo().getBlogId());
					bloginfoOutput.setLatitude(blogdata[i].getInfo().getLatitude());
					bloginfoOutput.setLongitude(blogdata[i].getInfo().getLongitude());
					bloginfoOutput.setName(blogdata[i].getInfo().getName());
					bloginfoOutput.setTag(blogdata[i].getInfo().getTag());
					bloginfoOutput.setTime(blogdata[i].getInfo().getTime());
					blogdataOutput[i].setData(blogdata[i].getData());
					blogdataOutput[i].setInfo(bloginfoOutput);
				}
				else 
				{
					blogdataOutput = null;
				}
				
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return blogdataOutput;
		
	}
	
}
