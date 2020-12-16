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
    <input name="mxmbid" id="mxmbid" type="hidden" value="${jcszMxmb.id}">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>类型：</label>
                    </td>
                    <td>
                        <select name="type" class="form-control" id="type">
                            <c:if test="${jcszMxmb.type==0}">
                                <option value="0">收入</option>
                                <option value="1">支出</option>
                                <option value="2">调动</option>
                                <option value="3">借还</option>
                                <option value="4">资金流水公司</option>
                                <option value="5">资金流水项目</option>
                            </c:if>
                            <c:if test="${jcszMxmb.type==1}">
                                <option value="1">支出</option>
                                <option value="0">收入</option>
                                <option value="2">调动</option>
                                <option value="3">借还</option>
                                <option value="4">资金流水公司</option>
                                <option value="5">资金流水项目</option>
                            </c:if>
                            <c:if test="${jcszMxmb.type==2}">
                                <option value="2">调动</option>
                                <option value="0">收入</option>
                                <option value="1">支出</option>
                                <option value="3">借还</option>
                                <option value="4">资金流水公司</option>
                                <option value="5">资金流水收入项目</option>
                                <option value="6">资金流水支出项目</option>
                            </c:if>
                            <c:if test="${jcszMxmb.type==3}">
                                <option value="3">借还</option>
                                <option value="0">收入</option>
                                <option value="1">支出</option>
                                <option value="2">调动</option>
                                <option value="4">资金流水公司</option>
                                <option value="5">资金流水收入项目</option>
                                <option value="6">资金流水支出项目</option>
                            </c:if>
                            <c:if test="${jcszMxmb.type==4}">
                                <option value="4">资金流水公司</option>
                                <option value="3">借还</option>
                                <option value="0">收入</option>
                                <option value="1">支出</option>
                                <option value="2">调动</option>
                                <option value="5">资金流水收入项目</option>
                                <option value="6">资金流水支出项目</option>
                            </c:if>
                            <c:if test="${jcszMxmb.type==5}">
                                <option value="5">资金流水收入项目</option>
                                <option value="3">借还</option>
                                <option value="0">收入</option>
                                <option value="1">支出</option>
                                <option value="2">调动</option>
                                <option value="4">资金流水公司</option>
                                <option value="6">资金流水支出项目</option>
                            </c:if>
                            <c:if test="${jcszMxmb.type==6}">
                                <option value="6">资金流水支出项目</option>
                                <option value="5">资金流水收入项目</option>
                                <option value="3">借还</option>
                                <option value="0">收入</option>
                                <option value="1">支出</option>
                                <option value="2">调动</option>
                                <option value="4">资金流水公司</option>
                            </c:if>
                        </select>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>名称：</label>
                    </td>
                    <td>
                        <input name="name" id="name" htmlEscape="false" class="form-control" placeholder="请输入名称" value="${jcszMxmb.name}"/>
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
        var id = $("#mxmbid").val();
        var name = $("#name").val();
        var type = $("#type").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/jcsz/mxmb/updateMxmb",
            data: {
                id: id,
                name: name,
                type: type
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
