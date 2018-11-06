package cs601.project3;
import java.io.UnsupportedEncodingException;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReviewSearchHandler implements Handler
{
	//InvertedIndex invertedIndex = new InvertedIndex();
	private Charset fileEncoding = Charset.forName("ISO-8859-1");
	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public Response handle(Request request, Response response) 
	{
		// TODO Auto-generated method stub
		//		if(request.validMethod(request.getRequest()))
		if(request.validMethod(request.getRequest()).equals("GET"))//request.getRequest().equals("GET"))
		{
			//logger.log(Level.INFO, String.format(SearchAppLogMsgDict.serverRequest, parseMethod));
			//call get handler
			response = handleGet(request, response);
			logger.log(Level.INFO, String.format(SearchAppLogMsgDict.serverRequest, request.getRequest()));
			//System.out.println("inside get");

		}
		else if(request.validMethod(request.getRequest()).equals("POST"))
			//else if (request.getRequest().equals("POST"))
		{
			//call post handler
			try {
				handlePost(request, response);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("inside post");
		}
		else
		{
			//System.out.println("not valid method");
			response.setHeader("HTTP/1.0 405 Method Not Allowed\n" + "\r\n");
			response.setResponse(HtmlPages.HTML_405);
			//System.out.println("hello");
		}
		return response;
	}

	public Response handleGet(Request request, Response response)
	{
		response.setHeader("HTTP/1.0 200 OK\n" + "\r\n");
		response.setResponse(HtmlPages.HTML_REVIEW_SEARCH_FORM);
		return response;
	}

	public Response handlePost(Request request, Response response) throws UnsupportedEncodingException
	{
		System.out.println(request.getParameter());
		String postData = URLDecoder.decode(request.getParameter(), fileEncoding.toString());
		System.out.println("post data" +postData);
		List<String> result=null;
		String searchTerm = "", searchQuery ="";
		response.setHeader("HTTP/1.0 200 OK\n" + "\r\n");
		//request.getParameter().charAt(request.getParameter().indexOf("&"));
		//if(request.getParameter().indexOf("&"))
		String parameters[];
		if(postData.contains("&"))
		{
			parameters = postData.split("&");
			System.out.println("parameter" +parameters);

			for(String eachParameters : parameters)
			{
				//System.out.println("parameters...." +eachParameters);
				String eachEqualParameters[] = eachParameters.split("=");
				String tableRow = splitData(postData, eachEqualParameters, result, searchQuery, searchTerm, response);
				response.setResponse("<html><head><title>Project 3</title></head>"+"<body><table border = 2><tr><th>Asin number</th>"
						+ "<th>Reviewer id / Questions </th><th>Review Text / Answers</th></tr>"+
						tableRow +"</table></body></html>");
				//				if(eachParameters.split("=").length > 1)
				//				{
				//					searchQuery = eachParameters.split("=")[0];
				//					searchTerm = eachParameters.split("=")[1];
				//					if(searchQuery.equals("query") && !(searchTerm.isEmpty()))
				//					{
				//						result.addAll(getData(searchTerm));
				//						String tableRow = "";
				//						for(String eachrow : result)
				//							{
				//								tableRow+= eachrow;
				//							}
				//							
				//							response.setResponse("<html><head><title>Project 3</title></head>"+"<body><table border = 2><tr><th>Asin number</th>"
				//									+ "<th>Reviewer id / Questions </th><th>Review Text / Answers</th></tr>"+
				//									tableRow +"</table></body></html>");
				//						System.out.println(result);
				//					}
				//				}
			}
		}
		else if(postData.contains("="))
		{
			parameters = postData.split("=");
			String tableRow = splitData(postData, parameters, result, searchQuery, searchTerm, response);
			response.setResponse("<html><head><title>Project 3</title></head>"+"<body><table border = 2><tr><th>Asin number</th>"
					+ "<th>Reviewer id / Questions </th><th>Review Text / Answers</th></tr>"+
					tableRow +"</table></body></html>");
		}
		else
		{
			response.setHeader("HTTP/1.1 404 error\n\r\n");
			response.setResponse(HtmlPages.HTML_404);
		}

		//		if (request.getParameter().split("=").length > 1) 
		//		{
		//			//searchQuery = request.getParameter().split("=")[0];
		//			searchTerm = request.getParameter().split("=")[1].replaceAll("\\s+", "");
		//		}
		//		if(!(searchTerm.isEmpty()))
		//		{
		//			result = getData(searchTerm);
		//		//System.out.println("result= " + result);
		//		//response.setHeader("HTTP/1.0 200 OK\n" + "\r\n");
		//		String tableRow = "";
		//		for(String eachrow : result)
		//		{
		//			tableRow+= eachrow;
		//		}
		//		
		//		response.setResponse("<html><head><title>Project 3</title></head>"+"<body><table border = 2><tr><th>Asin number</th>"
		//				+ "<th>Reviewer id / Questions </th><th>Review Text / Answers</th></tr>"+
		//				tableRow +"</table></body></html>");
		//		}
		//		else
		//		{
		//			String file = HtmlPages.HTML_EMPTY_MANDATORY_FIELD;
		//			response.setResponse(file);
		//		}

		return response;
	}

	//will data from inverted index
	public List<String> getData(String word) 
	{
		List<String> result = new ArrayList<>();
		InvertedIndexInitilizer initilizer = InvertedIndexInitilizer.getInstance();

		if ((initilizer.getInvertIndexReview() != null)) 
		{
			System.out.println(initilizer.getInvertIndexReview().toString());
			result = initilizer.getInvertIndexReview().searchterm(word.toLowerCase());
			System.out.println(result.size());
		} else {
			result.add(StaticInfo.loading);

		}
		return result;
	}

	public String splitData(String postData, String parameters[], List<String> result, String searchQuery, String searchTerm, Response response )
	{
		String tableRow ="";
		//parameters = postData.split("=");
		if(parameters.length > 1)
		{
			searchQuery = parameters[0];
			searchTerm = parameters[1];
			if(searchQuery.equals("query") && !(searchTerm.isEmpty()))
			{
				if(searchTerm.split(" ").length > 1)
				{
					for(String eachSearchTerm: searchTerm.split(" "))
					{
						result = getData(eachSearchTerm);
						//tableRow = "";
						for(String eachrow : result)
						{
							tableRow+= eachrow;
							//System.out.println();
						}
					}
				}
				else
				{
					result = getData(searchTerm);
					//tableRow = "";
					for(String eachrow : result)
					{
						tableRow+= eachrow;
					}
				}

				System.out.println("result= " + result);
			}
			else
			{
				tableRow = "INVALID QUERY"; 
			}
		}
		System.out.println(tableRow.length());
		return tableRow;
	}
}
