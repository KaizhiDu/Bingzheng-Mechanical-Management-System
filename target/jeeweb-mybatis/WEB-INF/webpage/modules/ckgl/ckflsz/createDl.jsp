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

<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>大类名称：</label>
                    </td>
                    <td>
                        <input name="dlmc" id="dlmc" htmlEscape="false" class="form-control" placeholder="请输入大类名称"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>所属仓库：</label>
                    </td>
                    <td>
                        <select id="ssck" name="ssck" class="form-control">
                            <option value="标准件">标准件</option>
                            <option value="原材料">原材料</option>
                            <option value="刃具">刃具</option>
                            <option value="工具">工具</option>
                            <option value="低值易耗品">低值易耗品</option>
                            <option value="成品">成品</option>
                        </select>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="col-md-3">
    </div>
</div>


<script type="text/javascript">
    //点击保存，保存数据
    function check() {
        var dlmc = $("#dlmc").val();
        var ssck = $("#ssck").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/ckgl/ckflsz/saveDl",
            data: {
                id: null,
                dlmc: dlmc,
                ssck: ssck
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
