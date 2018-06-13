package com.store.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.store.util.JdbcUtil;



public class BaseDaoImpl {
	
	protected Connection conn;
	protected PreparedStatement st;
	
	public BaseDaoImpl() throws Exception {

	}

	/**
	 * jdbc执行sql查询的固定操作,final修饰，不允许被复写
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	protected final ResultSet templateQuery(String sql) throws SQLException {
		conn = JdbcUtil.getInstance().getConnection();
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 对jdbc执行sql更新的固定操作封装
	 * 
	 * @param sql
	 * @throws SQLException 
	 */
	protected final void templateUpdate(String sql) throws SQLException {
		conn = JdbcUtil.getInstance().getConnection();
		try {
			st = conn.prepareStatement(sql);
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 对jdbc执行sql删除的固定操作封装
	 * @param sql
	 * @throws SQLException
	 */
	
	protected final void templateDelete(String sql) throws SQLException {
		conn = JdbcUtil.getInstance().getConnection();
		try {
			st = conn.prepareStatement(sql);
			st.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
}
