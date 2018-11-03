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

<h4>包工明细</h4>

<div class="row">
    <div id="BgjyxqGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-3" style="margin-bottom: 10px">

            </div>
        </div>
    </div>
</div>
<grid:grid id="Bgjyxq"
           url="${adminPath}/jygl/bgjy/ajaxBgjyxqList?id=${id}" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>

    <grid:column label="日期" name="rq"/>
    <grid:column label="姓名" name="xm"/>
    <grid:column label="职位" name="zw"/>
    <grid:column label="计划名称" name="jhbh"/>
    <grid:column label="零部件名称" name="ljmc"/>
    <grid:column label="工艺大类名称" name="gydlmc"/>
    <grid:column label="工艺小类名称" name="gyxlmc"/>
    <grid:column label="设备名称" name="sbmc"/>
    <grid:column label="应完成量" name="ywcl"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>

</body>
</html>
