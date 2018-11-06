package cs601.project3;

public interface HtmlPages {
	
//	String CSS_SEARCH_RESULT = "<style>\r\n" + 
//			"#reviewdata {\r\n" + 
//			"	font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;\r\n" + 
//			"	border-collapse: collapse;\r\n" + 
//			"	width: 100%;\r\n" + 
//			"}\r\n" + 
//			"\r\n" + 
//			"#reviewdata td, #reviewdata th {\r\n" + 
//			"	border: 1px solid #ddd;\r\n" + 
//			"	padding: 8px;\r\n" + 
//			"}\r\n" + 
//			"\r\n" + 
//			"#reviewdata tr:nth-child(even) {\r\n" + 
//			"	background-color: #f2f2f2;\r\n" + 
//			"}\r\n" + 
//			"\r\n" + 
//			"#reviewdata tr:hover {\r\n" + 
//			"	background-color: #ddd;\r\n" + 
//			"}\r\n" + 
//			"\r\n" + 
//			"#reviewdata th {\r\n" + 
//			"	padding-top: 12px;\r\n" + 
//			"	padding-bottom: 12px;\r\n" + 
//			"	text-align: left;\r\n" + 
//			"	background-color: #4CAF50;\r\n" + 
//			"	color: white;\r\n" + 
//			"}\r\n" + 
//			"\r\n" + 
//			"#query {\r\n" + 
//			"	font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;\r\n" + 
//			"	padding: 8px;\r\n" + 
//			"}\r\n" + 
//			"</style>";
	
	String HTML_405 = "<!DOCTYPE html>\r\n" + 
			"<html>\r\n" + 
			"<head>\r\n" + 
			"<meta charset=\"ISO-8859-1\">\r\n" + 
			"<title>405</title>\r\n" + 
			"</head>\r\n" + 
			"<body>\r\n" + 
			"	<h1>405 BAD METHOD PASSED !!</h1>\r\n" + 
			"</body>\r\n" + 
			"</html>";

	String HTML_404 =  
			"<html>\r\n" + 
			"<head>\r\n" + 
			"<meta charset=\"ISO-8859-1\">\r\n" + 
			"<title>404</title>\r\n" + 
			"</head>\r\n" + 
			"<body>\r\n" + 
			"	<h1>404 Page Not found :(</h1>\r\n" + 
			"</body>\r\n" + 
			"</html>";
	
	String HTML_EMPTY_MANDATORY_FIELD = "<!DOCTYPE html>\r\n" + 
			"<html>\r\n" + 
			"<head>\r\n" + 
			"<meta charset=\"ISO-8859-1\">\r\n" + 
			"<title>404</title>\r\n" + 
			"</head>\r\n" + 
			"<body>\r\n" + 
			"	<h1>404 Page Not found :(</h1>\r\n" + 
			"</body>\r\n" + 
			"</html>";
	
	String HTML_FIND_FORM = "<!DOCTYPE html>\r\n" + 
			"<html>\r\n" + 
			"<head>\r\n" + 
			"<meta charset=\"ISO-8859-1\">\r\n" + 
			"<title>Find Asin</title>\r\n" + 
			"</head>\r\n" + 
			"<body>\r\n" +
			"<h1>Find Asin Search </h1>"+
			"	<form action='/find' method='POST'>\r\n" + 
			"		Enter text: <input type='text' name='asin'><br> \r\n" + 
			"		<input type='submit' value='Submit'>\r\n" +  
			"	</form>\r\n" + 
			"</body>\r\n" + 
			"</html>";

	String HTML_REVIEW_SEARCH_FORM = "<!DOCTYPE html>\r\n" + 
			"<html>\r\n" + 
			"<head>\r\n" + 
			"<meta charset=\"ISO-8859-1\">\r\n" + 
			"<title>Review Search</title>\r\n" + 
			"</head>\r\n" + 
			"<body>\r\n" + 
			"<h1>Review Search </h1>"+
			"	<form  action='/reviewsearch' method='POST'>\r\n" + 
			"		Enter text: <input type='text' name='query' required><br> <input\r\n" + 
			"			type='submit' value='Submit'>\r\n" + 
	
			"	</form>\r\n" + 
			"</body>\r\n" + 
			"</html>";
	
	String HTML_CHAT_APPLICATION_FORM = "<!DOCTYPE html>\r\n" + 
			"<html>\r\n" + 
			"<head>\r\n" + 
			"<meta charset=\"ISO-8859-1\">\r\n" + 
			"<title>Chat Application</title>\r\n" + 
			"</head>\r\n" + 
			"<body>\r\n" + 
			"<h1>Chat application Message </h1>"+
			"	<form  action='/slackbot' method='POST'>\r\n" + 
			"		Enter text: <input type='text' name='message' required><br> <input\r\n" + 
			"			type='submit' value='Submit'>\r\n" + 
	
			"	</form>\r\n" + 
			"</body>\r\n" + 
			"</html>";
	
//	String HTML_SEARCH_RESULT = "<!DOCTYPE html>\r\n" + 
//			"<html>\r\n" + 
//			"<head>\r\n" + 
//			"<meta charset=\"ISO-8859-1\">\r\n" + 
//			"<title>Result</title>\r\n" + 
//			CSS_SEARCH_RESULT +
//			"<Script>\r\n" + 
//			"{reload}\r\n" + 
//			"</Script>\r\n" + 
//			"</head>\r\n" + 
//			"<body>\r\n" + 
//			"	<br />\r\n" + 
//			"	<hr />\r\n" + 
//			"	<br />\r\n" + 
//			"	<div id=\"query\">{data}</div>\r\n" + 
//			"	<br />\r\n" + 
//			"	<hr />\r\n" + 
//			"	<br />\r\n" + 
//			"	<div>\r\n" + 
//			"		<table id=\"reviewdata\">\r\n" + 
//			"			<tr>\r\n" + 
//			"				<th>Asin</th>\r\n" + 
//			"				<th>Review Id/Question</th>\r\n" + 
//			"				<th>Review Text/Answer</th>\r\n" + 
//			"			</tr>\r\n" + 
//			"			{tableBody}\r\n" + 
//			"		</table>\r\n" + 
//			"	</div>\r\n" + 
//			"</body>\r\n" + 
//			"</html>";

}
