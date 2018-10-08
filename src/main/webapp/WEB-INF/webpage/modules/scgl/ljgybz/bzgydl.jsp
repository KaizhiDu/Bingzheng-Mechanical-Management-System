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

<h4>${mc}（${scjhglLjgl.ljmc}）工艺编制</h4>

<input id="ljid" name="ljid" type="hidden" value="${scjhglLjgl.id}">

<div class="row">
    <div id="ScglGydlbzGridQuery" class="col-md-12">

    </div>
</div>
<grid:grid id="ScglGydlbz"
           url="${adminPath}/scgl/ljgybz/ajaxGydlbzList?ljid=${scjhglLjgl.id}" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <grid:column label="sys.common.opt" name="opt" formatter="button" width="30"/>
    <grid:button title="编制工艺小类" groupname="opt" function="bzgyxl"
                 outclass="btn-success" url="${adminPath}/scgl/ljgybz/bzgyxl?ljid=${scjhglLjgl.id}&id=\"+row.id+\"" />

    <grid:column label="零件名称" name="ljmc" width="100"/>
    <grid:column label="工艺大类" name="gydlmc"  width="100"/>
    <grid:column label="排序" name="px"  width="30"/>

    <%--<grid:column label="设置" name="opt2" formatter="button" width="30"/>--%>
    <%--<grid:button title="设置排序" groupname="opt2" function="szdlpx"--%>
                 <%--outclass="btn-primary" url="${adminPath}/scgl/ljgybz/szdlpx?ljid=${scjhglLjgl.id}&id=\"+row.id+\"" />--%>

    <grid:toolbar function="addGydl" icon="fa fa-plus" btnclass="btn btn-sm btn-primary" title="添加工艺大类"/>\
    <grid:toolbar function="deleteGydl" icon="fa fa-trash-o" btnclass="btn btn-sm btn-danger" title="删除"/>
    <grid:toolbar function="szdlpx" icon="fa fa-edit" btnclass="btn btn-sm btn-warning" title="修改排序"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>


<script type="text/javascript">

    //添加工艺大类
    function addGydl(title, url, gridId, id, width, height, tipMsg) {
        var ljid = $("#ljid").val();
        var url = "${adminPath}/scgl/ljgybz/addGydl?ljid="+ljid;
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["85%", "70%"],
            title: "编制工艺大类",
            maxmin: true, //开启最大化最小化按钮
            content: url ,
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
                setTimeout(function(){top.layer.close(index)}, 1000);//延时0.1秒，对应360 7.1版本bug
                //layer.alert("添加成功！！", {icon: 0, title: '提示'});
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

    //删除工艺大类
    function deleteGydl(title, url, gridId, id, width, height, tipMsg) {
        //获取选中行的id数组
        var idsArray = $("#ScglGydlbzGrid").jqGrid("getGridParam", "selarrrow")
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
            layer.confirm('删除这个大类相关小类也会删除!  确定要删除吗？', {
                    btn: ['确定', '取消']
                }, function (index, layero) {
                    $.ajax({
                        type: "GET",
                        url: "${adminPath}/scgl/ljgybz/deleteGydl?ids="+ids,
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

    //设置大类排序
    function szdlpx(title, url, gridId, id, width, height, tipMsg){
        var url="${adminPath}/scgl/ljgybz/szdlpx?ljid=${scjhglLjgl.id}";
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["45%", "95%"],
            title: "设置排序",
            maxmin: true, //开启最大化最小化按钮
            content: url ,
            success: function(layero, index){
                //遍历父页面的button,使其失去焦点，再按enter键就不会弹框了
                $(":button").each(function () {
                    $(this).blur();
                });
            },
            btn: ['设置', '关闭'],
            yes: function(index, layero){
                var body = top.layer.getChildFrame('body', index);
                var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                //文档地址
                //http://www.layui.com/doc/modules/layer.html#use
                iframeWin.contentWindow.check();
                //判断逻辑并关闭
                setTimeout(function(){top.layer.close(index)}, 200);//延时0.1秒，对应360 7.1版本bug
                layer.alert("修改成功！！", {icon: 0, title: '提示'});
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

    //编制工艺小类
    function bzgyxl(title, url, gridId, id, width, height, tipMsg){
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["95%", "95%"],
            title: "编制工艺小类",
            maxmin: true, //开启最大化最小化按钮
            content: url,
            success: function(layero, index){
                //遍历父页面的button,使其失去焦点，再按enter键就不会弹框了
                $(":button").each(function () {
                    $(this).blur();
                });
            },
            btn: ['关闭'],
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
