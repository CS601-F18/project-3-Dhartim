package cs601.project1;

import java.nio.file.Path;
import java.nio.file.Paths;

//import java.sql.Time;
/**
 * 
 * @author dhartimadeka Main method . it will call all other methods to build
 *         invertedindex
 */

public class AmazonSearch {
	// enum typeOfFile{Review, QA }; //to check type of file
	public enum typeOfFile {
		Review, QA
	};

	public static void main(String[] args) {
		boolean proceedFurther = false;
		proceedFurther = UICommands.checkarguments(args); // check for arguments
		if (proceedFurther) {
			// creating objects
			InvertedIndexBuilder bds = new InvertedIndexBuilder();

			// using 2 occurence of inverted index
			InvertedIndex invertedIndexforReview;
			InvertedIndex invertedIndexforQA;
			Path reviewPath = Paths.get(args[1]); // passing file path
			Path qaPath = Paths.get(args[3]); // passing filepath
			System.out.println("Loading Data....");

			invertedIndexforReview = bds.readFile(reviewPath, typeOfFile.Review); // create inverted index for review
			invertedIndexforQA = bds.readFile(qaPath, typeOfFile.QA); // create inverted index for QA.

			System.out.println("Data loaded successfully");
			UICommands.getUserData(invertedIndexforReview, invertedIndexforQA); // calling menu
		} else {
			System.out.println("\nTerminating Program!! Please Try again!!");
			System.exit(0);
		}
	}

}
