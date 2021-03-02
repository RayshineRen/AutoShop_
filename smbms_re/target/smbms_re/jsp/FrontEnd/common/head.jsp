<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>售货机管理系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/FrontEnd/css/style.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/jsp/FrontEnd/css/public.css" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<!--头部-->
    <header class="publicHeader">
        <h1>自助售货机欢迎你！</h1>
        <div class="publicHeaderR">
            <p><span>你好！</span><span style="color: #fff21b"> ${userSession.userName }</span> , 欢迎你！</p>
            <a href="${pageContext.request.contextPath }/jsp/logout.do">退出</a>
        </div>
    </header>
    <section class="publicTime">
        <span id="time">2015年1月1日 11:11  星期一</span>
    </section>
    <section class="publicMian ">
         <div class="left">
             <h2 class="leftH2" style="color: #fff3f8"><span class="span1" style="color: #fff3f8"></span>货物种类<span></span></h2>
             <nav>
                 <ul class="list">
                     <li><a class="myA" href="${pageContext.request.contextPath }/jsp/FrontEnd/showGoods.do?method=query&queryclassCode=1">饮品</a></li>
                     <li><a class="myA" href="${pageContext.request.contextPath }/jsp/FrontEnd/showGoods.do?method=query&queryclassCode=2">日用品</a></li>
                     <li><a class="myA" href="${pageContext.request.contextPath }/jsp/FrontEnd/showGoods.do?method=query&queryclassCode=3">食品</a></li>
                     <li><a class="myA" href="${pageContext.request.contextPath }/jsp/FrontEnd/showGoods.do?method=query">查看全部</a></li>
                     <c:if test="${sessionScope.userRole<3}">
                         <li><a class="myA" href="${pageContext.request.contextPath }/jsp/goBack.do">返回</a></li>
                     </c:if>
                 </ul>
             </nav>
         </div>
         <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
         <input type="hidden" id="referer" name="referer" value="<%=request.getHeader("Referer")%>"/>