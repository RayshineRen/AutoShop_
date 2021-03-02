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
    <form method="get" action="${pageContext.request.contextPath }/jsp/echarts/echarts.do">
        <input name="method" value="showSearchWord" class="input-text" type="hidden">
        <input type="hidden" name="pageIndex" value="1"/>
    </form>
    <div id="main" style="width: 700px;height:500px; margin: 30px auto"></div>
    <script>
        var chartDom = document.getElementById('main');
        var myChart = echarts.init(chartDom);
        var option;

        option = {
            title: {
                text: '热搜关键词'
            },
            tooltip: {},
            legend: {
                data:['搜索次数']
            },
            xAxis: {
                type: 'category',
                data: [<c:forEach items="${map}" var="map"> '${map.key}', </c:forEach>],
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                name: '搜索次数',
                type: 'bar',
                data: [ <c:forEach items="${map}" var="map"> '${map.value}', </c:forEach>],
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

