package com.db.manager;

import com.MyApp.FaceBookUtil.ClassUtil;


//单例模式 数据库连接池
public class DBPool {
	//数据库连接池的路径
	private String poolPath;
	
	private DBPool(){
		
	}
	public static DBPool getDBPool(){
		return DBPoolDao.dbPool;
	}
	private static class DBPoolDao{
		private static DBPool dbPool = new DBPool();
		static{
			dbPool.setPoolPath("D:/proxool.xml");
		}
	}
	
	
	public String getPoolPath() {
		if(poolPath == null){
			poolPath = ClassUtil.getClassRootPath(DBPool.class)+"proxool.xml";
		}
		return poolPath;
	}
	public void setPoolPath(String poolPath) {
		this.poolPath = poolPath;
	}
	public static void main(String[] args) {
		System.out.println(ClassUtil.getClassRootPath(DBPool.class)+"proxool.xml");
	}
	
}
