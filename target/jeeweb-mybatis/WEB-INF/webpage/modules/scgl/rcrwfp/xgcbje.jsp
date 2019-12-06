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
<h2>${bgrwfp.xm}</h2>
<input type="hidden" id="bgrwfpid" name="bgrwfpid" value="${bgrwfp.id}">
<input type="hidden" id="bgmxid" name="bgmxid" value="${scglBgmx.id}">
<input type="hidden" id="ylje" name="ylje" value="${scglBgmx.cbje}">

<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>包工日期：</label>
                    </td>
                    <td>
                        ${bgrwfp.rq}
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>包工名称：</label>
                    </td>
                    <td>
                        ${scglBgmx.bgmc}
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>承包金额：</label>
                    </td>
                    <td>
                        ${scglBgmx.cbje}
                    </td>
                </tr>

                <tr class="form-group">
                    <td>
                        <label>减去数：</label>
                    </td>
                    <td>
                        <input name="cbje" id="cbje" htmlEscape="false" class="form-control" placeholder="请输入要减去的金额" onchange="checkCbje()"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="col-md-3">
    </div>
</div>


<script type="text/javascript">

    //校验
    function checkCbje() {
        var cbje = $("#cbje").val();
        var ylje = $("#ylje").val();
        var r = cbje.match(/^\d+(\.\d+)?$/);
        if(r == null){
            top.layer.alert("请输入数字");
            $("#cbje").val("");
        }else{
            if (parseFloat(cbje)>parseFloat(ylje)){
                top.layer.alert("不能大于原始金额");
                $("#cbje").val("");
            }
        }
    }

    //点击保存，保存数据
    function check() {
            var bgmxid = $("#bgmxid").val();
            var cbje = $("#cbje").val();
            $.ajax({
                type: "GET",
                url: "${adminPath}/scgl/rcrwfp/saveBgmx",
                data: {
                    id: bgmxid,
                    cbje: cbje
                },
                success: function (data) {


                }
            });


    }
</script>

</body>
</html>
