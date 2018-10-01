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

<h4>设备任务监控<font color="red">（还未完成）</font></h4>
<div class="row">
    <div id="SbrwjkGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">设备类型：</label>
                <select name="ssdl" class="form-control" id="ssdl">
                    <option value="">请选择</option>
                    <c:forEach items="${list}" var="each">
                        <option value="${each.id}">${each.flmc}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">是否占用：</label>
                <select name="sfky" class="form-control" id="sfky">
                    <option value="">请选择</option>
                    <option value="1">可用</option>
                    <option value="0">不可用</option>
                </select>
            </div>
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">设备编号：</label>
                <input htmlEscape="false" class="form-control" placeholder="请输入设备编号"  maxlength="20" id="sbbh" name="sbbh"/>
            </div>
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">设备名称：</label>
                <input htmlEscape="false" class="form-control" placeholder="请输入设备名称"  maxlength="20" id="sbmc" name="sbmc"/>
            </div>
        </div>
    </div>
</div>
<grid:grid id="Sbrwjk"
           url="${adminPath}/sbgl/sbrwjk/ajaxListSbrwjk" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>

    <grid:column label="设备编号" name="sbbh"/>
    <grid:column label="设备名称" name="sbmc"/>
    <grid:column label="所属类别" name="ssdl"/>
    <grid:column label="操作人" name="czr"/>
    <grid:column label="是否占用" name="sfky" dict="SFKY" formatterValue=""/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>

</body>
</html>
