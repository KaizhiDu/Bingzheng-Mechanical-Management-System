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
    <input type="hidden" id="sqje" name="sqje" value="${zzglJh.money}">
    <input type="hidden" id="jhid" name="jhid" value="${zzglJh.id}">
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
                        <label>借款人姓名：</label>
                    </td>
                    <td>
                        ${zzglJh.name}
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>所欠金额：</label>
                    </td>
                    <td>
                        ${zzglJh.money}
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>要还金额：</label>
                    </td>
                    <td>
                        <input name="money" id="money" htmlEscape="false" class="form-control" placeholder="请输入要还的金额" onchange="checkJe()"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>还款账户：</label>
                    </td>
                    <td>
                        <select name="hkzh" class="form-control" id="hkzh">
                            <option value="1-${jcszZzseName.one}">${jcszZzseName.one}</option>
                            <option value="2-${jcszZzseName.two}">${jcszZzseName.two}</option>
                            <option value="3-${jcszZzseName.three}">${jcszZzseName.three}</option>
                            <option value="4-${jcszZzseName.four}">${jcszZzseName.four}</option>
                            <option value="5-${jcszZzseName.five}">${jcszZzseName.five}</option>
                            <option value="6-${jcszZzseName.six}">${jcszZzseName.six}</option>
                            <option value="7-${jcszZzseName.seven}">${jcszZzseName.seven}</option>
                            <option value="8-${jcszZzseName.eight}">${jcszZzseName.eight}</option>

                        </select>
                    </td>
                </tr>

                <tr class="form-group">
                    <td>
                        <label>明细：</label>
                    </td>
                    <td>
                        <input name="mx" id="mx" htmlEscape="false" class="form-control" placeholder="请输入明细"/>
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
       var sqje = $("#sqje").val();
       var money = $("#money").val();
        var money = $("#money").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = money.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#money").val("");
        }
        else {
            //所欠金额不能高于要还金额
            if (parseFloat(sqje)<parseFloat(money)){
                top.layer.alert("所欠金额不能高于要还金额");
                $("#money").val("");
            }

        }
    }

    //点击保存，保存数据
    function check() {
        var jhid = $("#jhid").val();
        var rq = $("#rq").val();
        var money = $("#money").val();
        var hkzh = $("#hkzh").val();
        var mx = $("#mx").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/zzgl/jh/saveHk",
            data: {
                id: jhid,
                rq: rq,
                money: money,
                hkzh: hkzh,
                mx: mx
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
