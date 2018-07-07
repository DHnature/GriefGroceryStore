/**
 * 
 */

$("#register").click(function(){	
	var userName=$("#username").val();
	var password=$("#password").val();
	var question=$("#question").val();
	var answer=$("#answer").val();	
	var validationProblem=question+":"+answer;
	if(username==""){
		alert("请输入用户名！");
		return ;
	}
	if(password==""){
		alert("请输入密码！");
		return ;
	}
	if(question==""){
		alert("请输入验证问题！");
		return ;
	}
	if(answer==""){
		alert("请输入验证答案！");
		return ;
	}		
	var user=new Object();	
	user.userName=userName;
	user.userPassword=password;
	user.validationProblem=validationProblem;
	var json={"userInfo":JSON.stringify(user)};
	console.log(json);
	var url="http://localhost:8080/GriefGroceryStore/User?method=register";
	 $.ajax({
		   type:'post',
		   url:url,
		   data:json,
           dataType:'json',
	       success:function(json){
	       	  if(json.meta.message=="1"){
	       		 alert("注册成功！！！");
	       	  }
	       	  else{
	       		 alert(json.meta.message);
	       	  }	   
	   },
	     error:function(){
	    	 alert("Error!!!");
	     }
		});	   
});
