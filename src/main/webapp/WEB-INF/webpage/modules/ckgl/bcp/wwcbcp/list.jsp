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
<h4>未完成半成品</h4>
<hr>
<div class="row">
    <div id="CkglBcpGridQuery" class="col-md-12">
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
                    <c:forEach items="${bcpList}" var="lbj">
                        <option value="${lbj.lbjmc}">${lbj.lbjmc}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">零部件图号：</label>
                <select name="lbjth" class="form-control" id="lbjth">
                    <option value="">请选择</option>
                    <c:forEach items="${bcpList}" var="lbj">
                        <option value="${lbj.lbjth}">${lbj.lbjth}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
</div>

<grid:grid id="CkglBcp"
           url="${adminPath}/ckgl/bcp/ywcbcp/ajaxBcpList2" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>

    <grid:column label="sys.common.opt" name="opt" formatter="button" width="100"/>
    <grid:button title="工艺详情" groupname="opt" function="xq"
                 outclass="btn-success" url="${adminPath}/ckgl/bcp/wwcbcp/xq?id=\"+row.id+\"" />
    <grid:button title="加入生产" groupname="opt" function="jrsc"
                 outclass="btn-primary" url="${adminPath}/ckgl/bcp/wwcbcp/jrsc?bcpid=\"+row.id+\"" />

    <grid:column label="计划名称" name="jhbh"/>
    <grid:column label="零部件名称" name="lbjmc"/>
    <grid:column label="零部件图号" name="lbjth"/>
    <grid:column label="库存数量" name="rksl"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>


<script type="text/javascript">
    //详情
    function xq(title, url, gridId, id, width, height, tipMsg) {
        openDia("零部件详情",url,gridId,"50%","70%");
    }

    //加入生产
    function jrsc(title, url, gridId, id, width, height, tipMsg) {
        layer.confirm('是否要加入生产吗!', {
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
                layer.msg("操作成功!",{ icon: 1, time: 1000 });

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
