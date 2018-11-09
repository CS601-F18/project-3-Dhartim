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
/**
 * HttpServer - will setup connection with client and handle get and post request according to application.
 * @author dhartimadeka
 *
 */
public class HttpServer implements Runnable
{
	//to handle handlers
	private HashMap<String, Handler> handlersMap;
	private final static Logger logger =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 
	private ServerSocket server;
	private ExecutorService executor;
	private boolean requestResult;
	private static volatile boolean running = true;

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
	/**
	 * startServer - start server with threadpool
	 */
	//running server
	public void startServer()
	{
		executor.execute(this);
	}
	/**
	 * addMapping - Add api and respective application in hashmap and handle it
	 * @param handlertype - pass api path
	 * @param handler - pass application object.
	 */
	public void addMapping(String handlertype, Handler handler)
	{
		handlersMap.put(handlertype, handler);
	}

	/**
	 * run - start thread for different servers.
	 */
	public void run() 
	{
		while(running)
		{
			logger.log(Level.INFO, String.format(ServerMsgLog.serverUp));
			//handle request from browser.
			Request request =  new Request();
			//handle response from server.
			Response response = new Response(); 
			Handler handler = null;
			
			try(
					Socket socket = server.accept();
					BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					InputStream instream = socket.getInputStream();
					PrintWriter writer = new PrintWriter(socket.getOutputStream()))
			{
				String line = oneLine(instream);
				
				System.out.println(line);
				requestResult = request.validRequest(line);
				if(!request.validMethod(line) )
				{
					handler = new BadMethodHandler();
//					response.setHeader("HTTP/1.0 405 Method Not Allowed\n" + "\r\n");
//					response.setResponse(HtmlPages.HTML_405);

				}
				else if(!requestResult)
				{
					handler = new ErrorHandler();
				}
				String method = "", path = "";
				String splitline[] = line.split("\\s+");
				if(splitline.length > 1)
				{
					method = splitline[0];
					path = splitline[1];
				}
				//System.out.println();
				request.setRequest(method);
				if(handler == null)
					handler = request.isPathValid(path, handlersMap, response);
				System.out.println(request.getRequest());
				int postLength = -1;
				String postData;
				while(line != null && !line.trim().isEmpty()) 
				{
					line += "\n";
					line = oneLine(instream);
					System.out.println(line);
					if (line.indexOf("Content-Length:") > -1) 
					{
						postLength = Integer.parseInt(line.split(":")[1].trim());
					}
				}	
				if (postLength > 0) 
				{
					postData = readAndPassPostData(reader, postLength);
					System.out.println(postData);
					if (postData != null)
					{
						request.setParameter(postData);
					}
				}
				if(handler != null)
				{
					response = handler.handle(request, response);
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
	/**
	 * oneLine - read line from InputStream reader and convert it into String.
	 * @param instream - stream to read data from
	 * @return A string which is converted from an byte array.
	 * @throws IOException
	 */
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

	/**
	 * readAndPassPostData - read line and pass post data from body of post.
	 * @param reader - take data from bufferedReader.
	 * @param postLength - length of Content-length
	 * @return
	 */
	public String readAndPassPostData(BufferedReader reader, int postLength)
	{
		String postData;
		char[] charArray = new char[postLength];
		try {
			reader.read(charArray, 0, postLength);
			//System.out.println();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		postData = new String(charArray);
		return postData;
	}
	
	public void stopServer(boolean stop)
	{
		running = false;
	}

}