<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<link
	href="<c:url value='/resources/assets/css/table_salary.css' />"
	rel="stylesheet" type="text/css" />

<div class="table-container">
	<div class="table-search-group">
		<input type="text" name="" class="table-search"
			id="salary-searchField" placeholder="Tìm kiếm thông tin lương bổng"
			onkeyup="searchTable('salary-searchField', 'table-salary')" />
	</div>
	<div class="table-container__heading">
		<h1 class="table-title">Lương của bạn</h1>
	</div>
	<div class="table-wrap">
		<table class="table" id="table-salary">
			<tr class="table-row">
				<th class="table-heading">Tháng</th>
				<th class="table-heading">Năm</th>
				<th class="table-heading">Bậc lương</th>
				<th class="table-heading">Lương cơ bản</th>
				<th class="table-heading">Phụ cấp</th>
				<th class="table-heading">Số tiền tạm ứng</th>
				<th class="table-heading">Số tiền phạt</th>
				<th class="table-heading">Tổng lương</th>
				<th class="table-heading"></th>
			</tr>
			<c:forEach var="o" items="${listSalaryPersonal}">
				<tr class="table-row">
					<td class="table-data">${o.idctluong.thang}</td>
					<td class="table-data">${o.idctluong.nam}</td>
					<td class="table-data">${o.idctluong.bacluong}</td>
					<td class="table-data currency">${o.bacluongentity.luongcoban}</td>
					<td class="table-data currency">${o.bacluongentity.phucap }</td>
					<td class="table-data currency">${o.tientamung}</td>
					<td class="table-data currency">${tienphat.get(o.manvluong.manv)}</td>
					<td class="table-data currency">${o.bacluongentity.luongcoban + o.bacluongentity.phucap - o.tientamung - tienphat.get(o.manvluong.manv)}</td>
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
	sortTable("table-salary");
</script>