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
                <tr class="form-group">
                    <td>
                        <label>分类大类：</label>
                    </td>
                    <td>
                        <select id="fldl" name="fldl" class="form-control" onchange="getFlxl()">
                            <option value="">请选择</option>
                            <c:forEach var="dl" items="${DlList}">
                                <option value="${dl.id}">${dl.dlmc}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>分类小类：</label>
                    </td>
                    <td>
                        <select id="flxl" name="flxl" class="form-control">
                            <option value="">请选择</option>
                        </select>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>规格：</label>
                    </td>
                    <td>
                        <input name="gg" id="gg" htmlEscape="false" class="form-control" placeholder="请输入规格"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>单位：</label>
                    </td>
                    <td>
                        <input name="dw" id="dw" htmlEscape="false" class="form-control" placeholder="请输入单位"/>
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

    //检查预警量
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

    //根据大类id拿到所有小类名
    function getFlxl() {
        var fldl = $("#fldl").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/ckgl/bzj/getFlxl?fldl="+fldl,
            success : function (data) {
                $("#flxl").text("");
                $("#flxl").append(
                    "<option value=''>请选择</option>"
                );
                for (var i=0;i<data.length;i++){
                    var xl = data[i];
                    $("#flxl").append(
                        "<option value='"+xl.xlmc+"'>"+xl.xlmc+"</option>"
                    );
                }

            }
        });
    }

    //点击保存，保存数据
    function check() {
        var fldl = $("#fldl").val();
        var flxl = $("#flxl").val();
        var gg = $("#gg").val();
        var dw = $("#dw").val();
        var dyl = $("#dyl").val();
        var htid = $("#htid").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/scjhgl/bzjgl/saveBzj",
            data: {
                id: null,
                fldl: fldl,
                gg: gg,
                dw: dw,
                dyl: dyl,
                flxl: flxl,
                htid: htid

            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
