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
<input type="hidden" id="gjid" name="gjid" value="${ckglGj.id}">
<input type="hidden" id="zsl" name="zsl" value="${ckglGj.zsl}">
<input type="hidden" id="kc" name="kc" value="${ckglGj.kc}">
<h2>${ckglGj.fldl} - ${ckglGj.flxl} - ${ckglGj.gg}</h2>
<hr>
<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>归还：</label>
                    </td>
                    <td>
                        <input name="cg" id="cg" htmlEscape="false" class="form-control" placeholder="请输入归还人姓名"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>归还数量：</label>
                    </td>
                    <td>
                        <input name="rksl" id="rksl" htmlEscape="false" class="form-control" placeholder="请输入归还数量" onchange="checkSl()"/>
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
        var gjid = $("#gjid").val();
        var zsl = $("#zsl").val();
        var kc = $("#kc").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = rksl.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#rksl").val("");
        }
        else{
            //归还数量+库存<=总数量
            $.ajax({
                type: "GET",
                url: "${adminPath}/ckgl/gj/saveCheckZsl",
                data: {
                    gjid: gjid,
                    rksl: rksl,
                    zsl: zsl,
                    kc: kc
                },
                success: function (data) {
                    if (data==0){
                        top.layer.alert("归还数量和库存之和，大于总数量");
                        $("#rksl").val("");
                    }

                }
            });

        }
    }

    //点击保存，保存数据
    function check() {
        var gjid = $("#gjid").val();
        var cg = $("#cg").val();
        var rksl = $("#rksl").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/ckgl/gj/saveGjkc",
            data: {
                gjid: gjid,
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
