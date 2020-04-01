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
            name="layer,laydate,jqGrid,jquery,bootstrap,jquery-ui,peity,iCheck,sweetalert,Validform,jqgrid"/>
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

<div class="row">
    <h4>合同管理</h4>

    <div id="htglGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">合同号：</label>
                <input name="hth" id="hth" htmlEscape="false" class="form-control" placeholder="模糊搜索合同号"/>
            </div>
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">企业合同号：</label>
                <input name="qyhth" id="qyhth" htmlEscape="false" class="form-control" placeholder="模糊搜索企业合同号"/>
            </div>
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">签订日期：</label>
                <input name="qdrq" id="qdrq" htmlEscape="false"
                       class="form-control layer-date" pattern="yyyy-MM-dd"
                       onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" placeholder="年-月-日"
                       datatype="*"/>
            </div>
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">所属公司：</label>
                <select id="ssgs" name="ssgs" class="form-control">
                    <option value="">请选择</option>
                    <c:forEach var="item" items="${gsList}">
                        <option value="${item.mc}">${item.mc}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
</div>

<grid:grid id="htgl" url="${adminPath}/htgl/htgl/ajaxHtglList" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <grid:column label="sys.common.opt" name="opt" formatter="button" width="400"/>
    <grid:button title="合同明细" groupname="opt" function="htmx"
                 outclass="btn-primary" url="${adminPath}/htgl/htgl/htmx?id=\"+row.id+\"" />
    <grid:button title="合同进度" groupname="opt" function="htjd"
                 outclass="btn-success" url="${adminPath}/htgl/htgl/htjd?id=\"+row.id+\"" />
    <grid:button title="发票" groupname="opt" function="fp"
                 outclass="btn-warning" url="${adminPath}/htgl/htgl/fp?id=\"+row.id+\"" />
    <grid:button title="回款" groupname="opt" function="hk"
                 outclass="btn-info" url="${adminPath}/htgl/htgl/hk?id=\"+row.id+\"" />

    <grid:column label="合同号" name="hth"/>
    <grid:column label="企业编号" name="qyhth"/>
    <grid:column label="合同名称" name="mc"/>
    <grid:column label="签订日期" name="qdrq"/>
    <grid:column label="工期" name="gq"/>
    <grid:column label="所属公司" name="ssgs"/>
    <grid:column label="备注" name="bz"/>

    <grid:toolbar function="tjht" icon="fa fa-plus" btnclass="btn btn-sm btn-primary" title="添加合同"/>
    <grid:toolbar function="deleteHt" btnclass="btn btn-sm btn-danger" title="删除"/>
    <grid:toolbar function="reset"/>
    <grid:toolbar function="search"/>

</grid:grid>

<script type="text/javascript">

    //回款
    function hk(title, url, gridId, id, width, height, tipMsg){
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["90%", "90%"],
            title: "回款",
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
                refreshTable2(gridId);
            },
            end: function (index) {
                refreshTable2(gridId);
            }
        });
    }

    //发票
    function fp(title, url, gridId, id, width, height, tipMsg){
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["90%", "90%"],
            title: "发票",
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
                refreshTable2(gridId);
            },
            end: function (index) {
                refreshTable2(gridId);
            }
        });
    }

    //删除合同
    function deleteHt(title, url, gridId, id, width, height, tipMsg){
        //获取选中行的id数组
        var idsArray = $("#htglGrid").jqGrid("getGridParam", "selarrrow");
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
            layer.confirm('是否要删除合同!', {
                    btn: ['确定', '取消']
                }, function (index, layero) {
                    $.ajax({
                        type: "GET",
                        url: "${adminPath}/htgl/htgl/deleteHt?ids="+ids,
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

    //合同进度
    function htjd(title, url, gridId, id, width, height, tipMsg){
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["90%", "90%"],
            title: "合同进度",
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
                refreshTable2(gridId);
            },
            end: function (index) {
                refreshTable2(gridId);
            }
        });
    }

    //合同明细
    function htmx(title, url, gridId, id, width, height, tipMsg){
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["90%", "90%"],
            title: "合同明细",
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
                refreshTable2(gridId);
            },
            end: function (index) {
                refreshTable2(gridId);
            }
        });
    }


    function tjht(title, url, gridId, id, width, height, tipMsg){
        url = "${adminPath}/htgl/htgl/tjht";
        openDia("添加合同",url,gridId,"70%","70%");
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
