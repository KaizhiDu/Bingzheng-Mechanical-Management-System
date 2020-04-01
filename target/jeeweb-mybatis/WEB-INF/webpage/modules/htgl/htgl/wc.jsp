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
<input type="hidden" id="htmxId" name="htmxId" value="${htmxId}">

<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>日期：</label>
                    </td>
                    <td>
                        <input name="rq" id="rq" htmlEscape="false"
                               class="form-control layer-date" pattern="yyyy-MM-dd"
                               onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" placeholder="年-月-日"
                               value="${currentDate}"
                               datatype="*"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>完成数量：</label>
                    </td>
                    <td>
                        <input name="wcsl" id="wcsl" htmlEscape="false" class="form-control" placeholder="请输入完成数量" onchange="checkWcsl()"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>备注：</label>
                    </td>
                    <td>
                        <input name="bz" id="bz" htmlEscape="false" class="form-control" placeholder="请输入备注"/>
                    </td>
                </tr>

            </table>
        </form>
    </div>
    <div class="col-md-3">
    </div>
</div>


<script type="text/javascript">

    //检查数量
    function checkWcsl(){
        var wcsl = $("#wcsl").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = wcsl.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            $("#wcsl").val("");
            top.layer.alert("请输入数字");
        }
    }

    //点击保存，保存数据
    function check() {
        var htmxId = $("#htmxId").val();
        var rq = $("#rq").val();
        var wcsl = $("#wcsl").val();
        var bz = $("#bz").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/htgl/htgl/saveWc",
            data: {
                htmxid: htmxId,
                rq: rq,
                wcsl: wcsl,
                bz: bz
            },
            success: function (data) {

            }
        });
    }
</script>

</body>
</html>
