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
<h2>${grglYgxzgl.xm}</h2>
<div class="row">
    <div class="col-md-3">

    </div>
    <input id="ygxzglid" name="ygxzglid" type="hidden" value="${grglYgxzgl.id}">
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>奖励：</label>
                    </td>
                    <td>
                        <input name="jl" id="jl" htmlEscape="false" class="form-control" placeholder="请输入奖励" value="${grglYgxzgl.jl}"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>扣款：</label>
                    </td>
                    <td>
                        <input name="kk" id="kk" htmlEscape="false" class="form-control" placeholder="请输入扣款" value="${grglYgxzgl.kk}"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>备注：</label>
                    </td>
                    <td>
                        <textarea id="bz" name="bz" class="form-control" rows="3" cols="20" placeholder="请输入备注信息">${grglYgxzgl.bz}</textarea>
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
        var id = $("#ygxzglid").val();
        var jl = $("#jl").val();
        var kk = $("#kk").val();
        var bz = $("#bz").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/grgl/ygxzgl/saveYgxzgl",
            data: {
                id: id,
                jl: jl,
                kk: kk,
                bz: bz
            },
            success: function (data) {

            }
        });
    }
</script>

</body>
</html>
