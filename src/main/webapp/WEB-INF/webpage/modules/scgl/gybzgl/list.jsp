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

<h4>工艺编制概览</h4>

<div class="row">
    <div id="GybzglGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">计划名称：</label>
                <select name="jhbh" class="form-control" id="jhbh">
                    <option value="">请选择</option>
                    <c:forEach items="${jhglList}" var="jh">
                        <option value="${jh.jhid}">${jh.jhbh}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">零部件名称：</label>
                <input name="ljmc" id="ljmc" htmlEscape="false" class="form-control" placeholder="模糊搜索零部件名称"/>
            </div>
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">零部件图号：</label>
                <input name="ljth" id="ljth" htmlEscape="false" class="form-control" placeholder="模糊搜索零部件图号"/>
            </div>
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">工艺大类名称：</label>
                <input name="gydlmc" id="gydlmc" htmlEscape="false" class="form-control" placeholder="模糊搜索工艺大类名称"/>
            </div>
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">正在加工：</label>
                <select name="zzjg" class="form-control" id="zzjg">
                    <option value="">请选择</option>
                    <option value="1">正在加工</option>
                </select>
            </div>
        </div>
    </div>
</div>

<grid:grid id="Gybzgl"
           url="${adminPath}/scgl/gybzgl/ajaxGybzglList" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>

    <grid:column label="计划名称" name="jhbh"/>
    <grid:column label="零部件名称" name="ljmc"/>
    <grid:column label="零部件图号" name="ljth"/>
    <grid:column label="工艺大类" name="gydlmc"/>
    <grid:column label="工艺小类" name="gyxlmc"/>
    <grid:column label="数量" name="sl"/>
    <grid:column label="已分配数量" name="yfpsl"/>
    <grid:column label="可分配数量" name="kfpsl"/>
    <grid:column label="剩余数量" name="sysl"/>

    <grid:toolbar function="exportGy" icon="fa fa-file-excel-o" title="导出" btnclass="btn-warning"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>

<script type="text/javascript">

    function exportGy(){
        var jhbh = $("#jhbh").val();
        var ljmc = $("#ljmc").val();
        var ljth = $("#ljth").val();
        var gydlmc = $("#gydlmc").val();
        $.ajax({
            type: "get",
            url: "${adminPath}/scgl/gybzgl/exportGy",
            data: {
                jhbh: jhbh,
                ljmc: ljmc,
                ljth: ljth,
                gydlmc: gydlmc
            },
            success: function (data) {
                layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
                top.layer.alert("导出成功，请在D:/bingzhengjixie文件夹下查看", {icon: 0, title:'提示'});
            }
        });

    }

    //根据计划ID查询零部件信息
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

    //根据零部件ID查到所有下属工艺大类
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

</body>
</html>
