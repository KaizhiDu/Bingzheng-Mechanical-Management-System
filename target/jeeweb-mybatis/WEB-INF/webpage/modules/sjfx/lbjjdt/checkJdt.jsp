<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf" %>
<%
    String path = request.getContextPath();
    String tempBasePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>Title</title>
    <meta name="decorator" content="list"/>
    <html:css
            name="bootstrap-fileinput,font-awesome,animate,iCheck,datepicker,jqgrid,sweetalert,Validform,jqgrid"/>
    <html:js
            name="layer,jqGrid,jquery,bootstrap,jquery-ui,peity,iCheck,sweetalert,Validform,jqgrid,echarts"/>
    <script>
        $(function () {
            $(".ibox-title").hide();
        })
    </script>
    <style>
        .wrapper-content {
            padding: 0px;
        }

        .wrapper {
            padding: 0px;
        }
    </style>
</head>
<body>

<input type="hidden" id="lbjid" name="lbjid" value="${lbjid}">

<div class="row">
    <div id="JhjdtQuery" class="col-md-12">

        <div id="dataBody" style="width: 600px;height:400px;">

        </div>
    </div>

    <script type="text/javascript">

        window.onload=function(){
            //拿到了第一个计划的ID
            var lbjid = $("#lbjid").val();
            var url = "${adminPath}/sjfx/lbjjdt/searchLbjData?lbjid="+lbjid;
            $.ajax({
                type: "GET",
                url: url,
                success: function (data) {
                    console.log(data);
                    // 基于准备好的dom，初始化echarts实例
                    var myChart = echarts.init(document.getElementById("dataBody"));

                    var option = {
                        title: {
                            text: data.lbjmc,
                            left: 'center'
                        },
                        tooltip : {//提示图
                            trigger : 'item',//item,axis
                            formatter: "{b} : {c} ({d}%)"
                        },
                        legend: {
                            type:'plain',// 'scroll',//'plain'
                            orient: 'vertical',
                            right: 10,
                            /*  top: 20,
                             bottom: 20,  */
                            data:['已完成','未完成']

                            /*  data: data.legendData,

                             selected: data.selected */
                        },
                        series : [
                            {
                                //  name: '税收收入',
                                type: 'pie',
                                radius : '55%',
                                center: ['40%', '50%'],
                                data:[
                                    {name:'已完成',value:data.wcsl},
                                    {name:'未完成',value:data.wwcsl}

                                ],
                                itemStyle: {
                                    emphasis: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);


                }
            });

        };
    </script>
</body>
</html>
