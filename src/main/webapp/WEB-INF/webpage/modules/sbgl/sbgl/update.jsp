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
<input name="sbid" id="sbid" type="hidden" value="${sbgl.id}">
<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>设备标号：</label>
                    </td>
                    <td>
                        <input name="sbbh" id="sbbh" htmlEscape="false" class="form-control" placeholder="请输入设备编号" value="${sbgl.sbbh}"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>所属分类：</label>
                    </td>
                    <td>
                        <select id="ssdl" name="ssdl" class="form-control">
                            <c:forEach items="${list}" var="each">
                                <c:if test="${each.id==sbgl.ssdl}"><option value="${each.id}" selected="selected">${each.flmc}</option></c:if>
                                <c:if test="${each.id!=sbgl.ssdl}"><option value="${each.id}">${each.flmc}</option></c:if>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>状态：</label>
                    </td>
                    <td>
                        <select id="zt" name="zt" class="form-control">
                            <c:if test="${sbgl.zt==1}"><option value="1" selected="selected">可用</option><option value="0">停用</option><option value="2">维修</option></c:if>
                            <c:if test="${sbgl.zt==0}"><option value="1">可用</option><option value="0" selected="selected">停用</option><option value="2">维修</option></c:if>
                            <c:if test="${sbgl.zt==2}"><option value="1">可用</option><option value="0">停用</option><option value="2" selected="selected">维修</option></c:if>
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
        var sbid = $("#sbid").val();
        var sbbh = $("#sbbh").val();
        var sbmc = $("#sbmc").val();
        var ssdl = $("#ssdl").val();
        var zt = $("#zt").val();

        $.ajax({
            type: "GET",
            url: "${adminPath}/sbgl/sbgl/saveSb",
            data: {
                id: sbid,
                sbbh: sbbh,
                sbmc: sbmc,
                ssdl: ssdl,
                zt: zt
            },
            success: function (data) {

            }
        });
    }
</script>

</body>
</html>
