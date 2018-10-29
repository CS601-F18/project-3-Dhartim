package cs601.project3;

//class to get review file path and qa file path from properties files
public class ConfigurationBean 
{
	private String reviewFile;
	private String qaFile;
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
