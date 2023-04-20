<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<link href="<c:url value='/resources/assets/css/rules.css' />"
	rel="stylesheet" type="text/css" />

<div class="rules-container">
	<div class="rules-content">
		<h1 class="rules-heading">Bảng quy định phòng ban</h1>
		<ol id="rules-list">
			<c:forEach var="qd" items="${qds}">
				<li class="rules-item">
					<p class="rules-describe">
						<b>${qd.tenqd}: </b> ${qd.mota}
					</p>
				</li>
			</c:forEach>
		</ol>
	</div>
	<button id="add-rule-btn">Thêm quy định</button>
</div>

<div id="modal">
	<div class="modal-content" style="border-radius: 5px">
		<span class="btn-close">&times;</span>
		<h3 class="form-title">Thêm quy định</h3>
		<form action="admin/addrule" id="modal-form" class="form-content"
			style="display: flex; flex-direction: column">
			<span class="text--success" style="display: none"></span>
			<div class="form-group width-full">
				<label for="form-label">Tên quy định:</label> <input type="text"
					class="form-input" name="name" />
			</div>

			<div class="form-group"
				style="width: 500px; height: 200px; max-width: 500px; max-height: 200px;">
				<label for="form-label">Mô tả quy định:</label>
				<textarea class="form-input" name="description" style="flex: 1"></textarea>
				<button class="form-btn-submit" type="submit"
					style="min-height: 45px">Lưu</button>
			</div>
		</form>
	</div>
</div>

<script src="<c:url value='/resources/assets/javascript/rules.js' />"></script>