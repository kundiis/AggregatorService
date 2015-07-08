package ws.async;

import org.apache.axis2.AxisFault;
import org.example.www.aggregator3.SearchAppendandPass;
import org.example.www.aggregator3.SearchTo;

import ws.async.Aggregator4Stub.BlogData;

public class callNextWS {
	
	public void callNext(SearchAppendandPass saap, ws.async.Aggregator4Stub.SearchTo[] passSearchResults, BlogData[] blogdata){
		
		//simply call the next WS method setting the flag and decrementing the counter
		// unpack everything and then pack it
		if(saap.getOriginID()==3)
			saap.setCounter(3);  // decrement the counter
		else 
			saap.setCounter(saap.getCounter()-1); // else take the counter and decrement it
		
		
		
		String nextServiceUrl = new LoadProperties().getNextAggregatorUrl();
		System.out.println("Now calling Fourth WS");
		
		try {
			if(passSearchResults != null)
			{
				Aggregator4Stub secondWS = new ws.async.Aggregator4Stub(nextServiceUrl);
				ws.async.Aggregator4Stub.SearchAppendandPass secondSaap = new ws.async.Aggregator4Stub.SearchAppendandPass();
				
				secondSaap.setKeyword(saap.getKeyword());
				secondSaap.setCounter(saap.getCounter());
				secondSaap.setOriginID(saap.getOriginID());
				secondSaap.setUniqueId(saap.getUniqueId());
				System.out.println("Counter value "+saap.getCounter());
				secondSaap.setIn(passSearchResults);
				if(blogdata != null)
				{
					System.out.println("Other results are not null");
					secondSaap.setOtherResults(blogdata);
				}
				
				secondWS.searchAppendandPass(secondSaap);
				System.out.println("--------------------------------------");
			}
			else{
				saap.setCounter(0);
				System.out.println("No results found. Making internal call...");
				Aggregator3Implementation internalcall = new Aggregator3Implementation();
				internalcall.searchAppendandPass(saap);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}
