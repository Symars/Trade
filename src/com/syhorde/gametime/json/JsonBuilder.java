package com.syhorde.gametime.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.syhorde.gametime.util.StringUtil;

public class JsonBuilder {
	
	public static String toJson(Map<String, Object> resultMap, String callback) {
//		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();
		StringBuffer jsonData = new StringBuffer();
		if(StringUtil.isNotEmpty(callback)) {
			jsonData.append(callback);
			jsonData.append("(");
			jsonData.append(gson.toJson(resultMap));
			jsonData.append(")");
		} else {
			jsonData.append(gson.toJson(resultMap));
		}
		return jsonData.toString();
	}
	
	public static List<Map<String, Object>> toList(String json) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
        list = gson.fromJson(json, new TypeToken<List<Map<String, Object>>>() {}.getType());
        return list;
	}
	
}