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
<input type="hidden" id="ljgybzid" name="ljgybzid" value="${ljgybzid}">
<input type="hidden" id="sysl" name="sysl" value="${sysl}">
<input type="hidden" id="bgrwid" name="bgrwid" value="${bgrwid}">
<input type="hidden" id="xygzl" name="xygzl" value="${xygzl}">
<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>剩余数量：</label>
                    </td>
                    <td>
                        ${sysl}
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>任务量：</label>
                    </td>
                    <td>
                        <input name="rwl" id="rwl" htmlEscape="false" class="form-control" placeholder="请输入任务量" value="${xygzl}" onchange="checkRwl()"/>
                    </td>
                </tr>


            </table>
        </form>
    </div>
    <div class="col-md-3">
    </div>
</div>


<script type="text/javascript">

    //校验任务量
    function checkRwl(){
        var rwl = $("#rwl").val();
        var sysl = $("#sysl").val();
        var xygzl = $("#xygzl").val();
        var r = rwl.match(/^[0-9]*$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#rwl").val("");
        }else{
            //在判断输入的数字是否大于剩余数量
            var a = parseInt(rwl,10);
            var b = parseInt(sysl,10);
            var c = parseInt(xygzl,10);
            if (a>(b+c)){
                top.layer.alert("任务量不能大于剩余数量");
                $("#rwl").val("");
            }
        }
    }

    //点击保存，保存数据
    function check() {
        var bgrwid = $("#bgrwid").val();
        var gzl = $("#rwl").val();
        var xygzl = $("#xygzl").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/scgl/bgrwfp/saveGzl",
            data: {
                bgrwid: bgrwid,
                gzl: gzl,
                xygzl: xygzl
            },
            success: function (data) {

            }
        });
    }
</script>

</body>
</html>
