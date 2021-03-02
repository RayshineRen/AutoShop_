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
    <div id="main" style="width: 900px;height:600px; margin: 30px auto"></div>
    <script>
        var chartDom = document.getElementById('main');
        var myChart = echarts.init(chartDom);
        var option;

        option = {
            title: {
                text: '近五天日销量走势'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['饮品销量', '日用品销量', '食品销量', '总销量']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: [<c:forEach var="i" begin="0" end="${days.size()-1}"> '${days.get(i)}', </c:forEach>],
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    name: '饮品销量',
                    type: 'line',
                    stack: '总量',
                    data: [ <c:forEach var="i" begin="0" end="${drinkOrder.size()-1}"> '${drinkOrder[i]}', </c:forEach>]
                },
                {
                    name: '日用品销量',
                    type: 'line',
                    stack: '总量',
                    data: [ <c:forEach var="i" begin="0" end="${dailyOrder.size()-1}"> '${dailyOrder[i]}', </c:forEach>]
                },
                {
                    name: '食品销量',
                    type: 'line',
                    stack: '总量',
                    data: [ <c:forEach var="i" begin="0" end="${foodOrder.size()-1}"> '${foodOrder[i]}', </c:forEach>]
                },
                {
                    name: '总销量',
                    type: 'line',
                    stack: '总量',
                    data: [ <c:forEach var="i" begin="0" end="${totalOrder.size()-1}"> '${totalOrder[i]}', </c:forEach>]
                }
            ]
        };

        option && myChart.setOption(option);
    </script>
</div>
</section>
<%@include file="../common/foot.jsp" %>

