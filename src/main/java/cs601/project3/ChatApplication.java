package cs601.project3;

import java.util.logging.Level;
import java.util.logging.Logger;

//import cs601.project3.SlackBot;
/**
 * 
 * @author dhartimadeka
 * Main class for chat application.
 *
 */
public class ChatApplication {
	private final static Logger logger =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 
	public static void main(String[] args) 
	{
		if(args.length != 1)
		{
			System.out.println("please specify configuration file name");
			System.exit(0);
		}
		SlackBot slackBot = new SlackBot(args[0]);
		int port = 9090;
		HttpServer server = new HttpServer(port);
		server.addMapping("/slackbot", new ChatHandler());
		logger.log(Level.INFO, SearchAppLogMsgDict.urlMapping);
		server.startServer();
	}
}