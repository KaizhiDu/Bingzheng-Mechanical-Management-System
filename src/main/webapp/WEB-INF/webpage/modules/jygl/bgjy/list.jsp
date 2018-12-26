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

<h4>包工检验</h4>
<div class="row">
    <div id="BgjyGridQuery" class="col-md-12">
        <div class="form-inline">
             <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">工人：</label>
                <select name="xm" class="form-control" id="xm">
                    <option value="">请选择</option>
                    <c:forEach items="${ygsjList}" var="each">
                        <option value="${each.xm}">${each.xm}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">日期：</label>
                <input name="rq" id="rq" htmlEscape="false" class="form-control layer-date" pattern="yyyy-MM-dd" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"  placeholder="年-月-日"  datatype="*"/>
            </div>
        </div>
    </div>
</div>
<grid:grid id="Bgjy"
           url="${adminPath}/jygl/bgjy/ajaxBgjyList" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <grid:column label="sys.common.key" hidden="true" name="bgrwfpid"/>
    <grid:column label="sys.common.opt" name="opt" formatter="button" width="130"/>

    <grid:button title="检验" groupname="opt" function="jy"
                 outclass="btn-success" url="${adminPath}/jygl/bgjy/jy?id=\"+row.id+\"&bgrwfpid=\"+row.bgrwfpid+\"" />
    <grid:button title="注释" groupname="opt" function="zs"
                 outclass="btn-primary" url="${adminPath}/jygl/bgjy/zs?id=\"+row.id+\"" />

    <grid:column label="姓名" name="xm"/>
    <grid:column label="日期" name="rq"/>
    <grid:column label="零部件图号" name="ljth"/>
    <grid:column label="零部件名称" name="ljmc"/>
    <grid:column label="工艺大类名称" name="gydlmc"/>
    <grid:column label="工艺小类名称" name="gyxlmc"/>
    <grid:column label="设备名称" name="sbmc"/>
    <grid:column label="应完成量" name="ywcl"/>
    <grid:column label="实际完成量" name="sjwcl"/>
    <grid:column label="报废量" name="bfl"/>

    <grid:toolbar function="exportBgjyd" icon="fa fa-file-excel-o" btnclass="btn btn-sm btn-warning" title="导出包工检验单"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>

<script type="text/javascript">

    //注释
    function zs(title, url, gridId, id, width, height, tipMsg){
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["40%", "40%"],
            title: "注释",
            maxmin: true, //开启最大化最小化按钮
            content: url ,
            success: function(layero, index){
                //遍历父页面的button,使其失去焦点，再按enter键就不会弹框了
                $(":button").each(function () {
                    $(this).blur();
                });
            },
            btn: ['确定','关闭'],
            yes: function(index, layero){
                var body = top.layer.getChildFrame('body', index);
                var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                //文档地址
                //http://www.layui.com/doc/modules/layer.html#use
                iframeWin.contentWindow.check();
                //判断逻辑并关闭
                setTimeout(function(){top.layer.close(index)}, 200);//延时0.1秒，对应360 7.1版本bug
                layer.msg("保存成功!",{ icon: 1, time: 1000 });
                //refreshTable2(gridId);
            },
            cancel: function(index){
                //refreshTable2(gridId);
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

    //导出包工检验单
    function exportBgjyd(){
        var xm = $("#xm").val();
        var rq = $("#rq").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/jygl/bgjy/exportBgjyd",
            data: {
                xm: xm,
                rq: rq
            },
            success: function (data) {
                top.layer.alert("导出成功，请在D:/bingzhengjixie文件夹下查看", {icon: 0, title:'提示'});
            }
        });
    }

    //查看包工明细
    function jy(title, url, gridId, id, width, height, tipMsg){
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["40%", "40%"],
            title: "包工检验",
            maxmin: true, //开启最大化最小化按钮
            content: url ,
            success: function(layero, index){
                //遍历父页面的button,使其失去焦点，再按enter键就不会弹框了
                $(":button").each(function () {
                    $(this).blur();
                });
            },
            btn: ['确定','关闭'],
            yes: function(index, layero){
                var body = top.layer.getChildFrame('body', index);
                var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                //文档地址
                //http://www.layui.com/doc/modules/layer.html#use
                iframeWin.contentWindow.check();
                //判断逻辑并关闭
                setTimeout(function(){top.layer.close(index)}, 200);//延时0.1秒，对应360 7.1版本bug
                layer.msg("保存成功!",{ icon: 1, time: 1000 });
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

    //检验
    function sfhg(title, url, gridId, id, width, height, tipMsg){
        layer.confirm('确定检验合格吗？', {
                btn: ['合格', '取消']
            }, function (index, layero) {
                $.ajax({
                    type: "GET",
                    url: url,
                    success: function (data) {
                        refreshTable2(gridId);
                    }
                });
                layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
                layer.msg("检验合格!",{ icon: 1, time: 1000 });

            }
        );
    }


</script>
</body>
</html>
