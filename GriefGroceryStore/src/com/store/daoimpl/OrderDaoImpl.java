package com.store.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.store.dao.OrderDao;
import com.store.model.Product;
import com.store.model.User;
import com.store.util.DaoUtil;
import com.store.util.JdbcUtil;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao{

	public OrderDaoImpl() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

//直接支付	
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
						return "error:您的用户名不存在！！！请先登录";
					}
					if(!rs2.next()) {
						return "error:商品:  "+product.getProductName()+ "的卖家账号不存在！！！请联系管理员";
					}
					if(product.getProductInventory()<=0) {
						return "error:商品: "+product.getProductName()+"已售完！！！";
					}
					if(rs1.getDouble(4)<product.getProductPrice()) {
						return "error:您的余额不足！！！";
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
				return "success:支付成功！！！";	
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
				return "success:支付成功！！！";
	}

//向购物车中添加商品	
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
				return "success:添加成功!!!";
	}
//从购物车中删除商品
	@Override
	public String deleteOrder(String username, Product product) {
		User user= DaoUtil.queryUserByUsername(username);
		String sql1="select * from user where id="+user.getUserId();
		String sql2="delete from orderItem where uid="+user.getUserId()+"and pid="+product.getProductId();
		
		try {
			ResultSet rs=templateQuery(sql1);
			
			if(!rs.next()) {
				return "error:用户名不存在！！！";
			}
			templateDelete(sql2);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return "error:删除成功！！！";
	}
	
	@Override
	public List<Product> getCartProductList(String username){
		ArrayList<Product> productList=new ArrayList<>();
		User user=DaoUtil.queryUserByUsername(username);
		String sql="select * from orderItem where uid="+user.getUserId();
	    try {
			ResultSet rs=templateQuery(sql);			
			while(rs.next()) {
				//根据pid返回对应的product对象
				if(rs.getInt(6)==0) {
					Product product=DaoUtil.queryProductByPid(rs.getInt(4));
					productList.add(product);	
				}

			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productList;	
		
	}

	@Override
	public List<Product> getOrderList(String username) {
		ArrayList<Product> productList=new ArrayList<>();
		User user=DaoUtil.queryUserByUsername(username);
		String sql="select * from orderItem where uid="+user.getUserId();
	    try {
			ResultSet rs=templateQuery(sql);
			while(rs.next()&&rs.getInt(6)==1) {
				//根据pid返回对应的product对象
				Product product=DaoUtil.queryProductByPid(rs.getInt(4));
				productList.add(product);	
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productList;	
	}

	
}
