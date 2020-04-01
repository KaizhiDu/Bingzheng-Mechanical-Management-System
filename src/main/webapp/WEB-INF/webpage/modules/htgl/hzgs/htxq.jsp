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

<h2>${gsmc}</h2>

<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>金额：</label>
                    </td>
                    <td>
                        <h3>${gsje}</h3>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>欠发票：</label>
                    </td>
                    <td>
                        <h3>${gsfp}</h3>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>尾款：</label>
                    </td>
                    <td>
                        <h3>${gshk}</h3>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>合同完成情况：</label>
                    </td>
                    <td>
                        <table class="table">
                            <tr class="form-group">
                                <td><b>合同号</b></td>
                                <td><b>企业合同号</b></td>
                                <td><b>名称</b></td>
                                <td><b>完成情况</b></td>
                            </tr>
                            <c:forEach items="${gshtList}" var="item">
                               <tr class="form-group">
                                   <td>${item.hth}</td>
                                   <td>${item.qyhth}</td>
                                   <td>${item.mc}</td>
                                   <td>
                                       <c:if test="${item.sfwc == false}">
                                           <span style="color: red">未完成</span>
                                       </c:if>
                                       <c:if test="${item.sfwc == true}">
                                           <span style="color: green">已完成</span>
                                       </c:if>
                                   </td>
                               </tr>
                            </c:forEach>
                        </table>
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
    function checkFpje(){
        var fpje = $("#fpje").val();
        // var r = yjkc.match(/^[0-9]*$/);
        var r = fpje.match(/^\d+(\.\d+)?$/);
        //先判断是不是数字
        if(r == null){
            $("#fpje").val("");
            top.layer.alert("请输入数字");
        }
    }

    //点击保存，保存数据
    function check() {
        var htid = $("#htid").val();
        var rq = $("#rq").val();
        var fpje = $("#fpje").val();
        var bz = $("#bz").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/htgl/htgl/saveFpxq",
            data: {
                htid: htid,
                rq: rq,
                fpje: fpje,
                bz: bz
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
