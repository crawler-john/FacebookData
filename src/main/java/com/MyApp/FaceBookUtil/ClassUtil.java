package com.MyApp.FaceBookUtil;

public class ClassUtil {

	public static String getClassPath(Class<?> c){
		return c.getResource("").getPath().replace("%20", " ");
	}
	public static String getClassRootPath(Class<?> c){
		return c.getResource("/").getPath().replace("%20", " ");
	}
}
