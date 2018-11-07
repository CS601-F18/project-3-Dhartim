package cs601.project3;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//import cs601.project1.*;
/**
 * findHandler - handles findApplication
 * @author dhartimadeka
 *
 */
public class FindHandler implements Handler
{
	//InvertedIndex invertedIndex = new InvertedIndex();
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

		if(request.validMethod(request.getRequest()).equals("GET"))//request.getRequest().equals("GET"))
		{
			//call get handler
			response = handleGet(request, response);
			logger.log(Level.INFO, String.format(SearchAppLogMsgDict.serverRequest, request.getRequest()));
			//System.out.println("inside get");

		}
		else if(request.validMethod(request.getRequest()).equals("POST"))
			//else if (request.getRequest().equals("POST"))
		{
			//call post handler
			handlePost(request, response);
			//System.out.println("inside post");
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
		List<String> result=null;
		String searchQuery ="", searchTerm = "";
		response.setHeader("HTTP/1.0 200 OK\n" + "\r\n");
		if (request.getParameter().split("=").length > 1) 
		{
			searchQuery = request.getParameter().split("=")[0];
			searchTerm = request.getParameter().split("=")[1].replaceAll("\\s+", "");
		}
		if(searchQuery.equals("asin") && !(searchTerm.isEmpty()))
		{
			result = getData(searchTerm);
			System.out.println("result= " + result);
			//response.setHeader("HTTP/1.0 200 OK\n" + "\r\n");
			String tableRow = "";
			for(String eachrow : result)
			{
				tableRow+= eachrow;
			}
			response.setResponse("<html><head><title>Project 3</title></head>"+"<body><table border = '2'><tr><th>Asin number</th>"
					+ "<th>Reviewer id / Questions </th><th>Review Text / Answers</th></tr>"+
					tableRow +"</table></body></html>");
		}
		else
		{
			String file = HtmlPages.HTML_EMPTY_MANDATORY_FIELD;
			response.setResponse(file);
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

}
