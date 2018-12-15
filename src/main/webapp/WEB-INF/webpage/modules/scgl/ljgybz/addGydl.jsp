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

<h4>编制工艺</h4>

<input id="ljid" name="ljid" type="hidden" value="${ljid}">

<div class="row">
    <div id="GymbszGridQuery" class="col-md-12">
        <div class="form-inline">

        </div>
    </div>
</div>
<grid:grid id="Gymbsz"
           url="${adminPath}/scgl/gymbsz/ajaxGymbszList" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>

    <grid:column label="工艺大类代码" name="gydldm"/>
    <grid:column label="工艺大类名称" name="gydlmc"/>
    <grid:column label="是否启用" name="sfqy" dict="SBZT" formatterValue=""/>

    <%--<grid:toolbar function="search"/>--%>
    <%--<grid:toolbar function="reset"/>--%>
</grid:grid>


<script type="text/javascript">
    function check(title, url, gridId, id, width, height, tipMsg){
        //获取选中行的id数组
        var ljid = $("#ljid").val();
        var idsArray = $("#GymbszGrid").jqGrid("getGridParam", "selarrrow")
        if (idsArray.length>0){
            var ids = "";
            for (var i=0;i<idsArray.length;i++){
                if (i==0){
                    ids = idsArray[i];
                }
                else{
                    ids = ids + "," + idsArray[i];
                }
            }

            $.ajax({
                type: "GET",
                url: "${adminPath}/scgl/ljgybz/saveGydlbz?ids="+ids+"&ljid="+ljid,
                success: function (data) {
                    refreshTable2(gridId);
                    layer.msg(data.msg);
                }
            });

        }
        else{
            top.layer.alert('请选择要添加的工艺大类!', {icon: 0, title:'警告'});
            return;
        }
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
