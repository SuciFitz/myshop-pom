<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>我的商城 | 用户表单</title>
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
				用户管理
				<small>Control panel</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
				<li class="active">控制面板</li>
			</ol>
		</section>
		
		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					
					<c:if test="${baseResult != null}">
						<div class="alert alert-${baseResult.status == 200 ? 'success' : 'danger'} alert-dismissible">
							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
								${baseResult.message}
						</div>
					</c:if>
					
					<div class="box box-info">
						<div class="box-header with-border">
							<h3 class="box-title">${tbUser.id == null ? "新增" : "编辑"}用户</h3>
						</div>
						<!-- /.box-header -->
						<!-- form start -->
						<form class="form-horizontal" action="/user/save" method="post">
							<div class="box-body">
								<div class="form-group">
									<label for="inputEmail" class="col-sm-2 control-label">邮箱</label>
									
									<div class="col-sm-10">
										<input type="text" class="form-control" id="inputEmail" name="email"
										       placeholder="Email">
									</div>
								</div>
								<div class="form-group">
									<label for="inputPassword" class="col-sm-2 control-label">密码</label>
									
									<div class="col-sm-10">
										<input type="password" class="form-control" id="inputPassword" name="password"
										       placeholder="请输入密码">
									</div>
								</div>
								<div class="form-group">
									<label for="inputName" class="col-sm-2 control-label">姓名</label>
									
									<div class="col-sm-10">
										<input type="password" class="form-control" id="inputName" name="username"
										       placeholder="请输入姓名">
									</div>
								</div>
								<div class="form-group">
									<label for="inputPhone" class="col-sm-2 control-label">手机号码</label>
									
									<div class="col-sm-10">
										<input type="password" class="form-control" id="inputPhone" name="phone"
										       placeholder="请输入手机号码">
									</div>
								</div>
							</div>
							<!-- /.box-body -->
							<div class="box-footer">
								<button type="button" class="btn btn-default" onclick="history.back()">返回</button>
								<button type="submit" class="btn btn-info pull-right">提交</button>
							</div>
							<!-- /.box-footer -->
						</form>
					</div>
				</div>
			</div>
		</section>
	</div>
	<!-- /.content-wrapper -->
	<jsp:include page="../includes/copyright.jsp"/>
</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>
