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

<h4>设备管理</h4>
<div class="row">
    <div id="SbglGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">设备类型：</label>
                <select name="ssdl" class="form-control" id="ssdl">
                    <option value="">请选择</option>
                    <c:forEach items="${list}" var="each">
                        <option value="${each.id}">${each.flmc}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">状态：</label>
                <select name="zt" class="form-control" id="zt">
                    <option value="">请选择</option>
                    <option value="1">可用</option>
                    <option value="0">停用</option>
                    <option value="2">维修</option>
                </select>
            </div>
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">设备编号：</label>
                <input htmlEscape="false" class="form-control" placeholder="请输入设备编号"  maxlength="20" id="sbbh" name="sbbh"/>
            </div>
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">设备名称：</label>
                <input htmlEscape="false" class="form-control" placeholder="请输入设备名称"  maxlength="20" id="sbmc" name="sbmc"/>
            </div>
        </div>
    </div>
</div>
<grid:grid id="Sbgl"
           url="${adminPath}/sbgl/sbgl/ajaxListSbgl2" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <grid:column label="sys.common.opt" name="opt" formatter="button" width="100"/>

    <grid:button title="修改" groupname="opt" function="updateSb"
                 outclass="btn-success" url="${adminPath}/sbgl/sbgl/updateSb?id=\"+row.id+\"" />
    <grid:button title="删除" groupname="opt" function="deleteSb"
                 outclass="btn-danger" url="${adminPath}/sbgl/sbgl/deleteSb?id=\"+row.id+\"" />

    <grid:column label="设备编号" name="sbbh"/>
    <grid:column label="设备名称" name="sbmc"/>
    <grid:column label="设备类型" name="ssdl"/>
    <grid:column label="状态" name="zt" dict="SBZT" formatterValue=""/>

    <grid:toolbar function="createSb" icon="fa fa-plus" btnclass="btn btn-sm btn-primary" title="添加"/>
    <grid:toolbar function="delete" title="删除" btnclass="btn-danger"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>


<script type="text/javascript">
    //添加一个设备
    function createSb(title, url, gridId, id, width, height, tipMsg) {
        var url = "${adminPath}/sbgl/sbgl/createSb";
        openDia("添加设备",url,gridId,"800px","350px");
    }

    //修改一个设备
    function updateSb(title, url, gridId, id, width, height, tipMsg){
        openDia("更新设备",url,gridId,"800px","350px");
    }

    //删除一个设备
    function deleteSb(title, url, gridId, id, width, height, tipMsg){
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
