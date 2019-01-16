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
<input type="hidden" id="gsid" name="gsid" value="${htglGs.id}">
<div class="row">
    <div id="RcrwfpGridQuery" class="col-md-12">
        <%--<div class="form-inline">--%>
        <%--<div class="form-group col-md-3" style="margin-bottom: 10px">--%>
        <%--<label class="control-label">日期：</label>--%>
        <%--<input name="rq" id="rq" htmlEscape="false" class="form-control layer-date" pattern="yyyy-MM-dd" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"  placeholder="年-月-日"  datatype="*"/>--%>
        <%--</div>--%>
        <%--</div>--%>
    </div>
</div>
<h2>${htglGs.jf}</h2>
<grid:grid id="Ht" url="${adminPath}/htgl/gs/ajaxHtList?id=${htglGs.id}" pageable="true">


    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <grid:column label="sys.common.opt" name="opt" formatter="button" width="150"/>
    <grid:button title="开发票" groupname="opt" function="kfp"
                 outclass="btn-info" url="${adminPath}/htgl/gs/kfp?id=\"+row.id+\"" />
    <grid:button title="合同详情" groupname="opt" function="xq"
                 outclass="btn-success" url="${adminPath}/htgl/gs/xq?id=\"+row.id+\"" />

    <grid:column label="合同名称" name="htmc"/>
    <grid:column label="合同总额" name="zje"/>
    <grid:column label="尾款" name="je"/>
    <grid:column label="剩余发票" name="fp"/>
    <grid:column label="返款总额" name="fk"/>
    <grid:column label="返款余款" name="fkyk"/>
    <grid:column label="备注" name="bz"/>

    <grid:toolbar function="createHt" icon="fa fa-plus" btnclass="btn btn-sm btn-primary" title="添加"/>
    <grid:toolbar function="deleteHt" icon="fa fa-trash" btnclass="btn btn-sm btn-danger" title="删除"/>

</grid:grid>

<script type="text/javascript">

    //开发票
    function kfp(title, url, gridId, id, width, height, tipMsg){
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["87%", "87%"],
            title: "发票",
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

    //详情
    function xq(title, url, gridId, id, width, height, tipMsg){
        openDia("合同详情",url,gridId,"90%","90%");
    }

    //删除一条合同
    function deleteHt(title, url, gridId, id, width, height, tipMsg){

        //获取选中行的id数组
        var idsArray = $("#HtGrid").jqGrid("getGridParam", "selarrrow")
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
            layer.confirm('是否要删除该合同!', {
                    btn: ['确定', '取消']
                }, function (index, layero) {
                    $.ajax({
                        type: "GET",
                        url: "${adminPath}/htgl/gs/deleteHt?ids="+ids,
                        success: function (data) {
                            refreshTable2(gridId);
                        }
                    });
                    layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
                    layer.msg("删除成功!",{ icon: 1, time: 1000 });

                }
            );
    }
    else {
            top.layer.alert('请选择需要删除的数据!', {icon: 0, title:'警告'});
            return;

        }
    }

    //转到合同页面
    function ht(title, url, gridId, id, width, height, tipMsg){
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["95%", "95%"],
            title: "合同",
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

    //添加公司
    function createHt(title, url, gridId, id, width, height, tipMsg){
        var gsid = $("#gsid").val();
        url = "${adminPath}/htgl/gs/createHt?gsid="+gsid;
        openDia("添加合同",url,gridId,"50%","50%");
    }

    //修改公司
    function xg(title, url, gridId, id, width, height, tipMsg){
        openDia("修改",url,gridId,"50%","50%");
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
