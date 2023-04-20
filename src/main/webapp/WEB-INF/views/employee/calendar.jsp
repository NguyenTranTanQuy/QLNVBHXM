<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>

<link href="<c:url value='/resources/assets/css/add_working.css' />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/assets/css/calendar.css' />"
	rel="stylesheet" type="text/css" />

<h1 class="calendar-heading">Lịch làm việc</h1>
<div class="current-day"></div>
<div id="calendar"></div>
<div class="calendar-btn">
	<button class="calendar-prev-btn">Trước</button>
	<button class="calendar-next-btn">Sau</button>
</div>

<div id="modal"></div>

<script>
	function openModal() {
		let modal = document.getElementById("modal");
		modal.style.display = "flex";
	}
</script>
<script src="<c:url value='/resources/assets/javascript/calendar.js' />"></script>
<script>
	showCalendar("employee");
</script>
