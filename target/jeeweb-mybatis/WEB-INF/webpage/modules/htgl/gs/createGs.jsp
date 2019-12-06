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
                        <label>甲方：</label>
                    </td>
                    <td>
                        <input name="jf" id="jf" htmlEscape="false" class="form-control" placeholder="请输入甲方公司名称"/>
                    </td>
                </tr>
                <tr class="form-group">
                    <td>
                        <label>乙方：</label>
                    </td>
                    <td>
                        <input type="checkbox" name="yf" id="1" value="1-${jcszZzseName.one}"/>${jcszZzseName.one}
                        <input type="checkbox" name="yf" id="2" value="2-${jcszZzseName.two}"/>${jcszZzseName.two}
                        <input type="checkbox" name="yf" id="3" value="3-${jcszZzseName.three}"/>${jcszZzseName.three}
                        <input type="checkbox" name="yf" id="4" value="4-${jcszZzseName.four}"/>${jcszZzseName.four}
                        <br>
                        <input type="checkbox" name="yf" id="5" value="5-${jcszZzseName.five}"/>${jcszZzseName.five}
                        <input type="checkbox" name="yf" id="6" value="6-${jcszZzseName.six}"/>${jcszZzseName.six}
                        <input type="checkbox" name="yf" id="7" value="7-${jcszZzseName.seven}"/>${jcszZzseName.seven}
                        <input type="checkbox" name="yf" id="8" value="8-${jcszZzseName.eight}"/>${jcszZzseName.eight}
                    </td>
                </tr>

            </table>
        </form>
    </div>
    <div class="col-md-3">
    </div>
</div>


<script type="text/javascript">
    //点击保存，保存数据
    function check() {
        var jf = $("#jf").val();
        obj = document.getElementsByName("yf");
        check_val = [];
        fin_val = "";
        for(k in obj){
            if(obj[k].checked)
                check_val.push(obj[k].value);
        }
        for (var i=0;i<check_val.length;i++){
            if (i==0){
                fin_val = check_val[i];
            }
            else {
                fin_val = fin_val+","+check_val[i];
            }
        }
        $.ajax({
            type: "GET",
            url: "${adminPath}/htgl/gs/saveGs",
            data: {
                jf: jf,
                yf: fin_val
            },
            success: function (data) {


            }
        });
    }
</script>

</body>
</html>
