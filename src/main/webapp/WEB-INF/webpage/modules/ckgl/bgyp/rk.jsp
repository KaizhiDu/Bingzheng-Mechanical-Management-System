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
<input type="hidden" id="bgypid" name="bgypid" value="${ckglBgyp.id}">
<h2> ${ckglBgyp.gg}</h2>
<hr>
<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>采购：</label>
                    </td>
                    <td>
                        <input name="cg" id="cg" htmlEscape="false" class="form-control" placeholder="请输入采购员姓名"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>入库数量：</label>
                    </td>
                    <td>
                        <input name="rksl" id="rksl" htmlEscape="false" class="form-control" placeholder="请输入入库数量" onchange="checkSl()"/>
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
    function checkSl(){
        var rksl = $("#rksl").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = rksl.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#rksl").val("");
        }
    }

    //点击保存，保存数据
    function check() {
        var bgypid = $("#bgypid").val();
        var cg = $("#cg").val();
        var rksl = $("#rksl").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/ckgl/bgyp/saveBgypkc",
            data: {
                bgypid: bgypid,
                cg: cg,
                rksl: rksl
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
