package cs601.project3;

import static org.junit.Assert.*;

import org.junit.Test;

public class RequestTest {

	Request request = new Request();
	Response response = new Response();
	@Test
	public void testRequestLinePass() 
	{
		
		assertTrue(request.validRequest("GET /reviewsearch HTTP1.0"));
	}
	
	@Test
	public void testRequestLineMultipleSpace()
	{
		assertFalse(request.validRequest("GET    /reviewsearch HTTP1.0"));
	}
	
	@Test
	public void testRequestLineLength()
	{
		assertFalse(request.validRequest("GET /reviewsearch HTTP 1.0"));
	}
	@Test
	public void testGETRequestMethod()
	{
		assertEquals("GET", request.validMethod("GET"));
	}
	
	@Test
	public void testPOSTRequestMethod()
	{
		assertEquals("POST", request.validMethod("POST"));
	}
	
	@Test
	public void testOtherRequestMethod()
	{
		assertEquals("PUT", request.validMethod("PUT"));
	}
	
	
//	public void testRequestPath()
//	{
//		assertEquals(expected, request.isPathValid("/reviewsearch", , response));
//	}
}
