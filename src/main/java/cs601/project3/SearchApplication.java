package cs601.project3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SearchApplication - main class of search application
 * @author dhartimadeka
 *
 */
public class SearchApplication {

	private final static Logger logger =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 
	static int port = 8080;

	public static void main(String[] args) 
	{
		if(args.length != 1)
		{
			System.out.println("please specify configuration file name");
			System.exit(0);
		}
		String ConfigFileName = args[0];
		logger.log(Level.INFO, String.format(SearchAppLogMsgDict.loading));
		SetUpInvertedIndex setUpInvertedIndex = SetUpInvertedIndex.getInstance(ConfigFileName);
		setUpInvertedIndex.initInvertedIndex();
		logger.log(Level.INFO, String.format(SearchAppLogMsgDict.loadSuccess));
		HttpServer server = new HttpServer(port);
		//The request GET /reviewsearch will be dispatched to the 
		//handle method of the ReviewSearchHandler.
		server.addMapping("/reviewsearch", new ReviewSearchHandler());
		//The request GET /find will be dispatched to the 
		//handle method of the FindHandler.
		server.addMapping("/find", new FindHandler());
		logger.log(Level.INFO, String.format(SearchAppLogMsgDict.urlMapping));
		
		logger.log(Level.INFO, String.format(SearchAppLogMsgDict.serverStart));
		server.startServer();
	}

}
