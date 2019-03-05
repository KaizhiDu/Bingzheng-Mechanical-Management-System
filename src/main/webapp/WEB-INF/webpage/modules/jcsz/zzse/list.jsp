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
                <%--<tr class="form-group">--%>
                    <%--<td>--%>
                        <%--<label></label>--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--<label>资金名</label>--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--<label>资金数额</label>--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--<label>是否启用</label>--%>
                    <%--</td>--%>
                <%--</tr>--%>
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
                    <td>
                        <input name="sf1" id="sf1" htmlEscape="false" class="form-control" type="checkbox" onchange="checksf1()"/>
                    </td>
                    <td>
                        <input name="px1" id="px1" htmlEscape="false" class="form-control"  placeholder="请输入排序" value="${sort.one}" onchange="checkpx1()"/>
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
                    <td>
                        <input name="sf2" id="sf2" htmlEscape="false" class="form-control" type="checkbox" onchange="checksf2()"/>
                    </td>
                    <td>
                        <input name="px2" id="px2" htmlEscape="false" class="form-control"  placeholder="请输入排序" value="${sort.two}" onchange="checkpx2()"/>
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
                    <td>
                        <input name="sf3" id="sf3" htmlEscape="false" class="form-control" type="checkbox" onchange="checksf3()"/>
                    </td>
                    <td>
                        <input name="px3" id="px3" htmlEscape="false" class="form-control"  placeholder="请输入排序" value="${sort.three}" onchange="checkpx3()"/>
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
                    <td>
                        <input name="sf4" id="sf4" htmlEscape="false" class="form-control" type="checkbox" onchange="checksf4()"/>
                    </td>
                    <td>
                        <input name="px4" id="px4" htmlEscape="false" class="form-control"  placeholder="请输入排序" value="${sort.four}" onchange="checkpx4()"/>
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
                    <td>
                        <input name="sf5" id="sf5" htmlEscape="false" class="form-control" type="checkbox" onchange="checksf5()"/>
                    </td>
                    <td>
                        <input name="px5" id="px5" htmlEscape="false" class="form-control"  placeholder="请输入排序" value="${sort.five}" onchange="checkpx5()"/>
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
                    <td>
                        <input name="sf6" id="sf6" htmlEscape="false" class="form-control" type="checkbox" onchange="checksf6()"/>
                    </td>
                    <td>
                        <input name="px6" id="px6" htmlEscape="false" class="form-control"  placeholder="请输入排序" value="${sort.six}" onchange="checkpx6()"/>
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
                    <td>
                        <input name="sf7" id="sf7" htmlEscape="false" class="form-control" type="checkbox" onchange="checksf7()"/>
                    </td>
                    <td>
                        <input name="px7" id="px7" htmlEscape="false" class="form-control"  placeholder="请输入排序" value="${sort.seven}" onchange="checkpx7()"/>
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
                    <td>
                        <input name="sf8" id="sf8" htmlEscape="false" class="form-control" type="checkbox" onchange="checksf8()"/>
                    </td>
                    <td>
                        <input name="px8" id="px8" htmlEscape="false" class="form-control"  placeholder="请输入排序" value="${sort.eight}" onchange="checkpx8()"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>资金9：</label>
                    </td>
                    <td>
                        <input name="name9" id="name9" htmlEscape="false" class="form-control" placeholder="请输入资金9名称" value="${name.nine}"/>
                    </td>
                    <td>
                        <input name="value9" id="value9" htmlEscape="false" class="form-control" placeholder="请输入资金9基本资金" onchange="check9()" value="${value.nine}"/>
                    </td>
                    <td>
                        <input name="sf9" id="sf9" htmlEscape="false" class="form-control" type="checkbox" onchange="checksf9()"/>
                    </td>
                    <td>
                        <input name="px9" id="px9" htmlEscape="false" class="form-control"  placeholder="请输入排序" value="${sort.nine}" onchange="checkpx9()"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>资金10：</label>
                    </td>
                    <td>
                        <input name="name10" id="name10" htmlEscape="false" class="form-control" placeholder="请输入资金10名称" value="${name.ten}"/>
                    </td>
                    <td>
                        <input name="value10" id="value10" htmlEscape="false" class="form-control" placeholder="请输入资金10基本资金" onchange="check10()" value="${value.ten}"/>
                    </td>
                    <td>
                        <input name="sf10" id="sf10" htmlEscape="false" class="form-control" type="checkbox" onchange="checksf10()"/>
                    </td>
                    <td>
                        <input name="px10" id="px10" htmlEscape="false" class="form-control"  placeholder="请输入排序" value="${sort.ten}" onchange="checkpx10()"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>资金11：</label>
                    </td>
                    <td>
                        <input name="name11" id="name11" htmlEscape="false" class="form-control" placeholder="请输入资金11名称" value="${name.eleven}"/>
                    </td>
                    <td>
                        <input name="value11" id="value11" htmlEscape="false" class="form-control" placeholder="请输入资金11基本资金" onchange="check11()" value="${value.eleven}"/>
                    </td>
                    <td>
                        <input name="sf11" id="sf11" htmlEscape="false" class="form-control" type="checkbox" onchange="checksf11()"/>
                    </td>
                    <td>
                        <input name="px11" id="px11" htmlEscape="false" class="form-control"  placeholder="请输入排序" value="${sort.eleven}" onchange="checkpx11()"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>资金12：</label>
                    </td>
                    <td>
                        <input name="name12" id="name12" htmlEscape="false" class="form-control" placeholder="请输入资金12名称" value="${name.twelve}"/>
                    </td>
                    <td>
                        <input name="value12" id="value12" htmlEscape="false" class="form-control" placeholder="请输入资金12基本资金" onchange="check12()" value="${value.twelve}"/>
                    </td>
                    <td>
                        <input name="sf12" id="sf12" htmlEscape="false" class="form-control" type="checkbox" onchange="checksf12()"/>
                    </td>
                    <td>
                        <input name="px12" id="px12" htmlEscape="false" class="form-control"  placeholder="请输入排序" value="${sort.twelve}" onchange="checkpx12()"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>资金13：</label>
                    </td>
                    <td>
                        <input name="name13" id="name13" htmlEscape="false" class="form-control" placeholder="请输入资金13名称" value="${name.thirteen}"/>
                    </td>
                    <td>
                        <input name="value13" id="value13" htmlEscape="false" class="form-control" placeholder="请输入资金13基本资金" onchange="check13()" value="${value.thirteen}"/>
                    </td>
                    <td>
                        <input name="sf13" id="sf13" htmlEscape="false" class="form-control" type="checkbox" onchange="checksf13()"/>
                    </td>
                    <td>
                        <input name="px13" id="px13" htmlEscape="false" class="form-control"  placeholder="请输入排序" value="${sort.thirteen}" onchange="checkpx13()"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>资金14：</label>
                    </td>
                    <td>
                        <input name="name14" id="name14" htmlEscape="false" class="form-control" placeholder="请输入资金14名称" value="${name.fourteen}"/>
                    </td>
                    <td>
                        <input name="value14" id="value14" htmlEscape="false" class="form-control" placeholder="请输入资金14基本资金" onchange="check14()" value="${value.fourteen}"/>
                    </td>
                    <td>
                        <input name="sf14" id="sf14" htmlEscape="false" class="form-control" type="checkbox" onchange="checksf14()"/>
                    </td>
                    <td>
                        <input name="px14" id="px14" htmlEscape="false" class="form-control"  placeholder="请输入排序" value="${sort.fourteen}" onchange="checkpx14()"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>资金15：</label>
                    </td>
                    <td>
                        <input name="name15" id="name15" htmlEscape="false" class="form-control" placeholder="请输入资金15名称" value="${name.fifteen}"/>
                    </td>
                    <td>
                        <input name="value15" id="value15" htmlEscape="false" class="form-control" placeholder="请输入资金15基本资金" onchange="check15()" value="${value.fifteen}"/>
                    </td>
                    <td>
                        <input name="sf15" id="sf15" htmlEscape="false" class="form-control" type="checkbox" onchange="checksf15()"/>
                    </td>
                    <td>
                        <input name="px15" id="px15" htmlEscape="false" class="form-control"  placeholder="请输入排序" value="${sort.fifteen}" onchange="checkpx15()"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>资金16：</label>
                    </td>
                    <td>
                        <input name="name16" id="name16" htmlEscape="false" class="form-control" placeholder="请输入资金16名称" value="${name.sixteen}"/>
                    </td>
                    <td>
                        <input name="value16" id="value16" htmlEscape="false" class="form-control" placeholder="请输入资金16基本资金" onchange="check16()" value="${value.sixteen}"/>
                    </td>
                    <td>
                        <input name="sf16" id="sf16" htmlEscape="false" class="form-control" type="checkbox" onchange="checksf16()"/>
                    </td>
                    <td>
                        <input name="px16" id="px16" htmlEscape="false" class="form-control"  placeholder="请输入排序" value="${sort.sixteen}" onchange="checkpx16()"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>资金17：</label>
                    </td>
                    <td>
                        <input name="name17" id="name17" htmlEscape="false" class="form-control" placeholder="请输入资金17名称" value="${name.seventeen}"/>
                    </td>
                    <td>
                        <input name="value17" id="value17" htmlEscape="false" class="form-control" placeholder="请输入资金17基本资金" onchange="check17()" value="${value.seventeen}"/>
                    </td>
                    <td>
                        <input name="sf17" id="sf17" htmlEscape="false" class="form-control" type="checkbox" onchange="checksf17()"/>
                    </td>
                    <td>
                        <input name="px17" id="px17" htmlEscape="false" class="form-control"  placeholder="请输入排序" value="${sort.seventeen}" onchange="checkpx17()"/>
                    </td>
                </tr>
                    <tr class="form-group">
                        <td>
                            <label>资金18：(不计入总数)</label>
                        </td>
                        <td>
                            <input name="name18" id="name18" htmlEscape="false" class="form-control" placeholder="请输入资金12名称" value="${name.eighteen}"/>
                        </td>
                        <td>
                            <input name="value18" id="value18" htmlEscape="false" class="form-control" placeholder="请输入资金12基本资金" onchange="check18()" value="${value.eighteen}"/>
                        </td>
                        <td>
                            <input name="sf18" id="sf18" htmlEscape="false" class="form-control" type="checkbox" onchange="checksf18()"/>
                        </td>
                        <td>
                        <input name="px18" id="px18" htmlEscape="false" class="form-control"  placeholder="请输入排序" value="${sort.eighteen}" onchange="checkpx18()"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>资金19：(不计入总数)</label>
                    </td>
                    <td>
                        <input name="name19" id="name19" htmlEscape="false" class="form-control" placeholder="请输入资金12名称" value="${name.nineteen}"/>
                    </td>
                    <td>
                        <input name="value19" id="value19" htmlEscape="false" class="form-control" placeholder="请输入资金12基本资金" onchange="check19()" value="${value.nineteen}"/>
                    </td>
                    <td>
                        <input name="sf19" id="sf19" htmlEscape="false" class="form-control" type="checkbox" onchange="checksf19()"/>
                    </td>
                    <td>
                        <input name="px19" id="px19" htmlEscape="false" class="form-control"  placeholder="请输入排序" value="${sort.nineteen}" onchange="checkpx19()"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>资金20：(不计入总数)</label>
                    </td>
                    <td>
                        <input name="name20" id="name20" htmlEscape="false" class="form-control" placeholder="请输入资金20名称" value="${name.twenty}"/>
                    </td>
                    <td>
                        <input name="value20" id="value20" htmlEscape="false" class="form-control" placeholder="请输入资金20基本资金" onchange="check20()" value="${value.twenty}"/>
                    </td>
                    <td>
                        <input name="sf20" id="sf20" htmlEscape="false" class="form-control" type="checkbox" onchange="checksf20()"/>
                    </td>
                    <td>
                        <input name="px20" id="px20" htmlEscape="false" class="form-control"  placeholder="请输入排序" value="${sort.twenty}" onchange="checkpx20()"/>
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

    //页面加载
    window.onload = function(){
        $.ajax({
            type: "GET",
            url: "${adminPath}/jcsz/zzse/getSfqy",
            success: function (data) {
                if (data.one=="0"){
                    $("#name1").attr("disabled","disabled");
                    $("#value1").attr("disabled","disabled");
                    $("#px1").attr("disabled","disabled");
                }
                if (data.two=="0"){
                    $("#name2").attr("disabled","disabled");
                    $("#value2").attr("disabled","disabled");
                    $("#px2").attr("disabled","disabled");
                }
                if (data.three=="0"){
                    $("#name3").attr("disabled","disabled");
                    $("#value3").attr("disabled","disabled");
                    $("#px3").attr("disabled","disabled");
                }
                if (data.four=="0"){
                    $("#name4").attr("disabled","disabled");
                    $("#value4").attr("disabled","disabled");
                    $("#px4").attr("disabled","disabled");
                }
                if (data.five=="0"){
                    $("#name5").attr("disabled","disabled");
                    $("#value5").attr("disabled","disabled");
                    $("#px5").attr("disabled","disabled");
                }
                if (data.six=="0"){
                    $("#name6").attr("disabled","disabled");
                    $("#value6").attr("disabled","disabled");
                    $("#px6").attr("disabled","disabled");
                }
                if (data.seven=="0"){
                    $("#name7").attr("disabled","disabled");
                    $("#value7").attr("disabled","disabled");
                    $("#px7").attr("disabled","disabled");
                }
                if (data.eight=="0"){
                    $("#name8").attr("disabled","disabled");
                    $("#value8").attr("disabled","disabled");
                    $("#px8").attr("disabled","disabled");
                }
                if (data.nine=="0"){
                    $("#name9").attr("disabled","disabled");
                    $("#value9").attr("disabled","disabled");
                    $("#px9").attr("disabled","disabled");
                }
                if (data.ten=="0"){
                    $("#name10").attr("disabled","disabled");
                    $("#value10").attr("disabled","disabled");
                    $("#px10").attr("disabled","disabled");
                }
                if (data.eleven=="0"){
                    $("#name11").attr("disabled","disabled");
                    $("#value11").attr("disabled","disabled");
                    $("#px11").attr("disabled","disabled");
                }
                if (data.twelve=="0"){
                    $("#name12").attr("disabled","disabled");
                    $("#value12").attr("disabled","disabled");
                    $("#px12").attr("disabled","disabled");
                }
                if (data.thirteen=="0"){
                    $("#name13").attr("disabled","disabled");
                    $("#value13").attr("disabled","disabled");
                    $("#px13").attr("disabled","disabled");
                }
                if (data.fourteen=="0"){
                    $("#name14").attr("disabled","disabled");
                    $("#value14").attr("disabled","disabled");
                    $("#px14").attr("disabled","disabled");
                }
                if (data.fifteen=="0"){
                    $("#name15").attr("disabled","disabled");
                    $("#value15").attr("disabled","disabled");
                    $("#px15").attr("disabled","disabled");
                }
                if (data.sixteen=="0"){
                    $("#name16").attr("disabled","disabled");
                    $("#value16").attr("disabled","disabled");
                    $("#px16").attr("disabled","disabled");
                }
                if (data.seventeen=="0"){
                    $("#name17").attr("disabled","disabled");
                    $("#value17").attr("disabled","disabled");
                    $("#px17").attr("disabled","disabled");
                }
                if (data.eighteen=="0"){
                    $("#name18").attr("disabled","disabled");
                    $("#value18").attr("disabled","disabled");
                    $("#px18").attr("disabled","disabled");
                }
                if (data.nineteen=="0"){
                    $("#name19").attr("disabled","disabled");
                    $("#value19").attr("disabled","disabled");
                    $("#px19").attr("disabled","disabled");
                }
                if (data.twenty=="0"){
                    $("#name20").attr("disabled","disabled");
                    $("#value20").attr("disabled","disabled");
                    $("#px20").attr("disabled","disabled");
                }

                if (data.one=="1"){
                    $("#sf1").attr("checked","checked");
                }
                if (data.two=="1"){
                    $("#sf2").attr("checked","checked");
                }
                if (data.three=="1"){
                    $("#sf3").attr("checked","checked");
                }
                if (data.four=="1"){
                    $("#sf4").attr("checked","checked");
                }
                if (data.five=="1"){
                    $("#sf5").attr("checked","checked");
                }
                if (data.six=="1"){
                    $("#sf6").attr("checked","checked");
                }
                if (data.seven=="1"){
                    $("#sf7").attr("checked","checked");
                }
                if (data.eight=="1"){
                    $("#sf8").attr("checked","checked");
                }
                if (data.nine=="1"){
                    $("#sf9").attr("checked","checked");
                }
                if (data.ten=="1"){
                    $("#sf10").attr("checked","checked");
                }
                if (data.eleven=="1"){
                    $("#sf11").attr("checked","checked");
                }
                if (data.twelve=="1"){
                    $("#sf12").attr("checked","checked");
                }
                if (data.thirteen=="1"){
                    $("#sf13").attr("checked","checked");
                }
                if (data.fourteen=="1"){
                    $("#sf14").attr("checked","checked");
                }
                if (data.fifteen=="1"){
                    $("#sf15").attr("checked","checked");
                }
                if (data.sixteen=="1"){
                    $("#sf16").attr("checked","checked");
                }
                if (data.seventeen=="1"){
                    $("#sf17").attr("checked","checked");
                }
                if (data.eighteen=="1"){
                    $("#sf18").attr("checked","checked");
                }
                if (data.nineteen=="1"){
                    $("#sf19").attr("checked","checked");
                }
                if (data.twenty=="1"){
                    $("#sf20").attr("checked","checked");
                }
            }
        });
    }

    //是否启用
    function checksf1(){
        if($('#sf1').is(':checked')) {
               //alert("选中");
               //取消隐藏
            $("#name1").removeAttr("disabled");
            $("#value1").removeAttr("disabled");
            $("#px1").removeAttr("disabled");
            }
            else{
            //alert("未选中");
            $("#name1").attr("disabled","disabled");
            $("#value1").attr("disabled","disabled");
            $("#px1").attr("disabled","disabled");
        }
    }
    function checksf2(){
        if($('#sf2').is(':checked')) {
            //alert("选中");
            //取消隐藏
            $("#name2").removeAttr("disabled");
            $("#value2").removeAttr("disabled");
            $("#px2").removeAttr("disabled");
        }
        else{
            //alert("未选中");
            $("#name2").attr("disabled","disabled");
            $("#value2").attr("disabled","disabled");
            $("#px2").attr("disabled","disabled");
        }
    }
    function checksf3(){
        if($('#sf3').is(':checked')) {
            //alert("选中");
            //取消隐藏
            $("#name3").removeAttr("disabled");
            $("#value3").removeAttr("disabled");
            $("#px3").removeAttr("disabled");
        }
        else{
            //alert("未选中");
            $("#name3").attr("disabled","disabled");
            $("#value3").attr("disabled","disabled");
            $("#px3").attr("disabled","disabled");
        }
    }
    function checksf4(){
        if($('#sf4').is(':checked')) {
            //alert("选中");
            //取消隐藏
            $("#name4").removeAttr("disabled");
            $("#value4").removeAttr("disabled");
            $("#px4").removeAttr("disabled");
        }
        else{
            //alert("未选中");
            $("#name4").attr("disabled","disabled");
            $("#value4").attr("disabled","disabled");
            $("#px4").attr("disabled","disabled");
        }
    }
    function checksf5(){
        if($('#sf5').is(':checked')) {
            //alert("选中");
            //取消隐藏
            $("#name5").removeAttr("disabled");
            $("#value5").removeAttr("disabled");
            $("#px5").removeAttr("disabled");
        }
        else{
            //alert("未选中");
            $("#name5").attr("disabled","disabled");
            $("#value5").attr("disabled","disabled");
            $("#px5").attr("disabled","disabled");
        }
    }
    function checksf6(){
        if($('#sf6').is(':checked')) {
            //alert("选中");
            //取消隐藏
            $("#name6").removeAttr("disabled");
            $("#value6").removeAttr("disabled");
            $("#px6").removeAttr("disabled");
        }
        else{
            //alert("未选中");
            $("#name6").attr("disabled","disabled");
            $("#value6").attr("disabled","disabled");
            $("#px6").attr("disabled","disabled");
        }
    }
    function checksf7(){
        if($('#sf7').is(':checked')) {
            //alert("选中");
            //取消隐藏
            $("#name7").removeAttr("disabled");
            $("#value7").removeAttr("disabled");
            $("#px7").removeAttr("disabled");
        }
        else{
            //alert("未选中");
            $("#name7").attr("disabled","disabled");
            $("#value7").attr("disabled","disabled");
            $("#px7").attr("disabled","disabled");
        }
    }
    function checksf8(){
        if($('#sf8').is(':checked')) {
            //alert("选中");
            //取消隐藏
            $("#name8").removeAttr("disabled");
            $("#value8").removeAttr("disabled");
            $("#px8").removeAttr("disabled");
        }
        else{
            //alert("未选中");
            $("#name8").attr("disabled","disabled");
            $("#value8").attr("disabled","disabled");
            $("#px8").attr("disabled","disabled");
        }
    }
    function checksf9(){
        if($('#sf9').is(':checked')) {
            //alert("选中");
            //取消隐藏
            $("#name9").removeAttr("disabled");
            $("#value9").removeAttr("disabled");
            $("#px9").removeAttr("disabled");
        }
        else{
            //alert("未选中");
            $("#name9").attr("disabled","disabled");
            $("#value9").attr("disabled","disabled");
            $("#px9").attr("disabled","disabled");
        }
    }
    function checksf10(){
        if($('#sf10').is(':checked')) {
            //alert("选中");
            //取消隐藏
            $("#name10").removeAttr("disabled");
            $("#value10").removeAttr("disabled");
            $("#px10").removeAttr("disabled");
        }
        else{
            //alert("未选中");
            $("#name10").attr("disabled","disabled");
            $("#value10").attr("disabled","disabled");
            $("#px10").attr("disabled","disabled");
        }
    }
    function checksf11(){
        if($('#sf11').is(':checked')) {
            //alert("选中");
            //取消隐藏
            $("#name11").removeAttr("disabled");
            $("#value11").removeAttr("disabled");
            $("#px11").removeAttr("disabled");
        }
        else{
            //alert("未选中");
            $("#name11").attr("disabled","disabled");
            $("#value11").attr("disabled","disabled");
            $("#px11").attr("disabled","disabled");
        }
    }
    function checksf12(){
        if($('#sf12').is(':checked')) {
            //alert("选中");
            //取消隐藏
            $("#name12").removeAttr("disabled");
            $("#value12").removeAttr("disabled");
            $("#px12").removeAttr("disabled");
        }
        else{
            //alert("未选中");
            $("#name12").attr("disabled","disabled");
            $("#value12").attr("disabled","disabled");
            $("#px12").attr("disabled","disabled");
        }
    }
    function checksf13(){
        if($('#sf13').is(':checked')) {
            //alert("选中");
            //取消隐藏
            $("#name13").removeAttr("disabled");
            $("#value13").removeAttr("disabled");
            $("#px13").removeAttr("disabled");
        }
        else{
            //alert("未选中");
            $("#name13").attr("disabled","disabled");
            $("#value13").attr("disabled","disabled");
            $("#px13").attr("disabled","disabled");
        }
    }
    function checksf14(){
        if($('#sf14').is(':checked')) {
            //alert("选中");
            //取消隐藏
            $("#name14").removeAttr("disabled");
            $("#value14").removeAttr("disabled");
            $("#px14").removeAttr("disabled");
        }
        else{
            //alert("未选中");
            $("#name14").attr("disabled","disabled");
            $("#value14").attr("disabled","disabled");
            $("#px14").attr("disabled","disabled");
        }
    }
    function checksf15(){
        if($('#sf15').is(':checked')) {
            //alert("选中");
            //取消隐藏
            $("#name15").removeAttr("disabled");
            $("#value15").removeAttr("disabled");
            $("#px15").removeAttr("disabled");
        }
        else{
            //alert("未选中");
            $("#name15").attr("disabled","disabled");
            $("#value15").attr("disabled","disabled");
            $("#px15").attr("disabled","disabled");
        }
    }
    function checksf16(){
        if($('#sf16').is(':checked')) {
            //alert("选中");
            //取消隐藏
            $("#name16").removeAttr("disabled");
            $("#value16").removeAttr("disabled");
            $("#px16").removeAttr("disabled");
        }
        else{
            //alert("未选中");
            $("#name16").attr("disabled","disabled");
            $("#value16").attr("disabled","disabled");
            $("#px16").attr("disabled","disabled");
        }
    }
    function checksf17(){
        if($('#sf17').is(':checked')) {
            //alert("选中");
            //取消隐藏
            $("#name17").removeAttr("disabled");
            $("#value17").removeAttr("disabled");
            $("#px17").removeAttr("disabled");
        }
        else{
            //alert("未选中");
            $("#name17").attr("disabled","disabled");
            $("#value17").attr("disabled","disabled");
            $("#px17").attr("disabled","disabled");
        }
    }
    function checksf18(){
        if($('#sf18').is(':checked')) {
            //alert("选中");
            //取消隐藏
            $("#name18").removeAttr("disabled");
            $("#value18").removeAttr("disabled");
            $("#px18").removeAttr("disabled");
        }
        else{
            //alert("未选中");
            $("#name18").attr("disabled","disabled");
            $("#value18").attr("disabled","disabled");
            $("#px18").attr("disabled","disabled");
        }
    }
    function checksf19(){
        if($('#sf19').is(':checked')) {
            //alert("选中");
            //取消隐藏
            $("#name19").removeAttr("disabled");
            $("#value19").removeAttr("disabled");
            $("#px19").removeAttr("disabled");
        }
        else{
            //alert("未选中");
            $("#name19").attr("disabled","disabled");
            $("#value19").attr("disabled","disabled");
            $("#px19").attr("disabled","disabled");
        }
    }
    function checksf20(){
        if($('#sf20').is(':checked')) {
            //alert("选中");
            //取消隐藏
            $("#name20").removeAttr("disabled");
            $("#value20").removeAttr("disabled");
            $("#px20").removeAttr("disabled");
        }
        else{
            //alert("未选中");
            $("#name20").attr("disabled","disabled");
            $("#value20").attr("disabled","disabled");
            $("#px20").attr("disabled","disabled");
        }
    }

    //检查数量
    function check1(){
        var value1 = $("#value1").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value1.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value1").val("0");
        }
    }
    function check2(){
        var value2 = $("#value2").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value2.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value2").val("0");
        }
    }
    function check3(){
        var value3 = $("#value3").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value3.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value3").val("0");
        }
    }
    function check4(){
        var value4 = $("#value4").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value4.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value4").val("0");
        }
    }
    function check5(){
        var value5 = $("#value5").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value5.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value5").val("0");
        }
    }
    function check6(){
        var value6 = $("#value6").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value6.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value6").val("0");
        }
    }
    function check7(){
        var value7 = $("#value7").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value7.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value7").val("0");
        }
    }
    function check8(){
        var value8 = $("#value8").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value8.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value8").val("0");
        }
    }
    function check9(){
        var value9 = $("#value9").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value9.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value9").val("0");
        }
    }
    function check10(){
        var value10 = $("#value10").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value10.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value10").val("0");
        }
    }
    function check11(){
        var value11 = $("#value11").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value11.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value11").val("0");
        }
    }
    function check12(){
        var value12 = $("#value12").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value12.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value12").val("0");
        }
    }
    function check13(){
        var value13 = $("#value13").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value13.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value13").val("0");
        }
    }
    function check14(){
        var value14 = $("#value14").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value14.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value14").val("0");
        }
    }
    function check15(){
        var value15 = $("#value15").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value15.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value15").val("0");
        }
    }
    function check16(){
        var value16 = $("#value16").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value16.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value16").val("0");
        }
    }
    function check17(){
        var value17 = $("#value17").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value17.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value17").val("0");
        }
    }
    function check18(){
        var value18 = $("#value18").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value18.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value18").val("0");
        }
    }
    function check19(){
        var value19 = $("#value19").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value19.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value19").val("0");
        }
    }
    function check20(){
        var value20 = $("#value20").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = value20.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#value20").val("0");
        }
    }

//检查排序
    function checkpx1(){
        var px1 = $("#px1").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = px1.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#px1").val("0");
        }
    }
    function checkpx2(){
        var px = $("#px2").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = px.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#px2").val("0");
        }
    }
    function checkpx3(){
        var px = $("#px3").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = px.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#px3").val("0");
        }
    }
    function checkpx4(){
        var px = $("#px4").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = px.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#px4").val("0");
        }
    }
    function checkpx5(){
        var px = $("#px5").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = px.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#px5").val("0");
        }
    }
    function checkpx6(){
        var px = $("#px6").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = px.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#px6").val("0");
        }
    }
    function checkpx7(){
        var px = $("#px7").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = px.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#px7").val("0");
        }
    }
    function checkpx8(){
        var px = $("#px8").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = px.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#px8").val("0");
        }
    }
    function checkpx9(){
        var px = $("#px9").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = px.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#px9").val("0");
        }
    }
    function checkpx10(){
        var px = $("#px10").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = px.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#px10").val("0");
        }
    }
    function checkpx11(){
        var px = $("#px11").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = px.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#px11").val("0");
        }
    }
    function checkpx12(){
        var px = $("#px12").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = px.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#px12").val("0");
        }
    }
    function checkpx13(){
        var px = $("#px13").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = px.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#px13").val("0");
        }
    }
    function checkpx14(){
        var px = $("#px14").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = px.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#px14").val("0");
        }
    }
    function checkpx15(){
        var px = $("#px15").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = px.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#px15").val("0");
        }
    }
    function checkpx16(){
        var px = $("#px16").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = px.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#px16").val("0");
        }
    }
    function checkpx17(){
        var px = $("#px17").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = px.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#px17").val("0");
        }
    }
    function checkpx18(){
        var px = $("#px18").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = px.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#px18").val("0");
        }
    }
    function checkpx19(){
        var px = $("#px19").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = px.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#px19").val("0");
        }
    }
    function checkpx20(){
        var px = $("#px20").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = px.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#px20").val("0");
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
        var name9 = $("#name9").val();
        var name10 = $("#name10").val();
        var name11 = $("#name11").val();
        var name12 = $("#name12").val();
        var name13 = $("#name13").val();
        var name14 = $("#name14").val();
        var name15 = $("#name15").val();
        var name16 = $("#name16").val();
        var name17 = $("#name17").val();
        var name18 = $("#name18").val();
        var name19 = $("#name19").val();
        var name20 = $("#name20").val();

        var value1 = $("#value1").val();
        var value2 = $("#value2").val();
        var value3 = $("#value3").val();
        var value4 = $("#value4").val();
        var value5 = $("#value5").val();
        var value6 = $("#value6").val();
        var value7 = $("#value7").val();
        var value8 = $("#value8").val();
        var value9 = $("#value9").val();
        var value10 = $("#value10").val();
        var value11 = $("#value11").val();
        var value12 = $("#value12").val();
        var value13 = $("#value13").val();
        var value14 = $("#value14").val();
        var value15 = $("#value15").val();
        var value16 = $("#value16").val();
        var value17 = $("#value17").val();
        var value18 = $("#value18").val();
        var value19 = $("#value19").val();
        var value20 = $("#value20").val();

        var sf1 = "";
        var sf2 = "";
        var sf3 = "";
        var sf4 = "";
        var sf5 = "";
        var sf6 = "";
        var sf7 = "";
        var sf8 = "";
        var sf9 = "";
        var sf10 = "";
        var sf11 = "";
        var sf12 = "";
        var sf13 = "";
        var sf14 = "";
        var sf15 = "";
        var sf16 = "";
        var sf17 = "";
        var sf18 = "";
        var sf19 = "";
        var sf20 = "";

        if($('#sf1').is(':checked')) {
            sf1 = "1";
        }
        else {
            sf1 = "0";
        }
        if($('#sf2').is(':checked')) {
            sf2 = "1";
        }
        else {
            sf2 = "0";
        }
        if($('#sf3').is(':checked')) {
            sf3 = "1";
        }
        else {
            sf3 = "0";
        }
        if($('#sf4').is(':checked')) {
            sf4 = "1";
        }
        else {
            sf4 = "0";
        }
        if($('#sf5').is(':checked')) {
            sf5 = "1";
        }
        else {
            sf5 = "0";
        }
        if($('#sf6').is(':checked')) {
            sf6 = "1";
        }
        else {
            sf6 = "0";
        }
        if($('#sf7').is(':checked')) {
            sf7 = "1";
        }
        else {
            sf7 = "0";
        }
        if($('#sf8').is(':checked')) {
            sf8 = "1";
        }
        else {
            sf8 = "0";
        }
        if($('#sf9').is(':checked')) {
            sf9 = "1";
        }
        else {
            sf9 = "0";
        }
        if($('#sf10').is(':checked')) {
            sf10 = "1";
        }
        else {
            sf10 = "0";
        }
        if($('#sf11').is(':checked')) {
            sf11 = "1";
        }
        else {
            sf11 = "0";
        }
        if($('#sf12').is(':checked')) {
            sf12 = "1";
        }
        else {
            sf12 = "0";
        }
        if($('#sf13').is(':checked')) {
            sf13 = "1";
        }
        else {
            sf13 = "0";
        }
        if($('#sf14').is(':checked')) {
            sf14 = "1";
        }
        else {
            sf14 = "0";
        }
        if($('#sf15').is(':checked')) {
            sf15 = "1";
        }
        else {
            sf15 = "0";
        }
        if($('#sf16').is(':checked')) {
            sf16 = "1";
        }
        else {
            sf16 = "0";
        }
        if($('#sf17').is(':checked')) {
            sf17 = "1";
        }
        else {
            sf17 = "0";
        }
        if($('#sf18').is(':checked')) {
            sf18 = "1";
        }
        else {
            sf18 = "0";
        }
        if($('#sf19').is(':checked')) {
            sf19 = "1";
        }
        else {
            sf19 = "0";
        }
        if($('#sf20').is(':checked')) {
            sf20 = "1";
        }
        else {
            sf20 = "0";
        }

        var px1 = $("#px1").val();
        var px2 = $("#px2").val();
        var px3 = $("#px3").val();
        var px4 = $("#px4").val();
        var px5 = $("#px5").val();
        var px6 = $("#px6").val();
        var px7 = $("#px7").val();
        var px8 = $("#px8").val();
        var px9 = $("#px9").val();
        var px10 = $("#px10").val();
        var px11 = $("#px11").val();
        var px12 = $("#px12").val();
        var px13 = $("#px13").val();
        var px14 = $("#px14").val();
        var px15 = $("#px15").val();
        var px16 = $("#px16").val();
        var px17 = $("#px17").val();
        var px18 = $("#px18").val();
        var px19 = $("#px19").val();
        var px20 = $("#px20").val();


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
                name9: name9,
                name10: name10,
                name11: name11,
                name12: name12,
                name13: name13,
                name14: name14,
                name15: name15,
                name16: name16,
                name17: name17,
                name18: name18,
                name19: name19,
                name20: name20,

                value1: value1,
                value2: value2,
                value3: value3,
                value4: value4,
                value5: value5,
                value6: value6,
                value7: value7,
                value8: value8,
                value9: value9,
                value10: value10,
                value11: value11,
                value12: value12,
                value13: value13,
                value14: value14,
                value15: value15,
                value16: value16,
                value17: value17,
                value18: value18,
                value19: value19,
                value20: value20,

                sf1: sf1,
                sf2: sf2,
                sf3: sf3,
                sf4: sf4,
                sf5: sf5,
                sf6: sf6,
                sf7: sf7,
                sf8: sf8,
                sf9: sf9,
                sf10: sf10,
                sf11: sf11,
                sf12: sf12,
                sf13: sf13,
                sf14: sf14,
                sf15: sf15,
                sf16: sf16,
                sf17: sf17,
                sf18: sf18,
                sf19: sf19,
                sf20: sf20,

                px1: px1,
                px2: px2,
                px3: px3,
                px4: px4,
                px5: px5,
                px6: px6,
                px7: px7,
                px8: px8,
                px9: px9,
                px10: px10,
                px11: px11,
                px12: px12,
                px13: px13,
                px14: px14,
                px15: px15,
                px16: px16,
                px17: px17,
                px18: px18,
                px19: px19,
                px20: px20
            },
            success: function (data) {
//                top.layer.alert("修改成功");

            }
        });
    }
</script>

</body>
</html>
