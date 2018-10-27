package cs601.project3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer implements Runnable
{
	//private final static int PORT;
	private HashMap<String, Handler> handlersMap;// = new HashMap<>();
	private ServerSocket server;
	private ExecutorService executor;
	//handle request from browser.
	Request request =  new Request();
	//handle response from server.
	Response response = new Response();
	//private Handler handler;
//	private ReviewSearchHandler reviewHandler = new ReviewSearchHandler();
//	private FindHandler findHandler = new FindHandler();
//	private 
	public HttpServer(int PORT) 
	{
		// TODO Auto-generated constructor stub
		handlersMap =  new HashMap<>();
		try {
			//creating server socket
			server =  new ServerSocket(PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		executor = Executors.newFixedThreadPool(4);
	}
	
	//running server
	public void startServer()
	{
		executor.execute(this);
	}
	
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		//socket = server.accept();
		while(true)
		{
			
			try (	Socket socket = server.accept();
					BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					//output pipeline
					//socket = server.accept();
					PrintWriter writer = new PrintWriter(socket.getOutputStream())) //write data to file.
				{
				//read data from input stream
				String line = reader.readLine();
				//check first line of request
				if(line != null && !line.trim().isEmpty())
				{
					//request.setRequest(line + "\n"); //set it to request.
					System.out.println(line);
					//split request using space
					String splitrequest[] = splitRequest(line);
					request.setRequest(splitrequest[0]);
					//dynamic string to class call
					//if(splitrequest[0].equals))
					response =  parseRequestLine(splitrequest[1]);
					
					//get response....
					writer.write(response.getHeader());
					writer.write(response.getResponse());
					//handler.handle(request, response);
					//rsh.handle(request, response);
					//writer.write(response.getHeader());
				
					
				}
				writer.flush();
			}
			catch (IOException e) 
			{
				// TODO: handle exception
			}
		}

	}
	
	public void addMapping(String handlertype, Handler handler)
	{
		handlersMap.put(handlertype, handler);
		System.out.println(handlersMap);
	}

	public String[] splitRequest(String line)
	{
		String splitrequest[] = line.split("\\s+");
		if(splitrequest.length != 3)
		{
			
		}
		return splitrequest;
		
	}
	
	//parse request line accordingly and return response
	public Response parseRequestLine(String splitRequest)
	{
		if(splitRequest.equals("/reviewsearch") && (handlersMap.containsKey("/reviewsearch")))
		{
			Handler reviewHandler = handlersMap.get(splitRequest);
			reviewHandler.handle(request, response);
		}
		else if(splitRequest.equals("/find") && (handlersMap.containsKey("/find")))	
		{
			Handler findHandler = handlersMap.get(splitRequest);
			findHandler.handle(request, response);
		}
		else if(splitRequest.equals("/slackbot") && (handlersMap.containsKey("/slackbot")))
		{
			Handler chatHandler = handlersMap.get(splitRequest);
			chatHandler.handle(request, response);
		}
		else if(splitRequest.isEmpty())
		{
			response.setHeader("HTTP/1.0 405 Method Not Allowed\n" + "\r\n");
			//response.setResponse("Please give valid request");
			response.setResponse("<html><head><title>TEST</title></head><body>Invalid input</body></html>");
		}
		else
		{
			response.setHeader("HTTP/1.0 405 Method Not Allowed\n" + "\r\n");
			response.setResponse("<html><head><title>TEST</title></head><body>Invalid input</body></html>");
			//writer.write("<html><head><title>TEST</title></head><body>Invalid input</body></html>");
		}
		
		return response;
	}
}