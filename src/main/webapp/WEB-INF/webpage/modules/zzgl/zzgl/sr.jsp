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
                        <label>收入明细：</label>
                    </td>
                    <td>
                        <select name="mx1" class="form-control" id="mx1">
                            <c:forEach items="${jcszMxmbs}" var="srmx">
                                <option value="${srmx.name}">${srmx.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input name="mx2" id="mx2" htmlEscape="false" class="form-control" placeholder="请对明细进行补充"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>进项：</label>
                    </td>
                    <td>
                        <select name="gs" class="form-control" id="gs">
                            <option value="">不是合同收入</option>
                            <option value="">泉清</option>
                            <option value="">泉清研发</option>
                            <option value="">富友</option>
                        </select>
                    </td>
                    <td>
                        <select name="ht" class="form-control" id="ht">
                            <option value="">空</option>
                            <option value="">总额</option>
                            <option value="">合同1</option>
                            <option value="">合同2</option>
                            <option value="">合同3</option>
                        </select>
                    </td>


                </tr>
                <tr class="form-group">
                    <td>
                        <label>金额：</label>
                    </td>
                    <td>
                        <input name="money" id="money" htmlEscape="false" class="form-control" placeholder="请输入收入金额" onchange="checkJe()"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>进账：</label>
                    </td>
                    <td>
                        <select name="jz" class="form-control" id="jz">
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
        var money = $("#money").val();
        var mx1 = $("#mx1").val();
        var mx2 = $("#mx2").val();
        var jz = $("#jz").val();
        var rq = $("#rq").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/zzgl/zzgl/saveSr",
            data: {
                money: money,
                mx1: mx1,
                mx2: mx2,
                jz: jz,
                lx: "0",
                jtsj: rq
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
