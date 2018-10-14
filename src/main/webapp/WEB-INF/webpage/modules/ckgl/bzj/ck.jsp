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
<input type="hidden" id="bzjid" name="bzjid" value="${ckglBzj.id}">
<input type="hidden" id="kc" name="kc" value="${ckglBzj.kc}">
<h2>${ckglBzj.fldl} - ${ckglBzj.flxl} - ${ckglBzj.gg} </h2>
<h4>库存：${ckglBzj.kc}</h4>
<hr>
<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>领用：</label>
                    </td>
                    <td>
                        <input name="ly" id="ly" htmlEscape="false" class="form-control" placeholder="请输入领用人姓名"/>
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
        var kc = $("#kc").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = cksl.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#cksl").val("");
        }
        else{
            if (parseFloat(cksl)>parseFloat(kc)){
                top.layer.alert("出库数量大于库存");
                $("#cksl").val("");
            }

        }
    }

    //点击保存，保存数据
    function check() {
        var bzjid = $("#bzjid").val();
        var ly = $("#ly").val();
        var cksl = $("#cksl").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/ckgl/bzj/saveBzjkcck",
            data: {
                bzjid: bzjid,
                ly: ly,
                cksl: cksl
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
