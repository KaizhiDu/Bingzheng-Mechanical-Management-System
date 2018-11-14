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
<h4>标准件</h4>
<hr>
<div class="row">
    <div id="BzjGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">仓库大类：</label>
                <select id="fldl" name="fldl" class="form-control" onchange="getFlxl()">
                    <option value="">请选择</option>
                    <c:forEach var="dl" items="${DlList}">
                        <option value="${dl.id}">${dl.dlmc}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">仓库小类：</label>
                <select id="flxl" name="flxl" class="form-control">
                    <option value="">请选择</option>

                </select>
            </div>
            <div class="form-group col-md-4" style="margin-bottom: 10px">
                <label class="control-label">规格：</label>
                <input name="gg" id="gg" htmlEscape="false" class="form-control" placeholder="支持按照关键字查询">
            </div>
        </div>
    </div>
</div>

<grid:grid id="Bzj"
           url="${adminPath}/ckgl/bzj/ajaxBzjList" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>

    <grid:column label="sys.common.opt" name="opt" formatter="button" width="200"/>
    <grid:button title="入库" groupname="opt" function="rk"
                 outclass="btn-success" url="${adminPath}/ckgl/bzj/rk?id=\"+row.id+\"" />
    <grid:button title="出库" groupname="opt" function="ck"
                 outclass="btn-primary" url="${adminPath}/ckgl/bzj/ck?id=\"+row.id+\"" />
    <grid:button title="查看进销详情" groupname="opt" function="ckxq"
                 outclass="btn-warning" url="${adminPath}/ckgl/bzj/ckxq?id=\"+row.id+\"" />
    <grid:button title="修改" groupname="opt" function="xg"
                 outclass="btn-info" url="${adminPath}/ckgl/bzj/xg?id=\"+row.id+\"" />

    <grid:column label="大类" name="fldl"/>
    <grid:column label="小类" name="flxl"/>
    <grid:column label="规格" name="gg"/>
    <grid:column label="单位" name="dw"/>
    <grid:column label="库存" name="kc"/>
    <grid:column label="预警量" name="yjkc"/>
    <grid:column label="备注" name="bz"/>

    <grid:toolbar function="createBzj" icon="fa fa-plus" btnclass="btn btn-sm btn-primary" title="添加标准件"/>
    <grid:toolbar function="delete"/>
    <grid:toolbar function="jxxq" icon="fa fa-edit" btnclass="btn btn-sm btn-info" title="进销详情"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>

<script type="text/javascript">

    //修改
    function xg(title, url, gridId, id, width, height, tipMsg){
        openDia("修改",url,gridId,"40%","40%");
    }

    //查看整体进销详情
    function jxxq(title, url, gridId, id, width, height, tipMsg){
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["85%", "80%"],
            title: "查看进销详情",
            maxmin: true, //开启最大化最小化按钮
            content: "${adminPath}/ckgl/bzj/jxxq",
            success: function(layero, indexV){
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

    //添加标准件
    function createBzj(title, url, gridId, id, width, height, tipMsg) {
        var url = "${adminPath}/ckgl/bzj/createBzj";
        openDia("添加标准件",url,gridId,"60%","60%");
    }

    //入库
    function rk(title, url, gridId, id, width, height, tipMsg) {
        openDia("入库",url,gridId,"40%","50%");
    }

    //出库
    function ck(title, url, gridId, id, width, height, tipMsg) {
        openDia("出库",url,gridId,"40%","50%");
    }

    //查看进销详情
    function ckxq(title, url, gridId, id, width, height, tipMsg) {
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["30%", "80%"],
            title: "查看进销详情",
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

    //根据大类id拿到所有小类名
    function getFlxl() {
        var fldl = $("#fldl").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/ckgl/bzj/getFlxl?fldl="+fldl,
            success : function (data) {
                $("#flxl").text("");
                $("#flxl").append(
                    "<option value=''>请选择</option>"
                );
                for (var i=0;i<data.length;i++){
                    var xl = data[i];
                    $("#flxl").append(
                        "<option value='"+xl.xlmc+"'>"+xl.xlmc+"</option>"
                    );
                }

            }
        });
    }
</script>

</body>
</html>
