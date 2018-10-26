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
                        <label>计划编号：</label>
                    </td>
                    <td>
                        <select id="htid" name="htid" class="form-control">
                            <c:forEach var="ht" items="${htList}">
                                <option value="${ht.id}">${ht.htbh}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>部件图号：</label>
                    </td>
                    <td>
                        <input name="ljth" id="ljth" htmlEscape="false" class="form-control" placeholder="请输入部件图号"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>部件名称：</label>
                    </td>
                    <td>
                        <input name="ljmc" id="ljmc" htmlEscape="false" class="form-control" placeholder="请输入部件名称"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>单用量：</label>
                    </td>
                    <td>
                        <input name="dyl" id="dyl" htmlEscape="false" class="form-control" placeholder="请输入单用量" onchange="checkSl()"/>
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
        var sl = $("#sl").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = sl.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#sl").val("");
        }
    }

    //点击保存，保存数据
    function check() {
        var htid = $("#htid").val();
        var ljth = $("#ljth").val();
        var ljmc = $("#ljmc").val();
        var dyl = $("#dyl").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/scjhgl/bjgl/saveBj",
            data: {
                id: null,
                htid: htid,
                ljmc: ljmc,
                dyl: dyl,
                ljth: ljth
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
