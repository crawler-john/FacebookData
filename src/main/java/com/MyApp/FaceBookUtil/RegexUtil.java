package com.MyApp.FaceBookUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式实用类
 * @author 89596
 *
 */
public class RegexUtil {
	
	private static final String ChRegex = "";
	private static final String rootUrlRegex = "\\";
	private static final String currentUrlRegex = "";
	public static String getFirstString(String dealStr,String regexStr,int n) {
		if (dealStr == null || regexStr == null || n < 1){
			return "";
		}
		//Pattern.CASE_INSENSITIVE : 忽略大小写
		//Pattern.DOTALL	：忽略换行符
		Pattern pattern = Pattern.compile(regexStr, Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
		Matcher matcher = pattern.matcher(dealStr);
		while (matcher.find()) {
			return matcher.group(n).trim();
		}
		return "";	
	}
	/**
	 * 将连接地址中的中文进行编码处理
	 * @param url
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeUrlCh(String url) throws UnsupportedEncodingException{
		while(true){
			String s = getFirstString(url, ChRegex, 1);
			if("".equals(s)){
				return url;
			}
			url = url.replaceAll(s, URLEncoder.encode(s, "utf-8"));
		}
	}
	/**
	 * 组装网址 网页的url
	 * @param url
	 * @param currentUrl
	 * @return
	 */
	private static String getHttpUrl(String url,String currentUrl){
		//新增的replaceAll  转化有些地址接口中的转化地址，如\/test\/1.html
		try {
			url = encodeUrlCh(url).replaceAll("\\\\/","/");
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(url.indexOf("http") == 0){
			return url;
		}
		if(url.indexOf("/") == 0){
			return getFirstString(currentUrl, rootUrlRegex, 1)+url.substring(1);
		}
		if(url.indexOf("\\/") == 0){
			return getFirstString(currentUrl, rootUrlRegex, 1)+url.substring(2);
		}
		return getFirstString(currentUrl, currentUrlRegex, 1)+url;
		
	}
	
	
	/**
	 * 获取和正则匹配的绝对链接地址
	 * @param dealStr 需要匹配的字符串
	 * @param regexStr	正则字符串
	 * @param currentUrl 当前的URL
	 * @param n 提取内容在正则中位置
	 * @return
	 */
	public static List<String> getArrayList(String dealStr,String regexStr,String currentUrl,int n){
		List<String> list = new ArrayList<String>();
		if (dealStr == null || regexStr == null || n < 1 || dealStr.isEmpty()){
			return list;
		}
		Pattern pattern = Pattern.compile(regexStr, Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
		Matcher matcher = pattern.matcher(dealStr);
		while (matcher.find()) {
			list.add(getHttpUrl(matcher.group(n).trim(),currentUrl));
		}
		return list;
	}
	
	
	
	/**
	 * 获取和正则匹配的绝对链接地址
	 * @param dealStr
	 * @param regexStr
	 * @param n
	 * @return
	 */
	public static List<String> getListString(String dealStr,String regexStr,int n) {
		List<String> list = new ArrayList<String>();
		if (dealStr == null || regexStr == null || n < 1){
			return list;
		}
		Pattern pattern = Pattern.compile(regexStr, Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
		Matcher matcher = pattern.matcher(dealStr);
		while (matcher.find()) {
			list.add(matcher.group(n).trim());
		}
		return list;
	}
	
	public static List<String []> getListString(String dealStr,String regexStr,int[] array) {
		List<String[]> list = new ArrayList<String[]>();
		if (dealStr == null || regexStr == null || array == null){
			return list;
		}
		for(int i = 0;i < array.length;i++){
			if(array[i] < 1 ){
				return list;
			}	
		}
		Pattern pattern = Pattern.compile(regexStr, Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
		Matcher matcher = pattern.matcher(dealStr);
		while (matcher.find()) {
			String[] ss = new String[array.length];
			for(int j=0;j<array.length;j++){
				ss[j] = matcher.group(array[j]).trim();
			}
			list.add(ss);
		}
		
		return list;
	}
	

	public static String getString(String dealStr, String regexStr,
			String splitStr, int n) {
		if (dealStr == null || regexStr == null || n < 1){
			return "";
		}
		//Pattern.CASE_INSENSITIVE : 忽略大小写
		//Pattern.DOTALL	：忽略换行符
		Pattern pattern = Pattern.compile(regexStr, Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
		Matcher matcher = pattern.matcher(dealStr);
		String string="";
		while (matcher.find()) {
			string = string +splitStr +matcher.group(n).trim();
		}
		
		return string.trim();
		
		
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String dealStr  = "abcdddddd112312232a asdasdda";
		String regexStr = "a(.*?)a";
		System.out.println(RegexUtil.getFirstString(dealStr, regexStr, 1));
	}

}
