<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<link href="<c:url value='/resources/assets/css/timekeeping.css' />"
	rel="stylesheet" type="text/css" />

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

<div class="timekeeping__container">
	<h1 class="timekeeping__heading">Chấm công</h1>
	<div class="timekeeping__content">
		<div class="timekeeping__checkin">
			<div class="timekeeping__checkin--title">CHECKIN</div>
			<div class="timekeeping__checkin--group">
				<span class="timekeeping__checkin--time">Thời điểm vào ca:</span> <span
					class="timekeeping__checkin--timer" name="start">${vaoca}</span>
			</div>
			<div class="timekeeping__checkin--group">
				<span class="timekeeping__checkin--time">Thời điểm checkin:</span> <span
					class="timekeeping__checkin--timer" name="checkin">${checkin}</span>
			</div>

			<div class="timekeeping__checkin--group">
				<span class="timekeeping__checkin-status">Trạng thái:</span> <span
					class="timekeeping__checkin--status-name">${status}</span>
			</div>
		</div>
		<div class="timekeeping__checkout">
			<div class="timekeeping__checkout--title">CHECKOUT</div>
			<div class="timekeeping__checkout--group">
				<span class="timekeeping__checkout--time">Thời điểm tan ca:</span> <span
					class="timekeeping__checkout--timer" name="end">${tanca}</span>
			</div>
			<div class="timekeeping__checkout--group">
				<span class="timekeeping__checkout--time">Thời điểm checkout:</span>
				<span class="timekeeping__checkout--timer" name="checkout"></span>
			</div>
			<div class="timekeeping__checkout--group">
				<span class="timekeeping__checkout-status">Trạng thái:</span> <span
					class="timekeeping__checkout--status-name"></span>
			</div>
		</div>
		<form action="admin/timekeeping" id="form-timekeeping">
			<button class="timekeeping__button" name="chamcong"
				style="float: right">Chấm công</button>
		</form>
	</div>
</div>

<script
	src="<c:url value='/resources/assets/javascript/timekeeping.js' />"></script>

