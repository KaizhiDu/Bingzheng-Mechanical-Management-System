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
<input type="hidden" name="bzjid" id="bzjid" value="${scjhglBzjgl.id}">
<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>规格：</label>
                    </td>
                    <td>
                        <input name="gg" id="gg" htmlEscape="false" class="form-control" placeholder="请输入规格" value="${scjhglBzjgl.gg}"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>单位：</label>
                    </td>
                    <td>
                        <input name="dw" id="dw" htmlEscape="false" class="form-control" placeholder="请输入单位" value="${scjhglBzjgl.dw}"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>数量：</label>
                    </td>
                    <td>
                        <input name="sl" id="sl" htmlEscape="false" class="form-control" placeholder="请输入数量" value="${scjhglBzjgl.sl}" onchange="checkSl()"/>
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
    function checkSl(){
        var sl = $("#sl").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = sl.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#sl").val("");
        }
    }

    //根据大类id拿到所有小类名
    function getFlxl() {
        var fldl = $("#fldl").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/ckgl/bzj/getFlxl?fldl="+fldl,
            success : function (data) {
                $("#flxl").text("");
                $("#flxl").append(
                    "<option value=''>请选择</option>"
                );
                for (var i=0;i<data.length;i++){
                    var xl = data[i];
                    $("#flxl").append(
                        "<option value='"+xl.xlmc+"'>"+xl.xlmc+"</option>"
                    );
                }

            }
        });
    }

    //点击保存，保存数据
    function check() {
        var gg = $("#gg").val();
        var dw = $("#dw").val();
        var sl = $("#sl").val();
        var bzjid = $("#bzjid").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/scjhgl/bzjgl/updateBzj",
            data: {
                id: bzjid,
                gg: gg,
                dw: dw,
                sl: sl
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
