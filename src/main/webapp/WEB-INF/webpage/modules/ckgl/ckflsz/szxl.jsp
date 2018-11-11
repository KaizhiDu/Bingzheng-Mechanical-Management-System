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
<input type="hidden" name="dlid" id="dlid" value="${dlid}">
<input type="hidden" name="dlmc" id="dlmc" value="${dlmc}">
<h2>${dlmc}</h2>
<hr>
<div class="row">
    <div id="CkglCpGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-4" style="margin-bottom: 10px">

            </div>
        </div>
    </div>
</div>

<grid:grid id="CkglXl"
           url="${adminPath}/ckgl/ckflsz/ajaxCkxlList?dlid=${dlid}" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>

    <%--<grid:column label="sys.common.opt" name="opt" formatter="button" width="100"/>--%>
    <%--<grid:button title="设置下属小类" groupname="opt" function="szxl"--%>
                 <%--outclass="btn-success" url="${adminPath}/ckgl/ckflsz/szxl?id=\"+row.id+\"" />--%>

    <grid:column label="大类名称" name="dlmc"/>
    <grid:column label="小类名称" name="xlmc"/>

    <grid:toolbar function="createXl" icon="fa fa-plus" btnclass="btn btn-sm btn-primary" title="添加小类"/>
    <grid:toolbar function="deleteXl" icon="fa fa-trash" title="删除" btnclass="btn-danger"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>

<script type="text/javascript">
    //添加大类
    function createXl(title, url, gridId, id, width, height, tipMsg) {
        var dlid = $("#dlid").val();
        var dlmc = $("#dlmc").val();
        var url = "${adminPath}/ckgl/ckflsz/createXl?dlid="+dlid+"&dlmc="+dlmc;
        openDia("添加小类",url,gridId,"30%","30%");
    }

    //删除仓库小类信息
    function deleteXl(title, url, gridId, id, width, height, tipMsg) {
        //获取选中行的id数组
        var idsArray = $("#CkglXlGrid").jqGrid("getGridParam", "selarrrow")
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
            layer.confirm('是否要删除仓库小类!', {
                    btn: ['确定', '取消']
                }, function (index, layero) {
                    $.ajax({
                        type: "GET",
                        url: "${adminPath}/ckgl/ckflsz/deleteXl?ids="+ids,
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
</script>

</body>
</html>
