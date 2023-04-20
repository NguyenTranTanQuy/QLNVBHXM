<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<base href="${pageContext.servletContext.contextPath}/">
<link href="<c:url value='/resources/assets/css/table_employee.css' />"
	rel="stylesheet" type="text/css" />

<div class="table-container">
	<div class="table-search-group">
		<input type="text" name="" class="table-search"
			id="employee-searchField" placeholder="Tìm kiếm thông tin nhân viên"
			onkeyup="searchTable('employee-searchField', 'table-employee')" />
	</div>

	<h1 class="table-title">Bảng danh sách nhân viên</h1>

	<div class="table-wrap">
		<table class="table table-employee" id="table-employee">
			<tr class="table-row">
				<th class="table-heading">Mã nhân viên</th>
				<th class="table-heading">Họ và tên</th>
				<th class="table-heading">Giới tính</th>
				<th class="table-heading">Email</th>
				<th class="table-heading">Số điện thoại</th>
				<th class="table-heading">Trạng thái</th>
				<th class="table-heading">Sửa</th>
			</tr>
			<c:forEach var="nvs" items="${employees}">
				<tr class="table-row">
					<td class="table-data">${nvs.manv }</td>
					<td class="table-data">${nvs.ho}${nvs.ten }</td>
					<td class="table-data">${nvs.gioitinh }</td>
					<td class="table-data">${nvs.email }</td>
					<td class="table-data">${nvs.sdt }</td>
					<td class="table-data"><span name="${nvs.matk.trangthai}"
						class="table-status">${nvs.matk.trangthai?'Còn làm': 'Đã nghỉ'}</span>
					</td>
					<td class="table-data"><a id="open-modal"
						class="table-edit-btn"
						href="admin/edit?manv=${nvs.manv}&cmnd=${nvs.cmnd}&ho=${nvs.ho}&ten=${nvs.ten}&ngaysinh=${nvs.ngaysinh}&gioitinh=${nvs.gioitinh}&diachi=${nvs.diachi}&email=${nvs.email}&sdt=${nvs.sdt}&mapb=${nvs.mapb.mapb}&trangthai=${nvs.matk.trangthai}">
							<i class="table-edit-search bi bi-pencil-square"></i>
					</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>

<div id="modal" style="display: ${display ? none : display}">
	<form action="admin/confirm_edit" id="modal__form" class="modal__form"
		method="POST">
		<div class="form-heading"
			style="padding: 0; min-width: 333px; border: 1px solid #ccc; border-radius: 10px">
			<h2 class="form-title">Chỉnh sửa thông tin</h2>
			<img class="form-avatar" src="./resources/assets/avatar/avatar.png"
				alt=""
				onclick="document.getElementById('form-input-avatar').click();" />
			<input id="form-input-avatar" class="form-input-avatar" name="avatar"
				type="file" style="display: none" />
		</div>
		<div class="form-content">
			<span class="text--success" id="text--error"
				style="display: ${success == ''? 'none': 'inline-block'}; height: auto">${success}</span>
			<div class="form-group">
				<label class="form-label" for="">Mã nhân viên</label> <input
					name="manv" class="form-input" type="text" value="${manv}" disabled />
			</div>
			<div class="form-group">
				<label class="form-label" for="">CMND</label> <input name="cmnd"
					class="form-input number" type="text" value="${cmnd}" /> <span
					class="text--error"></span>
			</div>
			<div class="form-group">
				<label class="form-label" for="">Họ</label> <input name="ho"
					class="form-input" type="text" value="${ho}" /> <span
					class="text--error"></span>
			</div>
			<div class="form-group">
				<label class="form-label" for="">Tên</label> <input name="ten"
					class="form-input" type="text" value="${ten}" /> <span
					class="text--error"></span>
			</div>
			<div class="form-group">
				<label class="form-label" for="">Ngày sinh</label> <input
					name="ngaysinh" class="form-input" type="date" value="${ngaysinh}" />
				<span class="text--error"></span>
			</div>
			<div class="form-group">
				<label class="form-label" for="">Giới tính</label> <select
					name="gioitinh" class="form-input">
					<option value="male" ${gioitinh == 'Nam'?'selected':''}>Nam</option>
					<option value="female" ${gioitinh == 'Nữ'?'selected':''}>Nữ</option>
				</select>
			</div>
			<div class="form-group" style="width: 100%">
				<label class="form-label" for="">Địa chỉ</label> <input
					name="diachi" class="form-input" type="text" value="${diachi}" />
				<span class="text--error"></span>
			</div>
			<div class="form-group">
				<label class="form-label" for="">SĐT</label> <input name="sdt"
					class="form-input number" type="text" value="${sdt}" /> <span
					class="text--error"></span>
			</div>
			<div class="form-group">
				<label class="form-label" for="">Email</label> <input name="email"
					class="form-input email" type="email" value="${email}" /> <span
					class="text--error"></span>
			</div>
			<div class="form-group width-full">
				<label class="form-label" for="">Phòng ban</label> <select
					name="phongban" class="form-input">
					<c:forEach var="pb" items="${listOffice}">
						<option value="${pb.mapb}" ${mapb == pb.mapb?'selected':''}>${pb.tenpb}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group-btn">
				<button class="form-btn-submit" type="submit">Thay đổi</button>
			</div>
		</div>
	</form>
	<button class="btn-close">&times;</button>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="<c:url value='/resources/assets/javascript/searchTool.js' />"></script>
<script
	src="<c:url value='/resources/assets/javascript/validationEdit.js' />"></script>
<script
	src="<c:url value='/resources/assets/javascript/changeAvatarForm.js' />"></script>	
<script>
	sortTable("table-employee");
</script>

<script
	src="<c:url value='/resources/assets/javascript/common/modal.js' />"></script>

<script
	src="<c:url value='/resources/assets/javascript/modal.js' />"></script>
