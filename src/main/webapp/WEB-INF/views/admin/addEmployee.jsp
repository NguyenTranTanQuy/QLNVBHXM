<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>

<div class="form-container">
	<form action="admin/add-employee" id="form-employee"
		class="form form-employee" method="POST">
		<span class="text--success"
			style="display: ${success == ''? 'none': 'inline-block'}">${success}</span>
		<h1 class="form-heading">Thông tin nhân viên</h1>
		<div class="form-wrapped">
			<div class="form-group-avatar">
				<label for="" class="form-label">Hình ảnh</label><img
					class="form-avatar" src="./resources/assets/avatar/avatar.png"
					alt=""
					onclick="document.getElementById('form-input-avatar').click();" />
				<input id="form-input-avatar" class="form-input-avatar"
					name="avatar" type="file" style="display: none" />
			</div>
			<div class="form-group">
				<label for="" class="form-label">Mã nhân viên</label> <input
					type="text" name="manv" class="form-input" value="${manv}" disabled />
				<span class="text--error"></span>
			</div>
			<div class="form-group">
				<label for="" class="form-label">CMND</label> <input type="text"
					name="cmnd" class="form-input number"
					placeholder="Nhập CMND/CCCD của bạn" /> <span class="text--error"></span>
			</div>
			<div class="form-group">
				<label for="" class="form-label">Họ</label> <input type="text"
					name="ho" class="form-input" placeholder="Nhập họ của bạn" /> <span
					class="text--error"></span>
			</div>
			<div class="form-group">
				<label for="" class="form-label">Tên</label> <input type="text"
					name="ten" class="form-input" placeholder="Nhập tên của bạn" /> <span
					class="text--error"></span>
			</div>
			<div class="form-group">
				<label for="" class="form-label">Ngày sinh</label> <input
					type="date" name="ngaysinh" class="form-input" /> <span
					class="text--error"></span>
			</div>
			<div class="form-group">
				<label for="" class="form-label">Giới tính</label> <select
					name="gioitinh" class="form-input">
					<option value="male">Nam</option>
					<option value="female">Nữ</option>
				</select>
			</div>
			<div class="form-group">
				<label for="" class="form-label">Địa chỉ</label> <input type="text"
					name="diachi" class="form-input" placeholder="Nhập địa chỉ của bạn" />
				<span class="text--error"></span>
			</div>
			<div class="form-group">
				<label for="" class="form-label">Số điện thoại</label> <input
					type="text" name="sdt" class="form-input number"
					placeholder="Nhập số điện thoại của bạn" /> <span
					class="text--error"></span>
			</div>
			<div class="form-group">
				<label for="" class="form-label">Email</label> <input type="email"
					name="email" class="form-input email"
					placeholder="Nhập email của bạn" /> <span class="text--error"></span>
			</div>

			<!-- <div class="form-group">
              <label for="" class="form-label">Phòng ban</label> <select
                name="phongban" class="form-input">
                <option value="hc">Hành chính</option>
                <option value="kt">Kế toán</option>
                <option value="cskh">Chăm sóc khách hàng</option>
              </select>
            </div>  -->
		</div>
		<h1 class="form-heading">Thông Tin Hợp Đồng</h1>
		<div class="form-wrapped">
			<div class="form-group">
				<label for="" class="form-label">Mã hợp đồng</label> <input
					type="text" name="mahopdong" class="form-input" value="${mahdld}"
					disabled /> <span class="text--error"></span>
			</div>
			<div class="form-group">
				<label for="" class="form-label">Ngày bắt đầu HĐ</label> <input
					type="date" name="ngaybatdauhd" class="form-input" /> <span
					class="text--error"></span>
			</div>
			<div class="form-group">
				<label for="" class="form-label">Ngày kết thúc HĐ</label> <input
					type="date" name="ngayketthuchd" class="form-input" /> <span
					class="text--error"></span>
			</div>
			<div class="form-group">
				<div class="form-group width-full">
					<label for="" class="form-label">Bậc lương:</label> <select name="bacluong"
						id="" class="form-input">
						<option value="0">-- Lựa chọn bậc lương --</option>
						<c:forEach var="o" items="${listSalary}">
							<option value="${o.bacluong}">${o.bacluong} - Lương cơ bản: ${o.luongcoban} VNĐ</option>
						</c:forEach>
					</select>
					<span
					class="text--error"></span>
				</div>
			</div>
		</div>

		<!-- <h1 class="form-heading">Thông tin chức vụ</h1>
          <div class="form-wrapped">
            <div class="form-group">
              <label for="" class="form-label">Tên chức vụ</label> <select
                name="tenchucvu" class="form-input">
                <option value="nv">Nhân viên</option>
                <option value="ql">Quản lý</option>
              </select>
            </div>
          </div> -->

		<div class="form-button-setting">
			<button class="form-button submit" type="submit">Hoàn thành</button>
			<button class="form-button reset" type="reset">Làm mới</button>
		</div>
	</form>
</div>

<script
	src="<c:url value='/resources/assets/javascript/changeAvatarForm.js' />"></script>
<script
	src="<c:url value='/resources/assets/javascript/validationForm.js' />"></script>
<script>
	handleSubmit('form-employee')
</script>