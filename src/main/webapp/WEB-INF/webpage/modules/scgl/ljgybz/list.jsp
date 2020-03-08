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

<h4>零部件工艺编制</h4>
<div class="row">
    <div id="LjglGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">计划名称：</label>
                <select id="htid" name="htid" class="form-control">
                    <option value="">请选择</option>
                    <c:forEach items="${jhList}" var="each">
                        <option value="${each.id}">${each.htbh}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">零部件名：</label>
                <input htmlEscape="false" class="form-control" placeholder="模糊搜索零部件名"  maxlength="20" id="ljmc" name="ljmc"/>
            </div>

            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">图号：</label>
                <input htmlEscape="false" class="form-control" placeholder="模糊搜索图号"  maxlength="20" id="ljth" name="ljth"/>
            </div>
        </div>
    </div>
</div>
<grid:grid id="Ljgl"
           url="${adminPath}/scgl/ljgybz/ajaxlbjglList" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <grid:column label="sys.common.opt" name="opt" formatter="button" width="30"/>
    <grid:button title="编制工艺大类" groupname="opt" function="bzgydl"
                 outclass="btn-success" url="${adminPath}/scgl/ljgybz/bzgydl?id=\"+row.id+\"" />

    <grid:column label="计划名称" name="htid" width="20"/>
    <grid:column label="名称" name="ljmc" width="20"/>
    <grid:column label="图号" name="ljth" width="20"/>
    <grid:column label="数量" name="sl" width="20"/>
    <grid:column label="已分配大类" name="dls" width="80"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>


<script type="text/javascript">

    //编制工艺大类
    function bzgydl(title, url, gridId, id, width, height, tipMsg) {
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["100%", "100%"],
            title: "编制工艺",
            maxmin: true, //开启最大化最小化按钮
            content: url ,
            success: function(layero, index){
                //遍历父页面的button,使其失去焦点，再按enter键就不会弹框了
                $(":button").each(function () {
                    $(this).blur();
                });
            },
            btn: [ '关闭'],
            cancel: function(index){
                refreshTable2(gridId);
            },
            end: function (index) {
                refreshTable2(gridId);
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
