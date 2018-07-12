package com.store.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.dao.DaoFactory;
import com.store.dao.ProductDao;
import com.store.daoimpl.OrderDaoImpl;
import com.store.daoimpl.ProductDaoImpl;
import com.store.json.JsonContext;
import com.store.model.Product;
import com.store.model.User;
import com.store.service.OrderService;
import com.store.service.ProductService;
import com.store.service.UserService;
import com.store.util.ReflectUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class CartListServlet
 */

public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");//内容类型，解析为javascript代码或html代码
		response.setCharacterEncoding("utf-8");//内容编码，防止出现中文乱码
		response.setHeader("Access-Control-Allow-Origin", "*"); //Access-Control-Allow-Origin允许跨域
		                             //所谓跨域就是，在a.com域下，访问b.com域下的资源；出于安全的考虑，浏览器允许跨域写，而不允许跨域读，   
		ReflectUtil.invokeMethod(this.getClass(), request,response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	//获取用户购物车信息	
	public void getCartProductList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username=request.getParameter("username");
		OrderService service=new OrderService();
		JSONObject json=service.getCartProductList(username);	
		System.out.println(json);
        response.getWriter().write(json.toString());		
	}
	
	//单独购买一件商品
		public void paySingle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		    String username=request.getParameter("username");
			String productStr=request.getParameter("product");
			//将json字符串转化为java对象		
			JSONObject jsonObject = JSONObject.fromObject(productStr);	
			Product  product= (Product)JSONObject.toBean(jsonObject,Product.class);			
			OrderService serivce=new OrderService();
			JSONObject json=serivce.paySingle(username, product);
			response.getWriter().write(json.toString());	
		}  
		
	//在购物车中购买商品
		public void payCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
			 String products=request.getParameter("products");
			 String username=request.getParameter("username");
			 JSONArray jsonArr=JSONArray.fromObject(products);  
			 ArrayList<Product> productList=new ArrayList<>();
			 for(int i=0;i<jsonArr.size();i++) {
				 Product  product= (Product) JSONObject.toBean(jsonArr.getJSONObject(i),Product.class);	
				 productList.add(product);
				 System.out.println(product.getProductName());
			 }
			 OrderService service=new OrderService();
			 JSONObject json=service.payCart(username, productList);
			 response.getWriter().write(json.toString());
		}
		
		
		
		
		
		//添加商品到购物车
	    public void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	String username=request.getParameter("username");
			String productStr=request.getParameter("product");
			JSONObject jsonObject = JSONObject.fromObject(productStr);
			Product  product= (Product)JSONObject.toBean(jsonObject,Product.class);
			OrderService service=new OrderService();
			JSONObject json=service.addOrder(username, product);
			response.getWriter().write(json.toString());	
	    	
	    }
	    
	    
	    //从购物车删除商品
	    public void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	String username=request.getParameter("username");
	    	String productStr=request.getParameter("product");
			JSONObject jsonObject = JSONObject.fromObject(productStr);
			Product  product= (Product)JSONObject.toBean(jsonObject,Product.class);
			OrderService service=new OrderService();
			JSONObject json=service.deleteOrder(username, product);
			response.getWriter().write(json.toString());	
	    	  		
	    }
	    
		public void getOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
			String username=request.getParameter("username");
			OrderService service=new OrderService();
			JSONObject json=service.getOrder(username);	
			System.out.println(json);
	        response.getWriter().write(json.toString());		
		}
	    


}
