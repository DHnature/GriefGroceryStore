package com.store.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReflectUtil {
	
	public static void invokeMethod(Class clazz,HttpServletRequest request,HttpServletResponse response) {		    
		String methodName=request.getParameter("method");
		System.out.println("当前方法为       "+methodName);
		
		if(methodName==null||methodName.isEmpty()) {
			throw new RuntimeException("输入的方法参数为空!!!");
			
		}
		
		Class c=clazz;
		Method method=null;
		try {
			
			method=c.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			
		} catch (Exception e) {
			System.out.println("你调用的方法    "+methodName+"   不存在!!!");
			e.printStackTrace();	
	}
		try {
			method.invoke(clazz.newInstance(), request,response);
			
		} catch (Exception e) {
			System.out.println("你调用的方法"+methodName+"内部发生了异常!!!");
			throw new RuntimeException(e);
		}
	}
	
}
