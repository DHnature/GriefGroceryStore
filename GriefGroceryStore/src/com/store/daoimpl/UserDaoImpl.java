package com.store.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.store.dao.UserDao;
import com.store.model.Product;
import com.store.model.User;
import com.store.util.DaoUtil;
import com.store.util.JdbcUtil;
import com.store.util.ResultMapUtil;



public class UserDaoImpl extends BaseDaoImpl implements UserDao{
      public UserDaoImpl() throws Exception {
		// TODO Auto-generated constructor stub
	}     
     //用户登录 
	@Override
	public User login(String username,String password) {
        User user=new User();
        String sql="select * from user where username=? and password=?";
		try(Connection conn = JdbcUtil.getInstance().getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);) {
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
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
	
	
	@Override
	public String repassword(String username, String newPassword) {
		// TODO Auto-generated method stub
		String sql1="select * from user where username='"+username+"'";
		String sql2="update user set password='"+newPassword+"'"+"where username='"+username+"'";
		try {
			ResultSet rs=templateQuery(sql1);
			if(rs.next()) {	
					templateUpdate(sql2);
					return "success:密码修改成功！！！";
			}
			else {
				return "error:用户名不存在！！！";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}

	@Override
	public String getValidationProblem(String username) {
		// TODO Auto-generated method stub
		String sql1="select * from user where username=?";
		try(Connection conn=JdbcUtil.getInstance().getConnection();
			PreparedStatement ps=conn.prepareStatement(sql1)) {
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				if(rs.getString(5)!=null) {
					return rs.getString(5).split(":")[0];
				}
				return "error:您没有设置验证问题";								
			}
			else {
				return "error:未登录！！！";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String validationProblemComfire(String username,String answer) {
		// TODO Auto-generated method stub
		String sql1="select * from user where username='"+username+"'";
		try {
			ResultSet rs=templateQuery(sql1);
			if(rs.next()) {
				if(rs.getString(5).split(":")[1].equals(answer)) {
					return "success:验证成功！！！";
				}
				else {
					return "error:验证失败！！！";
				}
			}
			else {
				return "error:用户未登录";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		
		
		
	}

	@Override
	public String regisiter(User user) {
		// TODO Auto-generated method stub
		String sql="insert into user values(null,?,?,?,?)";
		try (Connection conn=JdbcUtil.getInstance().getConnection();
			 PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserPassword());
			ps.setDouble(3, user.getAccount());
			ps.setString(4, user.getValidationProblem());
			ps.execute();
			return "success:注册成功！！！";		
		} catch (SQLException e) {
			// TODO Auto-generated catch block				
			e.printStackTrace();
			return "error:注册失败！！！";
		}
				
	}

	@Override
	public String getProfilePhoto(String username) {
		// TODO Auto-generated method stub
		String sql="select * from user where username='"+username+"'";
		try {
			ResultSet rs=templateQuery(sql);
			if(rs.next()) {
				if(rs.getString(6)!=null) {
					return rs.getString(6);	
				}
				else
					return "error:用户没有设置头像！！！";					
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return "error:用户不存在！！！";
	}
	@Override
	public Map<String, String> getUserInfo(String username) {
		String sql="select * from user where username='"+username+"'";
		try {
			ResultSet rs=templateQuery(sql);
			if(rs.next()) {				
			return ResultMapUtil.getResultMap(rs);					
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}
	
	


}
