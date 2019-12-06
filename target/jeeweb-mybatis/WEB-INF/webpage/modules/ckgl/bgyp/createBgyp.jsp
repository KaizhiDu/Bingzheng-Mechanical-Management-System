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
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>名称：</label>
                    </td>
                    <td>
                        <input name="gg" id="gg" htmlEscape="false" class="form-control" placeholder="请输入办公用品名"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>单位：</label>
                    </td>
                    <td>
                        <input name="dw" id="dw" htmlEscape="false" class="form-control" placeholder="请输入单位"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>预警量：</label>
                    </td>
                    <td>
                        <input name="yjkc" id="yjkc" htmlEscape="false" class="form-control" placeholder="请输入预警量" onchange="checkYjkc()"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>备注：</label>
                    </td>
                    <td>
                        <textarea id="bz" name="bz" class="form-control" rows="3" cols="20" placeholder="请输入备注信息"></textarea>

                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="col-md-3">
    </div>
</div>


<script type="text/javascript">

    //检查预警量
    function checkYjkc(){
        var yjkc = $("#yjkc").val();
       // var r = yjkc.match(/^[0-9]*$/);
        var r = yjkc.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#yjkc").val("");
        }
    }

    //点击保存，保存数据
    function check() {
        var gg = $("#gg").val();
        var dw = $("#dw").val();
        var yjkc = $("#yjkc").val();
        var bz = $("#bz").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/ckgl/bgyp/saveBgyp",
            data: {
                id: null,
                gg: gg,
                dw: dw,
                yjkc: yjkc,
                bz: bz

            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
