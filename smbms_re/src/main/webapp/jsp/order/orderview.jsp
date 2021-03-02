<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
<div class="right">
     <div class="location">
         <strong>你现在所在的位置是:</strong>
         <span>购物记录管理页面 >> 信息查看</span>
     </div>
     <div class="providerView">
         <p><strong>订单编号：</strong><span>${order.orderCode }</span></p>
         <p><strong>商品名称：</strong><span>${order.productName }</span></p>
         <p><strong>商品描述：</strong><span>${order.productDesc }</span></p>
         <p><strong>总金额：</strong><span>${order.totalPrice }</span></p>
         <p><strong>购买者：</strong><span>${order.userCode }</span></p>
		<div class="providerAddBtn">
         	<input type="button" id="back" name="back" value="返回" >
        </div>
     </div>
 </div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/userview.js"></script>