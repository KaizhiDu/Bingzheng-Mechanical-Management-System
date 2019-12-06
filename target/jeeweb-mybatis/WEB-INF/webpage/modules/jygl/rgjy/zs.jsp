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
<input type="hidden" id="rgjyid" name="rgjyid" value="${jyglRgjy.id}">
<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>注释：</label>
                    </td>
                    <td>
                        <textarea id="zs" name="zs" class="form-control" rows="3" cols="20" placeholder="请输入备注信息">${jyglRgjy.zs}</textarea>

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
        var rgjyid = $("#rgjyid").val();
        var zs = $("#zs").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/jygl/rgjy/saveZs",
            data: {
                id: rgjyid,
                zs: zs
            },
            success: function (data) {

            }
        });
    }
</script>

</body>
</html>
