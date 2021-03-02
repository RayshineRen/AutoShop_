<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>后台管理系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/public.css" />
</head>
<body>
    <header class="publicHeader">
        <h1>售货机管理系统</h1>
        <div class="publicHeaderR">
            <p><span>下午好！</span><span style="color: #fff21b"> ${userSession.userName }</span> , 欢迎你！</p>
            <a href="${pageContext.request.contextPath }/jsp/logout.do">退出</a>
        </div>
    </header>
    <section class="publicTime">
        <span id="time">2015年1月1日 11:11  星期一</span>
    </section>
 <section class="publicMian ">
     <div class="left">
         <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
         <nav>
             <ul class="list">
                 <li><a href="${pageContext.request.contextPath }/jsp/order/order.do?method=query">购物记录</a></li>
                 <li><a href="${pageContext.request.contextPath }/jsp/provider/provider.do?method=query">供应商管理</a></li>
                 <li><a href="${pageContext.request.contextPath }/jsp/bill/bill.do?method=query">货物订单</a></li>
                 <li><a href="${pageContext.request.contextPath }/jsp/user/user.do?method=query">用户管理</a></li>
                 <li><a href="${pageContext.request.contextPath }/jsp/graphs.jsp">报表分析</a></li>
                 <li><a href="${pageContext.request.contextPath }/jsp/FrontEnd/index.jsp">查看前台</a></li>
                 <li><a href="${pageContext.request.contextPath }/jsp/logout.do">退出系统</a></li>
             </ul>
         </nav>
     </div>
     <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
     <input type="hidden" id="referer" name="referer" value="<%=request.getHeader("Referer")%>"/>