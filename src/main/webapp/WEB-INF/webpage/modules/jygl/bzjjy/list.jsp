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

<h4>标准件检验</h4>

<div class="row">
    <div id="bzjglGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">计划名称：</label>
                <select name="htid" class="form-control" id="htid">
                    <option value="">请选择</option>
                    <c:forEach items="${htList}" var="ht">
                        <option value="${ht.id}">${ht.htbh}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">规格：</label>
                <input name="gg" id="gg" htmlEscape="false" class="form-control" placeholder="请输入入库"/>
            </div>
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">入库人：</label>
                <select name="rksl" class="form-control" id="rksl">
                    <option value="">请选择</option>
                    <option value="冯立">冯立</option>
                    <option value="孙旭东">孙旭东</option>
                    <option value="张爱荣">张爱荣</option>
                </select>
            </div>
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">年（入库）：</label>
                <input name="n" id="n" htmlEscape="false" class="form-control" placeholder="请输入年"/>
            </div>
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">月（入库）：</label>
                <input name="y" id="y" htmlEscape="false" class="form-control" placeholder="请输入月"/>
            </div>
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">日（入库）：</label>
                <input name="r" id="r" htmlEscape="false" class="form-control" placeholder="请输入日"/>
            </div>
            <%--<div class="form-group col-md-3" style="margin-bottom: 10px">--%>
            <%--<label class="control-label">零部件名称：</label>--%>
            <%--<input name="ljmc" id="ljmc" htmlEscape="false" class="form-control" placeholder="请输入零部件名称"/>--%>
            <%--</div>--%>
            <%--<div class="form-group col-md-3" style="margin-bottom: 10px">--%>
            <%--<label class="control-label">排序方式：</label>--%>
            <%--<select name="pxfs" class="form-control" id="pxfs">--%>
            <%--<option value="">请选择</option>--%>
            <%--<option value="1">根据计划图号</option>--%>
            <%--<option value="2">根据添加时间</option>--%>
            <%--</select>--%>
            <%--</div>--%>
        </div>
    </div>
</div>

<grid:grid id="bzjgl"
           url="${adminPath}/jygl/bzjjy/ajaxBzjList" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <grid:column label="sys.common.opt" name="opt" formatter="button" width="100"/>

    <grid:button title="检验入库" groupname="opt" function="jyrk"
                 outclass="btn-success" url="${adminPath}/jygl/bzjjy/jyrk?id=\"+row.id+\"" />
    <%--<grid:button title="追加数量" groupname="opt" function="zjsl"--%>
    <%--outclass="btn-info" url="${adminPath}/scjhgl/ljgl/zjsl?id=\"+row.id+\"" />--%>
    <%--<grid:button title="删除" groupname="opt" function="deleteWorker"--%>
    <%--outclass="btn-danger" url="${adminPath}/grgl/grgl/deleteWorker?id=\"+row.id+\"" />--%>
    <grid:column label="计划名称" name="htid"/>
    <grid:column label="分类大类" name="fldl"/>
    <grid:column label="分类小类" name="flxl"/>
    <grid:column label="规格" name="gg"/>
    <grid:column label="单位" name="dw"/>
    <grid:column label="数量" name="sl"/>
    <grid:column label="入库人" name="rksl"/>
    <grid:column label="进货商" name="jhs"/>

    <%--<grid:toolbar function="ckrkxq" icon="fa fa-edit" title="查看入库详情" btnclass="btn-primary"/>--%>
    <grid:toolbar function="exportBzj" icon="fa fa-file-excel-o" title="导出" btnclass="btn-warning"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>


<script type="text/javascript">

    function jyrk(title, url, gridId, id, width, height, tipMsg){
        openDia("检验入库",url,gridId,"35%","40%");
    }

    function zjsl(title, url, gridId, id, width, height, tipMsg){
        openDia("追加数量",url,gridId,"25%","30%");
    }

    //导出
    function exportBzj(){
        var jhid = $("#htid").val();
        $.ajax({
            type: "get",
            url: "${adminPath}/jygl/bzjjy/exportBzj?jhid="+jhid,
            success: function (data) {
                layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
                top.layer.alert("导出成功，请在D:/bingzhengjixie文件夹下查看", {icon: 0, title:'提示'});
            }
        });

    }

    //修改数量
    function modifyBzj(title, url, gridId, id, width, height, tipMsg){
        openDia("修改标准件数量",url,gridId,"40%","40%");
    }

    //添加零部件
    function createBzj(title, url, gridId, id, width, height, tipMsg) {
        var url = "${adminPath}/scjhgl/bzjgl/createBzj";
        openDia("添加标准件件",url,gridId,"40%","60%");
    }

    //删除零部件
    function deleteBzj(title, url, gridId, id, width, height, tipMsg){
        //获取选中行的id数组
        var idsArray = $("#bzjglGrid").jqGrid("getGridParam", "selarrrow")
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
            layer.confirm('是否要删除该标准件!', {
                    btn: ['确定', '取消']
                }, function (index, layero) {
                    $.ajax({
                        type: "GET",
                        url: "${adminPath}/scjhgl/bzjgl/deleteBzj?ids="+ids,
                        success: function (data) {
                            refreshTable2(gridId);
                        }
                    });
                    layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
                    layer.msg("删除成功!",{ icon: 1, time: 1000 });

                }
            );
        }
        else {
            top.layer.alert('请选择要删除的数据!', {icon: 0, title:'警告'});
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
                layer.msg("保存成功!",{ icon: 1, time: 1000 });
                refreshTable2(gridId);
            },
            cancel: function(index){
                refreshTable2(gridId);
            },
            end: function (index) {
                refreshTable2(gridId);
            }
        });
    }

    //更新到当前页
    function refreshTable2(gridId){
        var queryParams = {};
        var queryFields=$('#queryFields').val();
        var curpagenum = $("#"+gridId+"").jqGrid('getGridParam', 'page');
        queryParams['queryFields'] = queryFields;
        //普通的查询
        $('#' + gridId + "Query").find(":input").each(function() {
            var val = $(this).val();
            if (queryParams[$(this).attr('name')]) {
                val = queryParams[$(this).attr('name')] + "," + $(this).val();
            }
            queryParams[$(this).attr('name')] = val;
        });

        // 普通的查询
        $('#' + gridId + "Query").find(":input").each(function() {
            var condition = $(this).attr('condition');
            if (!condition) {
                condition = "";
            }
            var key = "query." + $(this).attr('name') + "||" + condition;
            queryParams[key] = queryParams[$(this).attr('name')];
        });
        //刷新
        //传入查询条件参数
        $("#"+gridId).jqGrid('setGridParam',{
            datatype:'json',
            postData:queryParams, //发送数据
            page:curpagenum
        }).trigger("reloadGrid"); //重新载入
    }
</script>
</body>
</html>
