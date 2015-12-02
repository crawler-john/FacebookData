package com.db.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DBOperation {
	//连接池的名字
	private String poolName;
	//连接
	private Connection connection  = null;
	
	public DBOperation(String poolName){
		this.poolName = poolName;
	}
	/**
	 * 关闭连接
	 */
	public void close(){
		try {
			if(this.connection != null){
				this.connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 打开操作，获取一个连接
	 * @throws SQLException
	 */
	private void open() throws SQLException{
		close();
		this.connection = DBManager.getDbManager().getConnection(this.poolName);
	}
	
	
	private PreparedStatement setPres(String sql,HashMap<Integer, Object> params) throws SQLException, ClassNotFoundException{
		if(null == params || params.size() < 0){
			return null;
		}
		PreparedStatement pres = this.connection.prepareStatement(sql);
		for(int i = 1;i<=params.size();i++){
			if(params.get(i) == null){
				pres.setString(i, null);
			}else if(params.get(i).getClass() == Class.forName("java.lang.String")){
				pres.setString(i, params.get(i).toString());
			}else if(params.get(i).getClass() == Class.forName("java.lang.Integer")){
				pres.setInt(i, (Integer)params.get(i));
			}else if(params.get(i).getClass() == Class.forName("java.lang.Long")){
				pres.setLong(i, (Long)params.get(i));
			}else if(params.get(i).getClass() == Class.forName("java.lang.Double")){
				pres.setDouble(i, (Double)params.get(i));
			}else if(params.get(i).getClass() == Class.forName("java.lang.Float")){
				pres.setFloat(i, (Float)params.get(i));
			}else if(params.get(i).getClass() == Class.forName("java.lang.Boolean")){
				pres.setBoolean(i, (Boolean)params.get(i));
			}else if(params.get(i).getClass() == Class.forName("java.sql.Date")){
				pres.setDate(i, java.sql.Date.valueOf(params.get(i).toString()));
			}else {
				return null;
			}
		}
		return pres;
	}
	/**
	 * 数据库操作
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public int executeUpdate(String sql) throws SQLException{
		this.open();
		Statement state = this.connection.createStatement();
		return state.executeUpdate(sql);
	}
	/**
	 * 数据库操作
	 * @param sql
	 * @param params
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int executeUpdate(String sql,HashMap<Integer, Object> params) throws ClassNotFoundException, SQLException {
		this.open();
		PreparedStatement pres = setPres(sql, params);
		if(null == pres){
			return 0;
		}
		return pres.executeUpdate();
	}
	//数据库查询操作
	public ResultSet executeQuery(String sql) throws SQLException{
		this.open();
		Statement state = this.connection.createStatement();
		return state.executeQuery(sql);
 	}
	//数据库查询操作
	public ResultSet executeQuery(String sql,HashMap<Integer, Object> params) throws ClassNotFoundException, SQLException{
		this.open();
		PreparedStatement pres = setPres(sql, params);
		if(null == pres){
			return null;
		}
		return pres.executeQuery();
	}
	
}
