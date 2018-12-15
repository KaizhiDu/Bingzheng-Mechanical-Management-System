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
<h4>进货商</h4>
<hr>
<div class="row">
    <div id="CkglJhsGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-4" style="margin-bottom: 10px">

            </div>
        </div>
    </div>
</div>

<grid:grid id="CkglJhs"
           url="${adminPath}/ckgl/ckflsz/ajaxJhsList" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <%--<grid:column label="sys.common.opt" name="opt" formatter="button" width="100"/>--%>

    <grid:column label="进货商名称" name="jhs"/>

    <grid:toolbar function="createJhs" icon="fa fa-plus" btnclass="btn btn-sm btn-primary" title="添加进货商"/>
    <grid:toolbar function="deleteJhs" icon="fa fa-trash" title="删除" btnclass="btn-danger"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>

<script type="text/javascript">

    //删除
    function deleteJhs(title, url, gridId, id, width, height, tipMsg){
        //获取选中行的id数组
        var idsArray = $("#CkglJhsGrid").jqGrid("getGridParam", "selarrrow")
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
            //需要提示，确定要删除吗？删除这个计划，相关零部件也会删除
            layer.confirm('确定要删除吗？', {
                    btn: ['确定', '取消']
                }, function (index, layero) {
                    $.ajax({
                        type: "GET",
                        url: "${adminPath}/ckgl/ckflsz/deleteJhs?ids="+ids,
                        success: function (data) {
                            refreshTable2(gridId);
                        }
                    });
                    layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
                    layer.msg("删除成功!",{ icon: 1, time: 1000 });

                }
            );

        }
        else{
            top.layer.alert('请选择要删除的数据!', {icon: 0, title:'警告'});
            return;
     }
    }


    //添加进货商
    function createJhs(title, url, gridId, id, width, height, tipMsg){
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["50%", "50%"],
            title: "添加进货商",
            maxmin: true, //开启最大化最小化按钮
            content: "${adminPath}/ckgl/ckflsz/createJhs" ,
            success: function(layero, index){
                //遍历父页面的button,使其失去焦点，再按enter键就不会弹框了
                $(":button").each(function () {
                    $(this).blur();
                });
            },
            btn: ['保存', '关闭'],
            yes: function(index, layero){
                var body = top.layer.getChildFrame('body', index);
                var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                //文档地址
                //http://www.layui.com/doc/modules/layer.html#use
                iframeWin.contentWindow.check();
                //判断逻辑并关闭
                setTimeout(function(){top.layer.close(index)}, 300);//延时0.1秒，对应360 7.1版本bug
                layer.msg("添加成功!",{ icon: 1, time: 1000 });
                refreshTable2(gridId);
            },
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
