<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>

<link href="<c:url value='/resources/assets/css/add_purnish.css' />"
	rel="stylesheet" type="text/css" />

<div class="form-container flex-center">
	<form action="admin/add-purnish" id="form-purnish"
		class="form form-purnish" method="POST">
		<span class="text--success"
			style="display: ${success == ''? 'none': 'inline-block'}">${success}</span>
		<h1 class="form-heading">Thêm xử phạt</h1>
		<div class="form-wrapped" style="column-gap: 16px">
			<div class="form-group width-full">
				<label for="" class="form-label">Mã nhân viên (*)</label> <select
					name="manv" class="form-input">
					<option value="0">-- Selection --</option>
					<c:forEach var="nv" items="${nvs}">
						<option ${nvchon==nv.manv?'selected':''} value="${nv.manv}">${nv.manv}
							- ${nv.ho} ${nv.ten}</option>
					</c:forEach>
				</select> <span class="text--error"></span>
			</div>

			<div class="form-group width-full">
				<label for="" class="form-label">Vi phạm quy định</label> <select
					name="maqd" class="form-input" onchange="showMucDo()">
					<option value="0">-- Selection --</option>
					<c:forEach var="qd" items="${qds}">
						<option ${qdchon == qd.id?'selected':''} value="${qd.id}">${qd.tenqd}</option>
					</c:forEach>
				</select> <span class="text--error"></span>
			</div>

			<div class="form-group width-full">
				<label for="" class="form-label">Mức độ xử phạt (*)</label> <select
					name="mucdo" class="form-input">
					<option value="0">-- Selection --</option>
					<c:forEach var="o" items="${lstMucdo}">
						<option value="${o.id}">${o.tenmucdo} - Số tiền phạt:
							${o.tienphat} VNĐ</option>
					</c:forEach>
				</select> <span class="text--error"></span>
			</div>

			<div class="form-group" style="flex: 1">
				<label for="" class="form-label">Ngày phạt</label> <input
					type="datetime-local" name="date" class="form-input" required /> <span
					class="text--error"></span>
			</div>
		</div>

		<div class="form-button-setting" style="float: right">
			<button class="form-button submit" type="submit">Thêm</button>
			<button class="form-button reset" type="reset">Làm mới</button>
		</div>
	</form>
</div>

<script src="<c:url value='/resources/assets/javascript/purnish.js' />"></script>