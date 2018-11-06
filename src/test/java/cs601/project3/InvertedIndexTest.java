package cs601.project3;
//import cs601.project1.*;
//import cs601.project3.*;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

//import javax.net.ssl.HttpsURLConnection;

import org.junit.Before;
import org.junit.Test;

//import cs601.project1.InvertedIndexBuilder;

public class InvertedIndexTest 
{
	HttpServer server;
	HttpURLConnection connection;
	SetUpInvertedIndex invertedinIndex;
	SearchApplication search;
	
	@Before 
	public void preProcess()
	{
		server = new HttpServer(2020);
		invertedinIndex = invertedinIndex.getInstance("configuration.json");
		invertedinIndex.initInvertedIndex();
		server.addMapping("/review", new ReviewSearchHandler());
		server.addMapping("/find", new FindHandler());
		server.startServer();
	}
	
	@Test
	public void sampleReviewTest() 
	{
		
		try {
			connection = (HttpURLConnection) new URL("http://localhost:2020/reviewsearch").openConnection();
			connection.connect();
			assertEquals(connection.getResponseMessage(), "OK");
			connection.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void reviewSearchCamelCaseTest()
	{
		try
		{
			connection = (HttpURLConnection) new URL("http://localhost:2020/ReviewSearch").openConnection();
			connection.connect();
			assertEquals(connection.getResponseCode(), 404);
			connection.disconnect();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public String checkResponse(URLConnection connection)throws IOException
	{
		String line, response = "";
			connection = (HttpURLConnection) new URL("http://localhost:2020/reviewsearch").openConnection();
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			while((line = reader.readLine()) != null)
			{
				response += line;
				//System.out.println(response);
			}
			return response;
	}
	
	@Test
	public void checkFormInResponseTest()
	{
		
		try {
			connection = (HttpURLConnection) new URL("http://localhost:2020/reviewsearch").openConnection();
			System.out.println(connection);
			connection.connect();
			//System.out.print(connection.connect());
			System.out.println(checkResponse(connection));
			assertTrue(checkResponse(connection).contains("<form>"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	@Test
//	public void checkReviewParameterTest()
//	{
//		try
//		{
//			connec
//		}
//	}
	
	//@Test
//	public void reviewSearchCheckResponseTest()
//	{
//		connection = (HttpURLConnection) new URL("http://localhost:2020/reviewsearch").openConnection();
//		connection.connect();
//		//assertEquals(, );
//	}

}
