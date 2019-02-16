package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class GeneratorTest {

	public static JSONObject parseFile(String filename) {
		JSONObject json = null;
		File f = new File(filename);
		if (f.exists()) {
			String jsonText;
			try (InputStream is = new FileInputStream(filename)){
				jsonText = IOUtils.toString(is, "UTF-8");
				// System.out.println(jsonText);
				json = new JSONObject(jsonText);

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return json;
	}

	public static void main(String[] args) {
		String filename = "sample_json2.txt";
		JSONObject json = parseFile(filename);
		
		System.out.println(Parser.getLastTransactionID(json));
		System.out.println(Parser.getAccountIDArr1Obj1(json));
		System.out.println(Parser.getUnits(json));
	}
}
