/**
 * 
 */
package com.MyApp.FaceBookCralwer;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import com.MyApp.FaceBookUtil.CharsetUtil;

/**
 * @author john
 *
 */
public abstract class CrawlBase {
	// 日志登记类
	private static Logger logger = Logger.getLogger(CrawlBase.class);
	// 页面的代码
	private String JsonSourceCode = "";
	// 应答头信息
	private Header[] responHeaders = null;
	// 链接超时时间
	private static int connectTimeOut = 10000;
	// 读超时时间
	private static int readTimeOut = 20000;
	// 最大链接次数
	private static int MaxConnectTimes = 20;
	// 编码方式
	private static String charSetName = "iso-8859-1";

	private static MultiThreadedHttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
	private static HttpClient httpclient = new HttpClient(httpConnectionManager);
	// 静态构造方法
	static {

		httpclient.getHttpConnectionManager().getParams()
				.setConnectionTimeout(connectTimeOut);
		httpclient.getHttpConnectionManager().getParams()
				.setSoTimeout(readTimeOut);
		httpclient.getParams().setContentCharset("utf-8");
		httpclient.getHostConfiguration().setProxy("localhost", 8580);

	}

	/**
	 * 通过GET方式读取网页信息
	 * 
	 * @param urlStr
	 * @param params
	 *            头信息
	 * @param charSetName
	 * @return
	 */
	public boolean readJsonByGet(String urlStr, HashMap<String, String> params,
			String charSetName) {
		GetMethod method = createGetMethod(urlStr, params);
		return readJson(method, charSetName, urlStr);

	}

	/**
	 * 通过POST方法读取JSON信息
	 * 
	 * @param urlStr
	 * @param params
	 * @param charSetName
	 * @return
	 */
	public boolean readJsonByPost(String urlStr,
			HashMap<String, String> params, String charSetName) {
		PostMethod method = createPostMethod(urlStr, params);
		return readJson(method, charSetName, urlStr);
	}

	/**
	 * 读取JSON信息
	 * 
	 * @param method
	 * @param defaultCharset
	 * @param urlStr
	 * @return 是否读取成功
	 */
	private boolean readJson(HttpMethod method, String defaultCharset,
			String urlStr) {
		int n = MaxConnectTimes;
		while (n > 0) {
			try {
				if (httpclient.executeMethod(method) != HttpStatus.SC_OK) {
					logger.info(" can't connect " + urlStr
							+ (MaxConnectTimes - n + 1));
					n--;
				} else {
					responHeaders = method.getRequestHeaders();
					InputStream inputStream = method.getResponseBodyAsStream();
					InputStreamReader inputStreamReader = new InputStreamReader(
							inputStream, defaultCharset);
					BufferedReader bufferedReader = new BufferedReader(
							inputStreamReader);
					StringBuffer stringBuffer = new StringBuffer();
					String lineString = "";
					while (null != (lineString = bufferedReader.readLine())) {
						stringBuffer.append(lineString);
						stringBuffer.append("\n");
					}
		
					JsonSourceCode = stringBuffer.toString();

					// 对获取到的页面代码进行转码处理
					InputStream in = new ByteArrayInputStream(
							JsonSourceCode.getBytes(charSetName));
					String charSet = CharsetUtil.getStringCharset(in,
							defaultCharset);
					if (charSetName.toLowerCase().equals(charSet.toLowerCase())) {
						JsonSourceCode = new String(
								JsonSourceCode.getBytes(charSetName), charSet);
					}
					return true;

				}

			} catch (Exception e) {
				e.printStackTrace();
				logger.error(urlStr + " can't connect "
						+ (MaxConnectTimes - n + 1));
				n--;
			}
		}
		return false;

	}

	/**
	 * 创建GET方法
	 * 
	 * @param urlString
	 * @param params
	 * @return
	 */
	private GetMethod createGetMethod(String urlString,
			HashMap<String, String> params) {
		GetMethod method = new GetMethod(urlString);
		if (params == null) {
			return method;
		}
		Iterator<Entry<String, String>> iterator = params.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry entry = (Entry) iterator.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			method.setRequestHeader(key, value);
		}
		return method;
	}

	private PostMethod createPostMethod(String urlString,
			HashMap<String, String> params) {
		PostMethod method = new PostMethod(urlString);
		if (params == null) {
			return method;
		}
		Iterator<Entry<String, String>> iterator = params.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry entry = (Entry) iterator.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			method.setRequestHeader(key, value);
		}
		return method;
	}

	public static void setConnectTimeOut(int connectTimeOut) {
		CrawlBase.connectTimeOut = connectTimeOut;
	}

	public static void setReadTimeOut(int readTimeOut) {
		CrawlBase.readTimeOut = readTimeOut;
	}

	public static void setMaxConnectTimes(int maxConnectTimes) {
		MaxConnectTimes = maxConnectTimes;
	}

	public static void setCharSetName(String charSetName) {
		CrawlBase.charSetName = charSetName;
	}

	public String getJsonSourceCode() {
		return JsonSourceCode;
	}

	public Header[] getResponHeaders() {
		return responHeaders;
	}

}
