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

<h4>添加工艺小类</h4>

<input id="dlid" name="dlid" type="hidden" value="${dlid}">

<div class="row">
    <div id="SbglGridQuery" class="col-md-12">
        <div class="form-inline">

        </div>
    </div>
</div>
<grid:grid id="Gymbsz"
           url="${adminPath}/scgl/gymbsz/ajaxGymbszList" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <grid:column label="sys.common.opt" name="opt" formatter="button" width="100"/>

    <grid:button title="设置工艺小类" groupname="opt" function="szgyxl"
                 outclass="btn-success" url="${adminPath}/scgl/gymbsz/szgyxl?id=\"+row.id+\"" />

    <grid:column label="工艺大类代码" name="gydldm"/>
    <grid:column label="工艺大类名称" name="gydlmc"/>
    <grid:column label="是否启用" name="sfqy" dict="SBZT" formatterValue=""/>

    <grid:toolbar function="addGyxl" icon="fa fa-plus" btnclass="btn btn-sm btn-primary" title="添加工艺小类"/>
    <grid:toolbar function="delete" title="删除" btnclass="btn-danger"/>

    <%--<grid:toolbar function="search"/>--%>
    <%--<grid:toolbar function="reset"/>--%>
</grid:grid>


<script type="text/javascript">

    //添加工艺小类
    function addGyxl(title, url, gridId, id, width, height, tipMsg){
        var dlid = $("#dlid").val();
        url = "${adminPath}/scgl/gymbsz/addGyxl?dlid="+dlid;
        openDia("添加工艺小类",url,gridId,"1200px","800px");
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
                //setTimeout(function(){top.layer.close(index)}, 200);//延时0.1秒，对应360 7.1版本bug
                //layer.alert("保存成功！！", {icon: 0, title: '提示'});
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
