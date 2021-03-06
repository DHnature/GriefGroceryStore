package com.store.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.store.dao.ProductDao;
import com.store.model.OrderItem;
import com.store.model.Product;
import com.store.model.User;
import com.store.util.DaoUtil;
import com.store.util.JdbcUtil;

public class ProductDaoImpl extends BaseDaoImpl implements ProductDao{

	public ProductDaoImpl() throws Exception{
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public ArrayList<Product> getAllProductList() {
		// TODO Auto-generated method stub
		ArrayList<Product> productList=new ArrayList<>();
		try(Connection conn=JdbcUtil.getInstance().getConnection();) {
			String sql="select * from product order by id desc";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Product product=new Product();
				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setProductPrice(rs.getDouble(3));
				product.setProductInventory(rs.getInt(4));
				product.setProductSeller(rs.getString(5));
				product.setProductDes(rs.getString(6));
				productList.add(product);	
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}		
		return productList;
	}
	
	
}
