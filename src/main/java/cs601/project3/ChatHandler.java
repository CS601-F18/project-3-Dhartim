package cs601.project3;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author dhartimadeka
 *ChatHandler will handle get and post request of chat application.
 */
public class ChatHandler implements Handler
{
	private final static Logger logger =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 
	private Charset fileEncoding = Charset.forName("ISO-8859-1");
	private SlackBot slackhandler = new SlackBot();
	/**
	 * handle - handles get and post methods
	 * @param request - Pass Http Request
	 * @param response - pass Http response
	 * @return A response object is passed.
	 */
	@Override
	public Response handle(Request request, Response response) 
	{
		// TODO Auto-generated method stub
		if(request.getRequest().equals("GET"))
		{
			handleGet(request, response);
			logger.log(Level.INFO, "handling get request");
		}
		else if(request.getRequest().equals("POST"))
		{
			handlePost(request, response);
			logger.log(Level.INFO, "handling POST request");
		}
		else
		{
			response.setHeader("HTTP/1.0 405 Method Not Allowed");
			response.setResponse(HtmlPages.HTML_405);
			logger.log(Level.INFO, "BAD METHOD");
		}
		return response;

	}
	/**
	 * handleGet - handles get request
	 * @param request - pass http request
	 * @param response - pass http response
	 * @return http response object
	 */

	public Response handleGet(Request request, Response response)
	{
		response.setHeader("HTTP/1.0 200 OK\n" + "\r\n");
		response.setResponseCode("200");
		response.setResponse(HtmlPages.HTML_CHAT_APPLICATION_FORM);
		return response;
	}

	/**
	 * handlePost - handle post request
	 * @param request - pass http request
	 * @param response - pass http response
	 * @return
	 */
	public Response handlePost(Request request, Response response)
	{
		//System.out.println(request.getParameter());
		boolean status = false;
		String postData="", searchQuery = "", searchTerm ="";
		try 
		{
			if(request.getParameter() != null)
			{
				postData = URLDecoder.decode(request.getParameter(), fileEncoding.toString());
				if(postData.split("&").length > 1 && !(postData.isEmpty()))
				{
					String eachMessage[] = postData.split("&");
					for(String messages : eachMessage )
					{
						//System.out.println(messages);
						status = splitMessage(messages.split("="), searchQuery, searchTerm);
						if(status == true)
						{
							break;
						}
					}
				}
				else if(postData.contains("=") && !(postData.isEmpty()))
				{
					status = splitMessage(postData.split("="), searchQuery, searchTerm);
				}
				else
				{
					status =false;
				}
	
				logger.log(Level.INFO, "Send message to slack");
				//System.out.println(status);
				if(status)
				{
					response.setHeader("HTTP/1.0 200 OK\n" + "\r\n");
					response.setResponseCode("200");
					response.setResponse(HtmlPages.HTML_CHAT_APPLICATION_FORM);
					System.out.println("Message sent successfully !!!");
				}
				else
				{
					response.setHeader("HTTP/1.1 404 error\n\r\n");
					response.setResponseCode("404");
					response.setResponse(HtmlPages.HTML_404);
				}
			}
			else
			{
				response.setHeader("HTTP/1.1 404 error\n\r\n");
				response.setResponseCode("404");
				response.setResponse(HtmlPages.HTML_404);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * splitmessage - Will take list of parameters from request and manipulate it to give list of data from inverted index 
	 * @param parameters - list of parameters
	 * @param result - it has result of list
	 * @param searchQuery - will store query
	 * @param searchTerm - will store term to search in inverted index
	 * @return
	 */
	public boolean splitMessage(String parameters[], String searchQuery, String searchTerm) 
	{
		boolean status = false;
		// int counter = 0;
		if (parameters.length > 1) 
		{
			searchQuery = parameters[0];
			searchTerm = parameters[1];
			System.out.println(searchTerm);
			if (searchQuery.equals("message") && !(searchTerm.isEmpty())) 
			{
				//System.out.println(searchTerm);
				status = slackhandler.sendMsgToSlack(searchTerm);
				//System.out.println(status);
			}
		}
		return status;
	}
}