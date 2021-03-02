<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>系统登录</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <script type="text/javascript"></script>
    <script type="text/javascript" src="./jQuery/jquery-3.5.1.min.js"></script>
</head>

<body class="login_bg">
    <section class="loginBox">

        <header class="loginHeader">
            <h1>ATS</h1>
        </header>

        <section class="loginCont">
	        <form class="loginForm" action="${pageContext.request.contextPath }/login.do"  name="actionForm" id="actionForm"  method="post" >
				<div class="inputbox">
                    <label for="userCode">用户名：</label>
					<input type="text" class="input-text" id="userCode" name="userCode" placeholder="请输入用户名" required/>
				</div>
				<div class="inputbox">
                    <label for="userPassword">密码：</label>
                    <input type="password" id="userPassword" name="userPassword" placeholder="请输入密码" required/>
                </div>
                <div class="info">${error}</div>
				<div class="subBtn">
                    <input type="submit" value="登录"/>
                    <input type="reset" value="重置"/>
                </div>
			</form>
        </section>
    </section>
    <div id="menu">
        <ul>
            <li>
                <span>
                    <div>
                        <a href=${pageContext.request.contextPath}/My%20Interests/index.html>Contact us</a>
                    </div>
                </span>
            </li>
        </ul>
    </div>

    <style type="text/css">
        *{margin:0;padding:0;}
        a,img{border:0;}

        #menu{position:fixed;bottom:0px;width:100%;height:44px;line-height:44px;z-index:999;background:url(images/menubg.png) repeat-x;}
        #menu ul{margin:0 auto;list-style-type:none;width:100%;max-width:500px;height:100%;}
        #menu ul li{float:left;width:33.3%;height:100%;text-align:center;position:relative;font-size:14px;}
        #menu ul li .line{position:absolute;top:0px;right:0px;z-index:30;}
        #menu ul li .menu_li{position:absolute;top:0px;left:0px;z-index:20;width:100%;height:100%;color:#454545;background:url(images/bottom.jpg) repeat-x;}
        #menu ul li .img_front{position:absolute;top:0px;left:0px;z-index:30;width:100%;height:100%;}
        #menu ul li .img_front img{width:100%;height:100%;}
        #menu ul li span{position:absolute;bottom:-300px;left:50%;width:104px;margin-left:-52px;margin-bottom:14px;height:auto;text-align:center;z-index:10;}
        #menu ul li span div{position:absolute;top:0px;left:0px;}
        #menu ul li span a{float:left;width:100%;height:43px;line-height:43px;color:#454545;text-decoration:none;}

        .footer_front{position:fixed;width:100%;height:100%;top:0px;left:0px;z-index:888;display:none;}
    </style>

    <script type="text/javascript">
        window.onload = function() {
            $('#menu ul li').each(function(j) {
                $('#menu ul li').eq(j).removeClass("on");
                $('#menu ul li span').eq(j).animate({bottom: -$('#menu ul li span').eq(j).height()}, 100);
            });
        }

        $('#menu ul li').each(function(i) {
            $(this).click(function() {
                if ($(this).attr("class") != "on") {
                    $('#menu ul .on span').animate({bottom: -$('#menu ul .on span').height()}, 200);
                    $('#menu ul .on').removeClass("on");
                    $(this).addClass("on");
                    $('#menu ul li span').eq(i).animate({bottom: 35}, 200);
                    $('.footer_front').show();
                } else {
                    $('#menu ul li span').eq(i).animate({bottom: -$('#menu ul li span').eq(i).height()}, 200);
                    $(this).removeClass("on");
                    $('.footer_front').hide();
                }
            });
        });

        $('.footer_front').click(function() {
            $('#menu ul .on span').animate({bottom: -$('#menu ul .on span').height()}, 200);
            $('#menu ul .on').removeClass("on");
            $(this).hide();
        });
    </script>
</body>
</html>
