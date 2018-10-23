package cs601.project3;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerMain 
{

	private static final int PORT = 8080;
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		ServerSocket server;
		try {
			server = new ServerSocket(PORT);
			System.out.println("Server Started....on port:- " + PORT);
			while (true) 
			{
			//take client's request
				new HttpServer(server.accept());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//HttpServer server = new HttpServer(PORT);
	//	HttpServer server = new HttpServer();
		//ServerSocket server = new ServerSocket(PORT);

	}

}
