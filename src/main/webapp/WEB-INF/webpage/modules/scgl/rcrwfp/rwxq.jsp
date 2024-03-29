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
<h2>${xm}</h2>
<hr>
<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <c:if test="${getData!=null}">
            <table class="table">
                <c:forEach var="each" items="${getData}">
                    <tr class="form-group">
                        <td>
                            <label>${each}</label>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            </c:if>
        </form>
    </div>
    <div class="col-md-3">
    </div>
</div>

</body>
</html>
