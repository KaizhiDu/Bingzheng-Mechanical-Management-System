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

<h4>员工考勤记录</h4>

<div class="row">
    <div id="GrglYgkqjlGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">工人：</label>
                <select name="name" class="form-control" id="name">
                    <option value="">请选择</option>
                    <c:forEach items="${ygsjList}" var="each">
                        <option value="${each.name}">${each.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">日期：</label>
                <input name="rq" id="rq" htmlEscape="false" class="form-control layer-date" pattern="yyyy-MM-dd" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"  placeholder="年-月-日"  datatype="*"/>
            </div>
        </div>
    </div>
</div>

<grid:grid id="GrglYgkqjl"
           url="${adminPath}/grgl/ygkqjl/ajaxGrglYgkqjlList?currentTime=${currentTime}" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <grid:column label="sys.common.opt" name="opt" formatter="button" width="100"/>

    <grid:button title="记录考勤" groupname="opt" function="jlkq"
                 outclass="btn-success" url="${adminPath}/grgl/ygkqjl/jlkq?id=\"+row.id+\"&rq=\"+row.rq+\"" />

    <grid:column label="日期" name="rq"/>
    <grid:column label="姓名" name="name"/>
    <grid:column label="考勤时间" name="kqsj"/>
    <grid:column label="夜班" name="jb" dict="kq" dateformat=""/>
    <grid:column label="缺勤原因" name="qqyy"/>

    <grid:toolbar function="Szkqjcsj" icon="fa fa-plus" btnclass="btn btn-sm btn-primary" title="设置考勤基础数据"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>

</grid:grid>


<script type="text/javascript">

    //设置考勤基础数据
    function Szkqjcsj(title, url, gridId, id, width, height, tipMsg){
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["50%", "50%"],
            title: "设置考勤基础",
            maxmin: true, //开启最大化最小化按钮
            content: "${adminPath}/grgl/ygkqjl/Szkqjcsj" ,
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

    //记录考勤
    function jlkq(title, url, gridId, id, width, height, tipMsg) {
        openDia("记录考勤",url,gridId,"40%","40%");
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
                //refreshTable2(gridId);
            },
            cancel: function(index){
                //refreshTable2(gridId);
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
