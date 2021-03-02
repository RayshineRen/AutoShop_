$(function(){
	$(".viewOrder").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href=path+"/jsp/order/order.do?method=view&orderid="+ obj.attr("orderid");
	});
});