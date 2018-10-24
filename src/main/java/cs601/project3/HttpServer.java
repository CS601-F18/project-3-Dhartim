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
	private ExecutorService executor = Executors.newFixedThreadPool(4);
	private boolean htmlResponseFlag = false;
	private HtmlResponse htmlResponse = new HtmlResponse();
	public HttpServer(Socket inSocket) 
	{
		this.insocket = inSocket;
		executor.execute(this);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		//input pipeline
		try (	BufferedReader instream = new BufferedReader(new InputStreamReader(insocket.getInputStream()));
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

			String requestedMethod[] = message.split("/");
			String parseMethod = requestedMethod[0].trim();
			if(!(parseMethod.equals("GET") || parseMethod.equals("POST"))) 
			{
				htmlResponseFlag = true;
				htmlResponse.httpResponse(writer, htmlResponseFlag);
			}
			htmlResponse.httpResponse(writer, htmlResponseFlag);
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
