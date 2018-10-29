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
	//to handle handlers
	private HashMap<String, Handler> handlersMap;
	private ServerSocket server;
	private ExecutorService executor;
	//handle request from browser.
	Request request =  new Request();
	//handle response from server.
	Response response = new Response(); 
	int length = 0;
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
				//String line = "";
				
				String line = reader.readLine();
				System.out.println(line);
				//String header[];
				//split line using space in this method
				String splitRequest[] = splitRequest(line);
				request.setRequest(splitRequest[0]);
				//response =  parseRequestLine(splitRequest[1]);
				//check first line of request
				while((line = reader.readLine()) != null && (line.length() != 0))
				//while(line != null && !line.trim().isEmpty())
				{
					System.out.println(line);
					//if content length in post request
					if(line.startsWith("Content-Length:")) 
					{
						length = Integer.parseInt(line.split(":")[1].trim());
					}
				}
				//read characters
				char[] readParameter = new char[length];
				reader.read(readParameter);
				//covert to string
				String readParams = new String(readParameter);
				//split it to get term to search
				String splitParameters[] = readParams.split("=");
				//pass term to request and set it there
				if(splitParameters.length > 1)
				{
					request.setParameter(splitParameters[1]);
				}
				//response from request line accordingly
				response =  parseRequestLine(splitRequest[1]);
				
			//	System.out.println("term" + request.getParameter());
				writer.write(response.getHeader());
				writer.write(response.getResponse());
				writer.flush();
				//writer.flush();
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
		//System.out.println(handlersMap);
	}

	//to split request line with space
	public String[] splitRequest(String line)
	{
		String splitRequest[] = line.split("\\s+");
	//	System.out.println();
		//check array length to be more than 3
		if(splitRequest.length != 3)
		{
			response.setHeader("HTTP/1.0 405 Method Not Allowed\n" + "\r\n");
			response.setResponse("<html><head><title>Project 3</title></head><body>Length of request has to be 3</body></html>");
		}
		//System.out.println("splitrequest is....."+splitRequest.toString());
		return splitRequest;
		
	}
	
	//parse request line accordingly and return response
	public Response parseRequestLine(String splitRequest)
	{
		System.out.println(splitRequest);
		if(splitRequest.equals("/reviewsearch") && (handlersMap.containsKey("/reviewsearch")))
		{
			//get handler of respective class and call handle methods in each
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
			System.out.println("here");
			Handler chatHandler = handlersMap.get(splitRequest);
			chatHandler.handle(request, response);
		}
		else if(splitRequest.isEmpty())
		{
			response.setHeader("HTTP/1.0 405 Method Not Allowed\n" + "\r\n");
			//response.setResponse("Please give valid request");
			response.setResponse("<html><head><title>Project 3</title></head><body>Invalid input</body></html>");
		}
		else
		{
			response.setHeader("HTTP/1.0 405 Method Not Allowed\n" + "\r\n");
			response.setResponse("<html><head><title>Project 3</title></head><body>Invalid input</body></html>");
			//writer.write("<html><head><title>TEST</title></head><body>Invalid input</body></html>");
		}
		
		return response;
	}
	
	
}