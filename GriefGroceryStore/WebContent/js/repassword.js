/**
 * 
 */
$("#sumbit").click(function(){
	var username=$("input").val();
	var url="http://localhost:8080/GriefGroceryStore/User?method=usernameComfire&username="+username;
	$.ajax({
		   type:'get',
		   url:url,
           dataType:'json',
 	       success:function(json){
 	       	  if(json.meta.message=="1"){
 	       		
 	       		 progress(json);
 	       		  
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

function progress(json){
   //用户名验证成功后使用的函数，即问题验证界面
   //需要使用focus获取焦点
   $("input").val("").focus();  
   $("p").html("验证问题:     "+json.data.question+"?");
   $("#sumbit").unbind();
   var username=json.data.username;
   var url="http://localhost:8080/GriefGroceryStore/User?method=validationProblemComfire&username="+username;
   $("#sumbit").click(function(){	   	   	   
	   json={"answer":$("input").val()};	   
	   $.ajax({
		   type:'get',
		   url:url,
		   data:json,
           dataType:'json',
 	       success:function(json){
 	       	  if(json.meta.message=="1"){
 	       		
 	       		 repassword(json);
 	       	  }
 	       	  else{
 	       		 alert(json.meta.message);
 	       	  }	   
 	   },
 	     error:function(){
 	    	 alert("Error!!!");
 	     }
		});	   
   })

}

function repassword(json){
//修改密码界面		
	   $("input").val("").focus();  
	   $("p").html("请输入新密码");	   
	   $("#input").attr("type","password");
	   $("#input").attr("id","password");	   
	   $("#password").after("<input  id='comfirePassword' type='password' required='required'  />");
	   $("<div style='margin-top:30px;margin-left:140px;'><p id='message' style='color:red' > </p></div>").insertAfter("#comfirePassword");	   
	   $("#sumbit").unbind();
	   var username=json.data.username;
	   	   
	   $('#password').blur(function () {
		   if($("#password").val()==""){
			   $("#message").css("color","red");
			   $("#message").html("请输入新密码！！！");	
		   }
	   });
	   	   
	   $('#comfirePassword').blur(function () {		   
		   if( ($("#password").val()!= $("#comfirePassword").val())&&($("#comfirePassword").val()!="")
				   &&($("#password").val()!="")){
			    $("#message").css("color","red");
		    	$("#message").html("密码不一致！！！");	    	
		    } 
		   else if($("#password").val()!=""&& $("#comfirePassword").val()!=""){
			   $("#message").css("color","green");
			   $("#message").html("密码合法！！！");	  
		   }
		});  
	   
	   $("#sumbit").unbind();
	   
	   $("#sumbit").click(function(){
		   var username=json.data.username;
		   var url="http://localhost:8080/GriefGroceryStore/User?method=repassword&username="+username;
		   json={"newPassword":$("#password").val()};  
		   $.ajax({
			   type:'get',
			   url:url,
			   data:json,
	           dataType:'json',
	 	       success:function(json){
	 	       	  if(json.meta.message=="1"){
	 	       		 alert("修改成功！！！");
	 	       		 
	 	       		 window.location.href="http://localhost:8080/GriefGroceryStore/login.html"; 
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
}


