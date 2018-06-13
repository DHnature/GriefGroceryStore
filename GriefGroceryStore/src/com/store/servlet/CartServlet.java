package com.store.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.dao.DaoFactory;
import com.store.dao.ProductDao;
import com.store.daoimpl.OrderItemImpl;
import com.store.daoimpl.ProductDaoImpl;
import com.store.model.Product;
import com.store.model.User;
import com.store.util.ReflectUtil;

/**
 * Servlet implementation class CartListServlet
 */

public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        ReflectUtil.invokeMethod(this.getClass(), request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	public void getCartList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*		User user=(User) request.getSession().getAttribute("user");
		ProductDao productDao=DaoFactory.creatProductDao();
		List<Product> productList=productDao.getAllProductList();
		request.setAttribute("productList", productList);
		request.getRequestDispatcher("cartProductList.jsp").forward(request, response);*/
	}
	
	public void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     int uid=Integer.parseInt(request.getParameter("uid"));
	     int pid=Integer.parseInt(request.getParameter("pid"));
	     int number=Integer.parseInt(request.getParameter("number"));
	     String pname=request.getParameter("pname");
	    // new OrderItemImpl().addOrderItem(pname,uid, pid,number);
	     System.out.println("添加成功");		
		}

}
