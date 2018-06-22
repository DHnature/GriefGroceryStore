package com.store.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.store.dao.UserDao;
import com.store.model.Product;
import com.store.model.User;
import com.store.util.DaoUtil;
import com.store.util.JdbcUtil;



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
	
	//直接在商品页面支付
	@Override
	public String paySingle(String username, Product product) {
		// TODO Auto-generated method stub
		//分别搜索买家和卖家的信息，判断用户是否存在
		String sql1="select * from user where username=?";
		String sql2="select * from user where username=?";
		//更新商品信息，用户信息，卖家信息，
		String sql3="update user set account=? where username=?";
		String sql4="update user set account=? where username=?";
        String sql5="update product set inventory=? where id=?";						
		try(Connection coon=JdbcUtil.getInstance().getConnection();
			PreparedStatement ps1=coon.prepareStatement(sql1);
			PreparedStatement ps2=coon.prepareStatement(sql2);
			PreparedStatement ps3=coon.prepareStatement(sql3);
			PreparedStatement ps4=coon.prepareStatement(sql4);	
			PreparedStatement ps5=coon.prepareStatement(sql5);) {
			ps1.setString(1, username);
			ps2.setString(1, product.getProductSeller());
			ResultSet rs1=ps1.executeQuery();
			ResultSet rs2=ps2.executeQuery();
			if(!rs1.next()) {
				return "您的用户名不存在！！！请先登录";
			}
			if(!rs2.next()) {
				return "商品:  "+product.getProductName()+ "的卖家账号不存在！！！请联系管理员";
			}
			if(product.getProductInventory()<=0) {
				return "商品: "+product.getProductName()+"已售完！！！";
			}
			if(rs1.getDouble(4)<product.getProductPrice()) {
				return "您的余额不足！！！";
			}
			//加锁
			coon.setAutoCommit(false);
			//用户扣款
			ps3.setDouble(1, rs1.getDouble(4)-product.getProductPrice());
			ps3.setString(2, username);
			
			//卖家打款
			ps4.setDouble(1, rs2.getDouble(4)+product.getProductPrice());
			ps4.setString(2, product.getProductSeller());
			//更新商品信息	
			ps5.setInt(1, product.getProductInventory()-1);
			ps5.setInt(2, product.getProductId());
			//提交
			
			ps3.execute();
			ps4.execute();
			ps5.execute();
			coon.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return "支付成功！！！";		
	}
	
	
	//在购物车中支付
	@Override
	public String payCart(String username, List<Product> productList) {
		// TODO Auto-generated method stub
		ArrayList<String> payResult=new ArrayList<>();
		for(int i=0;i<productList.size();i++) {
		     String result=paySingle(username, productList.get(i));	
		   
		     payResult.add(i, result);
		}
		for(int j=0;j<payResult.size();j++) {
			if(payResult.get(j)!="支付成功！！！")
				return payResult.get(j);
		}		
		return "支付成功！！！";
	}
	
	
//添加单个商品
	
	@Override
	public String addOrder(String username, Product product) {
		// TODO Auto-generated method stub
		User user= DaoUtil.queryUserByUsername(username);
		String sql= "select * from orderItem where uid=? and pid=?";
		String sql2="update orderItem set number=? where uid=? and pid=?";
		String sql3="insert into orderItem values(null,?,?,?,?)";
		try(Connection coon=JdbcUtil.getInstance().getConnection();
			PreparedStatement ps1=coon.prepareStatement(sql);
		    PreparedStatement ps2=coon.prepareStatement(sql2);
		    PreparedStatement ps3=coon.prepareStatement(sql3);) {
			ps1.setInt(1, user.getUserId());
			ps1.setInt(2, product.getProductId());
			ResultSet rs=ps1.executeQuery();
			//判断购物车中是否有相同商品
			if(!rs.next()) {
				//若没有，直接添加商品
				ps3.setString(1, product.getProductName());
				ps3.setInt(2, user.getUserId());
				ps3.setInt(3, product.getProductId());
				ps3.setInt(4,1);
				ps3.execute();
			}
			else {
			//若存在，商品数量加1
		        int number=rs.getInt(4);
		        ps2.setInt(1, number+1);
		        ps2.setInt(2, user.getUserId());
		        ps2.setInt(3, product.getProductId());
		        ps2.execute();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return "添加成功!!!";
	}

	//删除单个订单
	public String deleteOrder(String username, Product product) {
		User user= DaoUtil.queryUserByUsername(username);
		String sql1="select * from user where id="+user.getUserId();
		String sql2="delete from orderItem where uid="+user.getUserId()+"and pid="+product.getProductId();
		
		try {
			ResultSet rs=templateQuery(sql1);
			
			if(!rs.next()) {
				return "用户名不存在！！！";
			}
			templateDelete(sql2);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return "删除成功！！！";
	}
	
	
	
	
	
	@Override
	public String repassword(String username,String newPassword) {
		// TODO Auto-generated method stub
		String sql1="select * from user where username='"+username+"'";
		String sql2="update user set password='"+newPassword+"'"+"where username='"+username+"'";
		try {
			ResultSet rs=templateQuery(sql1);
			if(rs.next()) {				
					templateUpdate(sql2);
					return "密码修改成功！！！";
			}
			else {
				return "用户名不存在！！！";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	//获取用户的验证问题
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
				return "您没有设置验证问题";
								
			}
			else {
				return "未登录！！！";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String validationProblemComfire(String username, String answer) {
		// TODO Auto-generated method stub		
		String sql1="select * from user where username='"+username+"'";
		try {
			ResultSet rs=templateQuery(sql1);
			if(rs.next()) {
				if(rs.getString(5).split(":")[1].equals(answer)) {
					return "验证成功！！！";
				}
				else {
					return "验证失败！！！";
				}
			}
			else {
				return "用户未登录";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	
	

}
