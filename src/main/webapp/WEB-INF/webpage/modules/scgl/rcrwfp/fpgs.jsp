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
<h2>${rcrwfp.xm}</h2>
<input type="hidden" id="rcrwfpid" name="rcrwfpid" value="${rcrwfp.id}">
<input type="hidden" id="rggsid" name="rggsid" value="${RggsId}">
<input type="hidden" id="gsmc" name="gsmc" value="${rggs.gsmc}">
<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>常白班：</label>
                    </td>
                    <td>
                        <input type="radio" name="optionsRadios" id="optionsRadios1" value="cbb"0>（8小时）
                    </td>
                </tr>
                <tr class3201="form-group">
                    <td>
                        <label>白班：</label>
                    </td>
                    <td>
                        <input type="radio" name="optionsRadios" id="optionsRadios2" value="bb">（12小时）
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>夜班：</label>
                    </td>
                    <td>
                        <input type="radio" name="optionsRadios" id="optionsRadios3" value="yb">（12小时）
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>加班：</label>
                    </td>
                    <td>
                        <input name="jb" id="jb" htmlEscape="false" class="form-control" placeholder="请输入加班时间，默认为空" value="${rggs.jb}" onchange="checkJb()"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="col-md-3">
    </div>
</div>



<script type="text/javascript">
    window.onload=function(){
        var gsmc = $("#gsmc").val();
        if (gsmc=='常白班'){
            $("#optionsRadios1").attr("checked","checked");
        }
        if (gsmc=='白班'){
            $("#optionsRadios2").attr("checked","checked");
        }
        if (gsmc=='夜班'){
            $("#optionsRadios3").attr("checked","checked");
        }
    }
</script>


<script type="text/javascript">

    //校验
function checkJb() {
    var jb = $("#jb").val();
    var r = jb.match(/^[0-9]*$/);
    if(r == null){
        top.layer.alert("请输入数字");
        $("#jb").val("");
    }else{

    }
}

    //点击保存，保存数据
    function check() {
        var jb = $("#jb").val();
        var gsmc = $('input:radio:checked').val();
        var rcrwfpid = $("#rcrwfpid").val();
        var id = $("#rggsid").val();
        var gs = "";
        if(gsmc=='bb'){
            gsmc = '白班';
            gs = '12';
        }
        if(gsmc=='yb'){
            gsmc = '夜班';
            gs = '12';
        }
        if(gsmc=='cbb'){
            gsmc = '常白班';
            gs = '8';
        }
        $.ajax({
            type: "GET",
            url: "${adminPath}/scgl/rcrwfp/saveGs",
            data: {
                id: id,
                jb: jb,
                gsmc: gsmc,
                gs: gs,
                rcrwfpid: rcrwfpid
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
