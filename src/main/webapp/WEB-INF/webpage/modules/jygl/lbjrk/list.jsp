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

<h4>零部件入库</h4>

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
    <grid:button title="入半成品库" groupname="opt" function="rbcpk"
    outclass="btn-success" url="${adminPath}/jygl/lbjrk/rbcpk?ljid=\"+row.id+\"" />

    <grid:column label="计划名称" name="htid"/>
    <grid:column label="零部件名称" name="ljmc"/>
    <grid:column label="零部件图号" name="ljth"/>
    <grid:column label="单用量" name="dyl"/>
    <grid:column label="数量" name="sl"/>
    <grid:column label="未入库数量" name="wrksl"/>

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

    //入标半成品未完成仓库
    function rbcpk(title, url, gridId, id, width, height, tipMsg) {

        layer.confirm('是否要入半成品库信息!', {
                btn: ['确定', '取消']
            }, function (index, layero) {
                $.ajax({
                    type: "GET",
                    url: url,
                    success: function (data) {
                        //0 没有未完成数据  1 有未完成数据  2 没有编辑工艺信息
                        if (data==0){
                            layer.msg("入库成功!",{ icon: 1, time: 1000 });
                        }
                        if (data==1){
                            top.layer.alert('有未完成工序!', {icon: 0, title:'警告'});
                        }
                        if (data==2){
                            top.layer.alert('没有编辑工艺信息!', {icon: 0, title:'警告'});
                        }

                        refreshTable2(gridId);
                    }
                });
                layer.closeAll('dialog');  //加入这个信息点击确定 会关闭这个消息框


            }
        );
    }

</script>
</body>
</html>
