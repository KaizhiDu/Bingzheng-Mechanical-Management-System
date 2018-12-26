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
<input type="hidden" name="bzjid" id="bzjid" value="${jyglBzjjy.id}">
<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>数量：</label>
                    </td>
                    <td>
                        ${jyglBzjjy.sl}
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>入库数量：</label>
                    </td>
                    <td>
                        <select name="rksl" class="form-control" id="rksl">
                            <option value="">请选择</option>
                            <option value="冯立">冯立</option>
                            <option value="孙旭东">孙旭东</option>
                            <option value="张爱荣">张爱荣</option>
                        </select>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>不修改日期：</label>
                    </td>
                    <td>
                        <input type="checkbox" name="bxgrq" id="bxgrq">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="col-md-3">
    </div>
</div>


<script type="text/javascript">

    //检查预警量
    function checkSl(){
        var sl = $("#sl").val();
        var rksl = $("#rksl").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = rksl.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#rksl").val("");
        }
        else if(parseFloat(sl)<parseFloat(rksl)){
            top.layer.alert("入库数量大于可入库数量");
            $("#rksl").val("");
        }
    }

    //根据大类id拿到所有小类名
    function getFlxl() {
        var fldl = $("#fldl").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/ckgl/bzj/getFlxl?fldl="+fldl,
            success : function (data) {
                $("#flxl").text("");
                $("#flxl").append(
                    "<option value=''>请选择</option>"
                );
                for (var i=0;i<data.length;i++){
                    var xl = data[i];
                    $("#flxl").append(
                        "<option value='"+xl.xlmc+"'>"+xl.xlmc+"</option>"
                    );
                }

            }
        });
    }

    //点击保存，保存数据
    function check() {
        var a = document.getElementById("bxgrq");
        var status = "0";
        if (a.checked){
            status = "1";
        }
        var rksl = $("#rksl").val();
        var bzjid = $("#bzjid").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/jygl/bzjjy/saveRkxx",
            data: {
                id: bzjid,
                rksl: rksl,
                status: status
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
