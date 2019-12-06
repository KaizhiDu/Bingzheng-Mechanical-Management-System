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
                        <label>类型：</label>
                    </td>
                    <td>
                        <select name="type" class="form-control" id="type">
                            <option value="0">收入</option>
                            <option value="1">支出</option>
                            <option value="2">调动</option>
                            <option value="3">借还</option>
                        </select>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>名称：</label>
                    </td>
                    <td>
                        <input name="name" id="name" htmlEscape="false" class="form-control" placeholder="请输入名称"/>
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
        var name = $("#name").val();
        var type = $("#type").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/jcsz/mxmb/addMxmb",
            data: {
                id: null,
                name: name,
                type: type
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
