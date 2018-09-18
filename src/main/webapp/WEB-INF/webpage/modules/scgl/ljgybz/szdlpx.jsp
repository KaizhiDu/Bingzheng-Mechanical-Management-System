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
            name="layer,jqGrid,jquery,bootstrap,jquery-ui,peity,iCheck,sweetalert,Validform,jqgrid"/>
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
<body>

<h4>修改排序</h4>

<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6" id="dlbzList">
        <form>
            <table class="table">

                <c:forEach items="${gydlbzsList}" var="dlbz">
                <tr class="form-group">
                    <td>
                        <label>${dlbz.gydlmc}</label>
                    </td>
                    <td>
                        <input name="serachTd" id="${dlbz.id}" htmlEscape="false" class="form-control" value="${dlbz.px}" >
                    </td>
                </tr>
                </c:forEach>

            </table>
        </form>
    </div>
    <div class="col-md-3">
    </div>
</div>
<script type="text/javascript">
    function check() {
        var list = "";
        var serachInputText = $("[name='serachTd']");
        $.each(serachInputText,function () {
            var input = $(this);
            // alert(input.attr("id"));
            // alert(input.val());
            //var obj = new Object();
            list = list + input.attr("id")+"-"+input.val()+",";
        });
        list = list.substring(0,list.length-1);
            $.ajax({
                type: "GET",
                url: "${adminPath}/scgl/ljgybz/saveDlpx?list="+list,
                success: function (data) {

                }
            });


    }
</script>
</body>
</html>
