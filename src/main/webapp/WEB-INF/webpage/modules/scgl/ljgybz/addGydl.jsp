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

<input id="jhid" name="jhid" type="hidden" value="${jhid}">

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
        var jhid = $("#jhid").val();
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
                url: "${adminPath}/scgl/ljgybz/saveGydlbz?ids="+ids+"&jhid="+jhid,
                success: function (data) {
                    refreshTable(gridId);
                    layer.msg(data.msg);
                }
            });

        }
        else{
            top.layer.alert('请选择要删除的数据!', {icon: 0, title:'警告'});
            return;
        }
    }
</script>
</body>
</html>
