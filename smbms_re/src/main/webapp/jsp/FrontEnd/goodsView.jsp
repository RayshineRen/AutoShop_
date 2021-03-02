<%--
  Created by IntelliJ IDEA.
  User: Ray
  Date: 2021/1/18
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@include file="/jsp/FrontEnd/common/head.jsp"%>
<style>
    .providerView {
        width: 42% !important;
        float: left;
        margin-bottom: 20px;
    }
</style>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>展示货品 >> 货品信息查看页面</span>
    </div>
    <div style="overflow:hidden;">
        <div class="providerView">
            <p><strong>货品编号：</strong><span>${goods.id }</span></p>
            <p><strong>货品名称：</strong><span>${goods.name }</span></p>
            <p><strong>货品类别：</strong>
                <span>
                <c:if test="${goods.classCode == 1 }">饮品</c:if>
                <c:if test="${goods.classCode == 2 }">日用品</c:if>
                <c:if test="${goods.classCode == 3 }">食品</c:if>
            </span>
            </p>
            <p><strong>货品单价：</strong><span>${goods.cost }</span></p>
            <p><strong>货品余量：</strong><span>${goods.number }</span></p>
            <p><strong>货品简介：</strong><span>${goods.description }</span></p>
            <p style="margin-bottom: 20px"><strong>货品来源：</strong><span>${goods.providerCode}</span></p>
        </div>
        <div class="providerView" style="height: 227px">
            <form action="${pageContext.request.contextPath }/jsp/FrontEnd/showGoods.do">
                <input name="method" value="like" class="input-text" type="hidden">
                <input name="goodsid" value="${goods.id}" class="input-text" type="hidden">
                <p style="margin: 25px auto;float:left;"><img src="${goods.img}" alt="${goods.name}"></p>
                <p style="float:left;margin: 50px auto;">
                    目前已经有${goods.likeit}人对这件货品表示了喜欢
                </p>
                <input type="submit" value="Like" style="width: 16%;margin-left: 60px;margin-bottom: 20px">
            </form>
        </div>
    </div>

    <div class="providerAddBtn" style="margin-left: 500px">
        <input type="button" id="back" name="back" value="返回" >
    </div>
</div>
</section>
<%@include file="/jsp/FrontEnd/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/userview.js"></script>
