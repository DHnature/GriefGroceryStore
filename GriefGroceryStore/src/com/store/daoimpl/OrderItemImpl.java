package com.store.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.store.dao.OrderDao;
import com.store.model.Product;
import com.store.model.User;
import com.store.util.JdbcUtil;

public class OrderItemImpl extends BaseDaoImpl implements OrderDao{

	public OrderItemImpl() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addOrderItem(String name,int uid,int pid,int number) {
		// TODO Auto-generated method stub
		String sql="insert into orderitem values(null,?,?,?,?)";
		String sql2="select * from orderitem where uid=? and pid=?";
		String sql3="update orderitem set number=? where uid=? and pid=?";
		try(Connection conn=JdbcUtil.getInstance().getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			PreparedStatement ps2=conn.prepareStatement(sql2);
		    PreparedStatement ps3=conn.prepareStatement(sql3)) {
			System.out.println(name);
			ps2.setInt(1, uid);
			ps2.setInt(2, pid);
			ResultSet rs=ps2.executeQuery();
			if(rs.next()==false) {
				ps.setString(1, name);
				ps.setInt(2, uid);
				ps.setInt(3, pid);
				ps.setInt(4, number);
	            ps.execute();
	            System.out.println("�����");
			}
			else {
				int updateNumber=rs.getInt(5)+1;
				System.out.println(rs.getInt(5));
				ps3.setInt(1, updateNumber);
				ps3.setInt(2, uid);
				ps3.setInt(3, pid);
				ps3.execute();
				System.out.println("�ظ����");
				
			} 
				

    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
