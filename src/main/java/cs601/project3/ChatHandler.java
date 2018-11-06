package cs601.project3;

public class ChatHandler implements Handler
{
	
	private SlackBot slackhandler = new SlackBot();
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

	public Response handleGet(Request request, Response response)
	{
		response.setHeader("HTTP/1.0 200 OK\n" + "\r\n");
		response.setResponse(HtmlPages.HTML_CHAT_APPLICATION_FORM);
		return response;
	}

	public Response handlePost(Request request, Response response)
	{
		String msg = request.getParameter().split("=")[1].trim();
		msg = "test: " + msg;
		//slackhandler.readJsonBody();
		boolean status = slackhandler.sendMsgToSlack(msg);
		System.out.println(status);
		if(status)
		{
			response.setHeader("HTTP/1.0 200 OK\n" + "\r\n");
			response.setResponse(HtmlPages.HTML_CHAT_APPLICATION_FORM);
		}
		else
		{
			response.setHeader("HTTP/1.0  405 message not sent");
			response.setResponse(HtmlPages.HTML_405);
		}
		
		return response;
	}
}
