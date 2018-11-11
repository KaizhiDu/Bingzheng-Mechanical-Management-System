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

<h4>${jhxx.ljmc} - ${gydlbz.gydlmc}</h4>

<input id="gydlid" name="gydlid" type="hidden" value="${gydlbz.gydlid}">
<input id="gydlbzid" name="gydlbzid" type="hidden" value="${gydlbz.id}">

<div class="row">
    <div id="ScglGydlbzGridQuery" class="col-md-12">

    </div>
</div>
<grid:grid id="ScglGyxlbz"
           url="${adminPath}/scgl/ljgybz/ajaxGyxlbzList?gydlbzid=${gydlbz.id}" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>

    <grid:column label="工艺小类" name="gyxlmc"  width="30"/>
    <grid:column label="描述" name="ms"  width="100"/>
    <grid:column label="排序" name="px"  width="30"/>

    <grid:toolbar function="addGyxl" icon="fa fa-plus" btnclass="btn btn-sm btn-primary" title="添加工艺小类"/>
    <%--<grid:toolbar function="deleteGyxl" icon="fa fa-trash-o" btnclass="btn btn-sm btn-danger" title="删除"/>--%>
    <grid:toolbar function="delete"/>
    <grid:toolbar function="szxlpx" icon="fa fa-edit" btnclass="btn btn-sm btn-warning" title="修改排序"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>


<script type="text/javascript">

    //添加工艺大类
    function addGyxl(title, url, gridId, id, width, height, tipMsg) {
        var gydlbzid = $("#gydlbzid").val();
        var url = "${adminPath}/scgl/ljgybz/addGyxl?gydlbzid="+gydlbzid;
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["40%", "50%"],
            title: "添加工艺小类",
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
                setTimeout(function(){top.layer.close(index)}, 200);//延时0.1秒，对应360 7.1版本bug
                layer.msg("添加成功!",{ icon: 1, time: 1000 });
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

    //设置小类排序
    function szxlpx(title, url, gridId, id, width, height, tipMsg){
        var url="${adminPath}/scgl/ljgybz/szxlpx?gydlbzid=${gydlbz.id}";
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["45%", "95%"],
            title: "设置小类排序",
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
                layer.msg("修改成功!",{ icon: 1, time: 1000 });
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
            area: ["95%", "85%"],
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
