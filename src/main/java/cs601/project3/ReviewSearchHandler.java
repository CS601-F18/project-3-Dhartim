package cs601.project3;
import java.io.UnsupportedEncodingException;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
/**
 * ReviewSearchHandler - will handle get and post request and response of reviewsearch application
 * @author dhartimadeka
 *
 */
public class ReviewSearchHandler implements Handler
{
	private Charset fileEncoding = Charset.forName("ISO-8859-1");
	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/***
	 * @param request - stores request from server
	 * @param response - stores response of method to handle
	 * 
	 */
	public Response handle(Request request, Response response) 
	{

		if(request.getRequest().equals("GET"))
		{
			//call get handler
			response = handleGet(request, response);
			logger.log(Level.INFO, String.format(SearchAppLogMsgDict.serverRequest, request.getRequest()));

		}
		else if(request.getRequest().equals("POST"))
		{
			//call post handler
			try {
				handlePost(request, response);

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			response.setHeader("HTTP/1.0 405 Method Not Allowed\n" + "\r\n");
			response.setResponse(HtmlPages.HTML_405);
		}
		return response;
	}

	/**
	 * 
	 * @param request - pass get request to handle method
	 * @param response - stores and handle html response
	 * @return
	 */
	public Response handleGet(Request request, Response response)
	{
		response.setHeader("HTTP/1.0 200 OK\n" + "\r\n");
		response.setResponse(HtmlPages.HTML_REVIEW_SEARCH_FORM);
		return response;
	}
	/**
	 * 
	 * @param request - pass post request
	 * @param response - stores response of post request.
	 * @return response object to which stores html page
	 * @throws UnsupportedEncodingException
	 */
	public Response handlePost(Request request, Response response) throws UnsupportedEncodingException
	{
		if(request.getParameter() != null)
		{
			String postData = URLDecoder.decode(request.getParameter(), fileEncoding.toString());
			//System.out.println(postData);
			List<String> result = null;
			String searchTerm = "", searchQuery = "";
			//response.setHeader("HTTP/1.0 200 OK\n" + "\r\n");
			String parameters[];
			StringBuffer tableRow = new StringBuffer();
			if (postData.split("&").length > 1) 
			{
				parameters = postData.split("&");

				for (String eachParameters : parameters) 
				{
					//System.out.println(eachParameters);
					String eachEqualParameters[] = eachParameters.split("=");
					tableRow.append(splitData(eachEqualParameters, result, searchQuery, searchTerm));
					response.setHeader("HTTP/1.0 200 OK\n" + "\r\n");
					response.setResponse("<html><head><title>Project 3</title></head>"
							+ "<body><table border = '2'><tr><th>Asin number</th>"
							+ "<th>Reviewer id / Questions </th><th>Review Text / Answers</th></tr>" + tableRow.toString()
							+ "</table></body></html>");
				}
			}
			else if (postData.contains("=")) {
				parameters = postData.split("=");
				tableRow.append(splitData(parameters, result, searchQuery, searchTerm));
				response.setHeader("HTTP/1.0 200 OK\n" + "\r\n");
				response.setResponse(
						"<html><head><title>Project 3</title></head>" + "<body><table border ='2'><tr><th>Asin number</th>"
								+ "<th>Reviewer id / Questions </th><th>Review Text / Answers</th></tr>" + tableRow.toString()
								+ "</table></body></html>");
			}
			else 
			{
				response.setHeader("HTTP/1.1 404 error\n\r\n");
				response.setResponse(HtmlPages.HTML_404);
			}
		}
		else
		{
			response.setHeader("HTTP/1.1 404 error\n\r\n");
			response.setResponse(HtmlPages.HTML_404);
		}
		//System.out.println(request.getRequest());
		return response;
	}

	/**
	 * getData - will help to store and retrieve list of data from inverted index
	 * @param word - String to search from inverted index
	 * @return
	 */
	//will data from inverted index
	public List<String> getData(String word) 
	{
		List<String> result = new ArrayList<>();
		InvertedIndexInitilizer initilizer = InvertedIndexInitilizer.getInstance();

		if ((initilizer.getInvertIndexReview() != null)) 
		{

			result = initilizer.getInvertIndexReview().searchterm(word.toLowerCase());

		} else {
			result.add("<tr><td>INVALID QUERY</td></tr>");

		}
		//System.out.println(result);
		return result;
	}
	/**
	 * splitData - Will take list of parameters from request and manipulate it to give list of data from inverted index 
	 * @param parameters - list of parameters
	 * @param result - it has result of list
	 * @param searchQuery - will store query
	 * @param searchTerm - will store term to search in inverted index
	 * @return
	 */
	public String splitData(String parameters[], List<String> result, String searchQuery, String searchTerm) {
		String tableRow = "";
		//System.out.println(parameters.length);
		// int counter = 0;
		if (parameters.length > 1) 
		{
			searchQuery = parameters[0];
			searchTerm = parameters[1];
			if (searchQuery.equals("query") && (searchTerm != null)) 
			{
				System.out.println("searchterm= "+searchTerm);
				if (searchTerm.split(" ").length > 1) {
					for (String eachSearchTerm : searchTerm.split(" ")) {

						result = getData(eachSearchTerm);
						// to limit records , to avoid java heap
						int limit = 70;
						if (result.size() <= 70) {
							limit = result.size();
						}
						for (int i = 0; i < limit; i++) {
							tableRow += result.get(i);
						}
					}
				} else {
					result = getData(searchTerm);
					System.out.println("searchterm =" +searchTerm);
					// to avoid java heap...limiting data
					int limit = 70;
					if (result.size() <= 70) {
						limit = result.size();
					}
					for (int i = 0; i < limit; i++) {
						tableRow += result.get(i);
					}
				}
			}
			else
			{
				System.out.println("searchterm =" +searchTerm);
				tableRow = "<tr><td>INVALID QUERY</td></tr>";
			}
		}
		//System.out.println(tableRow);
		return tableRow;
	}
}
