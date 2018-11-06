package cs601.project3;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpServer implements Runnable
{
	//to handle handlers
	private HashMap<String, Handler> handlersMap;
	private final static Logger logger =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 
	private ServerSocket server;
	private ExecutorService executor;
	private boolean requestResult;
	private static volatile boolean running = true;
	private Handler handler;
	//handle request from browser.
	private Request request =  new Request();
	//handle response from server.
	private Response response = new Response(); 
	//private int length = 0;

	public HttpServer(int PORT) 
	{
		// TODO Auto-generated constructor stub
		handlersMap =  new HashMap<String, Handler>();
		try {
			//creating server socket
			server =  new ServerSocket(PORT);
			logger.log(Level.INFO, String.format(ServerMsgLog.serverListening, PORT));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(Level.SEVERE, String.format(ServerMsgLog.serverNotStarted, PORT));
		}

		executor = Executors.newFixedThreadPool(4);
	}
	//running server
	public void startServer()
	{
		executor.execute(this);
		//System.out.println("hello...");
	}

	public void addMapping(String handlertype, Handler handler)
	{
		handlersMap.put(handlertype, handler);
		//System.out.println(handlersMap);
	}

	public void run() 
	{
		while(running)
		{
			System.out.println("here");
			logger.log(Level.INFO, String.format(ServerMsgLog.serverUp));
			
			try(Socket socket = server.accept();
					BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					InputStream instream = socket.getInputStream();
					PrintWriter writer = new PrintWriter(socket.getOutputStream()))
			{
				String line = oneLine(instream);
				//logger.log(Level.INFO, String.format(ServerMsgLog.serverMethodUrlReq, line));
				//String line  = reader.readLine();
				requestResult = request.validRequest(line);
				System.out.println("line :- " + line);
				if(!requestResult)
				{
					handler = new ErrorHandler();
					//return;
				}
					String method = "", path = "";
					String splitline[] = line.split("\\s+");
					if(splitline.length > 1)
					{
						method = splitline[0];
						path = splitline[1];
					}
					System.out.println("method = " + method + " path = " + path);
					handler = request.isPathValid(path, handlersMap, response);
					System.out.println(handler.getClass().toString());
					request.setRequest(method);
					//logger.log(Level.INFO, String.format(ServerMsgLog.method, method));
					//System.out.println("");
					int postLength = -1;
					String postData;
					//System.out.println(method + path );
					while(line != null && !line.trim().isEmpty()) 
						//logger.log(Level.INFO, String.format(ServerMsgLog.serverLog, line));
						
					{
						System.out.println(line);
						line += "\n";
						line = oneLine(instream);
						if (line.indexOf("Content-Length:") > -1) 
						{
							postLength = Integer.parseInt(line.split(":")[1].trim());
							System.out.println("post length = " + postLength);
						}
					}	
					if (postLength > 0) 
					{
						postData = readAndPassPostData(reader, postLength);
						System.out.println(postData);
						request.setParameter(postData);
						//logger.log(Level.INFO, String.format(ServerMsgLog.postedData, postData));
					}
					if(handler != null)
					{
						response = handler.handle(request, response);
						//System.out.println(response.getHeader());
						//System.out.println(response.getResponse());
						writer.write(response.getHeader());
						writer.write(response.getResponse());
						writer.flush();
					}
				//} 
			}catch (IOException e) {
				// TODO Auto-generated catch block
				logger.log(Level.SEVERE, String.format(ServerMsgLog.serverThreadStop));
				e.printStackTrace();
			}

		}
	}

	public String oneLine(InputStream instream)throws IOException
	{
		// TODO Auto-generated method stub
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		byte b = (byte) instream.read();
		while(b != '\n' && b != -1) 
		{
			bout.write(b);
			b = (byte) instream.read();
		}
		return new String(bout.toByteArray());
		//return null;
	}

	public String readAndPassPostData(BufferedReader reader, int postLength)
	{
		String postData;
		char[] charArray = new char[postLength];
		//postData = oneLine(reader);
		try {
			reader.read(charArray, 0, postLength);
			//System.out.println();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		postData = new String(charArray);
		System.out.println(postData);
		return postData;
		//request.setParameter(postData);

	}

}