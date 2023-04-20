<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<link
	href="<c:url value='/resources/assets/css/table_timekeeping.css' />"
	rel="stylesheet" type="text/css" />

<div class="table-container">
	<div class="table-search-group">
		<input type="text" name="" class="table-search"
			id="timekeeping-searchField"
			placeholder="Tìm kiếm thông tin chấm công"
			onkeyup="searchTable('timekeeping-searchField', 'table-timekeeping')" />
	</div>
	<div class="table-container__heading">
		<h1 class="table-title">Quản lý chấm công</h1>
	</div>
	<div class="table-wrap">
		<table class="table" id="table-timekeeping">
			<tr class="table-row">
				<th class="table-heading">ID</th>
				<th class="table-heading">Mã nhân viên</th>
				<th class="table-heading">Ngày chấm công</th>
				<th class="table-heading">Giờ vào ca</th>
				<th class="table-heading">Giờ tan ca</th>
				<th class="table-heading">Trạng thái</th>
				<th class="table-heading"></th>
			</tr>
			<c:forEach var="o" items="${listTimekeeping}">
				<tr class="table-row">
					<td class="table-data">${o.id}</td>
					<td class="table-data">${o.manv.manv}</td>
					<td class="table-data">${o.ngaycc}</td>
					<td class="table-data">${o.giovao}</td>
					<td class="table-data">${o.giora}</td>
					<td class="table-data">${o.trangthai}</td>
					<td class="table-data">
						<button class="table-btn-notification" name="notification">
							<i class="bi bi-envelope-fill"></i>
						</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>

<script
	src="<c:url value='/resources/assets/javascript/searchTool.js' />"></script>
<script>
	sortTable("table-timekeeping");
</script>