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
<input type="hidden" id="bgrwfpid" name="bgrwfpid" value="${bgrwfp.id}">
<input type="hidden" id="bgmxid" name="bgmxid" value="${scglBgmx.id}">
<input type="hidden" id="bgrg" name="bgrg" value="${scglBgmx.bgrg}">

<h2>${bgrwfp.xm}</h2>
<input type="radio" name="rgbg" id="rg" value="rg" onclick="checkrgbg()"> 日工
<input type="radio" name="rgbg" id="bg" value="bg" onclick="checkrgbg()"> 包工
<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <div id="rgform">
            <form>
                <table class="table">
                    <tr class="form-group">
                        <td>
                            <label>日工日期：</label>
                        </td>
                        <td>
                            <input name="rgrq" id="rgrq" value="${bgrwfp.rq}" htmlEscape="false" class="form-control layer-date" pattern="yyyy-MM-dd" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"  placeholder="年-月-日"  datatype="*"/>
                        </td>
                    </tr>
                    <tr class="form-group">
                        <td>
                            <label>日工名称：</label>
                        </td>
                        <td>
                            <input name="rgmc" id="rgmc" htmlEscape="false" class="form-control" placeholder="请输入名称" value="${scglBgmx.bgmc}"/>
                        </td>
                    </tr>

                    <tr class="form-group">
                        <td>
                            <label>注释：</label>
                        </td>
                        <td>
                            <textarea id="rgzs" name="rgzs" class="form-control" rows="4" cols="30" placeholder="请注释">${scglBgmx.zs}</textarea>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <div id="bgform">
            <form>
                <table class="table">
                    <tr class="form-group">
                        <td>
                            <label>包工日期：</label>
                        </td>
                        <td>
                            <input name="rq" id="rq" value="${bgrwfp.rq}" htmlEscape="false" class="form-control layer-date" pattern="yyyy-MM-dd" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"  placeholder="年-月-日"  datatype="*"/>
                        </td>
                    </tr>
                    <tr class="form-group">
                        <td>
                            <label>包工名称：</label>
                        </td>
                        <td>
                            <input name="bgmc" id="bgmc" htmlEscape="false" class="form-control" placeholder="请输入承包名称" value="${scglBgmx.bgmc}"/>
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
                            <label>注释：</label>
                        </td>
                        <td>
                            <textarea id="zs" name="zs" class="form-control" rows="4" cols="30" placeholder="请对该承包进行注释">${scglBgmx.zs}</textarea>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

    </div>
    <div class="col-md-3">
    </div>
</div>


<script type="text/javascript">

    window.onload=function(){
        var bgrg = $("#bgrg").val();
        if (bgrg === '包工') {
            $("#bg").click();
            $("#rgform").hide();
            $("#bgform").show();
        } else {
            $("#rg").click();
            $("#rgform").show();
            $("#bgform").hide();
        }
    }
    
    function checkrgbg() {
        var val = $('input[name="rgbg"]:checked').val();
        if (val === 'rg') {
            $("#rgform").show();
            $("#bgform").hide();
        }
        else {
            $("#rgform").hide();
            $("#bgform").show();
        }

    }

    //校验
    function checkCbje() {
        var cbje = $("#cbje").val();
        var r = cbje.match(/^[0-9]*$/);
        if(r == null){
            top.layer.alert("请输入数字");
            $("#cbje").val("");
        }else{

        }
    }

    //点击保存，保存数据
    function check() {

            var val = $('input[name="rgbg"]:checked').val();
            var bgrwfpid = $("#bgrwfpid").val();
            var id = $("#bgmxid").val();

            if (val === 'rg') {
                var rgzs = $("#rgzs").val();
                if (rgzs==""||rgzs==null){
                    top.layer.alert("请输入注释，并注意对注释内容进行区分");
                } else {
                    var rgmc = $("#rgmc").val();
                    var rgrq = $("#rgrq").val();
                    $.ajax({
                        type: "GET",
                        url: "${adminPath}/scgl/bgrwfp/saveBgmx",
                        data: {
                            id: id,
                            zs: rgzs,
                            rq: rgrq,
                            bgmc: rgmc,
                            bgrwfpid: bgrwfpid,
                            bgrg: "日工"

                        },
                        success: function (data) {
                        }
                    });
                }
            }
            else {

                var zs = $("#zs").val();
                if (zs==""||zs==null){
                    top.layer.alert("请输入注释，并注意对注释内容进行区分");
                } else {
                    var bgmc = $("#bgmc").val();
                    var rq = $("#rq").val();
                    $.ajax({
                        type: "GET",
                        url: "${adminPath}/scgl/bgrwfp/saveBgmx",
                        data: {
                            id: id,
                            zs: zs,
                            rq: rq,
                            bgmc: bgmc,
                            bgrwfpid: bgrwfpid,
                            bgrg: "包工"
                        },
                        success: function (data) {
                        }
                    });
                }
            }





    }
</script>

</body>
</html>
