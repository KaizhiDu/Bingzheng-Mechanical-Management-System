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
    <html:css name="simditor"/>
    <html:css
            name="bootstrap-fileinput,font-awesome,animate,iCheck,datepicker,jqgrid,sweetalert,Validform,jqgrid"/>
    <html:js
            name="jqGrid,jquery,bootstrap,jquery-ui,peity,iCheck,sweetalert,Validform,jqgrid"/>
    <html:js name="bootstrap-fileinput"/>
    <html:js name="simditor"/>
    <html:js name="laydate"/>
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
<h4>成品</h4>
<hr>
<div class="row">
    <div id="CkglCpGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">计划名称：</label>
                <select name="jhbh" class="form-control" id="jhbh">
                    <option value="">请选择</option>
                    <c:forEach items="${htList}" var="ht">
                        <option value="${ht.htbh}">${ht.htbh}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">零部件名称：</label>
                <select name="lbjmc" class="form-control" id="lbjmc">
                    <option value="">请选择</option>
                    <c:forEach items="${cpList}" var="lbj">
                        <option value="${lbj.lbjmc}">${lbj.lbjmc}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">零部件图号：</label>
                <select name="lbjth" class="form-control" id="lbjth">
                    <option value="">请选择</option>
                    <c:forEach items="${cpList}" var="lbj">
                        <option value="${lbj.lbjth}">${lbj.lbjth}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
</div>

<grid:grid id="CkglCp"
           url="${adminPath}/ckgl/cp/ajaxList" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>

    <grid:column label="sys.common.opt" name="opt" formatter="button" width="100"/>
    <grid:button title="出库" groupname="opt" function="ck"
                 outclass="btn-success" url="${adminPath}/ckgl/cp/ck?id=\"+row.id+\"" />

    <grid:column label="计划名称" name="jhbh"/>
    <grid:column label="零部件名称" name="lbjmc"/>
    <grid:column label="零部件图号" name="lbjth"/>
    <grid:column label="库存数量" name="rksl"/>

    <grid:toolbar function="exportShd" icon="fa fa-file-excel-o" btnclass="btn btn-sm btn-warning" title="导出送货单"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>

<script type="text/javascript">

    //导出送货单
    function exportShd(title, url, gridId, id, width, height, tipMsg){
        $.ajax({
            type: "get",
            url: "${adminPath}/ckgl/cp/exportShd",
            success: function (data) {
                top.layer.alert("导出成功，请在D:/bingzhengjixie文件夹下查看", {icon: 0, title:'提示'});
            }
        });
    }

    //出库
    function ck(title, url, gridId, id, width, height, tipMsg) {
        openDia("出库",url,gridId,"40%","50%");
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
            btn: ['出库', '关闭'],
            yes: function(index, layero){
                var body = top.layer.getChildFrame('body', index);
                var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                //文档地址
                //http://www.layui.com/doc/modules/layer.html#use
                iframeWin.contentWindow.check();
                //判断逻辑并关闭
                setTimeout(function(){top.layer.close(index)}, 300);//延时0.1秒，对应360 7.1版本bug
                layer.alert("出库成功！！", {icon: 0, title: '提示'});
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
