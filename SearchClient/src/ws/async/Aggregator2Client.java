package ws.async;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;

import javax.activation.DataHandler;

import org.apache.axis2.Constants;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;



import ws.async.Aggregator2Stub;
import ws.async.Aggregator2Stub.BlogData;
import ws.async.Aggregator2Stub.Search;
import ws.async.Aggregator2Stub.SearchResponse;
import ws.async.Aggregator2Stub.SearchTo;
public class Aggregator2Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties props = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream propsFile = classLoader.getResourceAsStream("client.properties");
		Random r = new Random();
		int RequestId = r.nextInt();
		try {
			props.load(propsFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Aggregator2Stub test = new ws.async.Aggregator2Stub(props.getProperty("aggregator2Url"));
			//SearchAppendAndPass request = new SearchAppendAndPass();
			Options options = test._getServiceClient().getOptions();
			options.setProperty(Constants.Configuration.ENABLE_MTOM,Constants.VALUE_TRUE);
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			System.out.println("Enter the keyword to search :");
			String s = br.readLine();
			System.out.println("Waiting for the response...");
			long starttime = System.currentTimeMillis();
			Search search = new Search();
			search.setIn(s);
			//request.setIn("Give me services");	
			SearchResponse sresponse = test.search(search);
			SearchTo[] srch = sresponse.getOut();
			for(int i=0;i<srch.length;i++)
			{
				if(srch[i].getAuthor() == null)
				{
					System.out.println("No results found for the keyword");
					break;
				}
				File file = new File(props.getProperty("OutputResultLocation")+srch[i].getAuthor()+RequestId);
				if(file.mkdirs())
				{
					System.out.println("Created directories :"+file.getAbsolutePath());
					
				}
				System.out.println("Author: "+srch[i].getAuthor());
				System.out.println("Type: "+srch[i].getType());
				System.out.println("Time: "+srch[i].getTime());
				if(srch[i].getVideo() != null)
				{
					System.out.println("Video Results present");
					DataHandler dhout = srch[i].getVideo();
					FileOutputStream fsout = new FileOutputStream(file.getAbsolutePath()+"\\"+srch[i].getAuthor()+"video"+i+".avi");
					dhout.writeTo(fsout);
				}
				if(srch[i].getImage() != null)
				{
					System.out.println("Image Results present");
					DataHandler dhout = srch[i].getImage();
					FileOutputStream fsout = new FileOutputStream(file.getAbsolutePath()+"\\"+srch[i].getAuthor()+"image"+i+".jpg");
					dhout.writeTo(fsout);
				}
				if(srch[i].getBlogContent() != null)
				{
					System.out.println("Blog Results present");
					FileOutputStream fsout = new FileOutputStream(file.getAbsolutePath()+"\\"+srch[i].getAuthor()+"file"+i+".txt");
					OutputStreamWriter osw = new OutputStreamWriter(fsout);
					osw.write(srch[i].getBlogContent());
					osw.close();
				}
				System.out.println("Associations for "+srch[i].getAuthor());
				for(int j=0;j<srch[i].getAssociation().length;j++)
				{
					System.out.println(srch[i].getAssociation()[j]);
				}
				System.out.println("-----------------------------------------------");
						
			}
						
			BlogData[] blog = sresponse.getOtherResults();
			
			for(int i=0;i<blog.length;i++)
			{
				if(blog[i] != null)
				{
					File file = new File(props.getProperty("OutputResultLocation")+"OtherResults\\"+blog[i].getInfo().getAuthor()+RequestId);
					if(file.mkdirs())
					{
						System.out.println("Created directories :"+file.getAbsolutePath());
						
					}
					System.out.println("Results from external service-------");
					System.out.println("Author: "+blog[i].getInfo().getAuthor());
					
					
					if(blog[i].getData() != null)
					{
						DataHandler dhout = blog[i].getData();
						FileOutputStream fsout = new FileOutputStream(file.getAbsolutePath()+"\\"+blog[i].getInfo().getAuthor()+i+".txt");
						dhout.writeTo(fsout);
					}
					
				}
			}
			System.out.println("Request served in "+(System.currentTimeMillis()-starttime)+" milliseconds");
			
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		


	}

}
