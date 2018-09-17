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
<input id="id" name="id" type="hidden" value="${id}">
<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>排序：</label>
                    </td>
                    <td>
                        <select id="px" name="px" class="form-control">
                            <c:forEach var="i" begin="0" end="${pxzs-1}" varStatus="status">
                                <c:if test="${status.index+1==dqpx}">
                                    <option selected="selected" value="${status.index+1}">${status.index+1}</option>
                                </c:if>
                                <c:if test="${status.index+1!=dqpx}">
                                    <option value="${status.index+1}">${status.index+1}</option>
                                </c:if>
                            </c:forEach>
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
        var px = $("#px").val();
        var id = $("#id").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/scgl/ljgybz/savePx?id="+id+"&px="+px,
            success: function (data) {

            }
        });
    }
</script>

</body>
</html>
