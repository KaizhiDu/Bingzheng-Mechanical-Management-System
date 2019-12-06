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
<input value="${bcpid}" name="bcpid" id="bcpid" type="hidden">
<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>计划名称：</label>
                    </td>
                    <td>
                        <select name="htid" class="form-control" id="htid">
                            <c:forEach items="${htList}" var="ht">
                                <option value="${ht.id}">${ht.htbh}</option>
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

    //检查数量
    function checkSl(){
        var dyl = $("#dyl").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = dyl.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#dyl").val("");
        }
    }

    //点击保存，保存数据
    function check() {
        var htid = $("#htid").val();
        var bcpid = $("#bcpid").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/ckgl/bcp/wwcbcp/saveJrsc",
            data: {
                htid: htid,
                bcpid: bcpid
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
