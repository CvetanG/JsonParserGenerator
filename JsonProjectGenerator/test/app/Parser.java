package app;

import org.json.JSONArray;
import org.json.JSONObject;

public class Parser {
	
	// Methods are copied lines from generated text from JSONParserGeneratorToClass
	
	public static String getLastTransactionID(JSONObject json) { 
		return json.getString("lastTransactionID");
	}

	public static String getAccountIDArr1Obj1(JSONObject json) { 
		JSONArray jsonArr1 = json.getJSONArray("transactions");
		JSONObject jsonObj1 = (JSONObject) jsonArr1.get(0);
		return jsonObj1.getString("accountID");
	}
	
	public static String getUnits(JSONObject json) {
		JSONArray jsonArr1 = json.getJSONArray("transactions");
		JSONObject jsonObj2 = (JSONObject) jsonArr1.get(1);
		JSONObject jsonObj = jsonObj2.getJSONObject("tradeOpened");
		return jsonObj.getString("units");
	}
	
	// JSONParserGeneratorToMethod
	private void parseOrder(JSONObject json) {
		JSONArray jsonArr1 = json.getJSONArray("transactions");
		JSONObject jsonObj2 = (JSONObject) jsonArr1.get(1);
		JSONObject jsonObj = jsonObj2.getJSONObject("tradeOpened");
		String units = jsonObj.getString("units");
	}

	public static String getTradeID(JSONObject json) { 
		return json.getString("tradeID");
	}


	
	
	
	
//	public static String getReason(JSONObject jsonObj1) { 
//		return json.getString("reason");
//	}
//
//	public static String getInstrument(JSONObject jsonObj1) { 
//		return json.getString("instrument");
//	}
//
//	public static String getId(JSONObject jsonObj1) { 
//		return json.getString("id");
//	}
//
//	public static String getTime(JSONObject jsonObj1) { 
//		return json.getString("time");
//	}
//
//	public static String getUnits(JSONObject jsonObj1) { 
//		return json.getString("units");
//	}
//
//	public static String getBatchID(JSONObject jsonObj1) { 
//		return json.getString("batchID");
//	}
//
//	public static String getType(JSONObject jsonObj1) { 
//		return json.getString("type");
//	}
//
//	public static String getPositionFill(JSONObject jsonObj1) { 
//		return json.getString("positionFill");
//	}
//
//	public static String getTimeInForce(JSONObject jsonObj1) { 
//		return json.getString("timeInForce");
//	}
//
//	public static String getUserID(JSONObject jsonObj1) { 
//		return json.getString("userID");
//	}
//
//	JSONObject jsonObj2 = jsonArr1.get(1);
//
//	public static String getReason(JSONObject jsonObj2) { 
//		return json.getString("reason");
//	}
//
//	public static String getOrderID(JSONObject jsonObj2) { 
//		return json.getString("orderID");
//	}
//
//	public static String getInstrument(JSONObject jsonObj2) { 
//		return json.getString("instrument");
//	}
//
//	public static String getUnits(JSONObject jsonObj2) { 
//		return json.getString("units");
//	}
//
//	public static String getBatchID(JSONObject jsonObj2) { 
//		return json.getString("batchID");
//	}
//
//	public static String getType(JSONObject jsonObj2) { 
//		return json.getString("type");
//	}
//
//	public static String getUserID(JSONObject jsonObj2) { 
//		return json.getString("userID");
//	}
//
//	public static String getAccountID(JSONObject jsonObj2) { 
//		return json.getString("accountID");
//	}
//
//	public static String getPrice(JSONObject jsonObj2) { 
//		return json.getString("price");
//	}
//
//	public static String getFinancing(JSONObject jsonObj2) { 
//		return json.getString("financing");
//	}
//
//	JSONObject jsonObj3 = json.getJSONObject(tradeOpened);
//
//	public static String getUnits(JSONObject jsonObj3) { 
//		return json.getString("units");
//	}
//
//	public static String getTradeID(JSONObject jsonObj3) { 
//		return json.getString("tradeID");
//	}
//
//	public static String getId(JSONObject jsonObj2) { 
//		return json.getString("id");
//	}
//
//	public static String getTime(JSONObject jsonObj2) { 
//		return json.getString("time");
//	}
//
//	public static String getAccountBalance(JSONObject jsonObj2) { 
//		return json.getString("accountBalance");
//	}
//
//	public static String getPl(JSONObject jsonObj2) { 
//		return json.getString("pl");
//	}
//
//	JSONObject jsonObj4 = jsonArr1.get(2);
//
//	public static String getAccountID(JSONObject jsonObj4) { 
//		return json.getString("accountID");
//	}
//
//	public static String getReason(JSONObject jsonObj4) { 
//		return json.getString("reason");
//	}
//
//	public static String getInstrument(JSONObject jsonObj4) { 
//		return json.getString("instrument");
//	}
//
//	public static String getId(JSONObject jsonObj4) { 
//		return json.getString("id");
//	}
//
//	public static String getTime(JSONObject jsonObj4) { 
//		return json.getString("time");
//	}
//
//	public static String getUnits(JSONObject jsonObj4) { 
//		return json.getString("units");
//	}
//
//	public static String getBatchID(JSONObject jsonObj4) { 
//		return json.getString("batchID");
//	}
//
//	public static String getType(JSONObject jsonObj4) { 
//		return json.getString("type");
//	}
//
//	public static String getPositionFill(JSONObject jsonObj4) { 
//		return json.getString("positionFill");
//	}
//
//	public static String getTimeInForce(JSONObject jsonObj4) { 
//		return json.getString("timeInForce");
//	}
//
//	public static String getUserID(JSONObject jsonObj4) { 
//		return json.getString("userID");
//	}
//
//	JSONObject jsonObj5 = jsonArr1.get(3);
//
//	public static String getReason(JSONObject jsonObj5) { 
//		return json.getString("reason");
//	}
//
//	public static String getOrderID(JSONObject jsonObj5) { 
//		return json.getString("orderID");
//	}
//
//	public static String getInstrument(JSONObject jsonObj5) { 
//		return json.getString("instrument");
//	}
//
//	public static String getUnits(JSONObject jsonObj5) { 
//		return json.getString("units");
//	}
//
//	public static String getBatchID(JSONObject jsonObj5) { 
//		return json.getString("batchID");
//	}
//
//	public static String getType(JSONObject jsonObj5) { 
//		return json.getString("type");
//	}
//
//	public static String getUserID(JSONObject jsonObj5) { 
//		return json.getString("userID");
//	}
//
//	public static String getAccountID(JSONObject jsonObj5) { 
//		return json.getString("accountID");
//	}
//
//	public static String getPrice(JSONObject jsonObj5) { 
//		return json.getString("price");
//	}
//
//	public static String getFinancing(JSONObject jsonObj5) { 
//		return json.getString("financing");
//	}
//
//	JSONObject jsonObj6 = json.getJSONObject(tradeOpened);
//
//	public static String getUnits(JSONObject jsonObj6) { 
//		return json.getString("units");
//	}
//
//	public static String getTradeID(JSONObject jsonObj6) { 
//		return json.getString("tradeID");
//	}
//
//	public static String getId(JSONObject jsonObj5) { 
//		return json.getString("id");
//	}
//
//	public static String getTime(JSONObject jsonObj5) { 
//		return json.getString("time");
//	}
//
//	public static String getAccountBalance(JSONObject jsonObj5) { 
//		return json.getString("accountBalance");
//	}
//
//	public static String getPl(JSONObject jsonObj5) { 
//		return json.getString("pl");
//	}
//

}
