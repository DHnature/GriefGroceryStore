/**
 * 
 */
$(document).ready(function(){
	loadLoginBar();
});

function loadLoginBar(){
	var username=getCookie("user");
	if(username==null||typeof(username)=="undefined"||username==0){
		$("#loginBar").replaceWith(
				"<nav id='login'>" +
				"<a href='http://localhost:8080/GriefGroceryStore/login.html' style='text-decoration:none;'>登录</a>" +
			   "|<a href='http://localhost:8080/GriefGroceryStore/register.html' style='text-decoration:none;';>注册</a >" +
				"</nav>"); 
	}
	else{
		$("#loginBar").replaceWith("<nav id='login'><a href='http://localhost:8080/GriefGroceryStore/personalSpace.html' style='text-decoration:none;color:red;'>"+"用户名:    "+username+"</a>|" +
				                 "<a id='exit'>退出</a></nav>"); 
		$("#exit").click(function(){
			var x;
			var r=confirm("是否退出登录？");
			if (r==true){
				removeCookie("user");
				location.reload();
			}
			else{
			}
		
		});
		
		
	}
	
	
	
	
	
	
}
