package cs601.project3;
/**
 *
 * Contains fields for storing relevant information when reading the review text
 * file
 */
public class Review extends Amazon
{

	private String reviewerID;
	private String reviewText;
	private double overall;
	public void setReviewerID(String reviewerID) {
		this.reviewerID = reviewerID;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public void setOverall(double overall) {
		this.overall = overall;
	}
	public String getReviewerID() {
		return reviewerID;
	}
	public String getReviewText() {
		return reviewText;
	}

	public double getOverall() 
	{
		return overall;
	}
	
	public String toString()
	{
		return this.asin+"&#"+this.reviewerID+"&#"+this.reviewText;
	}

}
