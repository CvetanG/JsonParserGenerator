package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class GeneratorTest {
	
public static String parseFile(String filename) {
        
        File f = new File(filename);
        if (f.exists()){
            InputStream is;
            String jsonText;
			try {
				is = new FileInputStream(filename);
				jsonText= IOUtils.toString(is, "UTF-8");
//				System.out.println(jsonText);
				JSONObject json = new JSONObject(jsonText);
//				System.out.println(json.length());
				parseJsonObject(json, "json");
				
				
				/*
				Object name = json.get("name");
				System.out.println(name);
				
				int id = json.getInt("id");
				System.out.println(id);
				
				JSONArray cities = json.getJSONArray("cities");
				System.out.println(cities);

				JSONObject properties = json.getJSONObject("properties");
				System.out.println(properties);
				*/
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return filename;
	}

	public static void main(String[] args) {
		String filename = "sample_json2.txt";
		parseFile(filename);
	}
}
