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

<h4>员工信息</h4>
<hr/>

<grid:grid id="Grgl"
           url="${adminPath}/grgl/grgl/ajaxListGrgl" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <grid:column label="sys.common.opt" name="opt" formatter="button" width="100"/>

    <grid:button title="修改" groupname="opt" function="modifyWorker"
                 outclass="btn-success" url="${adminPath}/grgl/grgl/updateWorker?id=\"+row.id+\"" />
    <grid:button title="删除" groupname="opt" function="deleteWorker"
                 outclass="btn-danger" url="${adminPath}/grgl/grgl/deleteWorker?id=\"+row.id+\"" />

    <grid:column label="姓名" name="name"/>
    <grid:column label="性别" name="gender" dict="sex" dateformat=""/>
    <grid:column label="电话" name="tel"/>
    <grid:column label="邮箱" name="email"/>
    <grid:column label="入职日期" name="enterdate" dateformat=""/>

    <grid:toolbar function="createWorker" icon="fa fa-plus" btnclass="btn btn-sm btn-primary" title="添加"/>
    <grid:toolbar function="delete" title="删除" btnclass="btn-danger"/>
</grid:grid>


<script type="text/javascript">
    //添加一个员工
    function createWorker(title, url, gridId, id, width, height, tipMsg) {
        var url = "${adminPath}/grgl/grgl/createWorker";
        openDia("添加员工",url,gridId,"800px","500px");
    }

    //修改一个员工信息
    function modifyWorker(title, url, gridId, id, width, height, tipMsg) {
        openDia("修改员工",url,gridId,"800px","500px");
    }

    //删除一个员工信息
    function deleteWorker(title, url, gridId, id, width, height, tipMsg) {
        layer.confirm('是否要删除信息!', {
                btn: ['确定', '取消']
            }, function (index, layero) {
            $.ajax({
                type: "GET",
                url: url,
                success: function (data) {
                    refreshTable(gridId);
                }
            });
                layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
                layer.msg("删除成功!",{ icon: 1, time: 1000 });

            }
        );
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
                setTimeout(function(){top.layer.close(index)}, 100);//延时0.1秒，对应360 7.1版本bug
                layer.alert("保存成功", {icon: 0, title: '提示'});
                refreshTable(gridId);
                //layer.alert("保存成功！！", {icon: 0, title: '提示'});
            },
            cancel: function(index){
                refreshTable(gridId);
            }
        });
    }
</script>
</body>
</html>
