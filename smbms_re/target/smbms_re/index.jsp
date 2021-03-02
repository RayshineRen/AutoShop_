<%--
  Created by IntelliJ IDEA.
  User: Ray
  Date: 2020/11/11
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>HomePage</title>
    <link href="./bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="./bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">AutoShop</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/jsp/frame.jsp">ViewData</a></li>
                <li><a href="${pageContext.request.contextPath}/jsp/graphs.jsp">Charts</a></li>
                <li class="about">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">More <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/My%20Interests/index.html">Contact</a></li>
                        <li><a href="${pageContext.request.contextPath}/err/getback.jsp">Complain</a></li>
                        <li role="separator" class="divider"></li>
                        <li class="dropdown-header">隐秘的角落</li>
                        <li><a href="https://github.com/RayshineRen">Try click it</a></li>
                        <li><a href="https://www.baidu.com/">Don't Touch!</a></li>
                    </ul>
                </li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container marketing">

    <!-- Three columns of text below the carousel -->
    <div class="row">
        <div class="col-lg-4">
            <img class="img-circle" src="./images/SunRise.jpg" alt="SunRise" width="140" height="140">
            <h2><a href="#home1" style="text-decoration: none">ViewData</a></h2>
            <p>You can get all the data of this system, and perform CRUD on this system.</p>
<%--            <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>--%>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
            <img class="img-circle" src="./images/universe.png" alt="universe" width="140" height="140">
            <h2><a href="#home2" style="text-decoration: none">Charts</a></h2>
            <p>You can see the data(Visualization of Data).</p>
<%--            <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>--%>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
            <img class="img-circle" src="./images/Sea.jpg" alt="Sea" width="140" height="140">
            <h2><a href="#home3" style="text-decoration: none">ContactMe</a></h2>
            <p>Contact us and give us advice.</p>
<%--            <p><a class="btn btn-default" href="#" role="button">More &raquo;</a></p>--%>
        </div><!-- /.col-lg-4 -->
    </div><!-- /.row -->

<hr class="featurette-divider">

<div class="row featurette">
    <div class="col-md-7">
        <h2 class="featurette-heading" id="home1">First feature ViewData. <span class="text-muted">It'll blow your mind.</span></h2>
        <p class="lead">这是后台数据的概览，在这里，可以看到本系统所有的数据</p>
        <p class="lead">用户数据、订单数据、供应商数据等等</p>
        <p class="lead">这是本系统的后台核心，有许多具有特色的衍生功能</p>
        <p class="lead">点击“导入CSV文件”按钮，管理员可以轻松导入批量数据</p>
        <p class="lead">点击“查询”按钮，管理员可以轻松筛选用户信息</p>
    </div>
    <div class="col-md-5">
        <img class="featurette-image img-responsive center-block" src="./images/home1.png" alt="Generic placeholder image">
    </div>
    <div class="col-md-5">
        <img class="featurette-image img-responsive center-block" src="./images/home2.png" alt="Generic placeholder image">
    </div>
    <div class="col-md-5">
        <img class="featurette-image img-responsive center-block" src="./images/home3.png" alt="Generic placeholder image">
    </div>
</div>

<hr class="featurette-divider">

<div class="row featurette">
    <div class="col-md-7 col-md-push-5">
        <h2 class="featurette-heading" id="home2">Second feature Charts. <span class="text-muted">See for yourself.</span></h2>
        <p class="lead">这是后台数据的可视化，在这里，可以看到有意思的图像</p>
        <p class="lead">有关用户和订单数据可视化等等</p>
        <p class="lead">可选择的图形有很多</p>
        <p class="lead">系统演示时，部分数据是演示数据</p>
    </div>
    <div class="col-md-5 col-md-pull-7">
        <img class="featurette-image img-responsive center-block" src="./images/home4.png" alt="Generic placeholder image">
    </div>
</div>

<hr class="featurette-divider">

<div class="row featurette">
    <div class="col-md-7">
        <h2 class="featurette-heading" id="home3">And lastly, Contact me. <span class="text-muted">Checkmate.</span></h2>
        <p class="lead">你可以在系统的很多地方找到写着我的联系方式的主页</p>
        <p class="lead">顶部导航栏、底部导航栏等等</p>
        <p class="lead">这是具有特色的衍生模块</p>
        <p class="lead">这是我学习HTML、CSS、JS前端三件套时</p>
        <p class="lead">用于练手的代码，欢迎查看！</p>
    </div>
    <div class="col-md-5">
        <img class="featurette-image img-responsive center-block" src="./images/home6.png" alt="Generic placeholder image">
    </div>
</div>

<hr class="featurette-divider">

<!-- /END THE FEATURETTES -->


<script src="./jQuery/jquery-3.5.1.min.js"></script>
<script src="./bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>
