package com.MyApp.FaceBookUtil;

import org.apache.commons.lang3.StringUtils;

public class EmojiFilterUtils {

	public static String filterEmoji(String source) {
		if(StringUtils.isNotBlank(source)){
			return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");
		}else{
			return source;
		}
		
	}
	public static void main(String[] args) {
		String string = "Inside the dining hall of the newly renovated Dunster House. 🍴";
		System.out.println(filterEmoji(string));
	}
	
	
}
