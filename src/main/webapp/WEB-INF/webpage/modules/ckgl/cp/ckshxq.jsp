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
            name="jqGrid,laydate,jquery,bootstrap,jquery-ui,peity,iCheck,sweetalert,Validform,jqgrid"/>
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
<h4>成品</h4>
<hr>
<div class="row">
    <div id="CkglCpxqGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">日期：</label>
                <input name="rq" id="rq" htmlEscape="false" class="form-control layer-date" pattern="yyyy-MM-dd" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"  placeholder="年-月-日"  datatype="*" />
            </div>
        </div>
    </div>
</div>

<grid:grid id="CkglCpxq"
           url="${adminPath}/ckgl/cp/ajaxCpxqList" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>

    <grid:column label="日期" name="rq"/>
    <grid:column label="计划名称" name="jhbh"/>
    <grid:column label="零部件名称" name="lbjmc"/>
    <grid:column label="零部件图号" name="lbjth"/>
    <grid:column label="库存数量" name="rksl"/>

    <grid:toolbar function="exportShd" icon="fa fa-file-excel-o" btnclass="btn btn-sm btn-warning" title="导出送货单"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>

<script type="text/javascript">



    //导出送货单
    function exportShd(title, url, gridId, id, width, height, tipMsg){
        var rq = $("#rq").val();
        $.ajax({
            type: "get",
            url: "${adminPath}/ckgl/cp/exportShd?rq="+rq,
            success: function (data) {
                top.layer.alert("导出成功，请在D:/bingzhengjixie文件夹下查看", {icon: 0, title:'提示'});
            }
        });
    }

</script>

</body>
</html>
