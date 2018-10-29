package cs601.project3;

public class SearchApplication {

	private static final int PORT = 8080;
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		//int port = 1024;
		String ConfigFileName = args[0];
		//SetUpInvertedIndex invertedIndex = SetUpInvertedIndex.getInstance(ConfigFileName);
		//call and make inverted index before server starts....
		SetUpInvertedIndex invtIndex = new SetUpInvertedIndex(ConfigFileName);
		invtIndex.setUpInvertedIndex();
		System.out.println("Data loaded successfully");
		HttpServer server = new HttpServer(PORT);
		//The request GET /reviewsearch will be dispatched to the 
		//handle method of the ReviewSearchHandler.
		server.addMapping("/reviewsearch", new ReviewSearchHandler());
		//The request GET /find will be dispatched to the 
		//handle method of the FindHandler.
		server.addMapping("/find", new FindHandler());
		//System.out.println();
		server.startServer();

	}

}
