package cs601.project3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpsServer implements Runnable
{
	private static final String METHOD_NOT_SUPPORTED = "not_supported_html";
	private Socket socketConnection;
	static final int PORT = 8080;

	public HttpsServer(Socket socket) 
	{
		// TODO Auto-generated constructor stub
		this.socketConnection = socket; 
	}

	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		System.out.println("connecting opened");
		try (//ServerSocket serve = new ServerSocket(1024);
				//Socket sock = serve.accept(); //accepting request from client.
				//input pipeline
				BufferedReader instream = new BufferedReader(new InputStreamReader(socketConnection.getInputStream()));
				//output pipeline
				PrintWriter writer = new PrintWriter(socketConnection.getOutputStream())) //write data to file.
		{

			String message = "";
			//read data from input pipeline
			String line = instream.readLine();
			while(line != null && !line.trim().isEmpty()) {
				message += line + "\n";
				line = instream.readLine();
			}
			System.out.println("Request: \n" + message);
//			StringTokenizer requestedstring = new StringTokenizer(line);
//			String parse = requestedstring.nextToken();
//			if(!parse.equals("GET"))
//			{
//				
//			}
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}


	}

}
