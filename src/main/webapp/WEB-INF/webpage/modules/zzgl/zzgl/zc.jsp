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
                        <input name="money" id="money" htmlEscape="false" class="form-control" placeholder="请输入支出金额" onchange="checkJe()"/>
                    </td>
                </tr>

                <tr class="form-group">
                    <td>
                        <label>支出明细：</label>
                    </td>
                    <td>

                        <input id="radio1" name="srmx" type="radio" value="radio1" checked onclick="changeStatus2()"/>选项
                        <select name="mx1" class="form-control" id="mx1" onchange="loadHt()">
                            <c:forEach items="${jcszMxmbs}" var="srmx">
                                <option value="${srmx.name}">${srmx.name}</option>
                            </c:forEach>
                        </select>
                        <br>
                        <input id="radio2" name="srmx" type="radio" value="radio2" onclick="changeStatus()"/>手动输入
                        <input name="mx12" id="mx12" htmlEscape="false" class="form-control" disabled="disabled"/>


                    </td>
                </tr>

<%--                <tr class="form-group">--%>
<%--                    <td>--%>
<%--                        <label>合同：</label>--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <select name="ht" class="form-control" id="ht">--%>
<%--                            <option value="">不是合同支出</option>--%>
<%--                        </select>--%>
<%--                    </td>--%>
<%--                </tr>--%>

                <tr class="form-group">
                    <td>
                        <label>备注：</label>
                    </td>
                    <td>
                        <input name="mx2" id="mx2" htmlEscape="false" class="form-control" placeholder="请对明细进行补充"/>
                    </td>
                </tr>

            </table>
        </form>
    </div>
    <div class="col-md-3">
    </div>
</div>


<script type="text/javascript">

    window.onload =function() {
        loadHt();
    }

    function loadHt(){
        var mx = $("#mx1").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/zzgl/zzgl/loadHt",
            data: {
                mx: mx
            },
            success: function (data) {
                //console.log(data);
                $('#ht').html("");
                $("#ht").append("<option value=''>不是合同返款</option>");
                for(var i=0;i<data.length;i++){
                    var ht = data[i];
                    $("#ht").append("<option value='"+ht.id+"'>"+ht.htmc+"</option>");
                }
            }
        });
    }

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

    function changeStatus(){
        $("#mx12").removeAttr("disabled");
        $("#mx1").attr("disabled","disabled");
    }

    function changeStatus2(){
        $("#mx1").removeAttr("disabled");
        $("#mx12").attr("disabled","disabled");
    }

    //点击保存，保存数据
    function check() {
        var rq = $("#rq").val();
        var cz = $("#cz").val();
        var money = $("#money").val();
        var srmx = $("input[name='srmx']:checked").val();
        var mx1 = "";
        if (srmx=="radio1"){
            mx1 = $("#mx1").val();
        }
        else{
            mx1 = $("#mx12").val();
        }
        var mx2 = $("#mx2").val();
        var ht = "";
        $.ajax({
            type: "GET",
            url: "${adminPath}/zzgl/zzgl/saveZc",
            data: {
                jtsj: rq,
                cz: cz,
                money: money,
                mx1: mx1,
                mx2: mx2,
                lx: "1",
                ht: ht
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
