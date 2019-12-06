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

<h4>员工职位薪资分布</h4>
<div class="row">
    <div id="GrglXzzwfpGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">职位：</label>
                <select name="zw" class="form-control" id="zw">
                    <option value="">请选择</option>
                    <option value="钳工">钳工</option>
                    <option value="钳工领班">钳工领班</option>
                    <option value="铣工">铣工</option>
                    <option value="车工">车工</option>
                    <option value="数控">数控</option>
                    <option value="数控领班">数控领班</option>
                    <option value="后勤">后勤</option>
                    <option value="保管">保管</option>
                    <option value="司机采购">司机采购</option>
                    <option value="技术">技术</option>
                    <option value="生产">生产</option>
                    <option value="质检仓库">质检仓库</option>
                    <option value="外协采购">外协采购</option>
                    <option value="外协勤务">外协勤务</option>
                </select>
            </div>
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">员工姓名：</label>
                <input htmlEscape="false" class="form-control" placeholder="请输入员工姓名"  maxlength="20" id="name" name="name"/>
            </div>
        </div>
    </div>
</div>
<grid:grid id="GrglXzzwfp"
           url="${adminPath}/grgl/xzzwfp/queryAjax" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>

    <grid:column label="sys.common.opt" name="opt" formatter="button" width="100"/>
    <grid:button title="设置" groupname="opt" function="setEmp"
                 outclass="btn-success" url="${adminPath}/grgl/xzzwfp/setEmp?id=\"+row.id+\"" />

    <grid:column label="姓名" name="name"/>
    <grid:column label="职位" name="zw"/>
    <grid:column label="职位工资" name="zwgz"/>
    <grid:column label="底薪" name="dx"/>
    <grid:column label="时薪" name="sx"/>
    <%--<grid:column label="餐补" name="bgqm"/>--%>
    <grid:column label="房补" name="fb"/>
    <grid:column label="交通费" name="jtf"/>
    <grid:column label="补贴" name="bt"/>
    <grid:column label="保险" name="bx"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>

<script type="text/javascript">
    function setEmp(title, url, gridId, id, width, height, tipMsg) {
        openDia("设置薪资职位",url,gridId,"800px","500px");
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
