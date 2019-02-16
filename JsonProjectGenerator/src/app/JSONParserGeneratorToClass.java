package app;

import java.util.Set;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicHeader;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSONParserGeneratorToClass extends JSONParserGenerator {
	
	public JSONParserGeneratorToClass(String fileName){
		super(fileName);
	}
	
	public JSONParserGeneratorToClass(HttpUriRequest httpGet, String fileName){
		super(httpGet, fileName);
	}
	
	@Override
	protected String parseJsonObject(JSONObject json, String param) {
		Set<String> keys = json.keySet();
		for (String keyName : keys) {
			Object obj = json.get(keyName);
			String objectType = objectToString(obj);
			String objectTypeShort = ("Integer".equals(objectType)) ? "Int" : objectType;
			if (obj instanceof JSONObject) {
				
				strings.add(getJSONObj(objectType, numObj, ++subObj, keyName));
				JSONObject jsonElement = (JSONObject) obj;
				parseJsonObject(jsonElement, "jsonObj" + numObj);
				strings.remove(strings.size() -1);
				subObj--;
			} else if (obj instanceof JSONArray) {
				strings.add(getJSONArr(objectType, ++numArr, keyName));
				
				JSONArray jsonArr = (JSONArray) obj;
				for (int i = 0; i < jsonArr.length(); i++) {
					Object objArr = jsonArr.get(i);
					if (objArr instanceof JSONObject) {
						strings.add(getJSONObjFromArr(
								objArr.getClass().getSimpleName(), ++numObj, numArr, i
								));
						
						parseJsonObject((JSONObject) objArr, "jsonObj" + numObj);
						strings.remove(strings.size() -1);
					}
				}
			} else {
//				String funcParam = json.getClass().getSimpleName() + " " + param;
				String funcParam = ((subObj == 0) ? param: param + subObj);
				strb.append(getKey(objectType, keyName, funcParam, objectTypeShort));
			}
		}
		return strb.toString();
	}
	
	// Print: JSONObject jsonObj21 = jsonObj2.getJSONObject("tradeOpened");
	private String getJSONObj(String objectType, int numObj, int subObj, String keyName){
		return String.format(
				"\t%s jsonObj%d%d = jsonObj%d.get%s(\"%s\");%n",
					objectType, numObj, subObj, numObj, objectType, keyName
				);
	}
	
	// Print: JSONArray jsonArr1 = json.getJSONArray("transactions");
	private String getJSONArr(String objectType, int numArr, String keyName){
		return String.format(
				"\t%s jsonArr%d = json.get%s(\"%s\");%n",
					objectType, numArr, objectType, keyName
				);
	}
	
	// Print: JSONObject jsonObj1 = (JSONObject) jsonArr1.get(0);
	private String getJSONObjFromArr(String className, int numObj, int numArr, int i){
		return String.format(
				"\t%s jsonObj%d = (%s) jsonArr%d.get(%d);%n",
				className, numObj, className, numArr, i
				);
	}
	
	// Print:
	// public static String getAccountID(JSONObject json) { 
	// 		JSONArray jsonArr1 = json.getJSONArray("transactions");
	//      JSONObject jsonObj1 = (JSONObject) jsonArr1.get(0);
	//      return jsonObj1.getString("accountID");
	// }
	private String getKey(String objectType, String keyName, String funcParam, String objectTypeShort){
		return String.format(
				"%s %s get%s(JSONObject json) { %n"
						+ "%s"
						+ "\treturn %s.get%s(\"%s\");%n"
						+ "}%n%n",
						METHOD_ACCESS_MODIFIER, objectType, capFirst(keyName), printList(),  funcParam, objectTypeShort, keyName
				);
	}
	
	public static void main(String[] args) {
//		String filename = "sample_json.txt";
//		String filename = "sample_json2.txt";
//		JSONParserGeneratorToClass p = new JSONParserGeneratorToClass(filename);
//		p.initFile();
		
		String url = "https://api-fxpractice.oanda.com/v3/accounts/101-004-8512520-001/pricing?instruments=EUR_USD";
		String accessToken = "a7fde9c17d2aa9ec0c8fb4f7ce7b9c0b-85132906f36af8e227d729f571657b4f";
		BasicHeader UNIX_DATETIME_HEADER = new BasicHeader("Content-Type", "application/x-www-form-urlencoded");
		
		BasicHeader authHeader = new BasicHeader("Authorization", "Bearer " + accessToken);
		HttpUriRequest httpGet = new HttpGet(url);
		httpGet.setHeader(authHeader);
		httpGet.setHeader(UNIX_DATETIME_HEADER);
		
		String fileName = "getResponse.txt";
		
		JSONParserGeneratorToClass pURL = new JSONParserGeneratorToClass(httpGet, fileName);
		pURL.initUrl();
	}
}
