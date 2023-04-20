<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="profile">
	<div class="profile-partical">
		<div class="profile-group">
			<h1 class="profile-title">Trang cá nhân của bạn</h1>
			<div class="profile-group-avatar">
				<img class="profile-avatar"
					src="./resources/assets/avatar/avatar.png" alt="Avatar" />
			</div>
		</div>
		<div class="profile-information">
			<div class="profile-information__detail">
				<span>Họ và tên:</span> ${nv.ho} ${nv.ten}
			</div>

			<div class="profile-information__detail">
				<span>Giới tính:</span> ${nv.gioitinh}
			</div>

			<div class="profile-information__detail">
				<span>Phòng ban:</span> ${nv.mapb.tenpb}
			</div>
			<div class="profile-information__detail">
				<span>Mô tả:</span> ${nv.mapb.mota}
			</div>

			<div class="profile-information__detail">
				<span>Email:</span> ${nv.email}
			</div>

			<div class="profile-information__detail">
				<span>Số điện thoại:</span> ${nv.sdt}
			</div>

			<div class="profile-information__detail">
				<span>Địa chỉ nơi ở:</span> ${nv.diachi}
			</div>
		</div>
		<div class="profile-edit-personal">
			<a id="open-modal" class="edit-personal-btn" href="edit-personal">
				<i class="edit-personal-icon bi bi-pencil-fill"></i>
			</a>
		</div>
	</div>
	<div class="profile-partical"
		style="padding: 0; border: none; justify-content: space-between;">
		<div class="profile-analyze" style="background-color: #FFC300;">
			<div class="profile-analyze__title">Số lượng nhân viên trong
				phòng ban</div>
			<span>${slnvpb} nhân viên</span>
		</div>

		<div class="profile-analyze" style="background-color: #39CCCC;">
			<div class="profile-analyze__title">Số ngày làm trong tháng</div>
			<span>${ngaylam} ngày</span>
		</div>

		<div class="profile-analyze" style="background-color: #FF5733;">
			<div class="profile-analyze__title">Số ca đi trễ</div>
			<span>${ditre} ca</span>
		</div>

		<div class="profile-analyze" style="background-color: #8E44AD;">
			<div class="profile-analyze__title">Số ca về sớm</div>
			<span>${vesom} ca</span>
		</div>
	</div>
</div>

<div id="modal" style="display: ${display ? none : display}">
	<form action="confirm_edit-personal" id="modal__form"
		class="modal__form" method="POST">
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
			<div class="form-group-btn">
				<button class="form-btn-submit" type="submit">Thay đổi</button>
			</div>
		</div>
	</form>
	<button class="btn-close">&times;</button>
</div>

<script
	src="<c:url value='/resources/assets/javascript/validationEdit.js' />"></script>
<script
	src="<c:url value='/resources/assets/javascript/changeAvatarForm.js' />"></script>
<script src="<c:url value='/resources/assets/javascript/modal.js' />"></script>
<script
	src="<c:url value='/resources/assets/javascript/common/modal.js' />"></script>