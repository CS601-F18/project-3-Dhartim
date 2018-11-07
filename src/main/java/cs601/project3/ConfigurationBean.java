package cs601.project3;
/**
 * CongirationBean - Class to get configuration.json elements into java objects.
 * @author dhartimadeka
 *
 */
public class ConfigurationBean 
{
	private String reviewFile;
	private String qaFile;
	private String url;
	private String contentType;
	private String token;
	private String channel;

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	public String getreviewFile() {
		return reviewFile;
	}
	public void setreviewFile(String reviewFile) {
		this.reviewFile = reviewFile;
	}
	public String getqaFile() {
		return qaFile;
	}
	public void setqaFile(String qaFile) {
		this.qaFile = qaFile;
	}
	
}
