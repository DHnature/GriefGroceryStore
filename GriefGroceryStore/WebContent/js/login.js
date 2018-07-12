/**
 * 
 */


function displayProp(obj){      
    var names="";         
    for(var name in obj){         
       names+=name+": "+obj[name]+", ";    
    }    
    alert(names);    
}  


 	
 function progress(json){
	 if(json.meta.state=="success"){
		 alert("success");
		 setCookie("user",json.data.user.userName,0);
		 setCookie("account",json.data.user.account,0);
		 alert(document.cookie);
		 window.location.href="http://localhost:8080/GriefGroceryStore/index.html"; 
	 }
	 else{
		 alert("failed");
	 }
	 
 } 	
   function login(){
	   username=$("#username").val();
	   password=$("#password").val(); 
       url="http://localhost:8080/GriefGroceryStore/User?method=login&username="+username+"&password="+password;
       $.ajax({
		   type:'get',
		   url:url,
           dataType:'json',
    	   success:function(json){
    		   progress(json);
    	   }   
		});	  
 
   }	

    $("#login").click(function(){
		login();		
	}); 
    
 	$("#register").click(function(){
 		 window.location.href="http://localhost:8080/GriefGroceryStore/register.html";		
	}); 
   
   
  	
   