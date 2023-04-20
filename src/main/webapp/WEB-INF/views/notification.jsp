<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<link href="<c:url value='/resources/assets/css/notification.css' />"
	rel="stylesheet" type="text/css" />

<div class="form-container flex-center" style="background-color: #ccc">
	<form action="send-notification" id="form-notification" class="form-notification" method="POST">
		<span class="text--success"
			style="display: ${success == ''? 'none': 'inline-block'}">${success}</span>
		<h1 class="form-heading">Tạo thông báo gửi đến nhân viên</h1>
		<div class="form-group width-full">
			<label for="" class="form-label">Từ: </label> <input type="text"
				name="" class="form-input" value="Quản lý ${nameOffice}" disabled />
		</div>
		<div class="form-group width-full">
			<label for="" class="form-label">Đến: </label> 
			<select name="receiver" class="form-input">
				<option value="0">-- Lựa chọn nhân viên bạn muốn gửi thông
					báo --</option>
				<option value="1">Toàn bộ nhân viên phòng ban</option>
				<c:forEach var="e" items="${listEmployee}">
					<option value="${e.manv}">${e.manv} - ${e.ho} ${e.ten}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group width-full">
			<label for="" class="form-label">Nội dung: </label>
			<textarea type="text" name="content" class="form-input"
				style="max-width: 690px; min-width: 690px; max-height: 150px"></textarea>
		</div>

		<div class="form-button-setting width-full"
			style="justify-content: flex-end">
			<button class="form-btn-submit">Gửi</button>
		</div>
	</form>
</div>

<script
	src="<c:url value='/resources/assets/javascript/notification.js' />"></script>