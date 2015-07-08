package client;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

import org.apache.axis2.Constants;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.example.www.aggregator1.BlogData;
import org.example.www.aggregator1.Search;
import org.example.www.aggregator1.SearchAppendandPass;
import org.example.www.aggregator1.SearchGoldBox;
import org.example.www.aggregator1.SearchGoldBoxResponse;
import org.example.www.aggregator1.SearchResponse;
import org.example.www.aggregator1.SearchTo;

import ws.async.Aggregator1Stub;
public class clientTest {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Random random = new Random();
			Aggregator1Stub test = new ws.async.Aggregator1Stub("http://localhost:8082/axis2/services/aggregator1");
			//SearchAppendAndPass request = new SearchAppendAndPass();
			Options options = test._getServiceClient().getOptions();
			options.setProperty(Constants.Configuration.ENABLE_MTOM,Constants.VALUE_TRUE);
			

			
			Search search = new Search();
			search.setIn("programming");
			//request.setIn("Give me services");	
			SearchResponse sresponse = test.search(search);
			SearchTo[] srch = sresponse.getOut();
			System.out.println("Results size "+srch.length);
			System.out.println(srch[0].getAuthor());
			System.out.println(srch[0].getBlogContent());
			System.out.println(srch[0].getImage());
			System.out.println(srch[0].getVideo());
			System.out.println(srch[0].getAssociation()[0]);
			
			BlogData[] blog = sresponse.getOtherResults();
			System.out.println(blog[0].getData());
			System.out.println(blog[0].getInfo().getAuthor());
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
