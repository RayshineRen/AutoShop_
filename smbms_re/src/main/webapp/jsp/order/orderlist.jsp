<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>

<style>
	#sad th, #sad td, #sad tr {
		text-align: center !important;
		vertical-align: middle !important;
	}
</style>
<div class="right">
       <div class="location">
           <strong>你现在所在的位置是:</strong>
           <span>购物记录管理页面</span>
       </div>
       <div class="search">
       <form method="get" action="${pageContext.request.contextPath }/jsp/order/order.do">
			<input name="method" value="query" class="input-text" type="hidden">
			<span>商品名称：</span>
			<input name="queryProductName" type="text" value="${queryProductName }">
		    <input type="hidden" name="pageIndex" value="1"/>
		    <input value="查 询" type="submit" id="searchbutton">
		    <a href="${pageContext.request.contextPath}/outputCSV" >导出为CSV文件</a>
		    <a href="${pageContext.request.contextPath }/jsp/order/orderadd.jsp">添加订单</a>
	   </form>
       </div>
       <table class="providerTable" cellpadding="0" cellspacing="0" id="sad">
	       <tr class="firstTr">
              <th width="20%">购物记录编码</th>
              <th width="10%">商品名称</th>
			  <th width="10%">购买者</th>
              <th width="10%">购物金额</th>
              <th width="20%">创建时间</th>
              <th width="30%">操作</th>
          </tr>
           <c:forEach var="order" items="${orderList }" varStatus="status">
				<tr>
					<td>
						<span>${order.orderCode }</span>
					</td>
					<td>
						<span>${order.productName }</span>
					</td>
					<td>
						<span>${order.userCode }</span>
					</td>
					<td>
						<span>${order.totalPrice}</span>
					</td>
					<td>
						<span>
							<fmt:formatDate value="${order.creationDate}" pattern="yyyy-MM-dd"/>
						</span>
					</td>
					<td>
						<span><a class="viewOrder" href="javascript:;" orderid=${order.id } ordercc=${order.orderCode }><img src="${pageContext.request.contextPath }/images/read.png" alt="查看" title="查看"/></a></span>
					</td>
				</tr>
			</c:forEach>
       </table>
	   <input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
	   <c:import url="../rollpage.jsp">
		   <c:param name="totalCount" value="${totalCount}"/>
		   <c:param name="currentPageNo" value="${currentPageNo}"/>
		   <c:param name="totalPageCount" value="${totalPageCount}"/>
	   </c:import>
  </div>
</section>

<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/orderlist.js"></script>