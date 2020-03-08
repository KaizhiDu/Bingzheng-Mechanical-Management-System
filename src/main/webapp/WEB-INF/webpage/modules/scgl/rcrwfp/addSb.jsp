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
<input type="hidden" id="rq" name="rq" value="${rq}">
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

        </div>
    </div>
</div>
<grid:grid id="Sbgl"
           url="${adminPath}/sbgl/sbgl/ajaxListSbgl?addSb=1&rq=${rq}" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>

    <grid:column label="日期" name="rq"/>
    <grid:column label="设备编号" name="sbbh"/>
    <grid:column label="设备类型" name="ssdl"/>
    <grid:column label="是否占用" name="sfky"/>


    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>


<script type="text/javascript">
    function check(title, url, gridId, id, width, height, tipMsg) {
        var rq = $("#rq").val();
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
            url: "${adminPath}/scgl/rcrwfp/saveSb?ids="+ids+"&rcrwfpid=${rcrwfpid}&rq="+rq,
            success: function (data) {
                refreshTable2(gridId);
            }
        });
    }

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
