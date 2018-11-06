package cs601.project3;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class ReadConfigurationFile 
{
	//private SlackBotConfiguration slackConfig = new SlackBotConfiguration();
	private ConfigurationBean configuration = new ConfigurationBean();
	private Gson gson = new Gson();
	
	public  ConfigurationBean readSlackConfigurationFile(String filename)
	{
		System.out.println(filename);
		try {
			JsonReader reader = new JsonReader(new FileReader(filename));
			configuration = gson.fromJson(reader, ConfigurationBean.class);
		} catch (FileNotFoundException e) 
		{
			System.exit(0);
		}
		return configuration;
	}
}
