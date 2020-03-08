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

<h4>设备任务监控</h4>
<div class="row">
    <div id="SbrwjkGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">日期：</label>
                <select name="rq" class="form-control" id="rq">
                    <c:forEach items="${dates}" var="each">
                        <option value="${each}">${each}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">设备类型：</label>
                <select name="ssdl" class="form-control" id="ssdl">
                    <option value="">请选择</option>
                    <c:forEach items="${list}" var="each">
                        <option value="${each.id}">${each.flmc}</option>
                    </c:forEach>
                </select>
            </div>

        </div>
    </div>
</div>
<grid:grid id="Sbrwjk"
           url="${adminPath}/sbgl/sbrwjk/ajaxListSbrwjk" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>

    <grid:column label="日期" name="rq"/>
    <grid:column label="设备编号" name="sbbh"/>
    <grid:column label="所属类别" name="ssdl"/>
    <grid:column label="是否占用" name="sfky"/>

    <grid:toolbar function="search"/>
</grid:grid>

</body>
</html>
