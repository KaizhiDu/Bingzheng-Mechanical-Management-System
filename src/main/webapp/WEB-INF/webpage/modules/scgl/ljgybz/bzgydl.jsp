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

<h4>零件工艺编制</h4>
<div class="row">
    <div id="ScglGydlbzGridQuery" class="col-md-12">

    </div>
</div>
<grid:grid id="ScglGydlbz"
           url="${adminPath}/scgl/ljgybz/ajaxGydlbzList?jhid=${scjhglHtgl.id}" pageable="true">

    <grid:column label="sys.common.key" hidden="true" name="id"/>
    <grid:column label="sys.common.opt" name="opt" formatter="button" width="30"/>

    <grid:button title="编制工艺小类" groupname="opt" function="bzgyxl"
                 outclass="btn-success" url="${adminPath}/scgl/ljgybz/bzgyxl?id=\"+row.id+\"" />

    <grid:column label="计划编号" name="jhid" width="30"/>
    <grid:column label="工艺大类" name="gydlid"  width="200"/>

    <grid:toolbar function="addGydl" icon="fa fa-plus" btnclass="btn btn-sm btn-primary" title="编制工艺大类"/>\
    <grid:toolbar function="deleteGydl" icon="fa fa-trash-o" btnclass="btn btn-sm btn-danger" title="删除"/>

    <grid:toolbar function="search"/>
    <grid:toolbar function="reset"/>
</grid:grid>


<script type="text/javascript">

    //编制工艺大类
    function addGydl(title, url, gridId, id, width, height, tipMsg) {
        var url = "${adminPath}/scgl/ljgybz/addGydl";
        if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
            width='auto';
            height='auto';
        }else{//如果是PC端，根据用户设置的width和height显示。

        }
        top.layer.open({
            type: 2,
            area: ["60%", "50%"],
            title: "编制工艺大类",
            maxmin: true, //开启最大化最小化按钮
            content: url ,
            success: function(layero, index){
                //遍历父页面的button,使其失去焦点，再按enter键就不会弹框了
                $(":button").each(function () {
                    $(this).blur();
                });
            },
            btn: [ '关闭'],
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
