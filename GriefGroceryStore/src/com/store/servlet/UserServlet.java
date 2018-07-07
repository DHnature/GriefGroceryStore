package com.store.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.SysexMessage;

import com.store.json.JsonContext;
import com.store.model.Product;
import com.store.model.User;
import com.store.service.UserService;
import com.store.util.DaoUtil;
import com.store.util.ReflectUtil;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class CustomerServlet
 */

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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
	
	//单独购买一件商品
	public void paySingle(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    String username=request.getParameter("username");
		String productStr=request.getParameter("product");
		//将json字符串转化为java对象		
		JSONObject jsonObject = JSONObject.fromObject(productStr);	
		Product  product= (Product)JSONObject.toBean(jsonObject,Product.class);			
		UserService serivce=new UserService();
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
		 UserService service=new UserService();
		 JSONObject json=service.payCart(username, productList);
		 response.getWriter().write(json.toString());
	}
	
	
	
	
	
	//添加商品到购物车
    public void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String username=request.getParameter("username");
		String productStr=request.getParameter("product");
		JSONObject jsonObject = JSONObject.fromObject(productStr);
		Product  product= (Product)JSONObject.toBean(jsonObject,Product.class);
		UserService serivce=new UserService();
		JSONObject json=serivce.addOrder(username, product);
		response.getWriter().write(json.toString());	
    	
    }
    
    
    //从购物车删除商品
    public void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String username=request.getParameter("username");
    	String productStr=request.getParameter("product");
		JSONObject jsonObject = JSONObject.fromObject(productStr);
		Product  product= (Product)JSONObject.toBean(jsonObject,Product.class);
		UserService serivce=new UserService();
		JSONObject json=serivce.deleteOrder(username, product);
		response.getWriter().write(json.toString());	
    	  		
    }
    
    //获取用户的验证问题，目前暂定一名用户只有一个验证问题
    
    public void getValidationProblem(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String username=request.getParameter("username");
		UserService serivce=new UserService();
		JSONObject json=serivce.getValidationProblem(username);
		response.getWriter().write(json.toString());
    	
    }

    //密码修改
    public void repassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String username=request.getParameter("username");
    	String newPassword=request.getParameter("newPassword");
		UserService serivce=new UserService();
		JSONObject json=serivce.repassword(username,newPassword);
		response.getWriter().write(json.toString());	    	  		
    }
    //根据用户名判断用户是否存在
    public void usernameComfire(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String username=request.getParameter("username");
        UserService service=new UserService();
    	JSONObject json=service.usernameComfire(username);     
    	response.getWriter().write(json.toString());	
    	
    	
    }
    //问题验证
    public void validationProblemComfire(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String username=request.getParameter("username");
    	String answer=request.getParameter("answer");
		UserService serivce=new UserService();
		JSONObject json=serivce.validationProblemComfire(username,answer);
		response.getWriter().write(json.toString());    	
    }
    
    //用户注册
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userInfo=request.getParameter("userInfo");
    	JSONObject jsonObject = JSONObject.fromObject(userInfo);
    	User user= (User)JSONObject.toBean(jsonObject,User.class);
    	user.setAccount(10000);
        UserService service=new UserService();
        JSONObject json=service.regisiter(user);
        response.getWriter().write(json.toString());    
    }
    
     
    

}
