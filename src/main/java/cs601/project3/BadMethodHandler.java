package cs601.project3;

public class BadMethodHandler implements Handler
{

	@Override
	public Response handle(Request resquest, Response response) 
	{
		response.setResponseCode("405");
		response.setHeader("HTTP/1.0 405 Method Not Allowed\n" + "\r\n");
		response.setResponse(HtmlPages.HTML_405);
		return response;
	}

}
