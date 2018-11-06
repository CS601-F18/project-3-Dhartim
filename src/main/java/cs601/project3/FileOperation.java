package cs601.project3;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class FileOperation {

	public String loadFile(String path, Charset encoding) {
		byte[] encoded = null;
		String page = "";

		try {
			encoded = Files.readAllBytes(Paths.get(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		page = new String(encoded, encoding);
		return page;
	}

	public String formatTableData(List<String> result) {
		String table = "";
		if (result.size() == 1 && result.contains("No Data Found")) {
			table = "No data found";
		} else {
			for (String word : result) {
				table = table + word;
			}
		}
		return table;
	}
	
	public String updatePageContent(HashMap<String, String> map, String file) {
		for (String key : map.keySet()) {
			file = file.replace(key, map.get(key));
		}
		return file;
	}
}
