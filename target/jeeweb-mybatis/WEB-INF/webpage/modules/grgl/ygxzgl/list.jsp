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

<h4>员工薪资管理</h4>

<div class="row">
    <div id="GrglYgxzglGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">年度：</label>
                <select name="nd2" class="form-control" id="nd2">
                    <option value="${nd}">${nd}</option>
                    <option value="${nd-1}">${nd-1}</option>
                    <option value="${nd-2}">${nd-2}</option>
                    <option value="${nd-3}">${nd-3}</option>
                </select>
            </div>
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">月份：</label>
                <select name="yf2" class="form-control" id="yf2">
                    <option ${yf eq '1' ? 'selected' : ''} value="1">1</option>
                    <option ${yf eq '2' ? 'selected' : ''} value="2">2</option>
                    <option ${yf eq '3' ? 'selected' : ''} value="3">3</option>
                    <option ${yf eq '4' ? 'selected' : ''} value="4">4</option>
                    <option ${yf eq '5' ? 'selected' : ''} value="5">5</option>
                    <option ${yf eq '6' ? 'selected' : ''} value="6">6</option>
                    <option ${yf eq '7' ? 'selected' : ''} value="7">7</option>
                    <option ${yf eq '8' ? 'selected' : ''} value="8">8</option>
                    <option ${yf eq '9' ? 'selected' : ''} value="9">9</option>
                    <option ${yf eq '10' ? 'selected' : ''} value="10">10</option>
                    <option ${yf eq '11' ? 'selected' : ''} value="11">11</option>
                    <option ${yf eq '12' ? 'selected' : ''} value="12">12</option>
                </select>
            </div>
        </div>
    </div>
</div>

<grid:grid id="GrglYgxzgl"
           url="${adminPath}/grgl/ygxzgl/ajaxYgxzglList?nd=${nd}&yf=${yf}" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <grid:column label="sys.common.opt" name="opt" formatter="button" width="100"/>

    <grid:button title="设置奖罚" groupname="opt" function="szjf"
                 outclass="btn-success" url="${adminPath}/grgl/ygxzgl/szjf?id=\"+row.id+\"" />

    <grid:column label="月份" name="yf"/>
    <grid:column label="姓名" name="xm"/>
    <grid:column label="职位" name="zw"/>
    <grid:column label="职位工资" name="zwgz"/>
    <grid:column label="底薪" name="dx"/>
    <grid:column label="房补" name="fb"/>
    <grid:column label="交通费" name="jtf"/>
    <grid:column label="补贴" name="bt"/>
    <grid:column label="保险(-)" name="bx"/>
    <grid:column label="餐补" name="cq"/>
    <grid:column label="出勤工资" name="zcqgz"/>
    <grid:column label="日工工资" name="rggz"/>
    <grid:column label="承包金额" name="cbje"/>
    <grid:column label="奖励" name="jl"/>
    <grid:column label="扣款(-)" name="kk"/>
    <grid:column label="备注" name="bz"/>
    <grid:column label="合计" name="hj"/>

    <grid:toolbar function="exportGzd" icon="fa fa-file-excel-o" btnclass="btn btn-sm btn-warning" title="导出工资单"/>

    <grid:toolbar function="search"/>
    <%--<grid:toolbar function="reset"/>--%>

</grid:grid>


<script type="text/javascript">
    //设置奖罚
    function szjf(title, url, gridId, id, width, height, tipMsg) {
        openDia("设置奖罚",url,gridId,"50%","50%");
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

    //导出工资单
    function exportGzd(){
        var nd = $("#nd2").val();
        var yf = $("#yf2").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/grgl/ygxzgl/exportGzd",
            data: {
                nd: nd,
                yf: yf
            },
            success: function (data) {
                top.layer.alert("导出成功，请在D:/bingzhengjixie文件夹下查看", {icon: 0, title:'提示'});

            }
        });

    }
</script>
</body>
</html>
