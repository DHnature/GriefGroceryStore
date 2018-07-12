package com.store.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 将jdbc的结果集转换为Map的工具类
 * @author Administrator
 *
 */
public class ResultMapUtil {
	public static Map<String,String>getResultMap(ResultSet rs)  
            throws SQLException{
		Map<String, String> hm = new HashMap<String, String>();  
        ResultSetMetaData rsmd = rs.getMetaData();  
        int count = rsmd.getColumnCount();  
        for (int i = 1; i <= count; i++) {  
            String key = rsmd.getColumnLabel(i);  
            String value = rs.getString(i);  
            hm.put(key, value);  
        }  
        return hm;  
	}
}
