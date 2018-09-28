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

<h4>任务管理</h4>

<div class="row">
    <div id="GybzglGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">计划编号：</label>
                <select name="jhbh" class="form-control" id="jhbh" onchange="cxlj()">
                    <option value="">请选择</option>
                    <c:forEach items="${jhglList}" var="jh">
                        <option value="${jh.jhid}">${jh.jhbh}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">零件名称：</label>
                <select name="ljmc" class="form-control" id="ljmc" onchange="cxgydl()">
                    <option value="">请选择</option>
                </select>
            </div>
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">工艺大类：</label>
                <select name="gydlmc" class="form-control" id="gydlmc" onchange="cxgyxl()">
                    <option value="">请选择</option>
                </select>
            </div>
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">工艺小类：</label>
                <select name="gyxlmc" class="form-control" id="gyxlmc">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
    </div>
</div>

<grid:grid id="Gybzgl"
           url="${adminPath}/scgl/gybzgl/ajaxGybzglList" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>

    <grid:column label="计划编号" name="jhbh"/>
    <grid:column label="零件名称" name="ljmc"/>
    <grid:column label="工艺大类" name="gydlmc"/>
    <grid:column label="工艺小类" name="gyxlmc"/>
    <grid:column label="数量" name="sl"/>
    <grid:column label="剩余数量" name="sysl"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>

<script type="text/javascript">

    //根据计划ID查询零件信息
    function cxlj() {
        var jhbh = $("#jhbh").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/scgl/gybzgl/cxLj?jhid="+jhbh,
            success : function (data) {
                $("#ljmc").text("");
                $("#ljmc").append(
                    "<option value=''>请选择</option>"
                );
                for (var i=0;i<data.length;i++){
                    var jh = data[i];
                    $("#ljmc").append(
                        "<option value='"+jh.ljid+"'>"+jh.ljmc+"</option>"
                    );
                }

            }
        });
    }

    //根据零件ID查到所有下属工艺大类
    function cxgydl() {
        var ljmc = $("#ljmc").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/scgl/gybzgl/cxGydl?ljid="+ljmc,
            success : function (data) {
                $("#gydlmc").text("");
                $("#gydlmc").append(
                    "<option value=''>请选择</option>"
                );
                for (var i=0;i<data.length;i++){
                    var gydl = data[i];
                    $("#gydlmc").append(
                        "<option value='"+gydl.gydlbzid+"'>"+gydl.gydlmc+"</option>"
                    );
                }

            }
        });
    }

    //根据编制大类ID查到所有下属的编制小类
    function cxgyxl() {
        var gydlmc = $("#gydlmc").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/scgl/gybzgl/cxGyxl?gydlbzid="+gydlmc,
            success : function (data) {
                $("#gyxlmc").text("");
                $("#gyxlmc").append(
                    "<option value=''>请选择</option>"
                );
                for (var i=0;i<data.length;i++){
                    var gyxl = data[i];
                    $("#gyxlmc").append(
                        "<option value='"+gyxl.id+"'>"+gyxl.gyxlmc+"</option>"
                    );
                }

            }
        });
    }

</script>

<script type="text/javascript">
    function check(title, url, gridId, id, width, height, tipMsg) {
        //获取选中行的id数组
        var idsArray = $("#GybzglGrid").jqGrid("getGridParam", "selarrrow")
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
            url: "${adminPath}/scgl/bgrwfp/saveRw?ids="+ids+"&fpsbid=${fpsbid}",
            success: function (data) {
                refreshTable(gridId);
            }
        });
    }
</script>

</body>
</html>
