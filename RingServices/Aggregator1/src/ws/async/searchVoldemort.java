package ws.async;

import org.example.www.aggregator1.SearchAppendandPass;
import org.example.www.aggregator1.SearchTo;
import org.example.www.aggregator1.BlogData;

import ws.async.Aggregator2Stub.BlogInfo;


public class searchVoldemort {

	public void searchData(SearchAppendandPass saap)
	{
		SearchImage search = new SearchImage();
	    
		SearchTo[] searchResults =search.searchData(saap.getKeyword());
		
		System.out.println("Data fetch complete!");
		
		ws.async.Aggregator2Stub.SearchTo[] passSearchResults = null;
		
		System.out.println("Now packing data to be used for next WS....");
		
		
		if(searchResults != null)
		{
			int resultsLength = searchResults.length;
			passSearchResults = new ws.async.Aggregator2Stub.SearchTo[searchResults.length];
			if(saap.getIn() == null)
			{
				System.out.println("This is the starting WS");
				
				for (int i =0;i<searchResults.length;i++){
					passSearchResults[i] = new ws.async.Aggregator2Stub.SearchTo();
					
					passSearchResults[i].setAuthor(searchResults[i].getAuthor());
					passSearchResults[i].setId(searchResults[i].getId());
					passSearchResults[i].setLatittude(searchResults[i].getLatittude());
					passSearchResults[i].setLongitude(searchResults[i].getLongitude());
					passSearchResults[i].setTime(searchResults[i].getTime());
					passSearchResults[i].setType(searchResults[i].getType());
					if(searchResults[i].getImage() != null)
					{
						System.out.println("Data exists for image");
						passSearchResults[i].setImage(searchResults[i].getImage());
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
								passSearchResults[i] = new ws.async.Aggregator2Stub.SearchTo();
								passSearchResults[i].setAuthor(searchResults[i].getAuthor());
								passSearchResults[i].setId(searchResults[i].getId());
								passSearchResults[i].setLatittude(searchResults[i].getLatittude());
								passSearchResults[i].setLongitude(searchResults[i].getLongitude());
								passSearchResults[i].setTime(searchResults[i].getTime());
								passSearchResults[i].setType(searchResults[i].getType());
								
								if(searchResults[i].getImage() != null)
								{
									System.out.println("Data exists for image");
									passSearchResults[i].setImage(searchResults[i].getImage());
								}
								if(searchResults2[j].getVideo() != null)
								{
									System.out.println("Data exists for video");
									passSearchResults[i].setVideo(searchResults2[j].getVideo());
								}
								if(searchResults2[j].getBlogContent() != null)
								{
									System.out.println("Data exists for blog");
									passSearchResults[i].setBlogContent(searchResults2[j].getBlogContent());
								}
								if(searchResults2[j].getAssociation() != null)
								{
									System.out.println("Data exists for associations");
									passSearchResults[i].setAssociation(searchResults2[j].getAssociation());
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
        
		ws.async.Aggregator2Stub.BlogData[] blogdata = null;
        if(saap.getOtherResults() != null)
        {
        	blogdata = extractOtherResults(saap.getOtherResults());
        }
		
        new callNextWS().callNext(saap, passSearchResults,blogdata);
        
	}
	private ws.async.Aggregator2Stub.BlogData[] extractOtherResults(BlogData[] blogdata)
	{
		ws.async.Aggregator2Stub.BlogData[] blogdataOutput = new ws.async.Aggregator2Stub.BlogData[blogdata.length];
		
		try{
			
			for(int i=0;i<blogdata.length;i++)
			{
				
				if(blogdata[i] != null)
				{
					
					blogdataOutput[i] = new ws.async.Aggregator2Stub.BlogData();
					BlogInfo bloginfoOutput = new BlogInfo();
					
					
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
