package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class JSONParserGenerator {
	
	protected String fileName;
	protected HttpUriRequest httpGet;
	
//	protected static final String METHOD_ACCESS_MODIFIER = "private static";
	protected static final String METHOD_ACCESS_MODIFIER = "public static";
//	protected static final String METHOD_ACCESS_MODIFIER = "public";
	
	protected StringBuilder strb = new StringBuilder();

	protected List<String> strings = new ArrayList<>();
	
	protected int numObj = 0;
	protected int subObj = 0;
	protected int numArr = 0;
	
	public JSONParserGenerator(String filename){
		this.fileName = filename;
	}
	
	public JSONParserGenerator(HttpUriRequest httpGet, String fileName){
		this.httpGet = httpGet;
		this.fileName = fileName;
	}
	
	public void initFile(){
		String contents = parseTextFile();
		String newName = processName();
		exportFile(contents, newName);
		System.out.println("Done!!!");
	}
	
	public void initUrl(){
		String contents;
		try {
			contents = parseGetResponse();
			exportFile(contents, this.fileName);
			System.out.println("Done!!!");
		} catch (Exception e) {
			System.out.println("Error encountered while fetching get response");
		}
		
	}
	
	protected String processName() {
		StringBuilder finalName = new StringBuilder();
		String[] split = this.fileName.split("\\.");
		String fName = split[0];
		String fExt = split[1];
		String suffix = "_parser_gen.";
		finalName.append(fName);
		finalName.append(suffix);
		finalName.append(fExt);
		return finalName.toString();
	}
	
	protected String parseTextFile() {
		String contents = "";
        File f = new File(this.fileName);
        if (f.exists()){
            String jsonText;
			try (InputStream is = new FileInputStream(this.fileName)) {
				
				jsonText = IOUtils.toString(is, "UTF-8");
//				System.out.println(jsonText);
				JSONObject json = new JSONObject(jsonText);
//				System.out.println(json.length());
				contents = parseJsonObject(json, "json");
				
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
		return contents;
	}
	
	protected String parseGetResponse() throws Exception {
		String contents = "";
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()){
			HttpResponse response = httpClient.execute(httpGet);
			String strResp = responseToString(response);
			if (StringUtils.isNoneEmpty(strResp)) {
				JSONObject json = new JSONObject(strResp);
				contents = parseJsonObject(json, "json");
			} else {
				HttpEntity entity = response.getEntity();
				String responseString = EntityUtils.toString(entity, "UTF-8");;
				System.err.println(responseString);
				throw new Exception();
			}
		}
		return contents;
	}
	
	private final String responseToString(HttpResponse response) throws IOException {
		HttpEntity entity = response.getEntity();
		if ((response.getStatusLine().getStatusCode() == HttpStatus.SC_OK || response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED)
				&& entity != null) {
			InputStream stream = entity.getContent();
			String line;
			BufferedReader br = new BufferedReader(new InputStreamReader(stream));
			StringBuilder strResp = new StringBuilder();
			while ((line = br.readLine()) != null) {
				strResp.append(line);
			}
			IOUtils.closeQuietly(stream);
			IOUtils.closeQuietly(br);
			return strResp.toString();
		} else {
			return StringUtils.EMPTY;
		}
	}
	
	protected static String objectToString(Object obj) {
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
	
	protected String printList() {
		StringBuilder strb = new StringBuilder();
		for (String str : strings) {
			strb.append(str);
		}
		return strb.toString();
	}

	protected void exportFile(String str, String fileName) {
		File file = new File(fileName);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
		    writer.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected String capFirst(String key) {
		key = key.substring(0,1).toUpperCase() + key.substring(1);
		return key;
	}
	
	protected abstract String parseJsonObject(JSONObject json, String param);
	
}
