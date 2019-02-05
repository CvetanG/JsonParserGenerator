package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class Generator {
	
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
		
	private static String objectToString(Object obj) {
		if (obj instanceof Integer) {
			return "Integer";
		} else if (obj instanceof String) {
			return "String";
		} else if (obj instanceof Long) {
			return "Long";
		} else if (obj instanceof Double) {
			return "Double";
		} else if (obj instanceof Boolean) {
			return "Boolean";
		} else if (obj instanceof JSONArray) {
			return "JSONArray";
		} else if (obj instanceof JSONObject) {
			return "JSONObject";
		}
		return null;
	}
	
	private static int numObj = 0;
	private static int numArr = 0;
	private static void parseJsonObject(JSONObject json, String param) {
		Set<String> keys = json.keySet();
		for (String keyName : keys) {
			Object obj = json.get(keyName);
			String objectType = objectToString(obj);
			String objectTypeShort = ("Integer".equals(objectType)) ? "Int" : objectType;
			if (obj instanceof JSONObject) {
				System.out.format(
						"%s jsonObj%d = json.get%s(%s);\n\n",
							objectType, ++numObj, objectType, keyName
						);
				
				JSONObject jsonElement = (JSONObject) obj;
				parseJsonObject(jsonElement, "jsonObj" + numObj);
			} else if (obj instanceof JSONArray) {
				System.out.format(
						"%s jsonArr%d = json.get%s(%s);\n\n",
							objectType, ++numArr, objectType,keyName
						);
				
				JSONArray jsonArr = (JSONArray) obj;
				for (int i = 0; i < jsonArr.length(); i++) {
					Object objArr = jsonArr.get(i);
					if (objArr instanceof JSONObject) {
						System.out.format(
								"%s jsonObj%d = jsonArr%d.get(%d);\n\n",
								objArr.getClass().getSimpleName(), ++numObj, numArr, i
								);
						
						parseJsonObject((JSONObject) objArr, "jsonObj" + numObj);
					}
				}
			} else {
				String funcParam = json.getClass().getSimpleName() + " " + param;
				System.out.format(
						"private static %s get%s(%s) { \n"
								+ "\treturn json.get%s(\"%s\");\n"
								+ "}\n\n",
								objectType, capFirst(keyName), funcParam, objectTypeShort, keyName
						);
			}
		}
	}
	
	private static String capFirst(String key) {
		key = key.substring(0,1).toUpperCase() + key.substring(1);
		return key;
	}
	
	private static Integer getId(JSONObject json) {
		return json.getInt("id");
	}
	
	public static void main(String[] args) {
//		String filename = "sample_json.txt";
		String filename = "sample_json2.txt";
		parseFile(filename);
//		System.out.println(parseFile(filename));
	}
}
