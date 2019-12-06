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
<body class="container">
<input name="dlszid" id="dlszid" type="hidden" value="${sbglSbflgl.id}">
<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>分类代码：</label>
                    </td>
                    <td>
                        <input name="fldm" id="fldm" htmlEscape="false" class="form-control" placeholder="请输入分类代码" value="${sbglSbflgl.fldm}"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>分类名称：</label>
                    </td>
                    <td>
                        <input name="flmc" id="flmc" htmlEscape="false" class="form-control" placeholder="请输入分类名称" value="${sbglSbflgl.flmc}"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>状态：</label>
                    </td>
                    <td>
                        <select id="zt" name="zt" class="form-control">
                            <c:if test="${sbglSbflgl.zt==1}"><option value="1">可用</option><option value="0">不可用</option></c:if>
                            <c:if test="${sbglSbflgl.zt==0}"><option value="0">不可用</option><option value="1">可用</option></c:if>
                        </select>
                    </td>
                </tr>

            </table>
        </form>
    </div>
    <div class="col-md-3">
    </div>
</div>


<script type="text/javascript">
    //点击保存，保存数据
    function check() {
        var dlszid = $("#dlszid").val();
        var fldm = $("#fldm").val();
        var flmc = $("#flmc").val();
        var zt = $("#zt").val();

        $.ajax({
            type: "GET",
            url: "${adminPath}/sbgl/sbflgl/saveSbflgl",
            data: {
                id: dlszid,
                fldm: fldm,
                flmc: flmc,
                zt: zt
            },
            success: function (data) {

            }
        });
    }
</script>

</body>
</html>
