package cs601.project3;

import java.nio.file.Path;
import java.nio.file.Paths;
import cs601.project1.InvertedIndexBuilder;
import cs601.project1.AmazonSearch.typeOfFile;
import cs601.project1.InvertedIndex;

public class SetUpInvertedIndex 
{
	public static boolean isDataLoading = true;
	//private Thread reviewThread, qathread;
	//private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static SetUpInvertedIndex instance;
	private String configurationFile;
	private ReadConfigurationFile readConfigfile = new ReadConfigurationFile();
	private ConfigurationBean configurationBuilder = new ConfigurationBean();
	

	private SetUpInvertedIndex(String file) 
	{
		this.configurationFile = file;
	}

	public static SetUpInvertedIndex getInstance(String file) {
		if (instance == null) {
			instance = new SetUpInvertedIndex(file);
		}
		return instance;
	}
	
	
	
	public void initInvertedIndex() {
		
		InvertedIndexBuilder bds = new InvertedIndexBuilder();

		InvertedIndex invertedIndexforReview;
		InvertedIndex invertedIndexforQA;
		InvertedIndexInitilizer indexInitilizer = InvertedIndexInitilizer.getInstance();
		configurationBuilder = readConfigfile.readSlackConfigurationFile(configurationFile);
		//pass file path
		Path reviewFile = Paths.get(configurationBuilder.getreviewFile());
		Path qaFile = Paths.get(configurationBuilder.getqaFile());
		//logger.log(Level.INFO, String.format(SearchAppLogMsgDict.loading));
		isDataLoading = true;
		invertedIndexforReview = bds.readFile(reviewFile, typeOfFile.Review); // create inverted index for review
		invertedIndexforQA = bds.readFile(qaFile, typeOfFile.QA); // create inverted index for QA.
		
		isDataLoading = false;

		indexInitilizer.setInvertIndexReview(invertedIndexforReview);
		indexInitilizer.setInvertIndexQA(invertedIndexforQA);
		//logger.log(Level.INFO, String.format(SearchAppLogMsgDict.loadSuccess));

	}
}
