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
    <input type="hidden" name="gsid" id="gsid" value="${gsid}">
<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>合同名称：</label>
                    </td>
                    <td>
                        <input name="htmc" id="htmc" htmlEscape="false" class="form-control" placeholder="请输入合同名称"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>金额：</label>
                    </td>
                    <td>
                        <input name="je" id="je" htmlEscape="false" class="form-control" placeholder="请输入合同金额" onchange="checkJe()"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>返款：</label>
                    </td>
                    <td>
                        <input name="fk" id="fk" htmlEscape="false" class="form-control" placeholder="请输入返款金额" onchange="checkJe()"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>备注：</label>
                    </td>
                    <td>
                        <textarea id="bz" name="bz" class="form-control" rows="3" cols="20" placeholder="请输入备注信息"></textarea>
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
        var money = $("#je").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = money.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            top.layer.alert("请输入数字");
            $("#je").val("");
        }
    }

    //点击保存，保存数据
    function check() {
        var htmc = $("#htmc").val();
        var je = $("#je").val();
        var bz = $("#bz").val();
        var gsid = $("#gsid").val();
        var fk = $("#fk").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/htgl/gs/saveHt",
            data: {
                htmc: htmc,
                je: je,
                bz: bz,
                gsid: gsid,
                fk: fk
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
