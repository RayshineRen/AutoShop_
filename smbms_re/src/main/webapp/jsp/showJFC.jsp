<%--
  Created by IntelliJ IDEA.
  User: Ray
  Date: 2020/11/2
  Time: 9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
<div class="right">
    <img class="wColck" src="${pageContext.request.contextPath }/images/clock.jpg" alt=""/>
    <div class="wFont">
        <h2>${userSession.userName}</h2>
        <p><span id="welcome" style="font-family: 华文楷体;font-size: large">欢迎使用${requestScope.Gname}报表功能!</span></p>
    </div>
    <div>
        <img src="${requestScope.url}" alt="">
    </div>
</div>
</section>
<%@include file="/jsp/common/foot.jsp" %>

