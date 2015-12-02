/**
 * 加密算法
 */

package com.MyApp.FaceBookUtil;

import java.security.MessageDigest;

import antlr.debug.MessageAdapter;

public class Encrypt {
	public static final String MD5 = "MD5";
	public static final String SHA1 = "SHA-1";
	public static final String SHA256 = "SHA256";

	/**
	 * 加密算法
	 * @param str
	 * @param encName
	 * @return
	 */
	public static String encrypt(String str,String encName){
		String reStr =null;
		try {
			//java自带的加密器
			MessageDigest digset = MessageDigest.getInstance(encName);
			byte[] bytes = digset.digest(str.getBytes());
			StringBuffer stringBuffer = new StringBuffer();
			for(byte b : bytes){
				int bt = b&0xff;
				if(bt < 16){
					stringBuffer.append(0);
				}
				stringBuffer.append(Integer.toHexString(bt));
			}
			reStr = stringBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reStr;
		
	}
	
	

}
