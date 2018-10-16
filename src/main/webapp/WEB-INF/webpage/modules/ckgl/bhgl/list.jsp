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
<h4>办公用品</h4>
<hr>
<div class="row">
    <div id="BhglGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">名称或规格：</label>
                <input name="gg" id="gg" htmlEscape="false" class="form-control" placeholder="支持按照关键字查询">
            </div>
        </div>
    </div>
</div>

<grid:grid id="Bhgl"
           url="${adminPath}/ckgl/bhgl/ajaxBhglList" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>

    <grid:column label="所属仓库" name="ck"/>
    <grid:column label="大类" name="fldl"/>
    <grid:column label="小类" name="flxl"/>
    <grid:column label="名称/规格" name="gg"/>
    <grid:column label="单位" name="dw"/>
    <grid:column label="库存" name="kc"/>
    <grid:column label="预警量" name="yjkc"/>
    <grid:column label="应补数量" name="ybsl"/>
    <grid:column label="备注" name="bz"/>

    <grid:toolbar function="exportBhd" icon="fa fa-file-excel-o" btnclass="btn btn-sm btn-warning" title="导出补货单"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>

<script type="text/javascript">
    //导出补货单
    function exportBhd(title, url, gridId, id, width, height, tipMsg) {
        $.ajax({
            type: "get",
            url: "${adminPath}/ckgl/bhgl/exportBhd",
            success: function (data) {
                top.layer.alert("导出成功，请在D:/bingzhengjixie文件夹下查看", {icon: 0, title:'提示'});
            }
        });
    }
</script>

</body>
</html>
