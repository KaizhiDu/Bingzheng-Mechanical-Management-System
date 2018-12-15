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

<h4>计划进度图</h4>

<input type="hidden" id="firstJhid" name="firstJhid" value="${firstJh.id}">

<div class="row">
    <div id="JhjdtQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">计划名称：</label>
                <select name="jhid" class="form-control" id="jhid" onchange="changeJh()">
                    <c:forEach items="${htList}" var="each">
                        <option value="${each.id}">${each.htbh}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <button class="btn btn-success" onclick="checkZjh()">查看整个计划进度</button>
            </div>
        </div>
    </div>
    <br><br><br>
    <div id="dataBody">

    </div>
</div>

<script type="text/javascript">

    //查看总计划情况
    function checkZjh(){
        var jhid = $("#jhid").val();
        var url = "${adminPath}/sjfx/jhjdt/checkZjh?jhid="+jhid;
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["50%", "60%"],
            title: "计划执行情况",
            maxmin: true, //开启最大化最小化按钮
            content: url ,
            success: function(layero, index){
                //遍历父页面的button,使其失去焦点，再按enter键就不会弹框了
                $(":button").each(function () {
                    $(this).blur();
                });
            },
            btn: ['关闭'],
            cancel: function(index){
                refreshTable2(gridId);
            },
            end: function (index) {
                refreshTable2(gridId);
            }
        });
    }

    //改变计划
    function changeJh(){
        var jhid = $("#jhid").val();

        //需要清空dataBody
        $('#dataBody').html("");

        var url = "${adminPath}/sjfx/jhjdt/searchData?jhid="+jhid;
        $.ajax({
            type: "GET",
            url: url,
            success: function (data) {
                //先循环创建div
                for (var i=0;i<data.length;i++){
                    var lbj = data[i];
                    var div=document.createElement("div");
                    div.id = lbj.lbjth;
                    div.style.cssText="width:1200px;height:150px;";
                    $("#dataBody").append(div);
                }

                //然后循环放入div
                for (var i=0;i<data.length;i++){
                    var lbj = data[i];
                    // 基于准备好的dom，初始化echarts实例
                    var myChart = echarts.init(document.getElementById(lbj.lbjth));
                    // 指定图表的配置项和数据\
                    var option = {
                        tooltip : {
                            trigger: 'axis',
                            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                            }
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        xAxis:  {
                            type: 'value'
                        },
                        yAxis: {
                            type: 'category',
                            data: [lbj.lbjmc]
                        },
                        series: [
                            {
                                name: '已完成',
                                type: 'bar',
                                stack: '总量',
                                label: {
                                    normal: {
                                        show: true,
                                        position: 'insideRight'
                                    }
                                },
                                data: [lbj.wcsl]
                            },
                            {
                                name: '未完成',
                                type: 'bar',
                                stack: '总量',
                                label: {
                                    normal: {
                                        show: true,
                                        position: 'insideRight'
                                    }
                                },
                                data: [lbj.wwcsl]
                            },

                        ]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                }

            }
        });
    }

    window.onload=function(){

//        var div1=document.createElement("div");
//        div1.innerHTML = "div1";
//        div1.id ="Video";
//        div1.style.cssText="width:850px;height:200px;";
//        document.body.appendChild(div1);

        //拿到了第一个计划的ID
        var firstJhid = $("#firstJhid").val();
        var url = "${adminPath}/sjfx/jhjdt/searchData?jhid="+firstJhid;
        $.ajax({
            type: "GET",
            url: url,
            success: function (data) {
                //先循环创建div
                for (var i=0;i<data.length;i++){
                    var lbj = data[i];
                    var div=document.createElement("div");
                    div.id = lbj.lbjth;
                    div.style.cssText="width:1200px;height:150px;";
                    $("#dataBody").append(div);
                }

                //然后循环放入div
                for (var i=0;i<data.length;i++){
                    var lbj = data[i];
                    // 基于准备好的dom，初始化echarts实例
                    var myChart = echarts.init(document.getElementById(lbj.lbjth));
                    // 指定图表的配置项和数据
                    var option = {
                        tooltip : {
                            trigger: 'axis',
                            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                            }
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        xAxis:  {
                            type: 'value'
                        },
                        yAxis: {
                            type: 'category',
                            data: [lbj.lbjmc]
                        },
                        series: [
                            {
                                name: '已完成',
                                type: 'bar',
                                stack: '总量',
                                label: {
                                    normal: {
                                        show: true,
                                        position: 'insideRight'
                                    }
                                },
                                data: [lbj.wcsl]
                            },
                            {
                                name: '未完成',
                                type: 'bar',
                                stack: '总量',
                                label: {
                                    normal: {
                                        show: true,
                                        position: 'insideRight'
                                    }
                                },
                                data: [lbj.wwcsl]
                            },

                        ]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                }

            }
        });

    };

    //更新到当前页
    function refreshTable2(gridId){
        var queryParams = {};
        var queryFields=$('#queryFields').val();
        var curpagenum = $("#"+gridId+"").jqGrid('getGridParam', 'page');
        queryParams['queryFields'] = queryFields;
        //普通的查询
        $('#' + gridId + "Query").find(":input").each(function() {
            var val = $(this).val();
            if (queryParams[$(this).attr('name')]) {
                val = queryParams[$(this).attr('name')] + "," + $(this).val();
            }
            queryParams[$(this).attr('name')] = val;
        });

        // 普通的查询
        $('#' + gridId + "Query").find(":input").each(function() {
            var condition = $(this).attr('condition');
            if (!condition) {
                condition = "";
            }
            var key = "query." + $(this).attr('name') + "||" + condition;
            queryParams[key] = queryParams[$(this).attr('name')];
        });
        //刷新
        //传入查询条件参数
        $("#"+gridId).jqGrid('setGridParam',{
            datatype:'json',
            postData:queryParams, //发送数据
            page:curpagenum
        }).trigger("reloadGrid"); //重新载入
    }
</script>
</body>
</html>
