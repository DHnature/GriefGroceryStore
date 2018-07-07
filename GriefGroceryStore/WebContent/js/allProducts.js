$(document).ready(function(){
	url="http://localhost:8080/GriefGroceryStore/Product?method=getAllProducts";
	$.ajax({
		   type:'get',
		   url:url,
		   dataType:'json',
		   success:function(json){
			  showProductInfo(json);
		   },
		   error:function(){
			   alert("error");
		   }
		});
	
	function showProductInfo(json){
		var productInfo = json.data;
		var allProducts = productInfo.allProducts;	
		for(var i=0;i<allProducts.length;i++){
			var element = "<div class='item'><img src='img/testImg2.jpg'/>" +
			        "<p>"+allProducts[i].productName+"</p>"+
					"<strong>¥"+allProducts[i].productPrice+"</strong><span>库存为"+allProducts[i].productInventory+"件</span>" +
					"<p>"+allProducts[i].productDes+"</p>" +
					"<adress>"+allProducts[i].productSeller+"</adress></div>";		
   			$(".waterfall").append(element);
		}
	}
});