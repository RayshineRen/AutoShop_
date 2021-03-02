var orderCode = null;
var productName = null;
var userCode = null;
var productDesc = null;
var totalPrice = null;
var addBtn = null;
var backBtn = null;

function priceReg (value){
	value = value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
		value = value.replace(/^\./g,"");  //验证第一个字符是数字而不是.
    value = value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的.
    value = value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");//去掉特殊符号￥
	if(value.indexOf(".")>0){
		value = value.substring(0,value.indexOf(".")+3);
	}
	return value;
}

$(function(){
	orderCode = $("#orderCode");
	productName = $("#productName");
	productDesc = $("#productDesc");
	userCode = $("#userCode");
	totalPrice = $("#totalPrice");

	addBtn = $("#add");
	backBtn = $("#back");
	//初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
	orderCode.next().html("*");
	productName.next().html("*");
	userCode.next().html("*")
	productDesc.next().html("*");
	totalPrice.next().html("*");

	orderCode.on("blur",function(){
		if(orderCode.val() != null && orderCode.val() != ""){
			validateTip(orderCode.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(orderCode.next(),{"color":"red"},imgNo+" orderCode can't be empty",false);
		}
	}).on("focus",function(){
		validateTip(orderCode.next(),{"color":"#666666"},"* input orderCode",false);
	}).focus();
	
	productName.on("focus",function(){
		validateTip(productName.next(),{"color":"#666666"},"* input productName",false);
	}).on("blur",function(){
		if(productName.val() != null && productName.val() != ""){
			validateTip(productName.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(productName.next(),{"color":"red"},imgNo+" productName can't be empty",false);
		}
	});

	userCode.on("focus",function(){
		validateTip(userCode.next(),{"color":"#666666"},"* input userCode",false);
	}).on("blur",function(){
		if(userCode.val() != null && userCode.val() != ""){
			validateTip(userCode.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(userCode.next(),{"color":"red"},imgNo+" userCode can't be empty",false);
		}
	});

	productDesc.on("focus",function(){
		validateTip(productDesc.next(),{"color":"#666666"},"* input productDesc",false);
	}).on("blur",function(){
		if(productDesc.val() != null && productDesc.val() != ""){
			validateTip(productDesc.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(productDesc.next(),{"color":"red"},imgNo+" productDesc can't be empty",false);
		}
	});

	totalPrice.on("focus",function(){
		validateTip(totalPrice.next(),{"color":"#666666"},"* input >0 number with two decimal",false);
	}).on("keyup",function(){
		this.value = priceReg(this.value);
	}).on("blur",function(){
		this.value = priceReg(this.value);
	});
	
	addBtn.on("click",function(){
		if(orderCode.attr("validateStatus") != "true"){
			orderCode.blur();
		}else if(productName.attr("validateStatus") != "true"){
			productName.blur();
		}else if(userCode.attr("validateStatus") != "true"){
			userCode.blur();
		}else{
			if(confirm("Are you sure to commit?")){
				$("#orderForm").submit();
			}
		}
	});
	
	backBtn.on("click",function(){
		if(referer != undefined 
			&& null != referer 
			&& "" != referer
			&& "null" != referer
			&& referer.length > 4){
		 window.location.href = referer;
		}else{
			history.back(-1);
		}
	});
});