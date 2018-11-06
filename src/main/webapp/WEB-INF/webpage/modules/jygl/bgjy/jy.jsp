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
<input type="hidden" id="ywcl" name="ywcl" value="${ywcl}">
<input type="hidden" id="bgrwid" name="bgrwid" value="${bgrwid}">
<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>应完成量：</label>
                    </td>
                    <td>
                        ${ywcl}
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>实际完成量：</label>
                    </td>
                    <td>
                        <input name="sjwcl" id="sjwcl" htmlEscape="false" class="form-control" value="${sjwcl}" placeholder="请输入实际完成数量" onchange="checkRwl()"/>
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
        var sjwcl = $("#sjwcl").val();
        var ljgybzid = $("#ljgybzid").val();
        var bgrwid = $("#bgrwid").val();
        var r = sjwcl.match(/^[0-9]*$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#sjwcl").val("");
        }else{
            //判断输入的数字是否大于剩余数量
            $.ajax({
                type: "GET",
                url: "${adminPath}/jygl/bgjy/sfdysysl",
                data: {
                    sjwcl: sjwcl,
                    ljgybzid: ljgybzid,
                    bgrwid: bgrwid
                },
                success: function (data) {
                    if (data==1){
                        top.layer.alert("实际完成量大于剩余数量");
                        $("#sjwcl").val("");
                    }

                }
            });
        }
    }

    //点击保存，保存数据
    function check() {
        var sjwcl = $("#sjwcl").val();
        var ljgybzid = $("#ljgybzid").val();
        var bgrwid = $("#bgrwid").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/jygl/bgjy/saveWcl",
            data: {
                sjwcl: sjwcl,
                ljgybzid: ljgybzid,
                bgrwid: bgrwid
            },
            success: function (data) {

            }
        });
    }
</script>

</body>
</html>
