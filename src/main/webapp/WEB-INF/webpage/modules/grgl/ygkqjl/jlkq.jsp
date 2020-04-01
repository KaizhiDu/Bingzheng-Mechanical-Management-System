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
<h2>${grgl.name}</h2>
<hr>
<input type="hidden" id="ygid" name="ygid" value="${grgl.id}">
<input type="hidden" id="ygkqjlid" name="ygkqjlid" value="${ygkqjl.id}">
<input type="hidden" id="jbb" name="jbb" value="${ygkqjl.jb}">
<input type="hidden" id="kqsj" name="jbb" value="${ygkqjl.kqsj}">
<input type="hidden" id="rq" name="rq" value="${rq}">
<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>考勤时间：</label>
                    </td>
                    <td>
                        <input type="radio" name="optionsRadios" id="optionsRadios0" value="0"> 0&nbsp;&nbsp;
                        <input type="radio" name="optionsRadios" id="optionsRadios1" value="1"> 1&nbsp;&nbsp;
                        <input type="radio" name="optionsRadios" id="optionsRadios2" value="2"> 2&nbsp;&nbsp;
                        <input type="radio" name="optionsRadios" id="optionsRadios3" value="3"> 3&nbsp;&nbsp;
                        <input type="radio" name="optionsRadios" id="optionsRadios4" value="4"> 4&nbsp;&nbsp;

                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label></label>
                    </td>
                    <td>
                        <input type="radio" name="optionsRadios" id="optionsRadios5" value="5"> 5&nbsp;&nbsp;
                        <input type="radio" name="optionsRadios" id="optionsRadios6" value="6"> 6&nbsp;&nbsp;
                        <input type="radio" name="optionsRadios" id="optionsRadios7" value="7"> 7&nbsp;&nbsp;
                        <input type="radio" name="optionsRadios" id="optionsRadios8" value="8"> 8&nbsp;&nbsp;
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label></label>
                    </td>
                    <td>
                        <input type="radio" name="optionsRadios" id="optionsRadios9" value="9"> 9&nbsp;&nbsp;
                        <input type="radio" name="optionsRadios" id="optionsRadios10" value="10"> 10&nbsp;
                        <input type="radio" name="optionsRadios" id="optionsRadios11" value="11"> 11&nbsp;
                        <input type="radio" name="optionsRadios" id="optionsRadios12" value="12"> 12&nbsp;
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label></label>
                    </td>
                    <td>
                        <input type="radio" name="optionsRadios" id="optionsRadios13" value="13"> 13&nbsp;
                        <input type="radio" name="optionsRadios" id="optionsRadios14" value="14"> 14&nbsp;
                        <input type="radio" name="optionsRadios" id="optionsRadios15" value="15"> 15&nbsp;
                        <input type="radio" name="optionsRadios" id="optionsRadios16" value="16"> 16&nbsp;
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>夜班：</label>
                    </td>
                    <td>
                        <input type="radio" name="jbRadios" id="jb" value="jb"> 中班&nbsp;&nbsp;
                        <input type="radio" name="jbRadios" id="yb" value="yb"> 夜班&nbsp;&nbsp;
                        <input type="radio" name="jbRadios" id="dyb" value="dyb"> 大夜班&nbsp;&nbsp;
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>缺勤原因：</label>
                    </td>
                    <td>
                        <textarea id="qqyy" name="qqyy" class="form-control" rows="3" cols="20" placeholder="请输入缺勤原因">${ygkqjl.qqyy}</textarea>
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
        var kqsj = $("#kqsj").val();
        var jb = $("#jbb").val();
        $("#optionsRadios"+kqsj).attr("checked","checked");
        if (jb === 'jb') {
            $("#jb").attr("checked","checked");
        }
        if (jb === 'yb') {
            $("#yb").attr("checked","checked");
        }
        if (jb === 'dyb') {
            $("#dyb").attr("checked","checked");
        }
        // var sw = $("#sww").val();
        // var xw = $("#xww").val();
        // var jb = $("#jbb").val();
        // if (sw=='1'){
        //     $("#sw").attr("checked","checked");
        // }
        // if (xw=='1'){
        //     $("#xw").attr("checked","checked");
        // }
        // if (jb=='1'){
        //     $("#jb").attr("checked","checked");
        // }

    }
</script>


<script type="text/javascript">

    //点击保存，保存数据
    function check() {
        var kqsj = $("input[name='optionsRadios']:checked").val();
        var ygkqjlid = $("#ygkqjlid").val();
        var ygid = $("#ygid").val();
        var qqyy = $("#qqyy").val();
        var rq = $("#rq").val();
        var checked = $("input[name='jbRadios']:checked").val() ? $("input[name='jbRadios']:checked").val() : "";
        $.ajax({
            type: "GET",
            url: "${adminPath}/grgl/ygkqjl/saveKq",
            data: {
                id: ygkqjlid,
                ygid: ygid,
                checked: checked,
                kqsj: kqsj,
                qqyy: qqyy,
                rq: rq
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
