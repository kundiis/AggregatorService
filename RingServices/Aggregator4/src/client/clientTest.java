package client;
import org.apache.axis2.Constants;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.example.www.aggregator4.Search;
import org.example.www.aggregator4.SearchAppendandPass;
import org.example.www.aggregator4.SearchGoldBox;
import org.example.www.aggregator4.SearchGoldBoxResponse;
import org.example.www.aggregator4.SearchResponse;
import org.example.www.aggregator4.SearchTo;

import ws.async.Aggregator4Stub;
public class clientTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Aggregator4Stub test = new ws.async.Aggregator4Stub("http://localhost:8082/axis2/services/aggregator3");
			//SearchAppendAndPass request = new SearchAppendAndPass();
			Options options = test._getServiceClient().getOptions();
			options.setProperty(Constants.Configuration.ENABLE_MTOM,Constants.VALUE_TRUE);
			
			Search search = new Search();
			search.setIn("Kunal");
			//request.setIn("Give me services");	
			SearchResponse sresponse = test.search(search);
			SearchTo[] srch = sresponse.getOut();
			System.out.println("Results size "+srch.length);
			System.out.println(srch[0].getAuthor());
			System.out.println(srch[0].getBlogContent());
			System.out.println(srch[0].getImage());
			System.out.println(srch[0].getVideo());
			System.out.println(srch[0].getAssociation()[0]);
			//System.out.println(response.getOut());
			/*SearchGoldBox src = new SearchGoldBox();
			src.setIn("Kunal");
			SearchGoldBoxResponse sres = test.searchGoldBox(src);
			SearchTo[] srch = sres.getOut();
			System.out.println(srch[0].getImage());*/
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		


	}

}
