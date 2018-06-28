/**
 * 
 */
function setCookie(name, value, iDay) 
{   
    var oDate=new Date();
    var ms=iDay*24*60*60*1000;
    oDate.setDate(oDate.getDate()+ms);
    document.cookie=name+'='+encodeURIComponent(value)+';expires='+oDate+";path=/";
    
}


 
function getCookie(name) 
{ 
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
 
    if(arr=document.cookie.match(reg))
 
        return decodeURIComponent(arr[2]); 
    else 
        return null; 
} 





function removeCookie(name)
{
    setCookie(name, '', -1);
}

function removeAllCookies(){
	var strCookie=document.cookie;
	  var arrCookie=strCookie.split("; "); // 将多cookie切割为多个名/值对
	  for(var i=0;i <arrCookie.length;i++)
	{ // 遍历cookie数组，处理每个cookie对
	    var arr=arrCookie[i].split("=");
	    
	    if(arr.length>0)
	     removeCookie(arr[0]);
	}
      alert("remove");
}

////验证是否登录
function checkLogin(){
	var username=getCookie("user");
	if(username==null||typeof(username)=="undefined"||username==0){
		alert("请先登录！！！");
		window.location.href="http://localhost:8080/GriefGroceryStore/login.html";  
	}
	else{
		alert("已登录!!!用户名为:"+username);
		
	}
}

function loadloginBar(){
	var username=getCookie("user");
	if(username==null||typeof(username)=="undefined"||username==0){
		$("#login").replaceWith(
				"<nav id='login'>" +
				"<a href='http://localhost:8080/GriefGroceryStore/login.html' style='text-decoration:none;'>登录</a>" +
				"|<a>注册</a href='http://localhost:8080/GriefGroceryStore/register.html' style='text-decoration:none;color:inherit;>" +
				"</nav>"); 
	}
	else{
		$("#login").replaceWith("<nav id='login'><a href='http://localhost:8080/GriefGroceryStore/personalSpace.html' style='text-decoration:none;color:red;'>"+"用户名:    "+username+"</a>|" +
				                 "<a id='exit'>退出</a></nav>"); 
		$("#exit").click(function(){
			alert("test");
			removeCookie("user");
			location.reload();
		});
		
		
	}
	
	
	
	
	
	
}














