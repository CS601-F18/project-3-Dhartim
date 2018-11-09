package cs601.project1;

import org.jsoup.Jsoup;

/**
 *
 * Contains fields for storing relevant information when reading the review text
 * file
 */
public class Review extends Amazon {

	private String reviewerID;
	// private String asin;
	private String reviewText;
	private double overall;

	public void setReviewerID(String reviewerID) {
		this.reviewerID = reviewerID;
	}

//	public void setAsin(String asin) {
//		this.asin = asin;
//	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public void setOverall(double overall) {
		this.overall = overall;
	}

	public String getReviewerID() {
		return reviewerID;
	}

//	public String getAsin() {
//		return asin;
//	}

	public String getReviewText() {
		return reviewText;
	}

	public double getOverall() {
		return overall;
	}

	public String toString() {
		return "<tr><td>" + Jsoup.parse(this.asin).toString() + "</td><td>" + Jsoup.parse(this.reviewerID).toString() + "</td><td>" + Jsoup.parse(this.reviewText).toString() + "</td></tr>";
	}

}
