<%--
  Created by IntelliJ IDEA.
  User: Ray
  Date: 2020/12/1
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/FrontEnd/common/head.jsp"%>
<div class="right">
    <div class="wFont" align="center">
        <p align="center" id="myP">欢迎来到自助售货管理系统!</p><br>
        <p align="center" id="myP" style="font-size:large">想买什么，搜索一下</p><br>
<%--        <p align="center" id="myP" style="font-size:large">在左侧导航栏处也可以选择自己需要的物品</p>--%>
        <div class="search">
            <form method="get" action="${pageContext.request.contextPath }/jsp/FrontEnd/search.do">
                <input name="method" value="search" class="input-text" type="hidden">
                <span style="font-size: 16px">商品名称：</span>
                <input name="word" type="text" value="" id="word">
                <input type="hidden" name="pageIndex" value="1"/>
                <input value="查 询" type="submit" id="searchbutton">
            </form>
            <form method="get" action="${pageContext.request.contextPath }/jsp/FrontEnd/search.do">
                <input name="method" value="guessLike" class="input-text" type="hidden">
                <input style="width: 14%" value="猜你喜欢" type="submit" id="searchbutton1">
            </form>
        </div>
    </div>
</div>
</section>
<%@include file="/jsp/FrontEnd/common/foot.jsp" %>
