package cs601.project3;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

//class to setup inverted index before server starts
//enum typeOfFile {Review, QA};
public class SetUpInvertedIndex 
{
	//	private static volatile SetUpInvertedIndex setItUp;
	// = new InvertedIndex();
	private  String configFile;

	public SetUpInvertedIndex(String configfile) 
	{
		this.configFile = configfile;
	}
	ConfigurationBean config = new ConfigurationBean();
	Gson gson = new Gson();
	public void setUpInvertedIndex()
	{
		InvertedIndexBuilder invertedIndexBuilder = new InvertedIndexBuilder();
		InvertedIndex invtIndexReview, invtindexQA;

		try {
			JsonReader reader = new JsonReader(new FileReader(configFile));
			config = gson.fromJson(reader, ConfigurationBean.class);
			//System.out.println(config.getreviewFile());

		} catch (FileNotFoundException e) 
		{
			System.exit(0);
		}
		Path reviewFile = Paths.get(config.getreviewFile());
		Path qaFile = Paths.get(config.getqaFile());
		//System.out.println(file);
		invtIndexReview = invertedIndexBuilder.readFile(reviewFile, typeOfFile.Review);
		invtindexQA = invertedIndexBuilder.readFile(qaFile, typeOfFile.QA);
		//invtIndexReview.addasin(asinId, asinText);
	}
}
