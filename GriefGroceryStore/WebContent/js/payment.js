/**
 * 
 */

//显示用户信息
$("#username").html(getCookie("user"));
$("#account").html(getCookie("account"));
//显示购买商品信息
var productName=getCookie("productName");
var productPrice=getCookie("productPrice");
var productInventory=getCookie("productInventory");
var productSeller=getCookie("productSeller");
var tr=tb.insertRow(1);
var td1 = tr.insertCell(0);
td1.innerHTML=productName; 
td1.setAttribute("style","width:15%;");
var td2 = tr.insertCell(1);
td2.innerHTML=productPrice;
td2.setAttribute("style","width:25%;");
var td3 = tr.insertCell(2);
td3.innerHTML=productInventory;  
td3.setAttribute("style","width:15%;");
var td4 = tr.insertCell(3);
td4.innerHTML=productSeller;  
td4.setAttribute("style","width:15%;");

//购买成功后的回调函数
function progress(json){
	alert(json.meta.message);
	location.reload();
}

//购买事件
function pay(){
	var username=getCookie("user");
	var url="http://localhost:8080/GriefGroceryStore/User?method=paySingle&username="+username;
	product=new Object();
	product.productId=getCookie("productId");
	product.productName=getCookie("productName");
	product.productPrice=getCookie("productPrice");
	product.productInventory=getCookie("productInventory");
	product.productSeller=getCookie("productSeller");
	var json={"product":JSON.stringify(product)};
     alert(json);	
	$.ajax({
		   type:'post',
		   url:url,
		   data:json,
           dataType:'json',          
 	       success:function(json){
 		   progress(json);
 	   },
 	       error:function(){
 	    	   alert("error!");
 	       }
		});	  
	
}

$("#pay").click(function(){	
	pay();
});