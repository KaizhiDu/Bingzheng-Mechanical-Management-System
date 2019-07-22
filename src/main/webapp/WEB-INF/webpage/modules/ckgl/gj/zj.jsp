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

<div class="row">
        <form>
            <table class="table">
                <input type="hidden" id="gjid" name="gjid" value="${ckglGj.id}">
                <tr class="form-group">
                    <b style="font-size: 20px">${ckglGj.fldl} - ${ckglGj.flxl} - ${ckglGj.gg}</b>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>员工：</label>
                    </td>
                    <td>
                        <input type="text" name="yg" id="yg" htmlEscape="false" class="form-control" placeholder="请输入员工姓名" />
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>现有库存：</label>
                    </td>
                    <td>
                        <input disabled type="number" name="xykc" id="xykc" htmlEscape="false" class="form-control" value="${ckglGj.kc}" />
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>增/减：</label>
                    </td>
                    <td>
                        <input type="number" name="zjsl" id="zjsl" htmlEscape="false" class="form-control" placeholder="请输入增减量" />
                    </td>
                </tr>

            </table>
        </form>
</div>


<script type="text/javascript">

    //检查预警量
    function checkZjsl(){
        var zjsl = $("#zjsl").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = zjsl.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#zjsl").val("");
        }
    }

    //点击保存，保存数据
    function check() {
        var gjid = $("#gjid").val();
        var zjsl = $("#zjsl").val();
        var yg = $("#yg").val();

        $.ajax({
            type: "GET",
            url: "${adminPath}/ckgl/gj/saveGjzj",
            data: {
                id: gjid,
                zjsl: zjsl,
                yg: yg

            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
