package cs601.project3;
/**
 * Handler - to handle request and response of different applications
 * @author dhartimadeka
 *
 */
public interface Handler 
{
	public Response handle(Request resquest, Response response);
}
