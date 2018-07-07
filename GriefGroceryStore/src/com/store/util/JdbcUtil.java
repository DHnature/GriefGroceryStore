package com.store.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.store.constants.SystemConstants;

public class JdbcUtil {
	private static JdbcUtil instance=new JdbcUtil();
	private JdbcUtil() {}
	//懒汉式单例模式
	public static JdbcUtil getInstance() {
		return instance;
	}
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Connection getConnection() throws SQLException {

		
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306"+SystemConstants.dataBaseName+"?characterEncoding=UTF-8", "root",
                "admin");
		
	}
	
	

}
