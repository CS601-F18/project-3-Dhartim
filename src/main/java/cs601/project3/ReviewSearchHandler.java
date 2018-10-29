package cs601.project3;

import java.util.ArrayList;
import java.util.List;

public class ReviewSearchHandler implements Handler 
{
	//private InvertedIndexBuilder invertedIndexBuilder = new InvertedIndexBuilder();
	private InvertedIndex invertedIndex = new InvertedIndex();
	private List<String> searchTermResult = new ArrayList<>();
	String tabledata []; int count=1;
	String tableRowData = "<tr><th>Record ID</th><th>Asin ID</th><th>Reviewer ID</th><th>Review Text</th></tr>";
	@Override
	public void handle(Request request, Response response) 
	{
		//check get or post
		if(request.getRequest().equals("GET"))
		{
			handleGet(response);
		}
		else if (request.getRequest().equals("POST"))
		{
			handlePost(request, response);
		}
		else
		{
			response.setHeader("HTTP/1.0 405 Method Not Allowed\n" + "\r\n");
			response.setResponse("<html><head><title>Project 3</title></head>"+"<body><h1>405 BAD METHOD PASSED !! </h1></body></html>");
		}
	}

	public void handlePost(Request request, Response response)
	{
		//printing value of parameter passed from form
		System.out.println(request.getParameter());
		//search term from inverted index
		searchTermResult = invertedIndex.searchterm(request.getParameter().toLowerCase());
		for(String s : searchTermResult)
		{
			System.out.println(count +"&#" + s);
			tabledata = s.split("&#");
			if(tabledata.length > 1)
			{
				tableRowData += "<tr><td>"+tabledata[0]+ "</td><td>"
						+tabledata[1]+"</td><td>"+tabledata[2]+"</td><td>" 
						+tabledata[tabledata.length -1]+ "</td></tr>";
				count +=1;
			}
			else
			{
				tableRowData = searchTermResult.toString();
			}

		}
		response.setHeader("HTTP/1.0 200 OK\n" + "\r\n");
		response.setResponse("<html><head><title>Project 3</title></head>"+"<body><table border = 2>"+
				tableRowData +"</table></body></html>");
	}

	public void handleGet(Response response)
	{
		response.setHeader("HTTP/1.0 200 OK\n" + "\r\n");
		response.setResponse("<html><head><title>Project 3</title></head>"+
				"<body>"+
				"<form  action ='/reviewsearch' method='POST'>" +
				"Enter text: <input type='text' name='query'><br>"+
				"<input type='submit' value='Submit'>"+
				"</form></body></html>");
	}
}
