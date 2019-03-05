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
<h2>${scglRcrwfp.xm}</h2>
<input type="hidden" id="rcrwfpid" name="rcrwfpid" value="${scglRcrwfp.id}">

<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>备注：</label>
                    </td>
                    <td>
                        <input name="bz" id="bz" htmlEscape="false" class="form-control" placeholder="请输入备注" value="${scglRcrwfp.bz}"/>
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
        var rcrwfpid = $("#rcrwfpid").val();
        var bz = $("#bz").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/scgl/rcrwfp/saveXgbz",
            data: {
                id: rcrwfpid,
                bz: bz
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
