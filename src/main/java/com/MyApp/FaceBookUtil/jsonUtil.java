package com.MyApp.FaceBookUtil;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class jsonUtil {
	
	private static final String noData = "{\"result\" : null}";
	private static ObjectMapper mapper;
	
	static{
		mapper = new ObjectMapper();
		//属性值是null，则不生成该属性
		mapper.setSerializationInclusion(Include.NON_NULL);
	}
	
	/**
	 * 解析json字符串
	 * @param object
	 * @return
	 */
	public static String parseJson(Object object){
		if(object == null){
			return noData;
		}
		try {
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
			return noData;
		}
	}
	
	/**
	 * 将json字符串转换成jsonnode
	 * @param Json
	 * @return
	 */
	public static JsonNode jsonToObject(String Json){
		try {
			return mapper.readTree(Json);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	


}
