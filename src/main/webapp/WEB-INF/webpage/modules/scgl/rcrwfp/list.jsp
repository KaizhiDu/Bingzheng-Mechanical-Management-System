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
            name="layer,laydate,jqGrid,jquery,bootstrap,jquery-ui,peity,iCheck,sweetalert,Validform,jqgrid"/>
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


<div class="row">
    <div id="RcrwfpGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">日期：</label>
                <input name="rq" id="rq" htmlEscape="false" class="form-control layer-date" pattern="yyyy-MM-dd" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"  placeholder="年-月-日"  datatype="*"/>
            </div>
        </div>
    </div>
</div>
<h2>日工分配</h2>
<grid:grid id="Rcrwfp"
           url="${adminPath}/scgl/rcrwfp/ajaxRcrwfpList" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <grid:column label="sys.common.opt" name="opt" formatter="button" width="120"/>
    <grid:button title="工时" groupname="opt" function="fpgs"
                 outclass="btn-primary" url="${adminPath}/scgl/rcrwfp/fpgs?id=\"+row.id+\"" />
    <grid:button title="任务" groupname="opt" function="fpsb"
                 outclass="btn-success" url="${adminPath}/scgl/rcrwfp/fpsb?id=\"+row.id+\"&rq=\"+row.rq+\"" />
    <grid:button title="详情" groupname="opt" function="rwxq"
                 outclass="btn-info" url="${adminPath}/scgl/rcrwfp/rwxq?id=\"+row.id+\"&rq=\"+row.rq+\"&xm=\"+row.xm+\"" />

    <grid:column label="工时" name="gs" width="30"/>
    <grid:column label="包工占用" name="bgzy" width="50"/>
    <grid:column label="日期" name="rq"/>
    <grid:column label="姓名" name="xm"/>
    <grid:column label="职位" name="zw"/>
    <grid:column label="性别" name="xb" dict="sex" dateformat=""/>

    <grid:column label="sys.common.opt" name="opt1" formatter="button" width="50"/>
    <grid:button title="导出派工单" groupname="opt1" function="exportGrpgd"
                 outclass="btn-warning" url="${adminPath}/scgl/rcrwfp/exportGrpgd?id=\"+row.id+\"&rq=\"+row.rq+\"" />

    <grid:toolbar function="createPgd" icon="fa fa-file-excel-o" btnclass="btn btn-sm btn-warning" title="生成派工单"/>

    <grid:toolbar function="search"/>
</grid:grid>

<script type="text/javascript">

    //任务详情
    function rwxq(title, url, gridId, id, width, height, tipMsg){
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["30%", "60%"],
            title: "任务详情",
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
                refreshTable(gridId);
            },
            end: function (index) {
                refreshTable(gridId);
            }
        });
    }

    //分配工时
    function fpgs(title, url, gridId, id, width, height, tipMsg) {
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["30%", "50%"],
            title: "分配工时",
            maxmin: true, //开启最大化最小化按钮
            content: url ,
            success: function(layero, index){
                //遍历父页面的button,使其失去焦点，再按enter键就不会弹框了
                $(":button").each(function () {
                    $(this).blur();
                });
            },
            btn: ['保存', '关闭'],
            yes: function(index, layero){
                var body = top.layer.getChildFrame('body', index);
                var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                //文档地址
                //http://www.layui.com/doc/modules/layer.html#use
                iframeWin.contentWindow.check();
                //判断逻辑并关闭
                setTimeout(function(){top.layer.close(index)}, 200);//延时0.1秒，对应360 7.1版本bug
                layer.msg("保存成功!",{ icon: 1, time: 1000 });
                refreshTable(gridId);
            },
            cancel: function(index){
                refreshTable(gridId);
            },
            end: function (index) {
                refreshTable(gridId);
            }
        });
    }

    //分配日常任务
    function fpsb(title, url, gridId, id, width, height, tipMsg) {
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["95%", "90%"],
            title: "日常任务分配",
            maxmin: true, //开启最大化最小化按钮
            content: url ,
            success: function(layero, index){
                //遍历父页面的button,使其失去焦点，再按enter键就不会弹框了
                $(":button").each(function () {
                    $(this).blur();
                });
            },
            btn: [ '关闭'],
            cancel: function(index){
                refreshTable(gridId);
            },
            end: function (index) {
                refreshTable(gridId);
            }
        });
    }

    function exportGrpgd(title, url, gridId, id, width, height, tipMsg){
        var rq = $("#rq").val();
        //需要提示
        layer.confirm('确定要导出'+rq+"该员工的派工单吗？", {
                btn: ['确定', '取消']
            }, function (index, layero) {
                $.ajax({
                    type: "get",
                    url: url,
                    success: function (data) {

                    }
                });
                layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
                top.layer.alert("导出成功，请在D:/bingzhengjixie文件夹下查看", {icon: 0, title:'提示'});

            }
        );
    }

    function createPgd(){
        var rq = $("#rq").val();
        //需要提示
        layer.confirm('确定要导出'+rq+"的派工信息吗？", {
                btn: ['确定', '取消']
            }, function (index, layero) {
            $.ajax({
                type: "get",
                url: "${adminPath}/scgl/rcrwfp/createPgd?rq="+rq,
                success: function (data) {

                }
            });
                layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
                top.layer.alert("导出成功，请在D:/bingzhengjixie文件夹下查看", {icon: 0, title:'提示'});

            }
        );


    }
</script>

</body>
</html>
