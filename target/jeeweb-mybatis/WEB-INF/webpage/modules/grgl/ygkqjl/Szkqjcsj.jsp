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
                        <label>名称</label>
                    </td>
                    <td>
                        <label>数值</label>
                    </td>
                </tr>
                <c:forEach var="each" items="${list}">
                    <tr class="form-group">
                        <td>
                            <label>${each.mc}</label>
                        </td>
                        <td>
                            <label> <input type="text" id="${each.id}" name="jcsj" onchange="checkSl('${each.id}')" value="${each.sz}">  </label>
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

    //检查数量
    function checkSl(id){

        var sz = $("#"+id).val();
        var r = sz.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#"+id).val("");
        }
        //输入的是数字就保存起来
        else{
            $.ajax({
                type: "GET",
                url: "${adminPath}/grgl/ygkqjl/saveJcsj",
                data: {
                    id: id,
                    sz: sz
                },
                success: function (data) {


                }
            });
        }

    }

</script>

</body>
</html>
