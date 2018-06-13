package com.store.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.daoimpl.ProductDaoImpl;
import com.store.model.Product;
import com.store.model.User;
import com.store.service.ProductService;
import com.store.util.ReflectUtil;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class ProductServlet
 */

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");//内容类型，解析为javascript代码或html代码
		response.setCharacterEncoding("utf-8");//内容编码，防止出现中文乱码
		response.setHeader("Access-Control-Allow-Origin", "*"); //Access-Control-Allow-Origin允许跨域
		                             //所谓跨域就是，在a.com域下，访问b.com域下的资源；出于安全的考虑，浏览器允许跨域写，而不允许跨域读，
		System.out.println(request.getParameter("method"));
		ReflectUtil.invokeMethod(this.getClass(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	//用于在主页中显示所有商品的信息
	
	public void getAllProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
	     
		ProductService service=new ProductService();
		JSONObject json=service.getAllProduct();
        response.getWriter().write(json.toString());		
	}
	
	public void getCartProductList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username=request.getParameter("username");
		ProductService service=new ProductService();
		JSONObject json=service.getCartProductList(username);		
        response.getWriter().write(json.toString());		
	}
	

	
	
	
	
	
	
	
	
	
	

}
