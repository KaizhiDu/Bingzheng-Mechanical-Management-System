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
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>日期：</label>
                    </td>
                    <td>
                        <input name="rq" id="rq" htmlEscape="false" class="form-control layer-date" pattern="yyyy-MM-dd" value="${day}" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"  placeholder="年-月-日"  datatype="*"/>

                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>出账：</label>
                    </td>
                    <td>
                        <select name="cz" class="form-control" id="cz">
                            <c:forEach var="each" items="${qyList}">
                                <option value="${each.zjy}-${each.name}">${each.name}</option>
                            </c:forEach>
                            <%--<option value="1-${jcszZzseName.one}">${jcszZzseName.one}</option>--%>
                            <%--<option value="2-${jcszZzseName.two}">${jcszZzseName.two}</option>--%>
                            <%--<option value="3-${jcszZzseName.three}">${jcszZzseName.three}</option>--%>
                            <%--<option value="4-${jcszZzseName.four}">${jcszZzseName.four}</option>--%>
                            <%--<option value="5-${jcszZzseName.five}">${jcszZzseName.five}</option>--%>
                            <%--<option value="6-${jcszZzseName.six}">${jcszZzseName.six}</option>--%>
                            <%--<option value="7-${jcszZzseName.seven}">${jcszZzseName.seven}</option>--%>
                            <%--<option value="8-${jcszZzseName.eight}">${jcszZzseName.eight}</option>--%>

                        </select>
                    </td>
                </tr>

                <tr class="form-group">
                    <td>
                        <label>金额：</label>
                    </td>
                    <td>
                        <input name="money" id="money" htmlEscape="false" class="form-control" placeholder="请输入调动金额" onchange="checkJe()"/>
                    </td>
                </tr>

                <tr class="form-group">
                    <td>
                        <label>进账：</label>
                    </td>
                    <td>
                        <select name="jz" class="form-control" id="jz">
                            <c:forEach var="each" items="${qyList}">
                                <option value="${each.zjy}-${each.name}">${each.name}</option>
                            </c:forEach>
                            <%--<option value="2-${jcszZzseName.two}">${jcszZzseName.two}</option>--%>
                            <%--<option value="1-${jcszZzseName.one}">${jcszZzseName.one}</option>--%>
                            <%--<option value="3-${jcszZzseName.three}">${jcszZzseName.three}</option>--%>
                            <%--<option value="4-${jcszZzseName.four}">${jcszZzseName.four}</option>--%>
                            <%--<option value="5-${jcszZzseName.five}">${jcszZzseName.five}</option>--%>
                            <%--<option value="6-${jcszZzseName.six}">${jcszZzseName.six}</option>--%>
                            <%--<option value="7-${jcszZzseName.seven}">${jcszZzseName.seven}</option>--%>
                            <%--<option value="8-${jcszZzseName.eight}">${jcszZzseName.eight}</option>--%>

                        </select>
                    </td>
                </tr>

                <tr class="form-group">
                    <td>
                        <label>备注：</label>
                    </td>
                    <td>
                        <input name="mx" id="mx" htmlEscape="false" class="form-control" placeholder="请输入备注"/>
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
    function checkJe(){
        var money = $("#money").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = money.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#money").val("");
        }
    }

    //点击保存，保存数据
    function check() {
        var cz = $("#cz").val();
        var jz = $("#jz").val();
        var money = $("#money").val();
        var rq = $("#rq").val();
        var mx = $("#mx").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/zzgl/zzgl/saveDd",
            data: {
                cz: cz,
                jz: jz,
                money: money,
                jtsj: rq,
                mx2: mx,
                lx: "2"
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
