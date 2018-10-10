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

<h4>零件管理</h4>

<div class="row">
    <div id="ljglGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">合同编号：</label>
                <select name="htid" class="form-control" id="htid">
                    <option value="">请选择</option>
                    <c:forEach items="${htList}" var="ht">
                        <option value="${ht.id}">${ht.htbh}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">零件图号：</label>
                <input name="ljth" id="ljth" htmlEscape="false" class="form-control" placeholder="请输入零件图号"/>
            </div>
        </div>
    </div>
</div>

<grid:grid id="ljgl"
           url="${adminPath}/scjhgl/ljgl/ajaxljglList" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <%--<grid:column label="sys.common.opt" name="opt" formatter="button" width="100"/>--%>

    <%--<grid:button title="修改" groupname="opt" function="modifyWorker"--%>
                 <%--outclass="btn-success" url="${adminPath}/grgl/grgl/updateWorker?id=\"+row.id+\"" />--%>
    <%--<grid:button title="删除" groupname="opt" function="deleteWorker"--%>
                 <%--outclass="btn-danger" url="${adminPath}/grgl/grgl/deleteWorker?id=\"+row.id+\"" />--%>
    <grid:column label="计划编号" name="htid"/>
    <grid:column label="零件名称" name="ljmc"/>
    <grid:column label="零件图号" name="ljth"/>
    <grid:column label="单用量" name="dyl"/>
    <grid:column label="数量" name="sl"/>
    <grid:column label="未入库数量" name="wrksl"/>

    <grid:toolbar function="createLj" icon="fa fa-plus" btnclass="btn btn-sm btn-primary" title="添加零件"/>
    <grid:toolbar function="delete" title="删除" btnclass="btn-danger"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>


<script type="text/javascript">
    //添加一个员工
    function createLj(title, url, gridId, id, width, height, tipMsg) {
        var url = "${adminPath}/scjhgl/ljgl/createLj";
        openDia("添加零件",url,gridId,"800px","500px");
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
