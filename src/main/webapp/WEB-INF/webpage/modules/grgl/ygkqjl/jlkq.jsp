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
<input type="hidden" id="sww" name="sww" value="${ygkqjl.sw}">
<input type="hidden" id="xww" name="xww" value="${ygkqjl.xw}">
<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>上午：</label>
                    </td>
                    <td>
                        <input type="checkbox" name="sw" id="sw" value="sw"> 上午
                    </td>
                </tr>
                <tr class3201="form-group">
                    <td>
                        <label>下午：</label>
                    </td>
                    <td>
                        <input type="checkbox" name="xw" id="xw" value="xw"> 下午
                    </td>
                </tr>
                <tr class3201="form-group">
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
        var sw = $("#sww").val();
        var xw = $("#xww").val();
        if (sw=='1'){
            $("#sw").attr("checked","checked");
        }
        if (xw=='1'){
            $("#xw").attr("checked","checked");
        }
    }
</script>


<script type="text/javascript">

    //点击保存，保存数据
    function check() {
        var ygkqjlid = $("#ygkqjlid").val();
        var ygid = $("#ygid").val();
        var qqyy = $("#qqyy").val();
        var checked = "";
        $('input:checkbox:checked').each(function() {
            //checked.push($(this).val());
            checked = checked+","+$(this).val();
        });
        $.ajax({
            type: "GET",
            url: "${adminPath}/grgl/ygkqjl/saveKq",
            data: {
                id: ygkqjlid,
                ygid: ygid,
                checked: checked,
                qqyy: qqyy
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
