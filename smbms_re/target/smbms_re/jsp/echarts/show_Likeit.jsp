<%--
  Created by IntelliJ IDEA.
  User: Ray
  Date: 2020/11/2
  Time: 9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/head.jsp"%>
<script src="../../Echarts/echarts.min.js"></script>
<div class="right">
<%--    <img class="wColck" src="${pageContext.request.contextPath }/images/clock.jpg" alt=""/>--%>
<%--    <div class="wFont">--%>
<%--        <h2>${userSession.userName}</h2>--%>
<%--        <p><span id="welcome" style="font-family: 华文楷体;font-size: large">欢迎使用${requestScope.Gname}报表功能!</span></p>--%>
<%--    </div>--%>
    <form method="get" action="${pageContext.request.contextPath }/jsp/echarts/echarts.do">
        <input name="method" value="showLikeit" class="input-text" type="hidden">
        <input type="hidden" name="pageIndex" value="1"/>
    </form>
    <div id="main" style="width: 600px;height:400px; margin: 50px auto"></div>
<%--    <script type="text/javascript">--%>
<%--        // 基于准备好的dom，初始化echarts实例--%>
<%--        var myChart = echarts.init(document.getElementById('main'));--%>

<%--        // 指定图表的配置项和数据--%>
<%--        var option = {--%>
<%--            title: {--%>
<%--                text: '货物受喜爱程度'--%>
<%--            },--%>
<%--            tooltip: {},--%>
<%--            legend: {--%>
<%--                data:['Like数量']--%>
<%--            },--%>
<%--            xAxis: {--%>
<%--                data: [<c:forEach var="i" begin="0" end="${goodsList.size()-1}"> '${goodsList[i].name}', </c:forEach>],--%>
<%--            },--%>
<%--            yAxis: {},--%>
<%--            series: [{--%>
<%--                name: '销量',--%>
<%--                type: 'bar',--%>
<%--                data: [ <c:forEach var="i" begin="0" end="${goodsList.size()-1}"> '${goodsList[i].likeit}', </c:forEach>]--%>
<%--            }]--%>
<%--        };--%>

<%--        // 使用刚指定的配置项和数据显示图表。--%>
<%--        myChart.setOption(option);--%>
<%--    </script>--%>

    <script>
        var chartDom = document.getElementById('main');
        var myChart = echarts.init(chartDom);
        var option;

        option = {
            title: {
                text: '货物受喜爱程度'
            },
            tooltip: {},
            legend: {
                data:['Like数量']
            },
            xAxis: {
                type: 'category',
                data: [<c:forEach var="i" begin="0" end="${goodsList.size()-1}"> '${goodsList[i].name}', </c:forEach>],
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                name: '销量',
                type: 'bar',
                data: [ <c:forEach var="i" begin="0" end="${goodsList.size()-1}"> '${goodsList[i].likeit}', </c:forEach>],
                showBackground: true,
                backgroundStyle: {
                    color: 'rgba(180, 180, 180, 0.2)'
                },
                itemStyle: {
                    normal: {
                        //这里是重点
                        color: function(params) {
                            //注意，如果颜色太少的话，后面颜色不会自动循环，最好多定义几个颜色
                            var colorList = ['#c23531','#2f4554', '#61a0a8', '#d48265', '#91c7ae','#749f83', '#ca8622'];
                            return colorList[params.dataIndex]
                        }
                    }
                }
            }]
        };
        option && myChart.setOption(option);
    </script>
    <input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
    <c:import url="../rollpage.jsp">
        <c:param name="totalCount" value="${totalCount}"/>
        <c:param name="currentPageNo" value="${currentPageNo}"/>
        <c:param name="totalPageCount" value="${totalPageCount}"/>
    </c:import>
</div>
</section>
<%@include file="../common/foot.jsp" %>

