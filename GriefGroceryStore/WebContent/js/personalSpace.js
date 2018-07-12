$(document).ready(function(){
	var username=getCookie("user");	
	$("#userInfo").mouseenter(function(){clickEvent("userInfo")});
	$("#orderItem").mouseenter(function(){clickEvent("orderItem")});
	$("#cart").mouseenter(function(){clickEvent("cart")});
	$("#accountInfo").mouseenter(function(){clickEvent("userInfo")});
	
	function clickEvent(module){
		var url;
		if(module=="userInfo"){
	      url="http://localhost:8080/GriefGroceryStore/User?method=getUserInfo&username="+username;
		}
		if(module=="cart"){
		  url="http://localhost:8080/GriefGroceryStore/Order?method=getCartProductList&username="+username;
		}
		if(module=="orderItem"){
			url="http://localhost:8080/GriefGroceryStore/Order?method=getOrder&username="+username;
		}
			$.ajax({
			method:"post",
			url:url,
			success:function(json){
				
				$("#content").html(json);
				var obj = JSON.parse(json);
				progress(obj,url);
			},
			error:function(){
				console.log("失败");
			}
		});
	}	
	
	function prograss(obj,module){
		if(module == "baseInfo"){
			prograssBaseInfo(obj);
		}
	}
	
	function prograssBaseInfo(obj){
		$("#content").append("<form id='formBox'></form>");
		$("#formBox").append("<span>昵称：</span><input name='userName' readOnly = 'readOnly' value="+obj.userName+"><br><br>");
		$("#formBox").append("<span>密码：</span><input name='password' readOnly = 'readOnly' value="+obj.password+"><br><br>");
		$("#formBox").append("<span>验证问题：</span><input name='question' readOnly = 'readOnly' value="+obj.question+"><br><br>");
		$("#formBox").append("<span>验证回答：</span><input name='answer' readOnly = 'readOnly' value="+obj.answer+"><br><br>");
		$("#formBox").append("<button type='button' id='update'>修改</button>");
		$("#formBox").append("<button type='button' id='submit'>确定</button>");
		
		$("#update").click(function(){
			$("#formBox input").removeAttr("readOnly");
		});
		
		$("#submit").click(function(){
			$.ajax({
				method:"post",
				url:"updateInfo",
				data:$("#formBox").serialize(),
				success:function(){
					console.log("修改成功");
				},
				error:function(){
					console.log("修改失败");
				}
			});
		});
	}
});
