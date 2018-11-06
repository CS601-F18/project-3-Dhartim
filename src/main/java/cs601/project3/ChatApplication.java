package cs601.project3;
//import cs601.project3.SlackBot;

public class ChatApplication {
	public static void main(String[] args) 
	{
		
		SlackBot slackBot = new SlackBot(args[0]);
		int port = 9090;
		HttpServer server = new HttpServer(port);
		server.addMapping("/slackbot", new ChatHandler());
		server.startServer();
	}
}