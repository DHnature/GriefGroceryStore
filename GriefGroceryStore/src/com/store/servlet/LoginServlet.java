package com.store.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.constants.SystemConstants;
import com.store.daoimpl.UserDaoImpl;
import com.store.model.User;
import com.store.service.LoginService;
import com.store.util.ReflectUtil;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class LoginServelt
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		LoginService service=new LoginService();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		JSONObject json=service.login(username, password);
		
		System.out.println("json是         "+json);
		String state=(String) ((JSONObject) json.get("meta")).get("state");
		System.out.println(state);
		if(state=="success") {
			System.out.println("登录成功！！！");			
			request.getSession().setAttribute("user", ((JSONObject) json.get("data")).get("user"));
			
			System.out.println(request.getSession().getAttribute("user"));
		}
		else {
			System.out.println("登录失败！！！");
		}
		response.getWriter().write(json.toString());			
	}
	

}
