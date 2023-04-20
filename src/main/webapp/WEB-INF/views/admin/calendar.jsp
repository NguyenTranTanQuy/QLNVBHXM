<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>

<link href="<c:url value='/resources/assets/css/add_working.css' />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/assets/css/calendar.css' />"
	rel="stylesheet" type="text/css" />

<h1 class="calendar-heading">Quản lý lịch làm việc</h1>
<div class="current-day"></div>
<div id="calendar"></div>
<div class="calendar-btn">
	<button class="calendar-prev-btn">Trước</button>
	<button class="calendar-next-btn">Sau</button>
</div>

<div id="modal"></div>

<div class="loader-container" style="display: none">
	<div class="load-6">
		<div class="letter-holder">
			<div class="l-1 letter">L</div>
			<div class="l-2 letter">o</div>
			<div class="l-3 letter">a</div>
			<div class="l-4 letter">d</div>
			<div class="l-5 letter">i</div>
			<div class="l-6 letter">n</div>
			<div class="l-7 letter">g</div>
			<div class="l-8 letter">.</div>
			<div class="l-9 letter">.</div>
			<div class="l-10 letter">.</div>
		</div>
	</div>
</div>

<script>
	function openModal() {
		let modal = document.getElementById("modal");
		modal.style.display = "flex";
	}
</script>
<script src="<c:url value='/resources/assets/javascript/calendar.js' />"></script>
<script>
	showCalendar("admin");
</script>