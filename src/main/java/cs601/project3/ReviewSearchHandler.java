package cs601.project3;

public class ReviewSearchHandler implements Handler 
{
	
	@Override
	public void handle(Request request, Response response) 
	{
		//check get or post
		if(request.getRequest().equals("GET"))
		{
			response.setHeader("HTTP/1.0 200 OK\n" + "\r\n");
			response.setResponse("<html><head><title></title></head>"+
					"<body>"+
					"<form  method='POST'>" +
					"Enter text: <input type='text' name='fname'><br>"+
					"<input type='submit' value='Submit'>"+
					"</form></body></html>");
		}
		else if (request.getRequest().equals("POST"))
		{
			response.setHeader("HTTP/1.0 200 OK\n" + "\r\n");
			response.setResponse("<html><head><title></title></head>"+"<body>"+
					"<form><p>This is post page</p></form></body></html>");
		}
		else
		{
			response.setHeader("HTTP/1.0 405 Method Not Allowed\n" + "\r\n");
			response.setResponse("<html><head><title></title></head>"+"<body><h1>405 BAD METHOD PASSED !! </h1></body></html>");
		}
	}
//		//split request line using space....
//		String requestmethod[] = request.getRequest().split("\\s+");
//		request.setProtocol(requestmethod[0]);
//		request.setRequestPath(requestmethod[1]);
//		//System.out.println(request.getRequestPath().toString());
//		// TODO Auto-generated method stub
//		if((request.getProtocol().equals("GET") && request.getRequestPath().equals("/reviewsearch")))// || request.getRequest().equals("POST"))
//		{
//			//call reviewsearch handler.....
//			response.setHeader("HTTP/1.0 200 OK\n" + "\r\n");
//			
//			response.setResponse("<html><head><title></title></head>"+
//					"<body>"+
//					"<form  method='POST'>" +
//					"Enter text: <input type='text' name='fname'><br>"+
//					"<input type='submit' value='Submit'>"+
//					"</form></body></html>");
//		}
//		else if (request.getProtocol().equals("POST") && request.getRequestPath().equals("/reviewsearch"))
//		{
//			//call review handler.....
//			response.setHeader("HTTP/1.0 200 OK\n" + "\r\n");
//			response.setResponse("<html><head><title></title></head>"+"<body>"+
//					"<form  method='POST'><p>This is post page</p></form></body></html>");
//		}
//		else
//		{
//			//bad page....
//			response.setHeader("HTTP/1.0 405 Method Not Allowed\n" + "\r\n");
//			response.setResponse("<html><head><title></title></head>"+"<body><h1>405 BAD METHOD PASSED !! </h1></body></html>");
//		}
		
	//}

}
