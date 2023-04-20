<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>


<div class="form-content-working" style="position: relative">
	<span class="btn-close">&times;</span>
	<div class="list-working-container">
		<div class="list-title">Ca làm việc</div>
		<ul class="list-working">
			<c:forEach var="ca" items="${llvs}">
				<li class="working-time">
					<button class="btn-working-time">${ca.giobatdau}-${ca.gioketthuc}</button>
				</li>
			</c:forEach>
		</ul>
	</div>

	<div class="list-employee-working">
		<h1 class="form-title">Danh sách nhân viên</h1>
		<h2 class="list-employee-time"></h2>
		<div class="list-employee"></div>
	</div>
</div>

<script src="<c:url value='/resources/assets/javascript/modal.js' />"></script>
<script
	src="<c:url value='/resources/assets/javascript/timeWorking.js' />"></script>
<script>
	handleTimeWorking("employee");
</script>