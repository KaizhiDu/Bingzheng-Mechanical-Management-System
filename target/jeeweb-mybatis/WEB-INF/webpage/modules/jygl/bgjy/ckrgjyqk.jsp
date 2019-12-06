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

<h4>日工检验情况</h4>
<div class="row">
    <div id="RgjlGridQuery" class="col-md-12">
        <div class="form-inline">

            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">零部件图号：</label>
                <input name="ljth" id="ljth" htmlEscape="false" class="form-control" placeholder="支持按照关键字查询">
            </div>

            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">零部件名称：</label>
                <input name="ljmc" id="ljmc" htmlEscape="false" class="form-control" placeholder="支持按照关键字查询">
            </div>

            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">工人：</label>
                <select name="xm" class="form-control" id="xm">
                    <option value="">请选择</option>
                    <c:forEach items="${ygsjList}" var="each">
                        <option value="${each.name}">${each.name}</option>
                    </c:forEach>
                </select>
<%--                <input name="xm" id="xm" htmlEscape="false" class="form-control" placeholder="请输入工人姓名">--%>
            </div>

            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">创建日期：</label>
                <input name="cjrq" id="cjrq" htmlEscape="false" class="form-control layer-date" pattern="yyyy-MM-dd" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"  placeholder="年-月-日"  datatype="*"/>
            </div>

            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">检验日期：</label>
                <input name="jyrq" id="jyrq" htmlEscape="false" class="form-control layer-date" pattern="yyyy-MM-dd" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"  placeholder="年-月-日"  datatype="*"/>
            </div>

        </div>
    </div>
</div>
<grid:grid id="Rgjl"
           url="${adminPath}/jygl/bgjy/ajaxRgjl" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <grid:column label="sys.common.key" hidden="true" name="bgrwfpid"/>

    <grid:column label="姓名" name="xm"/>
    <grid:column label="创建日期" name="cjrq"/>
    <grid:column label="检验日期" name="jyrq"/>
    <grid:column label="名称" name="mc"/>
    <grid:column label="设备名称" name="sbmc"/>
    <grid:column label="计划编号" name="jhbh"/>
    <grid:column label="零件名称" name="ljmc"/>
    <grid:column label="零件图号" name="ljth"/>
    <grid:column label="工艺大类名称" name="gydlmc"/>
    <grid:column label="工艺小类名称" name="gyxlmc"/>
    <grid:column label="应完成量" name="ywcl"/>
    <grid:column label="实际完成量" name="sjwcl"/>
    <grid:column label="每天任务量" name="mtrwl"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>

<script type="text/javascript">

    //更新到当前页
    function refreshTable2(gridId){
        var queryParams = {};
        var queryFields=$('#queryFields').val();
        var curpagenum = $("#"+gridId+"").jqGrid('getGridParam', 'page');
        queryParams['queryFields'] = queryFields;
        //普通的查询
        $('#' + gridId + "Query").find(":input").each(function() {
            var val = $(this).val();
            if (queryParams[$(this).attr('name')]) {
                val = queryParams[$(this).attr('name')] + "," + $(this).val();
            }
            queryParams[$(this).attr('name')] = val;
        });

        // 普通的查询
        $('#' + gridId + "Query").find(":input").each(function() {
            var condition = $(this).attr('condition');
            if (!condition) {
                condition = "";
            }
            var key = "query." + $(this).attr('name') + "||" + condition;
            queryParams[key] = queryParams[$(this).attr('name')];
        });
        //刷新
        //传入查询条件参数
        $("#"+gridId).jqGrid('setGridParam',{
            datatype:'json',
            postData:queryParams, //发送数据
            page:curpagenum
        }).trigger("reloadGrid"); //重新载入
    }

</script>
</body>
</html>
