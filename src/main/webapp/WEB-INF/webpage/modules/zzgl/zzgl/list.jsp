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
<%--<div class="container-fiuled">--%>
    <%--占满视口,但两边有15px空白.在手机上显示的话不好--%>
<%--</div>--%>
<div class="row">
    <div id="zjfx" class="col-md-12">
        <div class="form-inline">
            <c:forEach var="each" items="${qyList}">
                <div class="form-group col-md-4" style="margin-bottom: 10px">
                    <div class="form-group col-md-5" style="margin-bottom: 10px">
                        <label class="control-label"><font size="6">${each.name}:</font></label>
                    </div>
                    <div class="form-group col-md-7" style="margin-bottom: 10px">
                        <div id="value${each.zjy}">

                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</div>

<div class="row">
    <div id="zj" class="col-md-12">
        <div class="form-group col-md-4" style="margin-bottom: 10px">

        </div>
        <div class="form-group col-md-8" style="margin-bottom: 10px">
            <div id="valueSum">
            <label class="control-label"><font size="8">总计：</font></label>

            </div>

        </div>

    </div>
</div>

<hr>

<div class="row">
    <div id="ZzglGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-2" style="margin-bottom: 10px">
                <label class="control-label">年：</label>
                <input name="n" id="n" htmlEscape="false" class="form-control" value="${nd}"/>
            </div>
            <div class="form-group col-md-2" style="margin-bottom: 10px">
                <label class="control-label">月：</label>
                <select name="y" class="form-control" id="y">
                    <option value="${yf}">${yf}</option>
                    <option value="">全部</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                </select>
            </div>
            <div class="form-group col-md-2" style="margin-bottom: 10px">
                <label class="control-label">日：</label>
                <select name="r" class="form-control" id="r">
                    <option value="${r}">${r}</option>
                    <option value="">全部</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                    <option value="16">16</option>
                    <option value="17">17</option>
                    <option value="18">18</option>
                    <option value="19">19</option>
                    <option value="20">20</option>
                    <option value="21">21</option>
                    <option value="22">22</option>
                    <option value="23">23</option>
                    <option value="24">24</option>
                    <option value="25">25</option>
                    <option value="26">26</option>
                    <option value="27">27</option>
                    <option value="28">28</option>
                    <option value="29">29</option>
                    <option value="30">30</option>
                    <option value="31">31</option>
                </select>
            </div>

            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">单位：</label>
                <input name="mx" id="mx" htmlEscape="false" class="form-control" placeholder="请输入单位名称"/>
            </div>

            <div class="form-group col-md-2" style="margin-bottom: 10px">
                <label class="control-label">资金流动类型：</label>
                <select name="lx" class="form-control" id="lx">
                    <option value="">全部</option>
                    <option value="0">收入</option>
                    <option value="1">支出</option>
                    <option value="2">调动</option>
                </select>
            </div>
            <%--<div class="form-group col-md-2" style="margin-bottom: 10px">--%>
                <%--<label class="control-label">排序：</label>--%>
                <%--<select name="px" class="form-control" id="px">--%>
                    <%--<option value="0">时间</option>--%>
                    <%--<option value="1">类型</option>--%>
                <%--</select>--%>
            <%--</div>--%>
        </div>
    </div>
</div>

<grid:grid id="Zzgl"
           url="${adminPath}/zzgl/zzgl/ajaxZzglList" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <grid:column label="sys.common.opt" name="opt" formatter="button" width="100"/>
    <grid:button title="修改时间" groupname="opt" function="xgsj"
    outclass="btn-success" url="${adminPath}/zzgl/zzgl/xgsj?id=\"+row.id+\"" />
    <%--<grid:button title="删除" groupname="opt" function="deleteWorker"--%>
    <%--outclass="btn-danger" url="${adminPath}/grgl/grgl/deleteWorker?id=\"+row.id+\"" />--%>

    <grid:column label="日" name="r" width="30"/>
    <grid:column label="类型" name="lx" width="45"/>
    <grid:column label="单位" name="mx" width="150"/>
    <grid:column label="明细" name="mxbc" width="150"/>

<c:forEach var="each" items="${qyList}">
    <grid:column label="${each.name}" name="${each.ywzjy}"/>
</c:forEach>

    <%--<grid:column label="${jcszZzseName.one}" name="one"/>--%>
    <%--<grid:column label="${jcszZzseName.two}" name="two"/>--%>
    <%--<grid:column label="${jcszZzseName.three}" name="three"/>--%>
    <%--<grid:column label="${jcszZzseName.four}" name="four"/>--%>
    <%--<grid:column label="${jcszZzseName.five}" name="five"/>--%>
    <%--<grid:column label="${jcszZzseName.six}" name="six"/>--%>
    <%--<grid:column label="${jcszZzseName.seven}" name="seven"/>--%>
    <%--<grid:column label="${jcszZzseName.eight}" name="eight"/>--%>

    <grid:toolbar function="sr" btnclass="btn btn-sm btn-primary" title="收入"/>
    <grid:toolbar function="zc" btnclass="btn btn-sm btn-success" title="支出"/>
    <grid:toolbar function="dd" btnclass="btn btn-sm btn-info" title="调动"/>
    <grid:toolbar function="jh" btnclass="btn btn-sm btn-primary" title="借还"/>
    <grid:toolbar function="deleteZzgl" icon="fa fa-trash" title="删除" btnclass="btn btn-sm btn-danger"/>
    <grid:toolbar function="exprortZzgl" icon="fa fa-file-excel-o" btnclass="btn btn-sm btn-warning" title="导出"/>

    <grid:toolbar function="search"/>
    <%--<grid:toolbar function="reset"/>--%>
</grid:grid>


<script type="text/javascript">

    //导出
    function exprortZzgl(title, url, gridId, id, width, height, tipMsg){
        url = "${adminPath}/zzgl/zzgl/dcym";

        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["40%", "40%"],
            title: "导出资金流动单",
            maxmin: true, //开启最大化最小化按钮
            content: url ,
            success: function(layero, index){
                //遍历父页面的button,使其失去焦点，再按enter键就不会弹框了
                $(":button").each(function () {
                    $(this).blur();
                });
            },
            btn: ['导出', '关闭'],
            yes: function(index, layero){
                var body = top.layer.getChildFrame('body', index);
                var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                //文档地址
                //http://www.layui.com/doc/modules/layer.html#use
                iframeWin.contentWindow.check();
                //判断逻辑并关闭
                setTimeout(function(){top.layer.close(index)}, 100);//延时0.1秒，对应360 7.1版本bug
//                layer.alert("保存成功！！", {icon: 0, title: '提示'});

            },
            cancel: function(index){
                refreshTable2(gridId);
            },
            end: function (index) {
                refreshTable2(gridId);
                top.layer.alert("导出成功，请在D:/bingzhengjixie文件夹下查看", {icon: 0, title:'提示'});
                //location.reload();
            }
        });

        //openDia("导出资金流动单",url,gridId,"40%","30%");
        <%--var n = $("#n").val();--%>
        <%--var y = $("#y").val();--%>
        <%--var r = $("#r").val();--%>
        <%--var lx = $("#lx").val();--%>
        <%--var px = $("#px").val();--%>
        <%--$.ajax({--%>
            <%--type: "get",--%>
            <%--url: "${adminPath}/zzgl/zzgl/exprortZzgl?n="+n+"&y="+y+"&r="+r+"&lx="+lx+"&px="+px,--%>
            <%--success: function (data) {--%>
                <%--top.layer.alert("导出成功，请在D:/bingzhengjixie文件夹下查看", {icon: 0, title:'提示'});--%>
            <%--}--%>
        <%--});--%>
    }

    //借还
    function jh(title, url, gridId, id, width, height, tipMsg){
        var url = "${adminPath}/zzgl/zzgl/jh";
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["90%", "90%"],
            title: "借还",
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

    window.onload=function(){
        changeValue();
    }

    //修改时间
    function xgsj(title, url, gridId, id, width, height, tipMsg){
        openDia("修改时间",url,gridId,"30%","30%");
    }

    //收入
    function sr(title, url, gridId, id, width, height, tipMsg) {
        var url = "${adminPath}/zzgl/zzgl/sr";
        openDia("收入",url,gridId,"40%","70%");
    }

    //支出
    function zc(title, url, gridId, id, width, height, tipMsg) {
        var url = "${adminPath}/zzgl/zzgl/zc";
        openDia("支出",url,gridId,"40%","70%");
    }

    //调动
    function dd(title, url, gridId, id, width, height, tipMsg) {
        var url = "${adminPath}/zzgl/zzgl/dd";
        openDia("调动",url,gridId,"40%","70%");
    }

    //删除部件
    function deleteZzgl(title, url, gridId, id, width, height, tipMsg){
        //获取选中行的id数组
        var idsArray = $("#ZzglGrid").jqGrid("getGridParam", "selarrrow")
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
            //需要提示，确定要删除吗？删除这个计划，相关零部件也会删除
            layer.confirm('确定要删除吗？', {
                    btn: ['确定', '取消']
                }, function (index, layero) {
                    $.ajax({
                        type: "GET",
                        url: "${adminPath}/zzgl/zzgl/deleteZzgl?ids="+ids,
                        success: function (data) {
                            refreshTable2(gridId);
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
                setTimeout(function(){top.layer.close(index)}, 100);//延时0.1秒，对应360 7.1版本bug
//                layer.alert("保存成功！！", {icon: 0, title: '提示'});

            },
            cancel: function(index){
                refreshTable2(gridId);
            },
            end: function (index) {
                refreshTable2(gridId);
                //location.reload();
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

        changeValue();
    }

    //静态改变各个资金源的数值
    function changeValue(){
        $.ajax({
            type: "GET",
            url: "${adminPath}/zzgl/zzgl/changeValue",
            success: function (data) {
                //console.log(data);
                var qyList = data.qyList;
                for(var i=0;i<qyList.length;i++){
                    var each = qyList[i];
                    //alert(each.zjy);
                    $('#value'+each.zjy).html("");
                    var num = each.ywzjy;
                    var value = "";
                    if (num=="one"){
                        value = data.one;
                    }
                    if (num=="two"){
                        value = data.two;
                    }
                    if (num=="three"){
                        value = data.three;
                    }
                    if (num=="four"){
                        value = data.four;
                    }
                    if (num=="five"){
                        value = data.five;
                    }
                    if (num=="six"){
                        value = data.six;
                    }
                    if (num=="seven"){
                        value = data.seven;
                    }
                    if (num=="eight"){
                        value = data.eight;
                    }
                    if (num=="nine"){
                        value = data.nine;
                    }
                    if (num=="ten"){
                        value = data.ten;
                    }
                    if (num=="eleven"){
                        value = data.eleven;
                    }
                    if (num=="twelve"){
                        value = data.twelve;
                    }
                    if (num=="thirteen"){
                        value = data.thirteen;
                    }
                    if (num=="fourteen"){
                        value = data.fourteen;
                    }
                    if (num=="fifteen"){
                        value = data.fifteen;
                    }
                    if (num=="sixteen"){
                        value = data.sixteen;
                    }
                    if (num=="seventeen"){
                        value = data.seventeen;
                    }
                    if (num=="eighteen"){
                        value = data.eighteen;
                    }
                    if (num=="nineteen"){
                        value = data.nineteen;
                    }
                    if (num=="twenty"){
                        value = data.twenty;
                    }
                    $("#value"+each.zjy).append("<font size='6'>"+value+"</font>");
                }
                $('#valueSum').html("");
                $("#valueSum").append("<label class='control-label'><font size='8'>总计：</font></label><font size='8'>"+data.sum+"</font>");

            }
        });
    }
</script>
</body>
</html>
