<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<script type="text/javascript">
	try{ace.settings.loadState('sidebar')}catch(e){}
</script>

<%--<div class="sidebar-shortcuts" id="sidebar-shortcuts">--%>
	<%--&lt;%&ndash;<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">&ndash;%&gt;--%>
		<%--&lt;%&ndash;<button class="btn btn-success">&ndash;%&gt;--%>
			<%--&lt;%&ndash;<i class="ace-icon fa fa-signal"></i>&ndash;%&gt;--%>
		<%--&lt;%&ndash;</button>&ndash;%&gt;--%>

		<%--&lt;%&ndash;<button class="btn btn-info">&ndash;%&gt;--%>
			<%--&lt;%&ndash;<i class="ace-icon fa fa-pencil"></i>&ndash;%&gt;--%>
		<%--&lt;%&ndash;</button>&ndash;%&gt;--%>

		<%--<!-- #section:basics/sidebar.layout.shortcuts -->--%>
		<%--&lt;%&ndash;<button class="btn btn-warning">&ndash;%&gt;--%>
			<%--&lt;%&ndash;<i class="ace-icon fa fa-users"></i>&ndash;%&gt;--%>
		<%--&lt;%&ndash;</button>&ndash;%&gt;--%>

		<%--&lt;%&ndash;<button class="btn btn-danger">&ndash;%&gt;--%>
			<%--&lt;%&ndash;<i class="ace-icon fa fa-cogs"></i>&ndash;%&gt;--%>
		<%--&lt;%&ndash;</button>&ndash;%&gt;--%>

		<%--<!-- /section:basics/sidebar.layout.shortcuts -->--%>
	<%--&lt;%&ndash;</div>&ndash;%&gt;--%>

	<%--<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">--%>
		<%--<span class="btn btn-success"></span>--%>

		<%--<span class="btn btn-info"></span>--%>

		<%--<span class="btn btn-warning"></span>--%>

		<%--<span class="btn btn-danger"></span>--%>
	<%--</div>--%>
<%--</div><!-- /.sidebar-shortcuts -->--%>

<ul class="nav nav-list">
	<%@include file="./menu.jsp"%>
</ul><!-- /.nav-list -->

<!-- #section:basics/sidebar.layout.minimize -->
<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
	<i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
</div>
