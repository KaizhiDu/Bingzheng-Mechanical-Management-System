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
                        <label>日期：</label>
                    </td>
                    <td>
                        <input name="rq" id="rq" htmlEscape="false" class="form-control layer-date" pattern="yyyy-MM-dd" value="${day}" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"  placeholder="年-月-日"  datatype="*"/>

                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>开发票：</label>
                    </td>
                    <td>
                        <input name="fp" id="fp" htmlEscape="false" class="form-control" placeholder="请输入发票额" onchange="checkJe()"/>
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
    function checkJe(){
        var fp = $("#fp").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = fp.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#fp").val("");
        }
    }

    //点击保存，保存数据
    function check() {
        var htid = $("#htid").val();
        var rq = $("#rq").val();
        var fp = $("#fp").val();
        var bz = $("#bz").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/htgl/gs/saveFp",
            data: {
                rq: rq,
                fp: fp,
                bz: bz,
                htid: htid
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
