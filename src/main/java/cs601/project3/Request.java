package cs601.project3;

import java.util.HashMap;

public class Request 
{
	// get request from client
	private String request;
	private String parameter;
	private String protocol;
	
	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	
	public boolean validRequest(String request)
	{
		boolean result = false;
		if(request.split(" ").length == 3)
		{
			result = true;
		}
		return result;
	}
	
	public String validMethod(String request)
	{
		String method = request;
		if(request.split(" ")[0].equals("GET"))
		{
			method = "GET";
		}
		else if(request.split(" ")[0].equals("POST"))
		{
			method = "POST";
		}
		return method;
	}
	
	public Handler isPathValid(String requestPath, HashMap<String, Handler> handlersMap, Response response)
	{
		Handler handler = new ErrorHandler();
		//System.out.println("requestpath = " +requestPath);
		for (String path : handlersMap.keySet()) {
			if (requestPath.contains(path)) 
			{
				//System.out.println("here.........." + path);
				handler = handlersMap.get(path);
			}
//			else
//			{
//				response.setHeader("HTTP 1.0/ 404 Page not found \r\n");
//				response.setResponse(HtmlPages.HTML_404);
//			}
		}
		return handler;
	}
	
	public boolean validProtocol(String protocol)
	{
		boolean result = false;
		if(protocol.equals("HTTP/1.0") || protocol.equals("HTTP/1.1"))
		{
			result = true;
		}
		return result;
	}

}
