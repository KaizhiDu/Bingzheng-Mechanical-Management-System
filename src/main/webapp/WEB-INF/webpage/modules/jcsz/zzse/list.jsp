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
    <div class="col-md-2">

    </div>
    <div class="col-md-8">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>资金1：</label>
                    </td>
                    <td>
                        <input name="name1" id="name1" htmlEscape="false" class="form-control" placeholder="请输入资金1名称" value="${name.one}"/>
                    </td>
                    <td>
                        <input name="value1" id="value1" htmlEscape="false" class="form-control" placeholder="请输入资金1基本资金" value="${value.one}" onchange="check1()"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>资金2：</label>
                    </td>
                    <td>
                        <input name="name2" id="name2" htmlEscape="false" class="form-control" placeholder="请输入资金2名称" value="${name.two}"/>
                    </td>
                    <td>
                        <input name="value2" id="value2" htmlEscape="false" class="form-control" placeholder="请输入资金2基本资金" onchange="check2()" value="${value.two}"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>资金3：</label>
                    </td>
                    <td>
                        <input name="name3" id="name3" htmlEscape="false" class="form-control" placeholder="请输入资金3名称" value="${name.three}"/>
                    </td>
                    <td>
                        <input name="value3" id="value3" htmlEscape="false" class="form-control" placeholder="请输入资金3基本资金" onchange="check3()" value="${value.three}"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>资金4：</label>
                    </td>
                    <td>
                        <input name="name4" id="name4" htmlEscape="false" class="form-control" placeholder="请输入资金4名称" value="${name.four}"/>
                    </td>
                    <td>
                        <input name="value4" id="value4" htmlEscape="false" class="form-control" placeholder="请输入资金4基本资金" onchange="check4()" value="${value.four}"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>资金5：</label>
                    </td>
                    <td>
                        <input name="name5" id="name5" htmlEscape="false" class="form-control" placeholder="请输入资金5名称" value="${name.five}"/>
                    </td>
                    <td>
                        <input name="value5" id="value5" htmlEscape="false" class="form-control" placeholder="请输入资金5基本资金" onchange="check5()" value="${value.five}"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>资金6：</label>
                    </td>
                    <td>
                        <input name="name6" id="name6" htmlEscape="false" class="form-control" placeholder="请输入资金6名称" value="${name.six}"/>
                    </td>
                    <td>
                        <input name="value6" id="value6" htmlEscape="false" class="form-control" placeholder="请输入资金6基本资金" onchange="check6()" value="${value.six}"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>资金7：</label>
                    </td>
                    <td>
                        <input name="name7" id="name7" htmlEscape="false" class="form-control" placeholder="请输入资金7名称" value="${name.seven}"/>
                    </td>
                    <td>
                        <input name="value7" id="value7" htmlEscape="false" class="form-control" placeholder="请输入资金7基本资金" onchange="check7()" value="${value.seven}"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>资金8：</label>
                    </td>
                    <td>
                        <input name="name8" id="name8" htmlEscape="false" class="form-control" placeholder="请输入资金8名称" value="${name.eight}"/>
                    </td>
                    <td>
                        <input name="value8" id="value8" htmlEscape="false" class="form-control" placeholder="请输入资金8基本资金" onchange="check8()" value="${value.eight}"/>
                    </td>
                </tr>

            </table>
            <button class="btn btn-primary" onclick="saveData()">保存</button>
        </form>
    </div>
    <div class="col-md-2">
    </div>


</div>


<script type="text/javascript">

    //检查数量
    function check1(){
        var value1 = $("#value1").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value1.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value1").val("");
        }
    }
    function check2(){
        var value2 = $("#value2").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value2.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value2").val("");
        }
    }
    function check3(){
        var value3 = $("#value3").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value3.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value3").val("");
        }
    }
    function check4(){
        var value4 = $("#value4").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value4.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value4").val("");
        }
    }
    function check5(){
        var value5 = $("#value5").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value5.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value5").val("");
        }
    }
    function check6(){
        var value6 = $("#value6").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value6.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value6").val("");
        }
    }
    function check7(){
        var value7 = $("#value7").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value7.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value7").val("");
        }
    }
    function check8(){
        var value8 = $("#value8").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value8.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value8").val("");
        }
    }

    //点击保存，保存数据
    function saveData() {

        var name1 = $("#name1").val();
        var name2 = $("#name2").val();
        var name3 = $("#name3").val();
        var name4 = $("#name4").val();
        var name5 = $("#name5").val();
        var name6 = $("#name6").val();
        var name7 = $("#name7").val();
        var name8 = $("#name8").val();
        var value1 = $("#value1").val();
        var value2 = $("#value2").val();
        var value3 = $("#value3").val();
        var value4 = $("#value4").val();
        var value5 = $("#value5").val();
        var value6 = $("#value6").val();
        var value7 = $("#value7").val();
        var value8 = $("#value8").val();

        $.ajax({
            type: "GET",
            url: "${adminPath}/jcsz/zzse/saveZzse",
//            cache:false,
            async:false,
            data: {
                name1: name1,
                name2: name2,
                name3: name3,
                name4: name4,
                name5: name5,
                name6: name6,
                name7: name7,
                name8: name8,
                value1: value1,
                value2: value2,
                value3: value3,
                value4: value4,
                value5: value5,
                value6: value6,
                value7: value7,
                value8: value8
            },
            success: function (data) {
//                top.layer.alert("修改成功");

            }
        });
    }
</script>

</body>
</html>
