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
<body>

<div class="row">
    <div id="wcqkGridQuery" class="col-md-12">
        <div class="form-inline">

        </div>
    </div>
</div>
<h4>完成情况</h4>

<input type="hidden" id="htmxid" name="htmxid" value="${htmxid}">

<grid:grid id="wcqk" url="${adminPath}/htgl/htgl/ajaxWcqkList?htmxid=${htmxid}" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
<%--    <grid:column label="sys.common.opt" name="opt" formatter="button" width="100"/>--%>
<%--    <grid:button title="完成" groupname="opt" function="wc"--%>
<%--                 outclass="btn-primary" url="${adminPath}/htgl/htgl/wc?id=\"+row.id+\"" />--%>
<%--    <grid:button title="完成情况" groupname="opt" function="wcqk"--%>
<%--                 outclass="btn-success" url="${adminPath}/htgl/htgl/wcqk?id=\"+row.id+\"" />--%>
    <grid:column label="日期" name="rq"/>
    <grid:column label="完成数量" name="wcsl"/>
    <grid:column label="备注" name="bz"/>
    <grid:toolbar function="deleteWcqk" btnclass="btn btn-sm btn-danger" title="删除"/>
</grid:grid>

<script type="text/javascript">

    //删除合同
    function deleteWcqk(title, url, gridId, id, width, height, tipMsg){
        var htmxid = $("#htmxid").val();
        //获取选中行的id数组
        var idsArray = $("#wcqkGrid").jqGrid("getGridParam", "selarrrow");
        if (idsArray.length>0){
            var ids = "";
            for (var i=0;i<idsArray.length;i++){
                if (i==0){
                    ids = idsArray[i];
                }
                else{
                    ids = ids + "," + idsArray[i];
                }
            }
            layer.confirm('是否要删除!', {
                    btn: ['确定', '取消']
                }, function (index, layero) {
                    $.ajax({
                        type: "GET",
                        url: "${adminPath}/htgl/htgl/deleteWcqk?ids="+ids+"&htmxid="+htmxid,
                        success: function (data) {
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
                    });
                    layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
                    layer.msg("删除成功!",{ icon: 1, time: 1000 });
                }
            );
            //refreshTable2(gridId);
        }
        else {
            top.layer.alert('请选择要删除的数据!', {icon: 0, title:'警告'});
        }
    }
</script>

</body>
</html>
