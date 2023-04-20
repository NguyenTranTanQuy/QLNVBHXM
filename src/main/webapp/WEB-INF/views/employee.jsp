<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
	
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Nhân viên | Pages</title>
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
<link href="<c:url value='/resources/assets/css/common/header.css' />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/assets/css/common/home.css' />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/assets/css/common/sidebar.css' />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/assets/css/common/form.css' />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/assets/css/common/table.css' />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/assets/css/common/modal.css' />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/assets/css/common/loading.css' />"
	rel="stylesheet" type="text/css" />
		
</head>
<body>
	<%@include file="/WEB-INF/common/employee/header.jsp"%>
	<%@include file="/WEB-INF/common/employee/sidebar.jsp"%>

	<main>
		<%@include file="/WEB-INF/views/home.jsp"%>
	</main>

	<%@include file="/WEB-INF/common/employee/footer.jsp"%>

</body>
</html>
