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

<h4>计划管理</h4>

<div class="row">
    <div id="ljglGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">是否完成：</label>
                <select name="sfwc" class="form-control" id="sfwc">
                    <option value="0">未完成</option>
                    <option value="1">已完成</option>
                </select>
            </div>
        </div>
    </div>
</div>

<grid:grid id="ljgl"
           url="${adminPath}/scgl/ljgybz/ajaxJhglList" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <grid:column label="sys.common.opt" name="opt" formatter="button" width="120"/>
    <grid:button title="修改" groupname="opt" function="updateHt"
    outclass="btn-success" url="${adminPath}/scjhgl/htgl/updateHt?id=\"+row.id+\"" />
    <grid:button title="删除" groupname="opt" function="deleteHt"
                 outclass="btn-danger" url="${adminPath}/scjhgl/htgl/deleteHt/?id=\"+row.id+\"" />
    <grid:button title="复制" groupname="opt" function="copyHt"
                 outclass="btn-warning" url="${adminPath}/scjhgl/htgl/copyHt?id=\"+row.id+\"" />
    <grid:button title="导出仓库比照表" groupname="opt" function="yckbz"
                 outclass="btn-info" url="${adminPath}/scjhgl/htgl/yckbz?id=\"+row.id+\"" />

    <grid:column label="计划名称" name="htbh" width="200"/>
    <grid:column label="描述" name="ms"/>
    <grid:column label="数量" name="sl" width="40"/>
    <grid:column label="创建日期" name="rq"/>

    <grid:toolbar function="createHt" icon="fa fa-plus" btnclass="btn btn-sm btn-primary" title="添加"/>
    <grid:toolbar function="search"/>

</grid:grid>

<script type="text/javascript">
    //导出仓库对照表
    function yckbz(title, url, gridId, id, width, height, tipMsg){
        $.ajax({
            type: "get",
            url: url,
            success: function (data) {
                layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
                top.layer.alert("导出成功，请在D:/bingzhengjixie文件夹下查看", {icon: 0, title:'提示'});
            }
        });
    }

    //添加一个合同
    function createHt(title, url, gridId, id, width, height, tipMsg) {
        var url = "${adminPath}/scjhgl/htgl/createHt";
        openDia("添加单项计划",url,gridId,"45%","40%");
    }

    //复制
    function copyHt(title, url, gridId, id, width, height, tipMsg) {
        openDia("复制计划",url,gridId,"45%","40%");
    }

    function deleteHt(title, url, gridId, id, width, height, tipMsg) {
            //需要提示，确定要删除吗？删除这个计划，相关零部件也会删除
            layer.confirm('确定要删除吗？', {
                    btn: ['确定', '取消']
                }, function (index, layero) {
                    $.ajax({
                        type: "GET",
                        url: url,
                        success: function (data) {
                            console.log(data.msg);
                            refreshTable2(gridId);
                            layer.msg(data.msg,{ icon: 1, time: 1000 });
                        }
                    });
                    layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框


                }
            );

    }

    //修改一个员工信息
    function updateHt(title, url, gridId, id, width, height, tipMsg) {
        openDia("修改单项计划",url,gridId,"45%","40%");
    }

    //打开一个窗口
    function openDia(title,url,gridId,width,height){
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: [width, height],
            title: title,
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
                setTimeout(function(){top.layer.close(index)}, 300);//延时0.1秒，对应360 7.1版本bug
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
