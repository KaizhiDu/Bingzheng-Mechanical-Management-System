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
    <input id="ygid" name="ygid" type="hidden" value="${xzzwfp.ygid}">
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>职务：</label>
                    </td>
                    <td>
                        <select id="zwid" name="zwid" class="form-control">
                            <option ${xzzwfp.zwid eq '钳工' ? 'selected' : ''} value="钳工">钳工</option>
                            <option ${xzzwfp.zwid eq '钳工领班' ? 'selected' : ''} value="钳工领班">钳工领班</option>
                            <option ${xzzwfp.zwid eq '铣工' ? 'selected' : ''} value="铣工">铣工</option>
                            <option ${xzzwfp.zwid eq '车工' ? 'selected' : ''} value="车工">车工</option>
                            <option ${xzzwfp.zwid eq '数控' ? 'selected' : ''} value="数控">数控</option>
                            <option ${xzzwfp.zwid eq '数控领班' ? 'selected' : ''} value="数控领班">数控领班</option>
                            <option ${xzzwfp.zwid eq '后勤' ? 'selected' : ''} value="后勤">后勤</option>
                            <option ${xzzwfp.zwid eq '保管' ? 'selected' : ''} value="保管">保管</option>
                            <option ${xzzwfp.zwid eq '司机采购' ? 'selected' : ''} value="司机采购">司机采购</option>
                            <option ${xzzwfp.zwid eq '技术' ? 'selected' : ''} value="技术">技术</option>
                            <option ${xzzwfp.zwid eq '生产' ? 'selected' : ''} value="生产">生产</option>
                            <option ${xzzwfp.zwid eq '质检仓库' ? 'selected' : ''} value="质检仓库">质检仓库</option>
                            <option ${xzzwfp.zwid eq '外协采购' ? 'selected' : ''} value="外协采购">外协采购</option>
                            <option ${xzzwfp.zwid eq '外协勤务' ? 'selected' : ''} value="外协勤务">外协勤务</option>

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
        var ygid = $("#ygid").val();
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
                ygid: ygid,
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
