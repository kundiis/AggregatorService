package ws.async;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {
	private String bootstrapUrl = null;
	private String nextAggregatorUrl = null;
	public LoadProperties()
	{
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		InputStream propertiesFile = null;

		try {
			propertiesFile = classLoader.getResourceAsStream("aggregator4.properties");
			properties.load(propertiesFile);
		} catch (FileNotFoundException e) {
			System.out.println("Configuration file cannot be found.");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.bootstrapUrl = properties.getProperty("voldemortUrl");
		this.nextAggregatorUrl = properties.getProperty("nextServiceUrl");
		
	}
	public String getBootstrapUrl()
	{
		
		return bootstrapUrl;
	}
	public String getNextAggregatorUrl()
	{
		return nextAggregatorUrl;
	}
	
	
}
