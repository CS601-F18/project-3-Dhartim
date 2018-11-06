package cs601.project3;

public class ErrorHandler implements Handler
{

	@Override
	public Response handle(Request resquest, Response response) 
	{
		// TODO Auto-generated method stub
		response.setHeader("HTTP/1.1 404 error\n\r\n");
		response.setResponse(HtmlPages.HTML_404);
		return response;
	}

}
