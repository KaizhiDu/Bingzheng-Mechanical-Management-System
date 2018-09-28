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
<div class="row">
    <div id="SbglGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">设备类型：</label>
                <select name="ssdl" class="form-control" id="ssdl">
                    <option value="">请选择</option>
                    <c:forEach items="${list}" var="each">
                        <option value="${each.id}">${each.flmc}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">设备编号：</label>
                <input htmlEscape="false" class="form-control" placeholder="请输入设备编号"  maxlength="20" id="sbbh" name="sbbh"/>
            </div>
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">设备名称：</label>
                <input htmlEscape="false" class="form-control" placeholder="请输入设备名称"  maxlength="20" id="sbmc" name="sbmc"/>
            </div>
        </div>
    </div>
</div>
<grid:grid id="Sbgl"
           url="${adminPath}/sbgl/sbgl/ajaxListSbgl?addSb=1" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>

    <grid:column label="设备编号" name="sbbh"/>
    <grid:column label="设备名称" name="sbmc"/>
    <grid:column label="设备类型" name="ssdl"/>


    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>


<script type="text/javascript">
    function check(title, url, gridId, id, width, height, tipMsg) {
        //获取选中行的id数组
        var idsArray = $("#SbglGrid").jqGrid("getGridParam", "selarrrow")
        if (idsArray.length > 0) {
            var ids = "";
            for (var i = 0; i < idsArray.length; i++) {
                if (i == 0) {
                    ids = idsArray[i];
                }
                else {
                    ids = ids + "," + idsArray[i];
                }
            }
        }
        $.ajax({
            type: "GET",
            url: "${adminPath}/scgl/bgrwfp/saveSb?ids="+ids+"&bgrwfpid=${bgrwfpid}",
            success: function (data) {
                refreshTable(gridId);
            }
        });
    }
</script>
</body>
</html>
