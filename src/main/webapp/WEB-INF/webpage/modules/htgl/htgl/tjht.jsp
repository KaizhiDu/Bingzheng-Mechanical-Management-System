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
                        <label>合同号：</label>
                    </td>
                    <td>
                        <input name="hth" id="hth" htmlEscape="false" class="form-control" placeholder="请输入合同号"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>企业合同号：</label>
                    </td>
                    <td>
                        <input name="qyhth" id="qyhth" htmlEscape="false" class="form-control" placeholder="请输入企业合同号"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>合同名称：</label>
                    </td>
                    <td>
                        <input name="mc" id="mc" htmlEscape="false" class="form-control" placeholder="请输入合同名称"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>签订日期：</label>
                    </td>
                    <td>
                        <input name="qdrq" id="qdrq" htmlEscape="false"
                               class="form-control layer-date" pattern="yyyy-MM-dd"
                               value="${currentDate}"
                               onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" placeholder="年-月-日"
                               datatype="*"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>工期：</label>
                    </td>
                    <td>
                        <input name="gq" id="gq" htmlEscape="false" class="form-control" placeholder="请输入工期"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>所属公司：</label>
                    </td>
                    <td>
                        <select id="ssgs" name="ssgs" class="form-control">
                            <option value="">请选择</option>
                            <c:forEach var="item" items="${gsList}">
                                <option value="${item.mc}">${item.mc}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>备注：</label>
                    </td>
                    <td>
                        <input name="bz" id="bz" htmlEscape="false" class="form-control" placeholder="请输入备注"/>
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
        var hth = $("#hth").val();
        var qyhth = $("#qyhth").val();
        var qdrq = $("#qdrq").val();
        var gq = $("#gq").val();
        var ssgs = $("#ssgs").val();
        var mc = $("#mc").val();
        var bz = $("#bz").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/htgl/htgl/saveHt",
            data: {
                mc: mc,
                hth: hth,
                qyhth: qyhth,
                qdrq: qdrq,
                gq: gq,
                ssgs: ssgs,
                bz: bz
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
