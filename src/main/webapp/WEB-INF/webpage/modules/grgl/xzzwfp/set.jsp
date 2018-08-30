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
<body class="container">

<div class="row">
    <div class="col-md-3">

    </div>
    <input id="xzzwfpid" name="xzzwfpid" type="hidden" value="${xzzwfp.id}">
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>职务：</label>
                    </td>
                    <td>
                        <select id="zwid" name="zwid" class="form-control">

                            <option ${xzzwfp.zwid eq '工人' ? 'selected' : ''} value="工人">工人</option>
                            <option ${xzzwfp.zwid eq '管理' ? 'selected' : ''} value="管理">管理</option>
                        </select>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>职位工资：</label>
                    </td>
                    <td>
                        <input name="zwgz" id="zwgz" htmlEscape="false" class="form-control" placeholder="请输入底薪" value="${xzzwfp.zwgz}"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>底薪：</label>
                    </td>
                    <td>
                        <input name="dx" id="dx" htmlEscape="false" class="form-control" placeholder="请输入底薪" value="${xzzwfp.dx}"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>时薪：</label>
                    </td>
                    <td>
                        <input name="sx" id="sx" htmlEscape="false" class="form-control" placeholder="请输入时薪" value="${xzzwfp.sx}"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>餐补：</label>
                    </td>
                    <td>
                        <input name="bgqm" id="bgqm" htmlEscape="false" class="form-control" placeholder="请输入餐补" value="${xzzwfp.bgqm}"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>房补：</label>
                    </td>
                    <td>
                        <input name="fb" id="fb" htmlEscape="false" class="form-control" placeholder="请输入房补" value="${xzzwfp.fb}"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>交通费：</label>
                    </td>
                    <td>
                        <input name="jtf" id="jtf" htmlEscape="false" class="form-control" placeholder="请输入交通费" value="${xzzwfp.jtf}"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>补贴：</label>
                    </td>
                    <td>
                        <input name="bt" id="bt" htmlEscape="false" class="form-control" placeholder="请输入补贴" value="${xzzwfp.bt}"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>保险：</label>
                    </td>
                    <td>
                        <input name="bx" id="bx" htmlEscape="false" class="form-control" placeholder="请输入保险" value="${xzzwfp.bx}"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>扣款：</label>
                    </td>
                    <td>
                        <input name="kk" id="kk" htmlEscape="false" class="form-control" placeholder="请输入扣款" value="${xzzwfp.kk}"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="col-md-3">
    </div>
</div>

<script type="text/javascript">
    //点击保存，保存数据
    function check() {
        var id = $("#xzzwfpid").val();
        var zwid = $("#zwid").val();
        var zwgz = $("#zwgz").val();
        var dx = $("#dx").val();
        var sx = $("#sx").val();
        var fb = $("#fb").val();
        var bgqm = $("#bgqm").val();
        var jtf = $("#jtf").val();
        var bt = $("#bt").val();
        var bx = $("#bx").val();
        var kk = $("#kk").val();

        $.ajax({
            type: "GET",
            url: "${adminPath}/grgl/xzzwfp/saveXzzwfp",
            data: {
                id: id,
                zwid: zwid,
                dx: dx,
                sx: sx,
                fb: fb,
                bt: bt,
                bx: bx,
                kk: kk,
                jtf: jtf,
                zwgz: zwgz,
                bgqm: bgqm
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
