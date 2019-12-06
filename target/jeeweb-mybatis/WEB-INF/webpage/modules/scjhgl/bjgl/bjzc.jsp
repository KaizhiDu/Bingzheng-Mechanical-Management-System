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
<input id="bjid" name="bjid" type="hidden" value="${bjid}">
<div class="row">
    <div class="col-md-3">

    </div>
    <div class="col-md-6">
        <form>
            <table class="table">
                <tr class="form-group">
                    <td>
                        <label>零部件图号</label>
                    </td>
                    <td>
                        <label>零部件名称</label>
                    </td>
                    <td>
                        <label>操作</label>
                    </td>
                </tr>
               <c:forEach var="lj" items="${ljList}">
                   <tr class="form-group">
                       <td>
                           <label>${lj.ljth}</label>
                       </td>
                       <td>
                           <label>${lj.ljmc}</label>
                       </td>
                       <td>
                           <input type="checkbox" id="${lj.id}" value="${lj.id}" name="lj" onchange="checkSl('${lj.id}')"></input>
                       </td>
                   </tr>
               </c:forEach>
            </table>
        </form>
    </div>
    <div class="col-md-3">
    </div>
</div>


<script type="text/javascript">


    //用window的onload事件，窗体加载完毕的时候
    window.onload=function(){
        var bjid = $("#bjid").val();
        $.ajax({
            type: "GET",
            url: "${adminPath}/scjhgl/bjgl/serachBjzc",
            data: {
                bjid: bjid
            },
            success: function (data) {
                for (var i=0;i<data.length;i++){
                    var ljid = data[i].ljid;
                    $('#'+ljid).attr('checked','checked');
                }
            }
        });
    }

    //检查数量
    function checkSl(ljid){
        var bjid = $("#bjid").val();
        //如果是选中，需要执行减去操作
        if ($('#'+ljid).is(':checked')){
            //alert("选中");
            $.ajax({
                type: "GET",
                url: "${adminPath}/scjhgl/bjgl/checkSl",
                data: {
                    bjid: bjid,
                    ljid: ljid
                },
                success: function (data) {
                    //1可以 0不可以
                    if (data==1){
                        $.ajax({
                            type: "GET",
                            url: "${adminPath}/scjhgl/bjgl/addDelete",
                            data: {
                                flag: "add",
                                bjid: bjid,
                                ljid: ljid
                            },
                            success: function (data) {


                            }
                        });
                    }
                    else{
                        top.layer.alert('零部件数量超出限制!', {icon: 0, title:'警告'});
                        $('#'+ljid).removeAttr('checked');
                    }
                }
            });
        }
        //如果是未选中，要添加操作
        else{
            //alert("未选中");
            $.ajax({
                type: "GET",
                url: "${adminPath}/scjhgl/bjgl/addDelete",
                data: {
                    flag: "delete",
                    bjid: bjid,
                    ljid: ljid
                },
                success: function (data) {


                }
            });
        }



    }


    //点击保存，保存数据
    function check() {
        var bjid = $("#bjid").val();
        obj = document.getElementsByName("lj");
        check_val = "";
        for(k in obj){
            if(obj[k].checked){
                check_val = check_val + "," + obj[k].value;
            }
        }
        $.ajax({
            type: "GET",
            url: "${adminPath}/scjhgl/bjgl/saveBjzc",
            data: {
                id: bjid,
                check_val: check_val
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
