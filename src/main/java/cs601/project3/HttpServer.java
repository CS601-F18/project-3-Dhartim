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
	//private Handler handler;
	private ReviewSearchHandler reviewHandler = new ReviewSearchHandler();
	private FindHandler findHandler = new FindHandler();
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
				//handle request from browser.
				Request request =  new Request();
				//handle response from server.
				Response response = new Response();
				//read data from input stream
				String line = reader.readLine();
				//check first line of request
				if(line != null && !line.trim().isEmpty())
				{
					request.setRequest(line + "\n"); //set it to request.
					System.out.println(line);
					//pass it to handler
					String splitrequest[] = splitRequest(line);
					
					//dynamic string to class call
//					for(String handlers : handlersMap.keySet())
//					{
//						if(handlers.equals(splitrequest[1]))
//						{
//							//call wat????
//						}
//					}
//					if(handlersMap.containsKey(splitrequest[1]))
//					{
//						System.out.println("hey...");
//					}
//					if(splitrequest[1].equals("/reviewsearch"))
//					{
//						reviewHandler.handle(request, response);
//					}
//					else if(splitrequest[1].equals("/find"))
//					{
//						findHandler.handle(request, response);
//					}
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
		return splitrequest;
		
	}
}