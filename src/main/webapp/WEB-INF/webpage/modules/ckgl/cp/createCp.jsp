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
                <%--<tr class="form-group">--%>
                    <%--<td>--%>
                        <%--<label>计划名称：</label>--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--<select name="jhbh" class="form-control" id="jhbh">--%>
                            <%--<c:forEach items="${htList}" var="ht">--%>
                                <%--<option value="${ht.htbh}">${ht.htbh}</option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <tr class="form-group">
                    <td>
                        <label>零部件图号：</label>
                    </td>
                    <td>
                        <input name="lbjth" id="lbjth" htmlEscape="false" class="form-control" placeholder="请输入零部件图号"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>零部件名称：</label>
                    </td>
                    <td>
                        <input name="lbjmc" id="lbjmc" htmlEscape="false" class="form-control" placeholder="请输入零部件名称"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>库存数量：</label>
                    </td>
                    <td>
                        <input name="rksl" id="rksl" htmlEscape="false" class="form-control" placeholder="请输入库存数量"/>
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
        var lbjmc = $("#lbjmc").val();
        var lbjth = $("#lbjth").val();
        var rksl = $("#rksl").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/ckgl/cp/saveCp",
            data: {
                id: null,
                lbjmc: lbjmc,
                lbjth: lbjth,
                rksl: rksl
            },
            success: function (data) {

            }
        });
    }

    //更新到当前页
    function refreshTable2(gridId){
        var queryParams = {};
        var queryFields=$('#queryFields').val();
        var curpagenum = $("#"+gridId+"").jqGrid('getGridParam', 'page');
        queryParams['queryFields'] = queryFields;
        //普通的查询
        $('#' + gridId + "Query").find(":input").each(function() {
            var val = $(this).val();
            if (queryParams[$(this).attr('name')]) {
                val = queryParams[$(this).attr('name')] + "," + $(this).val();
            }
            queryParams[$(this).attr('name')] = val;
        });

        // 普通的查询
        $('#' + gridId + "Query").find(":input").each(function() {
            var condition = $(this).attr('condition');
            if (!condition) {
                condition = "";
            }
            var key = "query." + $(this).attr('name') + "||" + condition;
            queryParams[key] = queryParams[$(this).attr('name')];
        });
        //刷新
        //传入查询条件参数
        $("#"+gridId).jqGrid('setGridParam',{
            datatype:'json',
            postData:queryParams, //发送数据
            page:curpagenum
        }).trigger("reloadGrid"); //重新载入
    }

</script>

</body>
</html>
