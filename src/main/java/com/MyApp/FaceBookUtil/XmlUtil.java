package com.MyApp.FaceBookUtil;

import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import javax.xml.bind.*;

public class XmlUtil {
	private static final String noResult = "<result>no result</result>";

	/**
	 * 将xml字符串转换成document类
	 * 
	 * @param xml
	 * @return
	 */
	public static Document createFromString(String xml) {
		try {
			return DocumentHelper.parseText(xml);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将javabean 转换为xml字符串
	 * 
	 * @param object
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static String parseObjectToXmlString(Object object) {
		if (object == null) {
			return noResult;
		}
		StringWriter stringWriter = new StringWriter();
		JAXBContext jaxbContext;
		Marshaller marshaller;

		try {
			jaxbContext = JAXBContext.newInstance(object.getClass());
			marshaller = jaxbContext.createMarshaller();
			marshaller.marshal(object, stringWriter);
			return stringWriter.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return noResult;

		}

	}

}
