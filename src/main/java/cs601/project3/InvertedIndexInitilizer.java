package cs601.project3;

import cs601.project1.InvertedIndex;
/**
 * InvertedIndexInitilizer - Initialize inverted index
 * @author dhartimadeka
 *
 */
public class InvertedIndexInitilizer {

	private static InvertedIndexInitilizer instance;
	private InvertedIndex invertIndexReview;
	private InvertedIndex invertIndexQA;

	private InvertedIndexInitilizer() {
	}

	public static InvertedIndexInitilizer getInstance() {
		if (instance == null) {
			instance = new InvertedIndexInitilizer();
		}
		return instance;
	}

	public InvertedIndex getInvertIndexReview() {
		return invertIndexReview;
	}

	public void setInvertIndexReview(InvertedIndex invertIndexReview) {
		this.invertIndexReview = invertIndexReview;
	}

	public InvertedIndex getInvertIndexQA() {
		return invertIndexQA;
	}

	public void setInvertIndexQA(InvertedIndex invertIndexQA) {
		this.invertIndexQA = invertIndexQA;
	}

}
