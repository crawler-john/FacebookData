package com.db.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;
/**
 * 数据库管理
 * @author 89596
 *
 */
public class DBManager {
	private DBManager(){
		try {
			//配置连接池
			JAXPConfigurator.configure(DBPool.getDBPool().getPoolPath(), false);
			//设置连接池驱动
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获取连接
	 * @param poolName 连接池的名字
	 * @return 一个connection
	 * @throws SQLException
	 */
	public Connection getConnection(String poolName) throws SQLException{
		return DriverManager.getConnection(poolName);
	}
	
	private static class DBManagerDao{
		private static DBManager dbManager = new DBManager();
	}
	/**
	 * 获取DbManger
	 * @return
	 */
	public static DBManager getDbManager(){
		return DBManagerDao.dbManager;
	}
	
}
