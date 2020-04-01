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
<input type="hidden" id="htid" name="htid" value="${htid}">

<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>明细名称：</label>
                    </td>
                    <td>
                        <input name="mc" id="mc" htmlEscape="false" class="form-control" placeholder="请输入名称"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>型号：</label>
                    </td>
                    <td>
                        <input name="xh" id="xh" htmlEscape="false" class="form-control" placeholder="请输入型号"/>
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
                        <label>单价：</label>
                    </td>
                    <td>
                        <input name="dj" id="dj" htmlEscape="false" class="form-control" placeholder="请输入单价" onchange="checkDj()"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>数量：</label>
                    </td>
                    <td>
                        <input name="sl" id="sl" htmlEscape="false" class="form-control" placeholder="请输入数量" onchange="checkSl()"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>金额：</label>
                    </td>
                    <td>
                        <input disabled name="je" id="je" htmlEscape="false" class="form-control"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>备注：</label>
                    </td>
                    <td>
                        <input name="bz" id="bz" htmlEscape="false" class="form-control" placeholder="请输入备注"/>
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
    function checkDj(){
        var dj = $("#dj").val();
        var sl = $("#sl").val() ? parseFloat($("#sl").val()) : 0;
        // var r = yjkc.match(/^[0-9]*$/);
        var r = dj.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            $("#dj").val("");
            top.layer.alert("请输入数字");
        } else {
            var je = dj * sl;
            $("#je").val(je);
        }
    }

    //检查数量
    function checkSl(){
        var sl = $("#sl").val();
        var dj = $("#dj").val() ? parseFloat($("#dj").val()): 0;
        // var r = yjkc.match(/^[0-9]*$/);
        var r = sl.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            $("#sl").val("");
            top.layer.alert("请输入数字");
        } else {
            var je = dj * sl;
            $("#je").val(je);
        }
    }



    //点击保存，保存数据
    function check() {
        var mc = $("#mc").val();
        var htid = $("#htid").val();
        var xh = $("#xh").val();
        var dw = $("#dw").val();
        var dj = $("#dj").val();
        var sl = $("#sl").val();
        var je = $("#je").val();
        var bz = $("#bz").val();

        if (dj && sl) {
             $.ajax({
                 type: "GET",
                 url: "${adminPath}/htgl/htgl/saveHtmx",
                 data: {
                     mc: mc,
                     htid: htid,
                     xh: xh,
                     dw: dw,
                     dj: dj,
                     sl: sl,
                     je: je,
                     bz: bz
                 },
                 success: function (data) {
                 }
             });
         }
         else {
             return;
         }

    }
</script>

</body>
</html>
