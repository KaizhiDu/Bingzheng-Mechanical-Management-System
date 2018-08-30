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

<h4>设备管理</h4>
<hr/>

<grid:grid id="Sbgl"
           url="${adminPath}/sbgl/sbgl/ajaxListSbgl" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <grid:column label="sys.common.opt" name="opt" formatter="button" width="100"/>

    <grid:button title="修改" groupname="opt" function="modifyWorker"
                 outclass="btn-success" url="${adminPath}/grgl/grgl/updateWorker?id=\"+row.id+\"" />
    <grid:button title="删除" groupname="opt" function="deleteWorker"
                 outclass="btn-danger" url="${adminPath}/grgl/grgl/deleteWorker?id=\"+row.id+\"" />

    <grid:column label="设备编号" name="name"/>
    <grid:column label="设备名称" name="gender" dict="sex" dateformat=""/>
    <grid:column label="状态" name="tel"/>

    <grid:toolbar function="createSb" icon="fa fa-plus" btnclass="btn btn-sm btn-primary" title="添加"/>
    <grid:toolbar function="delete" title="删除" btnclass="btn-danger"/>
</grid:grid>



</body>
</html>
