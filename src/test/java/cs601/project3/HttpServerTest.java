package cs601.project3;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class HttpServerTest 
{
	static HttpServer server;
	HttpURLConnection connection;
	static SetUpInvertedIndex invertedinIndex;
	SearchApplication search;

	@BeforeClass
	public static void connectServer()
	{
		server = new HttpServer(7070);
		invertedinIndex = SetUpInvertedIndex.getInstance("configuration.json");
		invertedinIndex.initInvertedIndex();
		server.addMapping("/reviewsearch", new ReviewSearchHandler());
		server.addMapping("/find", new FindHandler());
		server.startServer();
	}

	@Test
	public void sampleReviewTest() 
	{
		try {
			connection = (HttpURLConnection) new URL("http://localhost:7070/reviewsearch").openConnection();
			connection.connect();
			assertEquals(connection.getResponseMessage(), "OK");
			connection.disconnect();
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		//fail("Not yet implemented");
	}

	@Test
	public void checkURLReducePathTest()
	{
		try {
			connection = (HttpURLConnection) new URL("http://localhost:7070/reviewse").openConnection();
			connection.connect();
			assertEquals(connection.getResponseCode(), 404);
			connection.disconnect();
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
	}

	@Test
	public void checkURLExtendedPathTest()
	{
		try {
			connection = (HttpURLConnection) new URL("http://localhost:7070/reviewsearchextended").openConnection();
			connection.connect();
			assertEquals(connection.getResponseCode(), 404);
			connection.disconnect();
			//System.out.println("here...");
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
	}

	@Test
	public void checkURLNumberPathTest()
	{
		try {
			connection = (HttpURLConnection) new URL("http://localhost:7070/review123earch").openConnection();
			connection.connect();
			assertEquals(connection.getResponseCode(), 404);
			connection.disconnect();
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
	}

	@Test
	public void checkSpecialCharactersURLPathTest()
	{

		try {
			connection = (HttpURLConnection) new URL("http://localhost:7070/review#$%^&+++===search").openConnection();
			connection.connect();
			assertEquals(connection.getResponseCode(), 404);
			connection.disconnect();
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
	}
	
	@Test
	public void checkEmptyURLPathTest()
	{

		try {
			connection = (HttpURLConnection) new URL("http://localhost:7070/").openConnection();
			connection.connect();
			assertEquals(connection.getResponseCode(), 404);
			connection.disconnect();
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
	}

	@Test
	public void checkWithParameters() throws MalformedURLException, IOException
	{
		FindHandler findhandle = new FindHandler();
		connection = (HttpURLConnection) new URL("http://localhost:7070/find").openConnection();
		String params = "notused=unused&asin=B0024V0I0A";
		
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
		connection.setRequestProperty("Content-Length", Integer.toString(params.length()));
		connection.setUseCaches(false);
		
		String searchQuery="", searchTerm="";
		try(PrintWriter wr = new PrintWriter(connection.getOutputStream())) 
		{
			wr.write(params);
		}
		connection.connect();
		
		assertEquals(SetUpInvertedIndex.getInstance("B0024V0I0A"), findhandle.splitData(params.split("&"), findhandle.getData("B0024V0I0A"), searchQuery, searchTerm));
	}
	
	

	@AfterClass
	public static void stopServer()
	{
		server.stopServer(false);
	}

}
