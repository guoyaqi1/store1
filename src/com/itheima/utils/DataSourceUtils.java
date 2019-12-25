package com.itheima.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {
	private static ComboPooledDataSource ds=new ComboPooledDataSource();
	private static ThreadLocal<Connection> tl=new ThreadLocal<>();
	
	/**
	 * 
	 * @return 
	 */
	public static DataSource getDataSource(){
		return ds;
	}
	
	/**
	 * 
	 * @return 
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		Connection conn = tl.get();
		//
		if(conn==null){
			conn=ds.getConnection();
			
			//
			tl.set(conn);
		}
		
		return conn;
	}
	
	
	
	/**
	 *
	 * 
	 * @param conn
	 *            
	 * @param st
	 *            
	 * @param rs
	 *            
	 */
	public static void closeResource(Connection conn, Statement st, ResultSet rs) {
		closeResultSet(rs);
		closeStatement(st);
		closeConn(conn);
	}

	/**
	 * 
	 * 
	 * @param conn
	 *            
	 */
	public static void closeConn(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				//
				tl.remove();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}

	}

	/**
	 * 
	 * 
	 * @param st
	 *           
	 */
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			st = null;
		}

	}

	/**
	 * 
	 * 
	 * @param rs
	 *           
	 */
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}

	}
	
	/**
	 * 
	 * @throws SQLException 
	 */
	public static void startTransaction() throws SQLException{
		//1.
		Connection conn=getConnection();
		
		//2.
		conn.setAutoCommit(false);
	}
	
	/**
	 *
	 */
	public static void commitAndClose(){
		try {
			//0.
			Connection conn = getConnection();
			
			//1.
			conn.commit();
			
			//2.
			closeConn(conn);
		} catch (SQLException e) {
		}
		
	}
	
	/**
	 * 
	 */
	public static void rollbackAndClose(){
		try {
			//0.
			Connection conn = getConnection();
			
			//1.
			conn.rollback();
			
			//2.
			closeConn(conn);
		} catch (SQLException e) {
		}
		
	}
}
