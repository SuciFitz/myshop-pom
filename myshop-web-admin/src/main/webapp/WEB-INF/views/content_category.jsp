<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<html>
<head>
	<title>我的商城 | 内容管理</title>
	<jsp:include page="../includes/header.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
	<jsp:include page="../includes/nav.jsp"/>
	<jsp:include page="../includes/menu.jsp"/>
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				内容管理
				<small>User Management</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="/main"><i class="fa fa-dashboard"></i> 首页</a></li>
				<li class="active">用户列表</li>
			</ol>
		</section>
		
		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<!-- /.box-footer -->
					</div>
					
					<c:if test="${baseResult != null}">
						<div class="alert alert-${baseResult.status == 200 ? 'success' : 'danger'} alert-dismissible">
							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
								${baseResult.message}
						</div>
					</c:if>
					
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">分类列表</h3>
						</div>
						
						<div class="box-body">
							<a href="#" class="btn btn-sm btn-default" style="margin-right: 5px;"><i
									class="fa fa-plus"></i> 新增</a>
							<button class="btn btn-sm btn-default"
							        style="margin-right: 5px;"><i class="fa fa-trash-o"></i> 删除
							</button>
							<a href="#" class="btn btn-sm btn-default" style="margin-right: 5px;"><i
									class="fa fa-upload"></i> 导入</a>
							<a href="#" class="btn btn-sm btn-default" style="margin-right: 5px;"><i
									class="fa fa-download"></i> 导出</a>
						</div>
						<!-- /.box-header -->
						<div class="box-body table-responsive">
							<table class="table table-hover" id="dataTable">
								<thead>
								</thead>
								<tbody></tbody>
							</table>
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->
				</div>
			</div>
		</section>
	</div>
	<!-- /.content-wrapper -->
	<jsp:include page="../includes/copyright.jsp"/>
</div>
<jsp:include page="../includes/footer.jsp"/>

<%--自定义模态框--%>
<sys:modal/>

</body>
</html>
