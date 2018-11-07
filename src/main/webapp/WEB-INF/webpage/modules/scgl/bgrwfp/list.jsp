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
            name="layer,jqGrid,jquery,bootstrap,jquery-ui,peity,iCheck,sweetalert,Validform,jqgrid"/>
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
                <select id="rq" name="rq" class="form-control">
                    <c:forEach items="${dates}" var="each">
                        <option value="${each}">${each}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
</div>
<h2>${currentTime} 包工分配</h2>
<grid:grid id="Yggl"
           url="${adminPath}/grgl/grgl/ajaxGrglList" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <grid:column label="sys.common.opt" name="opt" formatter="button" width="50"/>
    <grid:button title="查看包工任务" groupname="opt" function="ckbgrw"
                 outclass="btn-primary" url="${adminPath}/scgl/bgrwfp/ckbgrw?id=\"+row.id+\"" />

    <grid:column label="姓名" name="name"/>
    <grid:column label="职位" name="zw"/>
    <grid:column label="性别" name="xb" dict="sex" dateformat=""/>

    <grid:toolbar function="createPgd" icon="fa fa-file-excel-o" btnclass="btn btn-sm btn-warning" title="生成派工单"/>

</grid:grid>

<script type="text/javascript">

    //分配包工任务
    function ckbgrw(title, url, gridId, id, width, height, tipMsg){
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["95%", "90%"],
            title: "分配包工任务",
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

    //生成派工单
    function createPgd(){
        var rq = $("#rq").val();
        $.ajax({
            type: "get",
            url: "${adminPath}/scgl/bgrwfp/createPgd?rq="+rq,
            success: function (data) {
                top.layer.alert("导出成功，请在D:/bingzhengjixie文件夹下查看", {icon: 0, title:'提示'});
            }
        });
    }

</script>

</body>
</html>
