
//初始化界面,显示用户信息
$("#username").html(getCookie("user"));
$("#account").html(getCookie("account"));
//checkLogin();

//全局变量username
var username=getCookie("user");

//全局变量productArray,用来存放product
var productArray=[]

//用来删除数组中指定的对象
function removeObjInArr(_arr,_obj) {
    var length = _arr.length;
    for(var i = 0; i < length; i++)
    {
        if(_arr[i] == _obj)
        {
            if(i == 0)
            {           	
                _arr.shift(); //删除并返回数组的第一个元素
                return;
            }
            else if(i == length-1)
            {
                _arr.pop();  //删除并返回数组的最后一个元素
                return;
            }
            else
            {
                _arr.splice(i,1); //删除下标为i的元素
                return;
            }
        }
    }
};

//用来计算当前商品的总价
function totalPrice(){
	var price=0;
	for(var i=0;i<productArray.length;i++){
		price+=productArray[i].productPrice;
	}
	return price;	
}

//购物车点击支付
$("#pay").click(function(){	
	pay();
});	
//输入密码
function pay(){
	$('#compirePay').modal();
}
	





//加载购物车信息
url="http://localhost:8080/GriefGroceryStore/Order?method=getCartProductList&username="+username;
$.ajax({
	   type:'get',
	   url:url,
	   dataType:'json',
	   success:function(json){
		   load(json);
	   }	   
	});


//页面加载函数
function load(json){
var productList=json.data.productlist;
for(var i=0;i<productList.length;i++){
	   var product=JSON.stringify(productList[i])
       var tr=tb.insertRow(i+1);
       var id="tr"+i;
       tr.setAttribute("id",id);
	   var td1 = tr.insertCell(0);
	   td1.innerHTML=productList[i].productName; 
	   td1.setAttribute("style","width:25%;");  
	   var td2 = tr.insertCell(1);
	   td2.innerHTML=productList[i].productPrice;
	   td2.setAttribute("style","width:25%;");
	   td2.setAttribute("price",productList[i].productPrice);
       var td3 = tr.insertCell(2);
	   td3.innerHTML=productList[i].productInventory;  
	   td3.setAttribute("style","width:20%;");
	    /*每一行都在最前面插入一个选中复选框的单元格*/	  
 	   $("#"+id).prepend("<label class='btn btn-defalut'> <input type='checkbox' id='checkbox"+i+"'> </label>");          	   
 	   //动态为每个checkbox设置change事件
	   $("#checkbox"+i).change(addItem(productList[i],"checkbox"+i)); 			  			 	    
}
}

//订单修改
function addItem(product,checkboxId){
	return function(){	
	    var price=0;	    
		$("#productNum").html("商品数量:       "+(productArray.length));
		if($("#"+checkboxId).is(':checked')){ 
			 productArray.push(product);
			 price=totalPrice();	 
			 $("#productPrice").html("商品总价                          "+price);
			 $("#productNum").html("商品数量:       "+(productArray.length));
		}
		else {
			removeObjInArr(productArray,product);
			price=totalPrice();	 
			$("#productPrice").html("商品总价                          "+price);			
			$("#productNum").html("商品数量:       "+(productArray.length));
	
		}		
	}	
}

//确认支付按钮的点击事件
 $('#compirePayBtn').click(function(){	
	var url="http://localhost:8080/GriefGroceryStore/Order?method=payCart&username="+username;
	$.ajax({
		   type:'post',
		   url:url,
		   data:{"products":JSON.stringify(productArray)},
		   dataType:'json',
		   success:function(json){
			   alert(json.meta.message)
		   }	   
		});	
	
}); 

	
 

