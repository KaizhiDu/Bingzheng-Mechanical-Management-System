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
<input type="hidden" id="bcpid" name="bcpid" value="${ckglBcp.id}">
<input type="hidden" id="kc" name="kc" value="${ckglBcp.rksl}">
<input type="hidden" id="syrksl" name="syrksl" value="${ckglBcp.syrksl}">
<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>零部件名称：</label>
                    </td>
                    <td>
                        ${ckglBcp.lbjmc}
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>零部件图号：</label>
                    </td>
                    <td>
                        ${ckglBcp.lbjth}
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>库存数量：</label>
                    </td>
                    <td>
                        ${ckglBcp.syrksl}
                    </td>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>出库数量：</label>
                    </td>
                    <td>
                        <input name="cksl" id="cksl" htmlEscape="false" class="form-control" placeholder="请输入出库数量" onchange="checkSl()"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>领用人：</label>
                    </td>
                    <td>
                        <input name="lyr" id="lyr" htmlEscape="false" class="form-control" placeholder="请输入领用人姓名"/>
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
        var cksl = $("#cksl").val();
        var syrksl = $("#syrksl").val();
        var r = cksl.match(/^[0-9]*$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#cksl").val("");
        }
        else{
            if (parseFloat(cksl)>parseFloat(syrksl)){
                top.layer.alert("出库数量大于库存");
                $("#cksl").val("");
            }

        }
    }

    //点击保存，保存数据
    function check() {
        var bcpid = $("#bcpid").val();
        var cksl = $("#cksl").val();
        var lyr = $("#lyr").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/ckgl/bcp/ywcbcp/saveCk",
            data: {
                id: bcpid,
                cksl: cksl,
                lyr: lyr
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
