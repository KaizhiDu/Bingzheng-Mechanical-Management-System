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
<h4>已完成半成品</h4>
<hr>
<div class="row">
    <div id="CkglBcpGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">计划名称：</label>
                <select name="jhbh" class="form-control" id="jhbh">
                    <option value="">请选择</option>
                    <c:forEach items="${htList}" var="ht">
                        <option value="${ht.htbh}">${ht.htbh}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">零部件名称：</label>
                <select name="lbjmc" class="form-control" id="lbjmc">
                    <option value="">请选择</option>
                    <c:forEach items="${bcpList}" var="lbj">
                        <option value="${lbj.lbjmc}">${lbj.lbjmc}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">零部件图号：</label>
                <select name="lbjth" class="form-control" id="lbjth">
                    <option value="">请选择</option>
                    <c:forEach items="${bcpList}" var="lbj">
                        <option value="${lbj.lbjth}">${lbj.lbjth}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
</div>

<grid:grid id="CkglBcp"
           url="${adminPath}/ckgl/bcp/ywcbcp/ajaxBcpList" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>

    <grid:column label="sys.common.opt" name="opt" formatter="button" width="100"/>
    <grid:button title="入成品库" groupname="opt" function="rcpk"
                 outclass="btn-success" url="${adminPath}/ckgl/bcp/ywcbcp/rcpk?id=\"+row.id+\"" />
    <grid:button title="出库" groupname="opt" function="ck"
                 outclass="btn-danger" url="${adminPath}/ckgl/bcp/ywcbcp/ck?id=\"+row.id+\"" />

    <grid:column label="计划名称" name="jhbh"/>
    <grid:column label="零部件名称" name="lbjmc"/>
    <grid:column label="零部件图号" name="lbjth"/>
    <grid:column label="库存数量" name="rksl"/>

    <grid:toolbar function="createBcp" icon="fa fa-plus" btnclass="btn btn-sm btn-primary" title="手动添加"/>
    <grid:toolbar function="checkRcpkxq" icon="fa fa-edit" btnclass="btn btn-sm btn-warning" title="查看入成品详情"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>

<script type="text/javascript">

    //出库
    function ck(title, url, gridId, id, width, height, tipMsg){
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["50%", "50%"],
            title: "出库",
            maxmin: true, //开启最大化最小化按钮
            content: url ,
            success: function(layero, index){
                //遍历父页面的button,使其失去焦点，再按enter键就不会弹框了
                $(":button").each(function () {
                    $(this).blur();
                });
            },
            btn: ['出库', '关闭'],
            yes: function(index, layero){
                var body = top.layer.getChildFrame('body', index);
                var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                //文档地址
                //http://www.layui.com/doc/modules/layer.html#use
                iframeWin.contentWindow.check();
                //判断逻辑并关闭
                setTimeout(function(){top.layer.close(index)}, 300);//延时0.1秒，对应360 7.1版本bug
                layer.msg("添加!",{ icon: 1, time: 1000 });
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

    //查看入成品详情
    function checkRcpkxq(title, url, gridId, id, width, height, tipMsg){
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["40%", "80%"],
            title: "入成品库详情",
            maxmin: true, //开启最大化最小化按钮
            content: "${adminPath}/ckgl/bcp/ywcbcp/checkRcpkxq" ,
            success: function(layero, index){
                //遍历父页面的button,使其失去焦点，再按enter键就不会弹框了
                $(":button").each(function () {
                    $(this).blur();
                });
            },
            btn: ['关闭'],
            cancel: function(index){
                refreshTable2(gridId);
            },
            end: function (index) {
                refreshTable2(gridId);
            }
        });
    }

    //手动入库
    function createBcp(title, url, gridId, id, width, height, tipMsg){
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["50%", "50%"],
            title: "手动添加",
            maxmin: true, //开启最大化最小化按钮
            content: "${adminPath}/ckgl/bcp/ywcbcp/createBcp" ,
            success: function(layero, index){
                //遍历父页面的button,使其失去焦点，再按enter键就不会弹框了
                $(":button").each(function () {
                    $(this).blur();
                });
            },
            btn: ['添加', '关闭'],
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

    //入成品库
    function rcpk(title, url, gridId, id, width, height, tipMsg) {

        layer.confirm('此操作无法找回请谨慎操作！是否入成品库？', {
                btn: ['确定', '取消']
            }, function (index, layero) {
                $.ajax({
                    type: "GET",
                    url: url,
                    success: function (data) {
                        refreshTable2(gridId);
                    }
                });
                layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
                layer.msg("入库成功!",{ icon: 1, time: 1000 });

            }
        );

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
