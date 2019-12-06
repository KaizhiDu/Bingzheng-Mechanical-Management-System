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

<input id="gydlbzid" name="gydlbzid" type="hidden" value="${gydlbzid}">

<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>工艺小类：</label>
                    </td>
                    <td>
                        <select id="gyxlid" name="gyxlid" class="form-control">
                            <c:forEach items="${gyxlList}" var="each">
                                <option value="${each.gyxlid}">${each.gyxlmc}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>描述：</label>
                    </td>
                    <td>
                        <textarea id="ms" name="ms" class="form-control" rows="3" cols="20" placeholder="请对该工序进行描述"></textarea>
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

        var gyxlid = $("#gyxlid").val();
        var gydlbzid = $("#gydlbzid").val();
        var ms = $("#ms").val();

        $.ajax({
            type: "GET",
            url: "${adminPath}/scgl/ljgybz/saveGyxl",
            data: {
                id: null,
                gyxlid: gyxlid,
                gydlbzid: gydlbzid,
                ms: ms
            },
            success: function (data) {

            }
        });
    }
</script>

</body>
</html>
