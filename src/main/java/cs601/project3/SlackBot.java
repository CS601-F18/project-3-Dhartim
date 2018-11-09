package cs601.project3;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
//import java.net.URLEncoder;
import java.nio.charset.Charset;

import javax.net.ssl.HttpsURLConnection;
/**
 * SlackBot - pss message to slack boy.
 * @author dhartimadeka
 *
 */
public class SlackBot 
{
	private static String configurationfile;
	private ConfigurationBean configuration = new ConfigurationBean();
	private ReadConfigurationFile readConfigFile = new ReadConfigurationFile();
	public SlackBot() {
		// TODO Auto-generated constructor stub
	}
	public SlackBot(String filePath) 
	{
		// TODO Auto-generated constructor stub
		SlackBot.configurationfile = filePath;
	}
	
	/**
	 * sendMsgToSlack - pass message to slack
	 * @param message - message to be send into slack
	 * @return
	 */
	public boolean sendMsgToSlack(String message)
	{
		configuration = readConfigFile.readJsonFile(configurationfile);
		//get text from url in encoded form so has to decode it....
		Charset fileEncoding = Charset.forName("ISO-8859-1");
		
		HttpsURLConnection connection;
		//create URL object
		URL url;
		try {
			//decoding msg and passing it
			String encodemsg = URLDecoder.decode(message, fileEncoding.toString());
			//System.out.println(encodemsg);
			url = new URL(configuration.getUrl());
			//create secure connection 
			connection = (HttpsURLConnection)url.openConnection();
			//set HTTP method
			connection = setConnectionProperties(connection, url);
			String jsonBody = "{\"text\":\"" + encodemsg + "\", " + "\"token\": \""
					+ configuration.getToken()+
					"\", \"channel\":"
	                + "\"" + configuration.getChannel()+ "\"}";
			connection.setDoOutput(true);
			OutputStream outputstream = connection.getOutputStream();
			outputstream.write(jsonBody.getBytes());
			outputstream.flush();
			int responseCode = connection.getResponseCode();
			System.out.println(responseCode);
			if(responseCode == 200)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return false;

	}
	
	public HttpsURLConnection setConnectionProperties(HttpsURLConnection connection, URL url)
	{
		try {
			connection = (HttpsURLConnection)url.openConnection();
			connection.setRequestMethod("POST");	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//set HTTP method
		connection.setRequestProperty("Content-Type", configuration.getContentType());	
		connection.setRequestProperty("Authorization", "Bearer " + configuration.getToken());
		return connection;
	}
}