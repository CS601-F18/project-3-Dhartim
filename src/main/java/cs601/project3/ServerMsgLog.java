package cs601.project3;

public interface ServerMsgLog {
	
	String serverListening = "Server is Up, listing at port : %s";
	String serverUp = "Server started";
	String serverNotStarted = "Unable to Start Server at port : %s";
	String serverThreadStop = "Unable to start server thread";
	String serverMethodUrlReq = "Server Init : %s";
	String serverLog = "Server : %s";
	String serverErrorLog = "Server Issue : %s";
	String inavalidUrl = "Invalid URL : %s";
	String method = "%s method invoked";
	String postedData = "Posted data : %s";
	String serverWriterClosed = "Server Writer Closed";
	

}
