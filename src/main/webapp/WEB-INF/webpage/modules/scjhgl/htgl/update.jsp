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
<input id="htid" name="htid" type="hidden" value="${scjhglHtgl.id}">
<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>计划名称：</label>
                    </td>
                    <td>
                        <input name="htbh" id="htbh" htmlEscape="false" class="form-control" placeholder="请输入合同编号" value="${scjhglHtgl.htbh}"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>描述：</label>
                    </td>
                    <td>
                        <textarea id="ms" name="ms" class="form-control" rows="3" cols="20" placeholder="请对该计划进行描述">${scjhglHtgl.ms}</textarea>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>数量：</label>
                    </td>
                    <td>
                        <input name="sl" id="sl" htmlEscape="false" class="form-control" placeholder="请输入计划生产数量" value="${scjhglHtgl.sl}" onchange="checkSl()"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>计划完工时间：</label>
                    </td>
                    <td>
                        <input name="jhwgsj" id="jhwgsj" htmlEscape="false" class="form-control layer-date" value="${scjhglHtgl.jhwgsj}" pattern="yyyy-MM-dd" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"  placeholder="年-月-日"  datatype="*" />
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>是否完成：</label>
                    </td>
                    <td>
                        <select name="sfwc" class="form-control" id="sfwc">
                          <c:if test="${scjhglHtgl.sfwc==0}">
                              <option value="0">未完成</option>
                              <option value="1">已完成</option>
                          </c:if>
                            <c:if test="${scjhglHtgl.sfwc==1}">
                                <option value="1">已完成</option>
                                <option value="0">未完成</option>
                            </c:if>
                        </select>
                    </td>
                </tr>


            </table>
        </form>
    </div>
    <div class="col-md-3">
    </div>
</div>


<script type="text/javascript">

    //检查数量
    function checkSl(){
        var sl = $("#sl").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = sl.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#sl").val("");
        }
    }

    //点击保存，保存数据
    function check() {
        var id = $("#htid").val();
        var htbh = $("#htbh").val();
        var ms = $("#ms").val();
        var sl = $("#sl").val();
        var sfwc = $("#sfwc").val();
        var jhwgsj = $("#jhwgsj").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/scjhgl/htgl/saveHt",
            data: {
                id: id,
                htbh: htbh,
                ms: ms,
                sl: sl,
                sfwc: sfwc,
                jhwgsj: jhwgsj
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
