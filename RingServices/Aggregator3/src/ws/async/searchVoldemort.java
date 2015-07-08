package ws.async;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;

import org.apache.axis2.AxisFault;
import org.example.www.aggregator3.SearchAppendandPass;
import org.example.www.aggregator3.SearchTo;

import voldemort.client.ClientConfig;
import voldemort.client.SocketStoreClientFactory;
import voldemort.client.StoreClient;
import voldemort.client.StoreClientFactory;
import ws.async.MTOMBlogTransferStub.BlogData;
import ws.async.MTOMBlogTransferStub.DownloadBlogRequest;
import ws.async.MTOMBlogTransferStub.DownloadBlogResponse;

public class searchVoldemort {

	public void searchData(SearchAppendandPass saap)
	{
		SearchBlog search = new SearchBlog();
	    
		SearchTo[] searchResults =search.searchData(saap.getKeyword());
		
		System.out.println("Data fetch complete!");
		
		ws.async.Aggregator4Stub.SearchTo[] passSearchResults = null;
		
		System.out.println("Now packing data to be used for next WS....");
		
		if(searchResults != null)
		{
			int resultsLength = searchResults.length;
			passSearchResults = new ws.async.Aggregator4Stub.SearchTo[searchResults.length];
			if(saap.getIn() == null)
			{
				System.out.println("This is the starting WS");
				
				for (int i =0;i<searchResults.length;i++){
					passSearchResults[i] = new ws.async.Aggregator4Stub.SearchTo();
					
					passSearchResults[i].setAuthor(searchResults[i].getAuthor());
					passSearchResults[i].setId(searchResults[i].getId());
					passSearchResults[i].setLatittude(searchResults[i].getLatittude());
					passSearchResults[i].setLongitude(searchResults[i].getLongitude());
					passSearchResults[i].setTime(searchResults[i].getTime());
					passSearchResults[i].setType(searchResults[i].getType());
					if(searchResults[i].getBlogContent() != null)
					{
						System.out.println("Data exists for blogs");
						passSearchResults[i].setBlogContent(searchResults[i].getBlogContent());
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
								passSearchResults[i] = new ws.async.Aggregator4Stub.SearchTo();
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
								if(searchResults2[j].getAssociation() != null)
								{
									System.out.println("Data exists for associations");
									passSearchResults[i].setAssociation(searchResults2[j].getAssociation());
								}
								if(searchResults[i].getBlogContent() != null)
								{
									System.out.println("Data exists for blog");
									passSearchResults[i].setBlogContent(searchResults[i].getBlogContent());
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

		ws.async.Aggregator4Stub.BlogData[] blogdata = callExternalService(saap.getKeyword());
		
		new callNextWS().callNext(saap, passSearchResults,blogdata);
		
        
	}
	/**
	 * Calling the Integrator for blog data. Data is sent to the client untouched.
	 */
	private ws.async.Aggregator4Stub.BlogData[] callExternalService(String keyword)
	{
		BlogData[] blogdata = null;
		ws.async.Aggregator4Stub.BlogData[] blogdataOutput = null;
		String externalServiceUrl = new LoadProperties().getExternalServiceUrl();
		try {
			MTOMBlogTransferStub stub = new MTOMBlogTransferStub(externalServiceUrl);
			DownloadBlogRequest request = new DownloadBlogRequest();
			request.setSearchType("Tag");
			request.setValue(keyword);
			DownloadBlogResponse response = stub.downloadBlog(request);
			blogdata = response.getData();
			System.out.println("Extracting data from external service...");
			if(blogdata != null)
			{
				System.out.println("Length is "+blogdata.length);
				blogdataOutput = new ws.async.Aggregator4Stub.BlogData[blogdata.length];
				for(int i=0;i<blogdata.length;i++)
				{
					blogdataOutput[i] = new ws.async.Aggregator4Stub.BlogData();
					blogdataOutput[i].setData(blogdata[i].getData());
					ws.async.Aggregator4Stub.BlogInfo bloginfoOutput = new ws.async.Aggregator4Stub.BlogInfo();
					bloginfoOutput.setAuthor(blogdata[i].getInfo().getAuthor());
					bloginfoOutput.setBlogId(blogdata[i].getInfo().getBlogId());
					bloginfoOutput.setLatitude(blogdata[i].getInfo().getLatitude());
					bloginfoOutput.setLongitude(blogdata[i].getInfo().getLongitude());
					bloginfoOutput.setName(blogdata[i].getInfo().getName());
					bloginfoOutput.setTag(blogdata[i].getInfo().getTag());
					bloginfoOutput.setTime(blogdata[i].getInfo().getTime());
					blogdataOutput[i].setInfo(bloginfoOutput);
					
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Extraction complete...");
		return blogdataOutput;
	}
}
