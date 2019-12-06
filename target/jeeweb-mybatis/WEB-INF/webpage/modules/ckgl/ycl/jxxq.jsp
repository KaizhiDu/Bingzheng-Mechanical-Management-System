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
    <html:css name="simditor"/>
    <html:css
            name="bootstrap-fileinput,font-awesome,animate,iCheck,datepicker,jqgrid,sweetalert,Validform,jqgrid"/>
    <html:js
            name="jqGrid,jquery,bootstrap,jquery-ui,peity,iCheck,sweetalert,Validform,jqgrid"/>
    <html:js name="bootstrap-fileinput"/>
    <html:js name="simditor"/>
    <html:js name="laydate"/>
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
<h4>原材料明细</h4>
<hr>
<div class="row">
    <div id="YclxqGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">年：</label>
                <input name="n" id="n" width="30" htmlEscape="false" class="form-control" placeholder="输入年度">
            </div>
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">月：</label>
                <input name="y" id="y" htmlEscape="false" class="form-control" placeholder="输入月份">
            </div>
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">日：</label>
                <input name="r" id="r" htmlEscape="false" class="form-control" placeholder="输入日期">
            </div>
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">分类大类：</label>
                <input name="fldl" id="fldl" htmlEscape="false" class="form-control" placeholder="输入分类大类">
            </div>
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">分类小类：</label>
                <input name="flxl" id="flxl" htmlEscape="false" class="form-control" placeholder="输入分类小类">
            </div>
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">规格：</label>
                <input name="gg" id="gg" htmlEscape="false" class="form-control" placeholder="输入规格">
            </div>
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">进货商：</label>
                <select id="jhs" name="jhs" class="form-control">
                    <option value="">请选择</option>
                    <c:forEach var="jhs" items="${ckglJhs}">
                        <option value="${jhs.jhs}">${jhs.jhs}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">入库/出库：</label>
                <select id="jx" name="jx" class="form-control">
                    <option value="">请选择</option>
                    <option value="0">入库</option>
                    <option value="1">出库</option>
                </select>
            </div>
        </div>
    </div>
</div>

<grid:grid id="Yclxq"
           url="${adminPath}/ckgl/ycl/ajaxXqList" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>

    <grid:column label="年" name="n" width="35"/>
    <grid:column label="月" name="y" width="30"/>
    <grid:column label="日" name="r" width="30"/>
    <grid:column label="大类" name="fldl" width="30"/>
    <grid:column label="小类" name="flxl" width="30"/>
    <grid:column label="规格" name="gg" width="30"/>
    <grid:column label="进货商" name="jhs" width="40"/>
    <grid:column label="明细" name="mx"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>

</body>
</html>
