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

<h4>零部件进度图</h4>

<div class="row">
    <div id="ljglGridQuery" class="col-md-12">
        <div class="form-inline">
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">计划名称：</label>
                <select name="htid" class="form-control" id="htid" onchange="cxlj()">
                    <option value="">请选择</option>
                    <c:forEach items="${htList}" var="ht">
                        <option value="${ht.id}">${ht.htbh}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-3" style="margin-bottom: 10px">
                <label class="control-label">零部件名称：</label>
                <select name="id" class="form-control" id="id">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
    </div>
</div>

<grid:grid id="ljgl"
           url="${adminPath}/jygl/lbjrk/ajaxlbjglList" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <grid:column label="sys.common.opt" name="opt" formatter="button" width="100"/>

    <%--<grid:button title="修改" groupname="opt" function="modifyWorker"--%>
    <%--outclass="btn-success" url="${adminPath}/grgl/grgl/updateWorker?id=\"+row.id+\"" />--%>
    <grid:button title="查看进度图" groupname="opt" function="checkJdt"
    outclass="btn-primary" url="${adminPath}/sjfx/lbjjdt/checkJdt?lbjid=\"+row.id+\"" />
    <grid:column label="计划名称" name="htid"/>
    <grid:column label="零部件名称" name="ljmc"/>
    <grid:column label="零部件图号" name="ljth"/>
    <grid:column label="单用量" name="dyl"/>
    <grid:column label="数量" name="sl"/>
    <grid:column label="未入库数量" name="wrksl"/>

    <%--<grid:toolbar function="createLj" icon="fa fa-plus" btnclass="btn btn-sm btn-primary" title="添加零部件"/>--%>
    <%--<grid:toolbar function="deleteLj" icon="fa fa-trash-o" title="删除" btnclass="btn-danger"/>--%>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>


<script type="text/javascript">

    //根据计划ID查询零部件信息
    function cxlj() {
        var htid = $("#htid").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/scgl/gybzgl/cxLj?jhid="+htid,
            success : function (data) {
                $("#id").text("");
                $("#id").append(
                    "<option value=''>请选择</option>"
                );
                for (var i=0;i<data.length;i++){
                    var jh = data[i];
                    $("#id").append(
                        "<option value='"+jh.ljid+"'>"+jh.ljmc+"</option>"
                    );
                }

            }
        });
    }

    //查看进度图
    function checkJdt(title, url, gridId, id, width, height, tipMsg) {
        openDia("查看进度图",url,gridId,"50%","60%");
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
            btn: ['关闭'],
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
