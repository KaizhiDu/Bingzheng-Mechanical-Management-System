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
    <html:css name="simditor"/>
    <html:css
            name="bootstrap-fileinput,font-awesome,animate,iCheck,datepicker,jqgrid,sweetalert,Validform,jqgrid"/>
    <html:js
            name="jqGrid,jquery,bootstrap,jquery-ui,peity,iCheck,sweetalert,Validform,jqgrid"/>
    <html:js name="bootstrap-fileinput"/>
    <html:js name="simditor"/>
    <html:js name="laydate"/>
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
    <div id="CkglBcpGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-4" style="margin-bottom: 10px">

            </div>
            <div class="form-group col-md-4" style="margin-bottom: 10px">

            </div>
        </div>
    </div>
</div>

<grid:grid id="CkglBcp"
           url="${adminPath}/ckgl/bcp/ajaxBcpList2" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>

    <grid:column label="sys.common.opt" name="opt" formatter="button" width="100"/>
    <grid:button title="详情" groupname="opt" function="xq"
                 outclass="btn-success" url="${adminPath}/ckgl/bcp/xq?id=\"+row.id+\"" />
    <grid:button title="加入生产" groupname="opt" function="jrsc"
                 outclass="btn-primary" url="${adminPath}/ckgl/bcp/jrsc?id=\"+row.id+\"" />

    <grid:column label="计划编号" name="jhbh"/>
    <grid:column label="零部件名称" name="lbjmc"/>
    <grid:column label="零部件图号" name="lbjth"/>
    <grid:column label="库存数量" name="rksl"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>


</body>
</html>