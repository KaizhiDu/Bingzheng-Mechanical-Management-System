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

<h4>包工检验</h4>
<div class="row">
    <div id="BgjyGridQuery" class="col-md-12">
        <div class="form-inline">
             <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">工人：</label>
                <select name="xm" class="form-control" id="xm">
                    <option value="">请选择</option>
                    <c:forEach items="${ygsjList}" var="each">
                        <option value="${each.xm}">${each.xm}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
</div>
<grid:grid id="Bgjy"
           url="${adminPath}/jygl/bgjy/ajaxBgjyList" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <grid:column label="sys.common.key" hidden="true" name="bgrwfpid"/>
    <grid:column label="sys.common.opt" name="opt" formatter="button" width="60"/>

    <grid:button title="包工明细" groupname="opt" function="jy"
                 outclass="btn-success" url="${adminPath}/jygl/bgjy/jy?id=\"+row.bgrwfpid+\"" />
    <grid:button title="检验" groupname="opt" function="sfhg"
                 outclass="btn-primary" url="${adminPath}/jygl/bgjy/sfhg?id=\"+row.id+\"&bgrwfpid=\"+row.bgrwfpid+\"" />

    <grid:column label="日期" name="rq"/>
    <grid:column label="姓名" name="xm"/>
    <grid:column label="职位" name="zw"/>
    <grid:column label="承包金额" name="cbje"/>
    <grid:column label="注释" name="zs"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>

<script type="text/javascript">
    //查看包工明细
    function jy(title, url, gridId, id, width, height, tipMsg){
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["90%", "85%"],
            title: "包工检验",
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

    //检验
    function sfhg(title, url, gridId, id, width, height, tipMsg){
        layer.confirm('确定检验合格吗？', {
                btn: ['合格', '取消']
            }, function (index, layero) {
                $.ajax({
                    type: "GET",
                    url: url,
                    success: function (data) {
                        refreshTable(gridId);
                    }
                });
                layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框
                layer.msg("检验合格!",{ icon: 1, time: 1000 });

            }
        );
    }


</script>
</body>
</html>
