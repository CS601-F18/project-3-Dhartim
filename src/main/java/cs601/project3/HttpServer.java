package cs601.project3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
//import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is a very poorly designed HTTP server able to reply to one request with a static HTML page.
 * @author srollins
 *
 */
public class HttpServer implements Runnable
{
	private Socket insocket;
	ExecutorService executor = Executors.newFixedThreadPool(4);
	public HttpServer(Socket inSocket) 
	{
		this.insocket = inSocket;
		executor.execute(this);
		//	this.start();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		System.out.println("thread...");
		//creating server socket
		try (//ServerSocket serve = new ServerSocket(1024);
				//Socket sock = serve.accept(); //accepting request from client.
				//input pipeline
				BufferedReader instream = new BufferedReader(new InputStreamReader(insocket.getInputStream()));
				//output pipeline
				PrintWriter writer = new PrintWriter(insocket.getOutputStream())) //write data to file.
		{

			String message = "";
			//read data from input pipeline
			String line = instream.readLine();
			//read it till it's empty or null
			while(line != null && !line.trim().isEmpty()) {
				message += line + "\n";
				line = instream.readLine();
			}
			System.out.println("Request: \n" + message);

			String requestedmethod[] = message.split("/");
			String parsemethod = requestedmethod[0].trim();
			System.out.println(parsemethod);
			if(!(parsemethod.equals("GET")) && (!(parsemethod.equals("POST")))) 
			{
				//System.out.println("405 Method Not Allowed");
				System.out.println("hi");
				String headers = "HTTP/1.0 405 Method Not Allowed\n" +
						"\r\n";

				String page = "<html> " + 
						"<head><title>TEST</title></head>" + 
						"<body>Dont support methods other then GET and POST.</body>" + 
						"</html>";

				//write back to output screen
				writer.write(headers);
				writer.write(page);
				writer.flush();
			}

			//System.out.println(parsemethod);

			String headers = "HTTP/1.0 200 OK\n" +
					"\r\n";

			String page = "<html> " + 
					"<head><title>TEST</title></head>" + 
					"<body>This is a short test page.</body>" + 
					"</html>";

			//write back to output screen
			writer.write(headers);
			writer.write(page);
			writer.flush();

		} catch(IOException ioe) {
			ioe.printStackTrace();
		}

	}
}
