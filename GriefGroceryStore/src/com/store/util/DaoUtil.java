package com.store.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.store.model.Product;
import com.store.model.User;

public class DaoUtil {
    
	
	public static User queryUserByUsername(String username) {
		String sql="select * from user where username=?";
		User user=new User();
		try(Connection conn=JdbcUtil.getInstance().getConnection();
			PreparedStatement ps=conn.prepareStatement(sql)) {
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
			System.out.println(rs.getInt(1));
			user.setUserId(rs.getInt(1));
			user.setUserName(rs.getString(2));	
            user.setUserPassword(rs.getString(3));
            user.setAccount(rs.getDouble(4));
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	   public static Product  queryProductByPid(int pid) {
		   Product product=new Product();
		   String sql="select * from product where id=?";
		   try(Connection conn=JdbcUtil.getInstance().getConnection();
			   PreparedStatement ps=conn.prepareStatement(sql);) {
			   ps.setInt(1, pid);
			   ResultSet rs=ps.executeQuery();
               if(rs.next()) {
            	product.setProductId(rs.getInt(1));
   				product.setProductName(rs.getString(2));
   				product.setProductPrice(rs.getDouble(3));
   				product.setProductInventory(rs.getInt(4));
   				product.setProductSeller(rs.getString(5));   				
               }			
 		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		   return product;
		   
		   
	   }
	
}
