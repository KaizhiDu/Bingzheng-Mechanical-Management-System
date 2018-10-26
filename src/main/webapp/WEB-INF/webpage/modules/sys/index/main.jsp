<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>首页</title>
	<meta name="keywords" content="<spring:message code="sys.site.keywords" arguments="${platformName}"/>">
	<meta name="description" content="<spring:message code="sys.site.description" arguments="${platformName}"/>">

    <html:css  name="favicon,bootstrap,font-awesome,animate"/>
    <link href="${staticPath}/common/css/style.css" rel="stylesheet">
    
</head>

<body class="gray-bg">
    <div class="row  border-bottom white-bg dashboard-header">
        <div class="col-sm-12">
            <blockquote class="text-warning" style="font-size:14px">
                济南易思创科机械有限公司成立于2004年，总占地面积2000平方米左右。<br>
                公司注册资金400万元 。 现有各类设备40余台，可以制作各种五金冲压件，承揽车、铣、刨、磨等各种机械加工项目。<br>
                公司拥有一支经过严格技术培训的、高水准的技工队伍，企业员工30余人。<br>
                公司实行了标准化管理和严格的产品质量管理，工艺精湛、检测完备、质量上乘。多年来，承蒙广大用户的支持，公司已发展成为集车、铣、刨、磨和五金冲压为一体的专业化机械加工企业。
                公司视质量为生命，以信誉求生存，以科技促发展，以优质信誉为用户服务，我们真切期待与事业伙伴们的全面合作、共创辉煌。
            </blockquote>
        </div>
    </div>
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>联系信息</h5>

                    </div>
                    <div class="ibox-content">
                        <p><i class="fa fa-send-o"></i> 姓名：<b>杜立新</b></p>
	                    <p><i class="fa fa-qq"></i> 微信：<a href="javascript:;">dulixin3232</a></p>
	                    <p><i class="fa fa-code"></i> 手机号：<b>18678873232</b></p>
                        <p><i class="fa fa-code"></i> 地址：<b>济北开发区黄河大街19号B3一层</b></p>
	                </div>

                </div>
            </div>
            <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>更新日志</h5>
                    </div>
                    <div class="ibox-content no-padding">
                        <div class="panel-body">
                            <div class="panel-group" id="version">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h5 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#version" href="#v01">v1.0</a><code class="pull-right">2018.10.26</code>
                                        </h5>
                                    </div>
                                    <div id="v01" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                             <ol>
                                                <li>系统1.0开发完成</li>
                                            </ol>
                                        </div>
                                    </div>
                                </div>
                                
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>主营业务</h5>
                    </div>
                    <div class="ibox-content">
                        <h4>易思创科机械有限公司主要经营：</h4>
                        <ol>
                            <li>机械设备及其配件、模具、机电产品、通讯设备、通讯器材的设计、制造、销售</li>
                            <li>机电设备的技术开发、技术服务、技术咨询</li>
                            <li>防盗设置的销售、安装</li>
                            <li>自有设备的租赁（不含融资性租赁）</li>
                            <li>金属结构制造、销售</li>
                        </ol>
                        <hr>
                         <div class="alert alert-warning">
                               依法须经批准的的项目，经相关部门批准后方可开展经营好的。
                         </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
 	<!-- 全局js -->
	<html:js  name="jquery,bootstrap"/>

	<!-- 自定义js -->
	<script src="${staticPath}/common/js/content.js"></script>
	
</body>

</html>