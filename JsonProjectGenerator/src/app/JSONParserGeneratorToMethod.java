package app;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONParserGeneratorToMethod extends JSONParserGenerator{
	
	int numTabs = 0;
	
	public JSONParserGeneratorToMethod(String fileName){
		super(fileName);
	}

	@Override 
	protected String parseJsonObject(JSONObject json, String param) {
		
		Set<String> keys = json.keySet();
		for (String keyName : keys) {
			Object obj = json.get(keyName);
			String objectType = objectToString(obj);
			String objectTypeShort = ("Integer".equals(objectType)) ? "Int" : objectType;
			if (obj instanceof JSONObject) {
				numTabs++;
				strb.append(getJSONObj(objectType, numObj, ++subObj, keyName));
				
				JSONObject jsonElement = (JSONObject) obj;
				parseJsonObject(jsonElement, "jsonObj" + numObj);
				numTabs--;
				subObj--;
			} else if (obj instanceof JSONArray) {
				numTabs++;
				strb.append(getJSONArr(objectType, ++numArr, keyName));
				
				JSONArray jsonArr = (JSONArray) obj;
				for (int i = 0; i < jsonArr.length(); i++) {
					Object objArr = jsonArr.get(i);
					if (objArr instanceof JSONObject) {
						numTabs++;
						strb.append(getJSONObjFromArr(
								objArr.getClass().getSimpleName(), ++numObj, numArr, i
								));
						
						parseJsonObject((JSONObject) objArr, "jsonObj" + numObj);
						numTabs--;
					}
				}
				numTabs--;
			} else {
//				String funcParam = json.getClass().getSimpleName() + " " + param;
				numTabs++;
				String funcParam = ((subObj == 0) ? param: param + subObj);
				strb.append(getKey(objectType, keyName, funcParam, objectTypeShort));
				numTabs--;
			}
		}
		return strb.toString();
	}
	
	// Print: JSONObject jsonObj21 = jsonObj2.getJSONObject("tradeOpened");
	private String getJSONObj(String objectType, int numObj, int subObj, String keyName){
		return String.format(
				"%s%s jsonObj%d%d = jsonObj%d.get%s(\"%s\");%n%n",
					printTabs(), objectType, numObj, subObj, numObj, objectType, keyName
				);
	}
		
	// JSONArray jsonArr1 = json.getJSONArray("transactions");
	private String getJSONArr(String objectType, int numArr, String keyName){
		return String.format(
				"%s%s jsonArr%d = json.get%s(\"%s\");%n%n",
					printTabs(), objectType, numArr, objectType, keyName
				);
	}			
		
	// JSONObject jsonObj1 = (JSONObject) jsonArr1.get(0);
	private String getJSONObjFromArr(String className, int numObj, int numArr, int i){
		return String.format(
				"%s%s jsonObj%d = (%s) jsonArr%d.get(%d);%n%n",
					printTabs(), className, numObj, className, numArr, i
				);
	}	
		
	// String accountID = jsonObj1.getString("accountID");
	private String getKey(String objectType, String keyName, String param, String objectTypeShort){
		return String.format(
				"%s%s %s = %s.get%s(\"%s\");%n%n",
					printTabs(), objectType, keyName, param, objectTypeShort, keyName
				);
	}
	
	private String printTabs(){
		StringBuilder tabs = new StringBuilder();
		for (int i = 1; i < numTabs; i++) {
			tabs.append("\t");
		}
		return tabs.toString();
	}
	
	public static void main(String[] args) {
//		String filename = "sample_json.txt";
		String filename = "sample_json2.txt";
		JSONParserGeneratorToMethod p = new JSONParserGeneratorToMethod(filename);
		p.initFile();
	}
}
