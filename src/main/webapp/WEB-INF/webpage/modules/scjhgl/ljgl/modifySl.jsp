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
<h2>${scjhglLjgl.ljmc}</h2>
<input type="hidden" id="lbjid" name="lbjid" value="${scjhglLjgl.id}">
<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>零部件数量：</label>
                    </td>
                    <td>
                        <input name="lbjsl" id="lbjsl" htmlEscape="false" class="form-control" placeholder="请输入数量" value="${scjhglLjgl.sl}" onchange="checkSl()"/>
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
        var lbjsl = $("#lbjsl").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = lbjsl.match(/^[0-9]*$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#lbjsl").val("");
        }
    }

    //点击保存，保存数据
    function check() {
        var lbjid = $("#lbjid").val();
        var lbjsl = $("#lbjsl").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/scjhgl/ljgl/saveSl",
            data: {
                id: lbjid,
                lbjsl: lbjsl
            },
            success: function (data) {

            }
        });
    }
</script>

</body>
</html>