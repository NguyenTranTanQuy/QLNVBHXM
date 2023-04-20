<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<link href="<c:url value='/resources/assets/css/resetpassword.css' />"
	rel="stylesheet" type="text/css" />

<div class="form-container flex-center">
	<form action="reset-password" id="form-rs-pw"
		class="form form-rs-account" method="POST">
		<span class="text--success"
			style="display: ${success == '' ? 'none' : ''}">${success}</span>
		<h1 class="form-title">Đổi mật khẩu</h1>
		<div class="form-group width-full">
			<label for="" class="form-label">Mật khẩu cũ</label><input
				type="password" class="form-input" autocomplete="oldpw" /><span
				class="text--error">${error_oldpw}</span>
		</div>
		<div class="form-group width-full">
			<label for="" class="form-label">Mật khẩu mới</label><input
				type="password" class="form-input" autocomplete="newpw" /><span
				class="text--error"></span>
		</div>
		<div class="form-group width-full">
			<label for="" class="form-label">Xác nhận mật khẩu mới</label><input
				type="password" class="form-input" autocomplete="confirmpw" /><span
				class="text--error confirm-pw"></span>
		</div>
		<div class="form-group width-full">
			<button class="btn-reset-pw" type="submit">Đổi</button>
		</div>
	</form>
</div>

<script
	src="<c:url value='/resources/assets/javascript/resetpassword.js' />"></script>