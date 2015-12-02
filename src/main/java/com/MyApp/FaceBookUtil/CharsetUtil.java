package com.MyApp.FaceBookUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;
/**
 * 
 * @author john
 *
 */
public class CharsetUtil {

	// 定义一个探测器
	private static final CodepageDetectorProxy detector;
	static {
		detector = CodepageDetectorProxy.getInstance();
		detector.add(new ParsingDetector(false));
		detector.add(ASCIIDetector.getInstance());
		detector.add(UnicodeDetector.getInstance());
		detector.add(JChardetFacade.getInstance());
	}
	/**
	 * 检测流的编码方式
	 * @param url
	 * @param defaultCharset
	 * @return
	 */
	public static String getStringCharset(URL url,
			String defaultCharset) {
		if (url == null) {
			return defaultCharset;
		}
		try {
			Charset charset = detector.detectCodepage(url);
			if (charset != null) {
				return charset.name();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultCharset;
	}
	
	
	
	/**
	 * 检测流的编码方式
	 * @param inputStream	需要检测的流
	 * @param defaultCharset	默认的编码方式
	 * @return
	 */
	public static String getStringCharset(InputStream inputStream,
			String defaultCharset) {
		if (inputStream == null) {
			return defaultCharset;
		}
		int count = 200;
		try {
			count = inputStream.available();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Charset charset = detector.detectCodepage(inputStream, count);
			if (charset != null) {
				return charset.name();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultCharset;
	}

	public static void main(String[] args) {

			try {
				InputStream inputStream = new FileInputStream(new File("Test.txt"));
				System.out.println(getStringCharset(inputStream, "UTF-8"));
				
				System.out.println(getStringCharset(new URL("http://zhidao.baidu.com/link?url=op5bB0DKrfF2UQM4zEFg_Rc46BY_6G-u-zoiO3GZgvKpxJ3M-GOAVm3MSWgDJCdBiVVaKsJQhTLvpvE5JFWxCP_-EOe4irzsc_NI3-hSiTe"), "UTF-8"));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		
	}
}
