<%--
  Created by IntelliJ IDEA.
  User: Ray
  Date: 2020/12/4
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%><%@include file="/jsp/FrontEnd/common/head.jsp"%>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是：</strong>
            <span>展示饮品货物</span>
        </div>

        <form method="get" action="${pageContext.request.contextPath }/jsp/FrontEnd/showGoods.do">
            <input name="method" value="query" class="input-text" type="hidden">
            <input name="queryclassCode" value="1" class="input-text" type="hidden">
            <input type="hidden" name="pageIndex" value="1"/>
        </form>
        <table class="providerTable" cellpadding="0" cellspacing="0">
            <tr class="firstTr">
                <th width="20%">货物名称</th>
                <th width="20%">货物种类</th>
                <th width="10%">货物价格</th>
                <th width="10%">货物余量</th>
                <th width="40%">操作</th>
            </tr>
            <c:forEach var="goods" items="${goodsList }" varStatus="status">
                <tr>
                    <td>
                        <span>${goods.name}</span>
                    </td>
                    <td>
                        <span>${goods.type}</span>
                    </td>
                    <td>
                        <span>${goods.cost}</span>
                    </td>
                    <td>
                        <span>${goods.number}</span>
                    </td>
                    <td>
                        <span><a class="viewGoods" href="javascript:;" goodsid=${goods.id } goodsname=${goods.name }><img src="${pageContext.request.contextPath }/images/read.png" alt="查看" title="查看"/></a></span>
                        <span><a class="buyGoods" href="javascript:;" goodsid=${goods.id } goodsname=${goods.name }><img src="${pageContext.request.contextPath }/images/xiugai.png" alt="购买" title="购买"/></a></span>
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
<%@include file="/jsp/FrontEnd/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/goodslist.js"></script>
