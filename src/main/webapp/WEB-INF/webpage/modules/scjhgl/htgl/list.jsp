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

<grid:grid id="ljgl"
           url="${adminPath}/scgl/ljgybz/ajaxJhglList" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <grid:column label="sys.common.opt" name="opt" formatter="button" width="30"/>
    <grid:button title="修改" groupname="opt" function="updateHt"
    outclass="btn-success" url="${adminPath}/scjhgl/htgl/updateHt?id=\"+row.id+\"" />
    <%--<grid:button title="复制" groupname="opt" function="copyHt"--%>
                 <%--outclass="btn-warning" url="${adminPath}/scjhgl/htgl/copyHt?id=\"+row.id+\"" />--%>

    <grid:column label="计划编号" name="htbh" width="30"/>
    <grid:column label="描述" name="ms"/>
    <grid:column label="数量" name="sl"/>

    <grid:toolbar function="createHt" icon="fa fa-plus" btnclass="btn btn-sm btn-primary" title="添加"/>
    <grid:toolbar function="deleteHt" icon="fa fa-trash-o" title="删除" btnclass="btn-danger"/>
</grid:grid>

<script type="text/javascript">
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
        //获取选中行的id数组
        var idsArray = $("#ljglGrid").jqGrid("getGridParam", "selarrrow")
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
            //需要提示，确定要删除吗？删除这个计划，相关零件也会删除
            layer.confirm('删除这个计划相关零件也会删除!  确定要删除吗？', {
                    btn: ['确定', '取消']
                }, function (index, layero) {
                    $.ajax({
                        type: "GET",
                        url: "${adminPath}/scjhgl/htgl/deleteHt?ids="+ids,
                        success: function (data) {
                            refreshTable(gridId);
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
                layer.alert("保存成功！！", {icon: 0, title: '提示'});
                refreshTable(gridId);
            },
            cancel: function(index){
                refreshTable(gridId);
            },
            end: function (index) {
                refreshTable(gridId);
            }
        });
    }
</script>
</body>
</html>
