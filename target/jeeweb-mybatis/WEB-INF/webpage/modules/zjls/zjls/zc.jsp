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
                        <label>项目：</label>
                    </td>
                    <td>
                        <select name="mx2" class="form-control" id="mx2">
                            <option value="材料">材料</option>
                            <option value="辅料">辅料</option>
                            <option value="外协">外协</option>
                            <option value="物业水电">物业水电</option>
                            <option value="工资">工资</option>
                            <option value="税金财务">税金财务</option>
                            <option value="办公">办公</option>
                            <option value="招待">招待</option>
                            <option value="午餐">午餐</option>
                            <option value="维修">维修</option>
                            <option value="运输">运输</option>
                            <option value="其他">其他</option>

                        </select>
                    </td>
                </tr>

                <tr class="form-group">
                    <td>
                        <label>金额：</label>
                    </td>
                    <td>
                        <input name="money" id="money" htmlEscape="false" class="form-control" placeholder="请输入支出金额" onchange="checkJe()"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>支出明细：</label>
                    </td>
                    <td>
                        <input name="mx" id="mx" htmlEscape="false" class="form-control" placeholder="请输入支出明细"/>
                    </td>

                </tr>
                <tr class="form-group">
                    <td>
                        <label>资金来源：</label>
                    </td>
                    <td>
                        <select name="zjly" class="form-control" id="zjly">
                            <option value="2">报销</option>
                            <option value="1">资金</option>
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
    function checkJe(){
        var money = $("#money").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = money.match(/-[0-9]+(.[0-9]+)?|[0-9]+(.[0-9]+)?/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#money").val("");
        }
    }

    //点击保存，保存数据
    function check() {
        var money = $("#money").val();
        var mx = $("#mx").val();
        var mx2 = $("#mx2").val();
        var rq = $("#rq").val();
        var zjly = $("#zjly").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/zjls/zjls/saveZc",
            data: {
                money: money,
                mx: mx,
                mx2: mx2,
                lx: "1",
                jtsj: rq,
                zjly: zjly
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
