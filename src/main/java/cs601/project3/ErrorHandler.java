package cs601.project3;
/**
 * ErrorHandler - To handle 404 error
 * @author dhartimadeka
 *
 */
public class ErrorHandler implements Handler
{

	/**
	 * handle 404 error request and response
	 * @param resquest - pass request
	 * @param response - pass response to get response
	 * @return Response to show an html page.
	 * 
	 */
	@Override
	public Response handle(Request resquest, Response response) 
	{
		// TODO Auto-generated method stub
		response.setHeader("HTTP/1.1 404 error\n\r\n");
		response.setResponse(HtmlPages.HTML_404);
		return response;
	}

}
