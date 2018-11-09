package cs601.project3;

import java.util.HashMap;
/**
 * Request - mentions HttpRequest and parts of it.
 * @author dhartimadeka
 *
 */
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
	/**
	 * validMethod - check if method is get or post
	 * @param request -pass request and validate it.
	 * @return method - either a post or Get
	 */
	public boolean validMethod(String request)
	{
		//String method = request;
		boolean result = false;
		if(request.split(" ")[0].equals("GET"))
		{
			this.setRequest("GET"); //= "GET";
			result = true;
		}
		else if(request.split(" ")[0].equals("POST"))
		{
			this.setRequest("POST"); //= "POST";
			result = true;
		}
		return result;
	}
	/**
	 * isPathValid - check if there is same api or not.
	 * @param requestPath - pass path of request api
	 * @param handlersMap - handles api with handlers into hashmap
	 * @param response - handle respective responses
	 * @return
	 */
	public Handler isPathValid(String requestPath, HashMap<String, Handler> handlersMap, Response response)
	{
		Handler handler = new ErrorHandler();
		
		for (String path : handlersMap.keySet()) 
		{
			if (requestPath.equals(path)) 
			{
				handler = handlersMap.get(path);
				//response.setHeader("HTTP/1.0 200 OK\n" + "\r\n");
			}
		}
		return handler;
	}
	/**
	 * validProtocol - check if it is http/1.0 or http/1.1
	 * @param protocol - pass protocol 
	 * @return
	 */
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
