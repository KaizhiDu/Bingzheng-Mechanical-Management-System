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

<h4>计划（${scjhglHtgl.htbh}）工艺编制</h4>

<input id="jhid" name="jhid" type="hidden" value="${scjhglHtgl.id}">

<div class="row">
    <div id="ScglGydlbzGridQuery" class="col-md-12">

    </div>
</div>
<grid:grid id="ScglGydlbz"
           url="${adminPath}/scgl/ljgybz/ajaxGydlbzList?jhid=${scjhglHtgl.id}" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <grid:column label="sys.common.opt" name="opt" formatter="button" width="30"/>
    <grid:button title="编制工艺小类" groupname="opt" function="bzgyxl"
                 outclass="btn-success" url="${adminPath}/scgl/ljgybz/bzgyxl?jhid=${scjhglHtgl.id}&id=\"+row.id+\"" />

    <grid:column label="计划编号" name="jhbh" width="30"/>
    <grid:column label="工艺大类" name="gydlmc"  width="200"/>
    <grid:column label="排序" name="px"  width="30"/>

    <grid:column label="设置" name="opt2" formatter="button" width="30"/>
    <grid:button title="设置排序" groupname="opt2" function="szdlpx"
                 outclass="btn-primary" url="${adminPath}/scgl/ljgybz/szdlpx?jhid=${scjhglHtgl.id}&id=\"+row.id+\"" />

    <grid:toolbar function="addGydl" icon="fa fa-plus" btnclass="btn btn-sm btn-primary" title="编制工艺大类"/>\
    <grid:toolbar function="deleteGydl" icon="fa fa-trash-o" btnclass="btn btn-sm btn-danger" title="删除"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>


<script type="text/javascript">

    //编制工艺大类
    function addGydl(title, url, gridId, id, width, height, tipMsg) {
        var jhid = $("#jhid").val();
        var url = "${adminPath}/scgl/ljgybz/addGydl?jhid="+jhid;
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

    //设置大类排序
    function szdlpx(title, url, gridId, id, width, height, tipMsg){
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["40%", "30%"],
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
                setTimeout(function(){top.layer.close(index)}, 500);//延时0.1秒，对应360 7.1版本bug
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

</script>
</body>
</html>
