package cs601.project3;

import java.io.PrintWriter;

public class HtmlResponse 
{
	private String headers, page;
	
	public void httpResponse(PrintWriter writer, boolean httpResponseFlag)
	{
		//System.out.println(httpResponseFlag);
		if(httpResponseFlag)
		{
			headers = "HTTP/1.0 405 Method Not Allowed\n" +
					"\r\n";

			page = "<html> " + 
					"<head><title>TEST</title></head>" + 
					"<body>Dont support methods other then GET and POST.</body>" + 
					"</html>";
//			headers = "HTTP/1.0 200 OK\n" +
//					"\r\n";
//
//			page = "<html> " + 
//					"<head><title>TEST</title></head>" + 
//					"<body>This is a short test page.</body>" + 
//					"</html>";
		}
		//here to do....
		headers = "HTTP/1.0 200 OK\n" +
				"\r\n";


		page = "<html><head><title></title></head>"+
				"<body>"+
				"<form  method='POST'>" +
				"Enter text: <input type='text' name='fname'><br>"+
				"<input type='submit' value='Submit'>"+
				"</form></body></html>";
//		page = "<html> " + 
//				"<head><title>TEST</title></head>" + 
//				"<body>This is a short test page.</body>" + 
//				"</html>";
//		else
//		{
////			headers = "HTTP/1.0 405 Method Not Allowed\n" +
////					"\r\n";
////
////			page = "<html> " + 
////					"<head><title>TEST</title></head>" + 
////					"<body>Dont support methods other then GET and POST.</body>" + 
////					"</html>";
		
		//write back to output screen
		writer.write(headers);
		writer.write(page);
		writer.flush();
	}
	
}
