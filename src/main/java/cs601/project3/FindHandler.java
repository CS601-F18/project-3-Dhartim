package cs601.project3;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;

//import cs601.project1.*;
/**
 * findHandler - handles findApplication
 * @author dhartimadeka
 *
 */
public class FindHandler implements Handler
{
	private Charset fileEncoding = Charset.forName("ISO-8859-1");
	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);  

	/**
	 * handle - will handle get and post request and 
	 * 	call handleGet and handlePost methods accordingly.
	 * @param request - pass get or post request
	 * @param response - to handle response of request accordingly.
	 */
	public Response handle(Request request, Response response) 
	{
		// TODO Auto-generated method stub
		if(request.getRequest().equals("GET"))
			//if(request.validMethod(request.getRequest()).equals("GET"))//request.getRequest().equals("GET"))
		{
			//call get handler
			response = handleGet(request, response);
			logger.log(Level.INFO, String.format(SearchAppLogMsgDict.serverRequest, request.getRequest()));


		}
		else if(request.getRequest().equals("POST"))
			//else if(request.validMethod(request.getRequest()).equals("POST"))
			//else if (request.getRequest().equals("POST"))
		{
			//call post handler
			handlePost(request, response);
			logger.log(Level.INFO, String.format(SearchAppLogMsgDict.serverRequest, request.getRequest()));

		}
		else
		{
			response.setHeader("HTTP/1.0 405 Method Not Allowed\n" + "\r\n");
			response.setResponse(HtmlPages.HTML_405);

		}
		return response;
	}
	/**
	 * handleGet - handle get request and give response
	 * @param request - pass get request
	 * @param response - handle get response
	 * @return get html response.
	 */
	public Response handleGet(Request request, Response response)
	{
		response.setHeader("HTTP/1.0 200 OK\n" + "\r\n");
		response.setResponse(HtmlPages.HTML_FIND_FORM);
		return response;
	}
	/**
	 * handlePost - handle post request and response
	 * @param request - pass post request
	 * @param response - handle post response
	 * @return post html response
	 */
	public Response handlePost(Request request, Response response)
	{
		String postData="";
		try 
		{
			if(request.getParameter() != null)
			{
				postData = URLDecoder.decode(request.getParameter(), fileEncoding.toString());

				List<String> result=null;
				StringBuffer tableRow = new StringBuffer();
				String searchQuery ="", searchTerm = "";
				response.setHeader("HTTP/1.0 200 OK\n" + "\r\n");
				if(postData.split("&").length > 1)
				{
					for(String eachAsin : postData.split("&"))
					{

						tableRow.append(splitData(eachAsin.split("="), result, searchQuery, searchTerm));
						response.setResponse("<html><head><title>Project 3</title></head>"
								+ "<body><table border = '2'><tr><th>Asin number</th>"
								+ "<th>Reviewer id / Questions </th><th>Review Text / Answers</th></tr>" + tableRow.toString()
								+ "</table></body></html>");
					}
				}
				else if(postData.contains("="))
				{
					tableRow.append(splitData(postData.split("="), result, searchQuery, searchTerm));
					response.setResponse("<html><head><title>Project 3</title></head>"
							+ "<body><table border = '2'><tr><th>Asin number</th>"
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
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	/**
	 * getData - Will search asin id from inverted index and return list result.
	 * @param word - pass asin id to find 
	 * @return result - list of matched asin id from inverted index.
	 */
	//will asin  from inverted index
	public List<String> getData(String word) {
		List<String> result = new ArrayList<>();

		InvertedIndexInitilizer initilizer = InvertedIndexInitilizer.getInstance();

		if ((initilizer.getInvertIndexReview() != null) && (initilizer.getInvertIndexQA() != null)) {

			result = initilizer.getInvertIndexReview().findAsin(word.toLowerCase());

		} else {
			result.add(StaticInfo.termNotFound);

		}

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
		System.out.println(parameters.length);
		// int counter = 0;
		if (parameters.length > 1) {
			searchQuery = parameters[0];
			searchTerm = parameters[1];
			if (searchQuery.equals("asin") && !(searchTerm.isEmpty())) 
			{
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
				tableRow = "<tr><td>INVALID QUERY</td></tr>";
			}
		}
		return tableRow;
	}

}
