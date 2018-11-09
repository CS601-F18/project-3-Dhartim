package cs601.project3;
/**
 * HtmlPages - Interface with html pages to implement as per response from server 
 * @author dhartimadeka
 *
 */
public interface HtmlPages {
	
	String HTML_405 = "<!DOCTYPE html>\r\n" + 
			"<html>\r\n" + 
			"<head>\r\n" + 
			"<meta charset=\"ISO-8859-1\" />\r\n" + 
			"<title>405</title>\r\n" + 
			"</head>\r\n" + 
			"<body>\r\n" + 
			"	<h1>405 BAD METHOD PASSED !!</h1>\r\n" + 
			"</body>\r\n" + 
			"</html>";

	String HTML_404 =  
			"<html>\r\n" + 
			"<head>\r\n" + 
			"<meta charset=\"ISO-8859-1\" />\r\n" + 
			"<title>404</title>\r\n" + 
			"</head>\r\n" + 
			"<body>\r\n" + 
			"	<h1>404 Page Not found :(</h1>\r\n" + 
			"</body>\r\n" + 
			"</html>";
	
	String HTML_EMPTY_MANDATORY_FIELD = "<!DOCTYPE html>\r\n" + 
			"<html>\r\n" + 
			"<head>\r\n" + 
			"<meta charset=\"ISO-8859-1\"/>\r\n" + 
			"<title>404</title>\r\n" + 
			"</head>\r\n" + 
			"<body>\r\n" + 
			"	<h1>404 Page Not found :(</h1>\r\n" + 
			"</body>\r\n" + 
			"</html>";
	
	String HTML_FIND_FORM = "<!DOCTYPE html>\r\n" + 
			"<html>\r\n" + 
			"<head>\r\n" + 
			"<meta charset=\"ISO-8859-1\"/>\r\n" + 
			"<title>Find Asin</title>\r\n" + 
			"</head>\r\n" + 
			"<body>\r\n" +
			"<h1>Find Asin Search </h1>"+
			"	<form action='/find' method='POST'>\r\n" + 
			"		Enter text: <input type='text' name='asin' required='required' /><br> \r\n" + 
			"		<input type='submit' value='Submit' />\r\n" +  
			"	</form>\r\n" + 
			"</body>\r\n" + 
			"</html>";

	String HTML_REVIEW_SEARCH_FORM = "<!DOCTYPE html>\r\n" + 
			"<html>\r\n" + 
			"<head>\r\n" + 
			"<meta charset=\"ISO-8859-1\"/>\r\n" + 
			"<title>Review Search</title>\r\n" + 
			"</head>\r\n" + 
			"<body>\r\n" + 
			"<h1>Review Search </h1>"+
			"	<form  action='/reviewsearch' method='POST'>\r\n" + 
			"		Enter text: <input type='text' name='query' required='required' /><br/> <input\r\n" + 
			"			type='submit' value='Submit' />\r\n" + 
	
			"	</form>\r\n" + 
			"</body>\r\n" + 
			"</html>";
	
	String HTML_CHAT_APPLICATION_FORM = "<!DOCTYPE html>\r\n" + 
			"<html>\r\n" + 
			"<head>\r\n" + 
			"<meta charset=\"ISO-8859-1\"/>\r\n" + 
			"<title>Chat Application</title>\r\n" + 
			"</head>\r\n" + 
			"<body>\r\n" + 
			"<h1>Chat application Message </h1>"+
			"	<form  action='/slackbot' method='POST'>\r\n" + 
			"		Enter text: <input type='text' name='message' required='required' /><br/> <input\r\n" + 
			"			type='submit' value='Submit' />\r\n" + 
	
			"	</form>\r\n" + 
			"</body>\r\n" + 
			"</html>";

}
