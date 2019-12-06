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
                        <label>工艺小类编号：</label>
                    </td>
                    <td>
                        <input name="gyxldm" id="gyxldm" htmlEscape="false" class="form-control" placeholder="请输入工艺小类编号"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>工艺小类名称：</label>
                    </td>
                    <td>
                        <input name="gyxlmc" id="gyxlmc" htmlEscape="false" class="form-control" placeholder="请输入工艺小类名称"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>所属分类：</label>
                    </td>
                    <td>
                        <select id="sfqy" name="sfqy" class="form-control">
                            <option value="1">可用</option>
                            <option value="0">不可用</option>
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
        var gyxldm = $("#gyxldm").val();
        var gyxlmc = $("#gyxlmc").val();
        var sfqy = $("#sfqy").val();

        $.ajax({
            type: "GET",
            url: "${adminPath}/scgl/gymbsz/saveGyxl",
            data: {
                id: null,
                gyxldm: gyxldm,
                gyxlmc: gyxlmc,
                sfqy: sfqy
            },
            success: function (data) {

            }
        });
    }
</script>

</body>
</html>
