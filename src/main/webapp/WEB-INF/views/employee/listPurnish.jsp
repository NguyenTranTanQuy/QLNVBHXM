<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<link href="<c:url value='/resources/assets/css/table_salary.css' />"
	rel="stylesheet" type="text/css" />

<div class="table-container">
	<div class="table-search-group">
		<input type="text" name="" class="table-search"
			id="purnish-searchField" placeholder="Tìm kiếm thông tin xử phạt"
			onkeyup="searchTable('purnish-searchField', 'table-purnish')" />
	</div>
	<div class="table-container__heading">
		<h1 class="table-title">Quản lý xử phạt</h1>
	</div>
	<div class="table-wrap">
		<table class="table" id="table-purnish">
			<tr class="table-row">
				<th class="table-heading">Mức độ</th>
				<th class="table-heading">Ngày xử phạt</th>
				<th class="table-heading">Số tiền phạt</th>
				<th class="table-heading"></th>
			</tr>
			<c:forEach var="o" items="${listPurnishPersonal}">
				<tr class="table-row">
					<td class="table-data">${o.key.mucdo}</td>
					<td class="table-data">${o.key.thoigianxuphat }</td>
					<td class="table-data currency">${o.idmd.tienphat}</td>
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
<script
	src="<c:url value='/resources/assets/javascript/common/formatCurrency.js' />"></script>	

<script>
	sortTable("table-purnish");
</script>