package ws.async;

import org.apache.axis2.AxisFault;
import org.example.www.aggregator2.SearchAppendandPass;
import org.example.www.aggregator2.SearchTo;

import ws.async.Aggregator3Stub.BlogData;

public class callNextWS {
	
	public void callNext(SearchAppendandPass saap, ws.async.Aggregator3Stub.SearchTo[] passSearchResults, BlogData[] blogdata){
		
		//simply call the next WS method setting the flag and decrementing the counter
		// unpack everything and then pack it
		if(saap.getOriginID()==2)
			saap.setCounter(3);  // decrement the counter
		else 
			saap.setCounter(saap.getCounter()-1); // else take the counter and decrement it
		
		//SearchTo[] searchResults = saap.getIn();
		
		String nextServiceUrl = new LoadProperties().getNextAggregatorUrl();
		System.out.println("Now calling third WS");
		
		try {
			if(passSearchResults != null)
			{
				Aggregator3Stub secondWS = new ws.async.Aggregator3Stub(nextServiceUrl);
				ws.async.Aggregator3Stub.SearchAppendandPass secondSaap = new ws.async.Aggregator3Stub.SearchAppendandPass();
				
				secondSaap.setKeyword(saap.getKeyword());
				secondSaap.setCounter(saap.getCounter());
				secondSaap.setOriginID(saap.getOriginID());
				secondSaap.setUniqueId(saap.getUniqueId());
				System.out.println("Counter value "+saap.getCounter());
				secondSaap.setIn(passSearchResults);
				if(blogdata != null)
				{
					System.out.println("Other results for blog data exists...");
					secondSaap.setOtherResults(blogdata);
				}
				secondWS.searchAppendandPass(secondSaap);
				System.out.println("--------------------------------------");
			}
			else{
				saap.setCounter(0);
				System.out.println("No results found. Making internal call...");
				Aggregator2Implementation internalcall = new Aggregator2Implementation();
				internalcall.searchAppendandPass(saap);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}
