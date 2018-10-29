package cs601.project3;

public class Request 
{
	//get request from client
	private String request;
	private String parameter;
	
//	private String protocol;
//	private String requestPath;
	
public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	//	public String getProtocol() {
//		return protocol;
//	}
//	public void setProtocol(String protocol) {
//		this.protocol = protocol;
//	}
//	public String getRequestPath() {
//		return requestPath;
//	}
//	public void setRequestPath(String requestPath) {
//		this.requestPath = requestPath;
//	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
}
