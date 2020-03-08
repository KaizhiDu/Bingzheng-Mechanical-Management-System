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

<h2>${grgl.name}</h2>
<input type="hidden" id="ygid" name="ygid" value="${grgl.id}">
<div class="row">
    <div id="BgrwfpGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label>日期：</label>
                <input name="rq" id="rq" htmlEscape="false" class="form-control layer-date" pattern="yyyy-MM-dd" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"  placeholder="年-月-日"  datatype="*"/>
            </div>
        </div>
    </div>
</div>
<grid:grid id="Bgrwfp"
           url="${adminPath}/scgl/bgrwfp/ajaxBgrwfpList?ygid=${grgl.id}" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <grid:column label="sys.common.opt" name="opt" formatter="button" width="150"/>
    <grid:button title="明细" groupname="opt" function="bgmx"
                 outclass="btn-primary" url="${adminPath}/scgl/bgrwfp/bgmx?id=\"+row.id+\"" />
    <grid:button title="分配任务" groupname="opt" function="fpsb"
                 outclass="btn-success" url="${adminPath}/scgl/bgrwfp/fprw?id=\"+row.id+\"&&bgrg=\"+row.bgrg+\"" />
    <grid:button title="删除" groupname="opt" function="deleteBgrw"
                 outclass="btn-danger" url="${adminPath}/scgl/bgrwfp/deleteBgrw?id=\"+row.id+\"" />

    <grid:column label="日期" name="rq"/>
    <grid:column label="名称" name="bgmc"/>
    <grid:column label="姓名" name="xm"/>
    <grid:column label="职位" name="zw"/>
    <grid:column label="包工/日工" name="bgrg"/>

    <grid:toolbar function="createBgrw" icon="fa fa-plus" btnclass="btn btn-sm btn-primary" title="创建任务"/>
    <grid:toolbar function="exportPgd" icon="fa fa-file-excel-o" btnclass="btn btn-sm btn-warning" title="导出派工单"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>

<script type="text/javascript">

    function exportPgd() {
        //获取选中行的id数组
        var idsArray = $("#BgrwfpGrid").jqGrid("getGridParam", "selarrrow")
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

            $.ajax({
                type: "get",
                url: "${adminPath}/scgl/bgrwfp/exportPgd?ids="+ids,
                success: function (data) {
                    top.layer.alert("导出成功，请在D:/bingzhengjixie文件夹下查看", {icon: 0, title:'提示'});
                }
            });

        }
        else{
            top.layer.alert('请选择至少一条数据!', {icon: 0, title:'警告'});
            return;
        }
    }

    //导出包工派工单
    function exprortBgpgd(){
        var rq = $("#rq").val();
        var ygid = $("#ygid").val();
        if (rq==''){
            layer.alert("请选择日期");
        }
        //导出
        else{
            $.ajax({
                type: "get",
                url: "${adminPath}/scgl/bgrwfp/exprortBgpgd?rq="+rq+"&ygid="+ygid,
                success: function (data) {

                }
            });
            layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
            top.layer.alert("导出成功，请在D:/bingzhengjixie文件夹下查看", {icon: 0, title:'提示'});
        }

    }

    //删除包工任务
    function deleteBgrw(title, url, gridId, id, width, height, tipMsg){
        layer.confirm('确定要删除吗？', {
                btn: ['确定', '取消']
            }, function (index, layero) {
                $.ajax({
                    type: "GET",
                    url: url,
                    success: function (data) {
                        refreshTable2(gridId);
                        layer.msg(data.msg,{ icon: 1, time: 1000 });
                    }
                });
                layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框


            }
        );
    }

    //包工明细
    function bgmx(title, url, gridId, id, width, height, tipMsg) {
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["70%", "80%"],
            title: "任务明细",
            maxmin: true, //开启最大化最小化按钮
            content: url ,
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

    //分配日常任务
    function fpsb(title, url, gridId, id, width, height, tipMsg) {
        //alert(url);
        //分配任务之前要先检查有没有包工明细
        var bgid = url.split("=")[1];
        $.ajax({
            type: "get",
            url: "${adminPath}/scgl/bgrwfp/checkBgmx?bgid="+bgid,
            success: function (data) {
                if (data==0){
                    top.layer.alert("请先创建任务明细");
                    return;
                }
                else {

                    if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
                        width='auto';
                        height='auto';
                    }else{//如果是PC端，根据用户设置的width和height显示。

                    }
                    top.layer.open({
                        type: 2,
                        area: ["95%", "90%"],
                        title: "任务分配",
                        maxmin: true, //开启最大化最小化按钮
                        content: url ,
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
            }
        });

    }

    function createBgrw(title, url, gridId, id, width, height, tipMsg){
        var ygid = $("#ygid").val();
        $.ajax({
            type: "get",
            url: "${adminPath}/scgl/bgrwfp/createBgrw?ygid="+ygid,
            success: function (data) {
                refreshTable2(gridId);
                layer.msg("创建成功!",{ icon: 1, time: 1000 });
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
