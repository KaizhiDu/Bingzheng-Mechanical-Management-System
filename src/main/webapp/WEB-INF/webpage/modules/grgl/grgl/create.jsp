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
                        <label>姓名：</label>
                    </td>
                    <td>
                        <input name="name" id="name" htmlEscape="false" class="form-control" placeholder="员工姓名"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>性别：</label>
                    </td>
                    <td>
                        <select id="gender" name="gender" class="form-control">
                                <option value="1">男</option>
                                <option value="0">女</option>
                        </select>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>电话：</label>
                    </td>
                    <td>
                        <input name="tel" id="tel" htmlEscape="false" class="form-control" placeholder="手机号"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>身份证号：</label>
                    </td>
                    <td>
                        <input name="email" id="email" htmlEscape="false" class="form-control" placeholder="身份证号"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>QQ：</label>
                    </td>
                    <td>
                        <input name="QQ" id="QQ" htmlEscape="false" class="form-control" placeholder="QQ号"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>入职日期：</label>
                    </td>
                    <td>
                        <input name="enterDate" id="enterDate" htmlEscape="false" class="form-control layer-date" value="<fmt:formatDate value='${xtsz.shksrq}' pattern='yyyy-MM-dd'/>" pattern="yyyy-MM-dd" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"  placeholder="年-月-日"  datatype="*"/>
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
        var gender = $("#gender").val();
        var tel = $("#tel").val();
        var email = $("#email").val();
        var QQ = $("#QQ").val();
        var enterDate = $("#enterDate").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/grgl/grgl/saveWorker",
            data: {
                id: null,
                name: name,
                gender: gender,
                tel: tel,
                email: email,
                qq: QQ,
                enterdate: enterDate
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
