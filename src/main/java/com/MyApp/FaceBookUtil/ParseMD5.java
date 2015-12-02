package com.MyApp.FaceBookUtil;
//MD5加密
public class ParseMD5 extends Encrypt{
	
	
	public static String parseStrToMD5(String str){
		return encrypt(str, MD5);
	}
	public static String parseStrToUpperMD5(String str){
		return parseStrToMD5(str).toUpperCase();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(ParseMD5.parseStrToMD5("http://book.zongheng.com/book/359388.html"));
	}

}
