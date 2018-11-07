package cs601.project3;

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
		}
		else if(request.getRequest().equals("POST"))
		{
			handlePost(request, response);
		}
		else
		{
			response.setHeader("HTTP/1.0 405 Method Not Allowed");
			response.setResponse(HtmlPages.HTML_405);
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
		String msg = request.getParameter().split("=")[1].trim();
		msg = "test: " + msg;
		//slackhandler.readJsonBody();
		logger.log(Level.INFO, "Send message to slack");
		boolean status = slackhandler.sendMsgToSlack(msg);
		System.out.println(status);
		if(status)
		{
			response.setHeader("HTTP/1.0 200 OK\n" + "\r\n");
			response.setResponse(HtmlPages.HTML_CHAT_APPLICATION_FORM);
		}
		else
		{
			response.setHeader("HTTP/1.1 404 error\n\r\n");
			response.setResponse(HtmlPages.HTML_404);
		}

		return response;
	}
}
