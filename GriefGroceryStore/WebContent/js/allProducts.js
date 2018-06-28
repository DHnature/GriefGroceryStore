/**
 * 
 */

function addOrder(product){
	json={"product":JSON.stringify(product)};
	url="http://localhost:8080/GriefGroceryStore/User?method=addOrder&username="+username;
	$.ajax({
		   type:'post',
		   url:url,
		   data:json,
		   dataType:'json',
		   success:function(json){
			  alert(json);
		   }	   
		});
}



function load(json){
    var productList=json.data.allProduct;
	var tb=document.getElementById("tb");
	for(var i=0;i<productList.length;i++){
       var tr=tb.insertRow(i+1);
	   var td1 = tr.insertCell(0);
	   td1.innerHTML=productList[i].productId; 
	   td1.setAttribute("style","width:15%;");
	   var td2 = tr.insertCell(1);
	   td2.innerHTML=productList[i].productName;
	   td2.setAttribute("style","width:25%;");
       var td3 = tr.insertCell(2);
	   td3.innerHTML=productList[i].productPrice;  
	   td3.setAttribute("style","width:15%;");
       var td4 = tr.insertCell(3);
	   td4.innerHTML=productList[i].productInventory;  
	   td4.setAttribute("style","width:15%;");
       var td5 = tr.insertCell(4);
	   td5.innerHTML=productList[i].productSeller;  
	   td5.setAttribute("style","width:25%;");
	   var td6 = tr.insertCell(5);
	   td6.innerHTML="<button type='button' class='btn btn-primary'> 加入购物车</button>";
	   td6.setAttribute("productId",productList[i].productId);
	   td6.setAttribute("productName",productList[i].productName);
	   td6.setAttribute("productPrice",productList[i].productPrice);
	   td6.setAttribute("productInventory",productList[i].productInventory);
	   td6.setAttribute("productSeller",productList[i].productSeller);
	   td6.onclick=function(){
		   var product=new Object();
		   product.productId=this.getAttribute("productId");
		   product.productName=this.getAttribute("productName");
		   product.productPrice=this.getAttribute("productPrice");
		   product.productInventory=this.getAttribute("productInventory");
		   product.productSeller=this.getAttribute("productSeller");
		   addOrder(product);           
	   };	   
	   var td7 = tr.insertCell(6);
	   td7.innerHTML="<button type='button' class='btn btn-primary'> 购买</button>";
	   td7.setAttribute("productId",productList[i].productId);
	   td7.setAttribute("productName",productList[i].productName);
	   td7.setAttribute("productPrice",productList[i].productPrice);
	   td7.setAttribute("productInventory",productList[i].productInventory);
	   td7.setAttribute("productSeller",productList[i].productSeller);
	   //function传参失败，自己也没找到好的解决办法，只有用setAttribute方法来解决
	   td7.onclick=function(){
		   setCookie("productId",this.getAttribute("productId"),0);
		   setCookie("productName",this.getAttribute("productName"),0);
		   setCookie("productPrice",this.getAttribute("productPrice"),0);
		   setCookie("productInventory",this.getAttribute("productInventory"),0);
		   setCookie("productSeller",this.getAttribute("productSeller"),0);
		   window.location.href="http://localhost:8080/GriefGroceryStore/payment.html"; 		   
	   }; 
	}

}

url="http://localhost:8080/GriefGroceryStore/Product?method=getAllProduct";
$.ajax({
	   type:'get',
	   url:url,
	   dataType:'json',
	   success:function(json){
		   load(json);
	   }	   
	});
