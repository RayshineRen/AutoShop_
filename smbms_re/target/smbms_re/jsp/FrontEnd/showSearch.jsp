<%--
  Created by IntelliJ IDEA.
  User: Ray
  Date: 2020/12/4
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%><%@include file="/jsp/FrontEnd/common/head.jsp"%>
<style>
    .box {
        width: 200px;
        height: 350px;
        margin: 30px auto;
        background-color: #fffffc;
        box-shadow: 10px 10px 10px 10px slategray;
        float: left;
        margin-right: 44px;
    }
    .review {
        height: 20px;
        font-size: 14px;
        padding: 0 34px;
        margin-top: 23px;
    }
    .appraise {
        font-size: 12px;
        color: #c9ccc9;
        margin-top: 50px;
        padding: 0 33px;
    }
    h4 {
        display: inline-block;
        font-size: 14px;
        margin-top: 20px;
        font-weight: 400;
        padding-left: 33px;
        margin-right: 20px;
    }
    .cost {
        display: inline-block;
        margin: 0 5px 0 20px;
        color: #d69865;
    }
    #box li:nth-child(1){
        margin-left: 44px;
    }
    img {
        margin-left: 25px;
        margin-right: 25px;
    }
</style>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是：</strong>
        <span>展示货物</span>
    </div>

    <div style="width: 100%;overflow: hidden;margin-bottom: 50px;background-color: #fff3f8">
        <ul id="box">
            <c:forEach var="goods" items="${goodsList }" varStatus="status">
                <li>
                    <div class="box">
                        <img src="${goods.img}" alt="${goods.name}">
                        <p class="review"><span>${goods.description }</span></p>
                        <div class="appraise">
                            来自于admin的评价
                        </div>
                        <div class="info">
                            <h4><span>${goods.name}</span></h4>|
                            <span class="cost">${goods.cost}</span>
                        </div>
                        <div style="margin-top: 20px">
                            <center>
                                <span><a class="viewGoods" href="javascript:;" goodsid=${goods.id } goodsname=${goods.name }><img src="${pageContext.request.contextPath }/images/read.png" alt="查看" title="查看"/></a></span>
                                <span><a class="buyGoods" href="javascript:;" goodsid=${goods.id } goodsname=${goods.name }><img src="${pageContext.request.contextPath }/images/goumai.jpeg" alt="购买" title="购买"/></a></span>
                            </center>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>

    </div>
</section>
<%@include file="/jsp/FrontEnd/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/goodslist.js"></script>
