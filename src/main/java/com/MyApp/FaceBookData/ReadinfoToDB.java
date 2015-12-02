package com.MyApp.FaceBookData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.db.manager.FaceBookDB;



public class ReadinfoToDB {
	public static void readFileByLines(String fileName){
		FaceBookDB db = new FaceBookDB();
		
		File file = new File(fileName);
		BufferedReader reader = null;
		   try {
	           System.out.println("以行为单位读取文件内容，一次读一整行：");
	           reader = new BufferedReader(new FileReader(file));
	           String tempString = null;
	           int i = 0;
	           // 一次读入一行，直到读入null为文件结束
	           while ((tempString = reader.readLine()) != null) {
	               // 显示行号
	        	   i++;
	        	   String str[] = tempString.split(";");
	        	   if(str.length == 2){
	        		   db.insertBasicInfo(i, str[0].trim(), str[1].trim());
	        		   System.out.println(str[0]+"                    "+str[1]);
	        	   }else{
	        		   db.insertBasicInfo(i, str[0].trim(), null);
	        		   System.out.println(str[0]);
	        	   }
	               
	              
	           }
	           reader.close();
	       } catch (IOException e) {
	           e.printStackTrace();
	       } finally {
	           if (reader != null) {
	               try {
	                   reader.close();
	               } catch (IOException e1) {
	               }
	           }
	       }
		
		}
	
	
	
	public static void readFileByLinesUpdate(String fileName){
		FaceBookDB db = new FaceBookDB();
		
		File file = new File(fileName);
		BufferedReader reader = null;
		   try {
	           System.out.println("以行为单位读取文件内容，一次读一整行：");
	           reader = new BufferedReader(new FileReader(file));
	           String tempString = null;
	           int i = 0;
	           // 一次读入一行，直到读入null为文件结束
	           while ((tempString = reader.readLine()) != null) {
	               // 显示行号
	        	   i++;
	        	   String str[] = tempString.split(";");
	        	   if(str.length == 2){
	        		   db.updateBasicData(str[0].trim(), str[1].trim());
	        		   System.out.println(str[0]+"                    "+str[1]);
	        	   }else{
	        		  
	        		   System.out.println(str[0]);
	        	   }
	               
	              
	           }
	           reader.close();
	       } catch (IOException e) {
	           e.printStackTrace();
	       } finally {
	           if (reader != null) {
	               try {
	                   reader.close();
	               } catch (IOException e1) {
	               }
	           }
	       }
		
		}
	
	public static void main(String[] args) {
		
//		ReadinfoToDB.readFileByLines("GlobalList.txt");
		readFileByLinesUpdate("GlobalList-1.txt");
	}
	
	
	
	
}
	


	
	
	
	

