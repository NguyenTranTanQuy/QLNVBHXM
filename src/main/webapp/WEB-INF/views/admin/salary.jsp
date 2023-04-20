<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<link href="<c:url value='/resources/assets/css/salary.css' />"
	rel="stylesheet" type="text/css" />

<div class="form-container" style="background-color: #fff;">
	<form action="admin/add_salary" id="form-salary"
		class="form form-salary" method="POST">
		<div class="form-wrapped"
			style="padding-bottom: 30px; border-bottom: 2px dashed #000">
			<h1 class="form-heading">Tính lương cho nhân viên</h1>
			<div class="form-group">
				<label for="" class="form-label">Tháng:</label> <input name="month"
					class="form-input" value="${month}" disabled />
			</div>
			<div class="form-group">
				<label for="" class="form-label">Năm:</label> <input name="year"
					class="form-input" value="${year}" disabled />
			</div>
			<div class="form-group width-full">
				<label for="" class="form-label">Nhân viên</label> 
				<select name="manv" class="form-input" onchange="loadDataSalary()">
					<option value="0">-- Lựa chọn mã nhân viên để tính lương --</option>
					<c:forEach var="employee" items = "${listEmployee}">
						<option value="${employee.manv}">${employee.manv} - ${employee.ho} ${employee.ten}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-wrapped"
			style="padding-bottom: 30px; border-bottom: 2px dashed #000">
			<h2 class="form-heading">Dữ kiện chấm công:</h2>
			<div class="form-group">
				<label for="" class="form-label">Tổng số giờ làm việc tối thiểu:</label> <input
					name="giolamviec" class="form-input" value="${totalTime}" disabled />
			</div>
			<div class="form-group">
				<label for="" class="form-label">Số giờ đi trễ:</label> <input
					name="ditre" class="form-input" value="${timeLate}" disabled />
			</div>
			<div class="form-group">
				<label for="" class="form-label">Số giờ về sớm:</label> <input
					name="vesom" class="form-input" value="${timeEarly}" disabled />
			</div>
			<div class="form-group">
				<label for="" class="form-label">Số giờ không đi làm:</label> <input
					name="vesom" class="form-input" value="${timeNotWorking}" disabled />
			</div>
			<div class="form-group">
				<label for="" class="form-label">Phần trăm giờ làm:</label> <input
					name="phantramgiolam" class="form-input" value="${percentageTime}" />
			</div>
		</div>
		<div class="form-wrapped"
			style="padding-bottom: 30px; border-bottom: 2px dashed #000">
			<h2 class="form-heading">Kết quả:</h2>
			<div class="form-group">
				<label for="" class="form-label">Tiền lương:</label> <input
					name="tienluong" class="form-input" value="${totalSalary}" disabled />
			</div>
			<div class="form-group">
				<label for="" class="form-label">Tiền phạt/thưởng:</label> <input
					name="tienphat" type="text" class="form-input" value="${fine}" disabled />
			</div>
		</div>
		<div class="form-button-setting" style="margin-top: 20px">
			<button class="form-button submit" type="submit">Tính lương</button>
			<button class="form-button reset" type="reset">Làm mới</button>
		</div>
	</form>
</div>

<script src="<c:url value='/resources/assets/javascript/salary.js' />"></script>