<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/FrontEnd/common/head.jsp"%>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>展示所有货物 >> 订单生成页面</span>
        </div>
        <div class="providerAdd">
        <form id="goodsForm" name="goodsForm" method="post" action="${pageContext.request.contextPath }/jsp/FrontEnd/showGoods.do">
			<input type="hidden" name="method" value="commit">
			<input type="hidden" name="goodsid" value="${goods.id }"/>
			<input type="hidden" name="userCode" value="${user.userCode }"/>
			 <div>
                    <label for="productName">货物名称：</label>
                    <input type="text" name="productName" id="productName" value="${goods.name }" disabled>
					<font color="red"></font>
             </div>
			 <div>
                    <label for="date">购物日期：</label>
                    <input type="text" Class="Wdate" id="date" name="date" value="${date }"
					readonly="readonly" disabled>
                    <font color="red"></font>
              </div>
                <div>
                    <label for="productDesc">货品描述：</label>
                    <input type="text" name="productDesc" id="productDesc" value="${goods.description }" disabled>
                    <font color="red"></font>
                </div>
                <div>
                    <label for="totalPrice">总金额：</label>
                    <input type="text" name="totalPrice" id="totalPrice" value="${goods.cost }" disabled>
                </div>
                <div>
                    <label for="userCode">userCode：</label>
                    <input type="text" name="userCode" id="userCode" value="${user.userCode }" disabled>
                </div>
			 <div class="providerAddBtn">
                    <input type="submit" name="save" id="save" value="确认" />
                    <input type="button" id="back" name="back" value="取消"/>
                </div>
            </form>
        </div>
    </div>
</section>
<%@include file="/jsp/FrontEnd/common/foot.jsp" %>
<%--<script type="text/javascript" src="${pageContext.request.contextPath }/js/usermodify.js" charset="UTF-8"></script>--%>
