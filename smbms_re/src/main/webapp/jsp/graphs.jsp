<%--
  Created by IntelliJ IDEA.
  User: Ray
  Date: 2020/11/2
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
<div class="right">
    <div class="wFont" style="text-align: center;margin-left: 500px">
        <h2>${userSession.userName }</h2>
        <p><span style="font-size: large;font-family: 华文楷体">欢迎使用报表分析功能!</span></p>
        <br>
        <p><span style="font-size: large;font-family: 华文楷体">请选择以下的功能</span></p><br>
        <ul>
            <li><a href="${pageContext.request.contextPath }/jsp/echarts/echarts.do?method=showClass"><span style="font-size: large;font-family: 华文楷体">货物购买种类的统计</span></a></li><br>
            <li><a href="${pageContext.request.contextPath }/jsp/echarts/echarts.do?method=showLikeit"><span style="font-size: large;font-family: 华文楷体">查看货物的受喜爱程度</span></a></li><br>
            <li><a href="${pageContext.request.contextPath }/jsp/echarts/echarts.do?method=showTimeOrder"><span style="font-size: large;font-family: 华文楷体">查看每日订单销量的趋势</span></a></li><br>
            <li><a href="${pageContext.request.contextPath }/jsp/echarts/echarts.do?method=showSearchWord"><span style="font-size: large;font-family: 华文楷体">查看热搜词汇</span></a></li><br>
            <li><a href="${pageContext.request.contextPath }/jsp/echarts/echarts.do?method=showSexuality"><span style="font-size: large;font-family: 华文楷体">查看用户性别组成</span></a></li><br>
        </ul>
    </div>
</div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
