package cs601.project3;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import cs601.project3.ConfigurationBean;

public class ChatApplicationTest {
	static String[] args = new String[]{"configuration.json"};
	static Request request;
	static Response response = new Response();
	static HttpServer server;
	static SlackBot slackBot;
	static ChatHandler handle;
	static ReadConfigurationFile readconfigfile;
	static ConfigurationBean config;
	
	@BeforeClass
	public static void connection()
	{
		server = new HttpServer(1701);
		readconfigfile = new ReadConfigurationFile();
		config = readconfigfile.readJsonFile(args[0]);
		server.addMapping("/slackbot", new ChatHandler());
		request = new Request();
		response = new Response();
		handle = new ChatHandler();
	}
	
	@Test
	public void checkSimpleGetRequestTest() 
	{
		request.setRequest("http://localhost:1701/slackbot");
		response = handle.handleGet(request, response);
		assertEquals("200", response.getResponseCode());
	}
	
	@Test
	public void checkReduceRequestTest() 
	{
		request.setRequest("http://localhost:1701/slacot");
		response = handle.handlePost(request, response);
		assertEquals("404", response.getResponseCode());
	}
	
	@Test
	public void checkExtendedRequestTest() 
	{
		
		request.setRequest("http://localhost:1701/slacoteewre3432");
		response = handle.handlePost(request, response);
		assertEquals("404", response.getResponseCode());
	}
	
	
	@Test
	public void checkEmptyRequestTest() 
	{
		
		request.setRequest("http://localhost:1701/");
		response = handle.handlePost(request, response);
		assertEquals("404", response.getResponseCode());
	}
	
	@Test
	public void invalidMethodTest()
	{
		boolean status =  request.validMethod("PUT");
		response = handle.handle(request, response);
		assertFalse(status);
	}
	
	@Test
	public void validMethodTest()
	{
		boolean status =  request.validMethod("POST");
		response = handle.handle(request, response);
		assertTrue(status);
	}
	

	@AfterClass
	public static void stopServer()
	{
		server.stopServer(false);
	}
}