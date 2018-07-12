package com.store.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.store.model.User;
import com.store.service.UserService;
import com.store.util.ReflectUtil;
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
		response.setHeader("Access-Control-Allow-Origin", "*");/* Access-Control-Allow-Origin允许跨域
		                                                                                                                所谓跨域就是，在a.com域下，访问b.com域下的资源；出于安全的考虑，
		                                                                                                                 浏览器允许跨域写，而不允许跨域读，*/		
		ReflectUtil.invokeMethod(this.getClass(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
    //获取用户的验证问题，目前暂定一名用户只有一个验证问题
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserService service=new UserService();
		System.out.println("test");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		JSONObject json=service.login(username, password);
		response.getWriter().write(json.toString());
    }
	
	
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
    //上传用户头像
    public void uploadProfilePhoto(HttpServletRequest request, HttpServletResponse response) {      	
    }
    
    //加载用户头像
    public void getProfilePhoto(HttpServletRequest request, HttpServletResponse response) throws IOException{    	
    	 String username=request.getParameter("username");
    	 UserService service=new UserService();
         JSONObject json=service.getProfilePhoto(username);
         response.getWriter().write(json.toString());      	 	
    }
    //获取用户所有信息
    public void getUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
   	    String username=request.getParameter("username");
   	    UserService service=new UserService();
        JSONObject json=service.getUserInfo(username);
        response.getWriter().write(json.toString());   
    }
    
     
    

}
