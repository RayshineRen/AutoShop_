<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>

<div class="right">
     <div class="location">
         <strong>你现在所在的位置是:</strong>
         <span>购物记录管理页面 >> 记录添加页面</span>
     </div>
     <div class="providerAdd">
         <form id="orderForm" name="orderForm" method="post" action="${pageContext.request.contextPath }/jsp/order/order.do">
             <input type="hidden" name="method" value="add">
             <div class="">
                 <label for="orderCode">订单编码：</label>
                 <input type="text" name="orderCode" class="text" id="orderCode" value=""> 
				 <!-- 放置提示信息 -->
				 <font color="red"></font>
             </div>
             <div>
                 <label for="productName">商品名称：</label>
                 <input type="text" name="productName" id="productName" value=""> 
				 <font color="red"></font>
             </div>
             <div>
                 <label for="userCode">购买者：</label>
                 <input type="text" name="userCode" id="userCode" value="">
                 <font color="red"></font>
             </div>
             <div>
                 <label for="productDesc">商品描述：</label>
                 <input type="text" name="productDesc" id="productDesc" value="">
                 <font color="red"></font>
             </div>
             <div>
                 <label for="totalPrice">总金额：</label>
                 <input type="text" name="totalPrice" id="totalPrice" value=""> 
				 <font color="red"></font>
             </div>
             <div class="providerAddBtn">
                  <input type="button" name="add" id="add" value="保存">
				  <input type="button" id="back" name="back" value="返回" >
             </div>
         </form>
     </div>
 </div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/orderadd.js"></script>