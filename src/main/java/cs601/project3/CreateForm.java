package cs601.project3;

public class CreateForm 
{

	
	String headers = "HTTP/1.0 200 OK \n" +
			"\r\n";

	String page = "<html><head><title></title></head>"+
			"<body>"+
			"<form  method='POST'>" +
			"Enter text: <input type='text' name='fname'><br>"+
			"<input type='submit' value='Submit'>"+
			"</form></body></html>";

}
