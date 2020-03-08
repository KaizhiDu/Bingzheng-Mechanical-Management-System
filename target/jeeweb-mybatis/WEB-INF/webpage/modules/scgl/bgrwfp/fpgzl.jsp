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
<input type="hidden" id="oldrwl" name="oldrwl" value="${oldrwl}">
<input type="hidden" id="bgrwid" name="bgrwid" value="${bgrwid}">
<input type="hidden" id="xygzl" name="xygzl" value="${xygzl}">
<input type="hidden" id="bgrg" name="bgrg" value="${bgrg}">
<input type="hidden" id="bgrwfpid" name="bgrg" value="${bgrwfpid}">

<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">

        <div id="rgform">
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
                            <input name="rgrwl" id="rgrwl" htmlEscape="false" class="form-control" placeholder="请输入任务量"
                                   value="${xygzl}" onchange="checkRgrwl()"/>
                        </td>
                    </tr>
                    <tr class="form-group">
                        <td>
                            <label>每天任务量：</label>
                        </td>
                        <td>
                            <input name="mtrwl" id="mtrwl" htmlEscape="false" class="form-control"
                                   placeholder="请输入每天任务量" value="${mtrwl}" onchange="checkMtrwl()"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <div id="bgform">
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
                            <input name="rwl" id="rwl" htmlEscape="false" class="form-control" placeholder="请输入任务量"
                                   value="${xygzl}" onchange="checkRwl()"/>
                        </td>
                    </tr>
                    <tr class="form-group">
                        <td>
                            <label>单价：</label>
                        </td>
                        <td>
                            <input name="dj" id="dj" htmlEscape="false" class="form-control" placeholder="请输入单价"
                                   onchange="checkSl()" value="${dj}"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>


    </div>
    <div class="col-md-3">
    </div>
</div>


<script type="text/javascript">

    window.onload = function () {
        var bgrg = $("#bgrg").val();
        if (bgrg === '包工') {
            $("#rgform").hide();
            $("#bgform").show();
        } else {
            $("#rgform").show();
            $("#bgform").hide();
        }
    }

    function checkMtrwl() {
        var mtrwl = $("#mtrwl").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = mtrwl.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if (r == null) {
            top.layer.alert("请输入数字");
            $("#mtrwl").val("");
        }
    }

    //检查数量
    function checkSl() {
        var dj = $("#dj").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = dj.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if (r == null) {
            top.layer.alert("请输入数字");
            $("#dj").val("");
        }
    }

    function checkRgrwl() {
        var rgrwl = $("#rgrwl").val();
        var sysl = $("#sysl").val();
        var xygzl = $("#xygzl").val();
        var r = rgrwl.match(/^[0-9]*$/);
        //先判断是不是数字
        if (r == null) {
            top.layer.alert("请输入数字");
            $("#rgrwl").val("");
        } else {
            //在判断输入的数字是否大于剩余数量
            var a = parseInt(rgrwl, 10);
            var b = parseInt(sysl, 10);
            var c = 0;
            if (xygzl != "") {
                c = parseInt(xygzl, 10);
            }
            if (a > (b + c)) {
                top.layer.alert("任务量不能大于剩余数量");
                $("#rgrwl").val("");
            }
        }
    }


    //校验任务量
    function checkRwl() {
        var rwl = $("#rwl").val();
        var sysl = $("#sysl").val();
        var xygzl = $("#xygzl").val();
        var r = rwl.match(/^[0-9]*$/);
        //先判断是不是数字
        if (r == null) {
            top.layer.alert("请输入数字");
            $("#rwl").val("");
        } else {
            //在判断输入的数字是否大于剩余数量
            var a = parseInt(rwl, 10);
            var b = parseInt(sysl, 10);
            var c = 0;
            if (xygzl != "") {
                c = parseInt(xygzl, 10);
            }
            if (a > (b + c)) {
                top.layer.alert("任务量不能大于剩余数量");
                $("#rwl").val("");
            }
        }
    }

    //点击保存，保存数据
    function check() {
        var bgrg = $("#bgrg").val();
        var bgrwid = $("#bgrwid").val();
        if (bgrg === '包工') {
            var gzl = $("#rwl").val();
        } else {
            var gzl = $("#rgrwl").val();
        }
        var sysl = $("#sysl").val();
        var xygzl = $("#xygzl").val();
        var dj = $("#dj").val();
        var oldrwl = $("#oldrwl").val();
        var mtrwl = $("#mtrwl").val();
        var bgrwfpid = $("#bgrwfpid").val();

        $.ajax({
            type: "GET",
            url: "${adminPath}/scgl/bgrwfp/saveGzl",
            data: {
                bgrwid: bgrwid,
                gzl: gzl,
                xygzl: xygzl,
                dj: dj,
                sysl: sysl,
                oldrwl: oldrwl,
                mtrwl: mtrwl,
                bgrwfpid: bgrwfpid
            },
            success: function (data) {

            }
        });


    }
</script>

</body>
</html>
