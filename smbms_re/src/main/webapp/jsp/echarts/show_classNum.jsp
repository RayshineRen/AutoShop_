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
    <div id="main" style="width: 700px;height:500px; margin: 30px auto"></div>
    <script>
        var chartDom = document.getElementById('main');
        var myChart = echarts.init(chartDom);
        var option;

        option = {
            title: {
                text: '购买货物种类的统计',
                subtext: '大数据统计',
                left: 'center'
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                orient: 'vertical',
                left: 'left',
            },
            series: [
                {
                    name: '种类',
                    type: 'pie',
                    radius: '50%',
                    data: [
                        {value: ${list.get(0)}, name: '饮品'},
                        {value: ${list.get(1)}, name: '日用品'},
                        {value: ${list.get(2)}, name: '食品'},
                    ],
                    emphasis: {
                        itemStyle: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    },
                    itemStyle: {
                        normal: {
                            color:function(params){
                                let colorList=[
                                    '#00FF00', '#4850b2', '#FF8C00', '#FF0000', '#FE8463',
                                ];
                                return colorList[params.dataIndex]
                            },
                            shadowBlur: 200,
                            shadowColor: 'rgba(0, 0, 0, 0.3)'
                        }
                    }
                }
            ]
        };
        option && myChart.setOption(option);
    </script>
</div>
</section>
<%@include file="../common/foot.jsp" %>

