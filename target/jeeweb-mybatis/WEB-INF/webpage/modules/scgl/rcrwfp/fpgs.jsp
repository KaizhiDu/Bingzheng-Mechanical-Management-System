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
<input type="hidden" id="zybf" name="zybf" value="${rggs.zybf}">
<input type="hidden" id="rq" name="rq" value="${rcrwfp.rq}">
<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>工时：</label>
                    </td>
                    <td>
                        <input name="gs" id="gs" htmlEscape="false" class="form-control" placeholder="请输入工时，默认为空" value="${rggs.gs}" onchange="checkGs()"/>
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
<%--                <tr class3201="form-group">--%>
<%--                    <td>--%>
<%--                        <label>10元补助：</label>--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <input type="radio" name="optionsRadios" id="optionsRadios1" value="10">--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr class="form-group">--%>
<%--                    <td>--%>
<%--                        <label>20元补助：</label>--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <input type="radio" name="optionsRadios" id="optionsRadios2" value="20">--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr class="form-group">--%>
<%--                    <td>--%>
<%--                        <label>30元补助：</label>--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <input type="radio" name="optionsRadios" id="optionsRadios3" value="30">--%>
<%--                    </td>--%>
<%--                </tr>--%>
            </table>
        </form>
    </div>
    <div class="col-md-3">
    </div>
</div>



<%--<script type="text/javascript">--%>
<%--    window.onload = function(){--%>
<%--        var zybf = $("#zybf").val();--%>
<%--        if (zybf=='10'){--%>
<%--            $("#optionsRadios1").attr("checked","checked");--%>
<%--        }--%>
<%--        if (zybf=='20'){--%>
<%--            $("#optionsRadios2").attr("checked","checked");--%>
<%--        }--%>
<%--        if (zybf=='30'){--%>
<%--            $("#optionsRadios3").attr("checked","checked");--%>
<%--        }--%>
<%--    };--%>
<%--</script>--%>


<script type="text/javascript">

    //校验
    function checkJb() {
        var jb = $("#jb").val();
        var r = jb.match(/^\d+(\.\d+)?$/);
        if(r == null){
            top.layer.alert("请输入数字");
            $("#jb").val("");
        }else{

        }
    }

    //校验
    function checkGs() {
        var gs = $("#gs").val();
        var r = gs.match(/^\d+(\.\d+)?$/);
        if(r == null){
            top.layer.alert("请输入数字");
            $("#gs").val("");
        }else{

        }
    }

    //点击保存，保存数据
    function check() {
        //var zybf = $("input[name='optionsRadios']:checked").val();
        var jb = $("#jb").val();
        var rcrwfpid = $("#rcrwfpid").val();
        var id = $("#rggsid").val();
        var rq = $("#rq").val();
        var gs = $("#gs").val();
//        if (zybf='undefined'){
//            zybf = '0';
//        }
        $.ajax({
            type: "GET",
            url: "${adminPath}/scgl/rcrwfp/saveGs",
            data: {
                id: id,
                jb: jb,
                gs: gs,
                rq: rq,
                //zybf: zybf,
                rcrwfpid: rcrwfpid
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
