$(function(){
	//通过jquery的class选择器（数组）
	//对每个class为viewUser的元素进行动作绑定（click）
	$(".viewGoods").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href=path+"/jsp/FrontEnd/showGoods.do?method=view&goodsid="+ obj.attr("goodsid");
	});
	
	$(".buyGoods").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/jsp/FrontEnd/showGoods.do?method=buy&goodsid="+ obj.attr("goodsid");
	});

});