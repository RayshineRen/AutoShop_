<%--
  Created by IntelliJ IDEA.
  User: Ray
  Date: 2021/1/19
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <title>你没有权限查看后台</title>
</head>
<body>
<center>
    <div>
        <img src="<%=request.getContextPath()%>/images/getback.jpg" alt="GoBack">
    </div>
    <p>尊敬的用户您好，您并没有查看后台的权限，请重新登录</p>
    <a href="<%=request.getContextPath()%>/login.jsp">重新登录</a>
</center>
</body>
</html>
