$(document).ready(function(){
	var name = window.sessionStorage.getItem("userName");
	var data = "userName="+name;
	var accountInfo = "accountInfo";
	$("#baseInfo").click(function(){clickEvent("baseInfo")});
	$("#orderItem").click(function(){clickEvent("orderItem")});
	$("#itemCar").click(function(){clickEvent("itemCar")});
	$("#accountInfo").click(function(){clickEvent("accountInfo")});
	
	function clickEvent(url){
			$.ajax({
			method:"post",
			url:url,
			data:data,
			success:function(json){
				var obj = JSON.parse(json);
				prograss(obj,url);
			},
			error:function(){
				console.log("失败");
			}
		});
	}	
	
	function prograss(obj,url){
		if(url == "baseInfo"){
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
