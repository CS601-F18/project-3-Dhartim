package cs601.project3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer 
{
	final static String EOT = "EOT";
	public static void main(String[] args) 
	{

		//try with resources ensures socket will be closed
		try (
				//open a server socket to listen on port 1024
				ServerSocket server = new ServerSocket(1024);
				//block on accept until a new client connects
				Socket sock = server.accept();
				//wrap the socket input stream in a BufferedReader
				BufferedReader instream = new BufferedReader(new InputStreamReader(sock.getInputStream()))
			) {

			//initialize the message
			String message = "";
			//read the first line
			String line = instream.readLine();
			
			//keep reading until end of transmission
			while(line != null && !line.trim().equals(EOT)) {
				//append to message
				message += line + "\n";
				//read next line
				line = instream.readLine();
			}
			//display client message
			System.out.println("Client says: " + message);
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}