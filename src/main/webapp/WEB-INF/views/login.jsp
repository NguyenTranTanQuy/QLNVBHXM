<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Đăng nhập</title>

<link rel="shortcut icon"
	href="<c:url value='/resources/assets/logo/logo.png'/>"
	type="image/x-icon">
<link rel="icon" href="<c:url value='/resources/assets/logo/logo.png'/>"
	type="image/x-icon">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Handlee&family=Montserrat:wght@400;500;600;700;900&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="<c:url value='/resources/assets/vendors/bootstrap-icons/bootstrap-icons.css' />">
<link href="<c:url value='/resources/assets/css/common/base.css' />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/assets/css/login.css' />"
	rel="stylesheet" type="text/css" />

</head>
<body>
	<main>
		<div class="login__background-image"></div>
		<div class="container">
			<h1 class="login__title">ĐĂNG NHẬP</h1>
			<p class="login__describe">Đăng nhập vào hệ thống Quản lí nhân
				viên ở cửa hàng bảo hành xe máy</p>

			<form action="<c:url value='j_spring_security_login' />"
				id="form-login" class="form-login" method="POST">
				<div class="form-login__group">
					<div class="form-login__icon">
						<i class="bi bi-person"></i>
					</div>
					<input name="username" class="form-login__username" type="text"
						placeholder="Tài khoản" value="${username}">
				</div>
				<span class="username__text--error"></span>

				<div class="form-login__group">
					<div class="form-login__icon">
						<i class="bi bi-shield-lock"></i>
					</div>
					<input name="password" class="form-login__password"
						type="password" placeholder="Mật khẩu">
				</div>
				<span style="display: block"
					class="password__text--error">${message}</span>

				<button class="btn_login" type="submit">Đăng nhập</button>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
		</div>
	</main>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
	<script
		src="<c:url value='/resources/assets/javascript/validationLogin.js' />"></script>
</body>
</html>