<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<aside>
	<div class="role-account">
		<span>Administrator</span>
	</div>

	<ul class="side-bar__list">
		<li class="side-bar__item side-bar__item"><a href="home"
			class="side-bar__item-link" name="home"> <i class="bi bi-house-fill"></i> <span>Trang
					chủ</span><span></span>
		</a></li>

		<li class="side-bar__item"><a href="#!"
			class="side-bar__item-link"> <i class="bi bi-person-fill"></i> <span>Quản
					lý nhân viên</span> <i class="icon-caret bi bi-caret-left-fill"></i>
		</a>
			<ul class="side-bar__sublist">
				<li class="side-bar__subitem"><a href="admin/add-employee"
					class="side-bar__subitem-link">Thêm nhân viên</a></li>
				<li class="side-bar__subitem"><a href="admin/list-employee"
					class="side-bar__subitem-link">Danh sách nhân viên</a></li>
				<li class="side-bar__subitem"><a href="admin/list-account"
					class="side-bar__subitem-link">Danh sách tài khoản</a></li>
			</ul></li>

		<!-- <li class="side-bar__item"><a href="#!"
			class="side-bar__item-link"> <i class="bi bi-briefcase-fill"></i>
				<span>Quản lý phòng ban</span> <i
				class="icon-caret bi bi-caret-left-fill"></i>
		</a>
			<ul class="side-bar__sublist">
				<li class="side-bar__subitem"><a href="#!"
					class="side-bar__subitem-link">Nhân viên</a></li>
				<li class="side-bar__subitem"><a href=""
					class="side-bar__subitem-link">Danh sách nhân viên</a></li>
				<li class="side-bar__subitem"><a href="#!"
					class="side-bar__subitem-link">Tài khoản</a></li>
				<li class="side-bar__subitem"><a href="#!"
					class="side-bar__subitem-link">Danh sách tài khoản</a></li>
			</ul></li> -->

		<li class="side-bar__item"><a href="#!"
			class="side-bar__item-link"> <i class="bi bi-pencil-fill"></i> <span>Chấm
					công</span> <i class="icon-caret bi bi-caret-left-fill"></i>
		</a>
			<ul class="side-bar__sublist">
				<li class="side-bar__subitem"><a href="admin/timekeeping"
					class="side-bar__subitem-link">Báo danh</a></li>
				<li class="side-bar__subitem"><a href="admin/table-timekeeping"
					class="side-bar__subitem-link">Bảng chấm công</a></li>
			</ul></li>

		<li class="side-bar__item"><a href="#!"
			class="side-bar__item-link"> <i class="bi bi-calendar-day-fill"></i>
				<span>Quản lý lịch làm việc</span> <i
				class="icon-caret bi bi-caret-left-fill"></i>
		</a>
			<ul class="side-bar__sublist">
				<li class="side-bar__subitem"><a href="admin/calendar"
					class="side-bar__subitem-link">Thêm lịch làm việc</a></li>
			</ul></li>

		<li class="side-bar__item"><a href="#!"
			class="side-bar__item-link"> <i
				class="bi bi-exclamation-triangle-fill"></i> <span>Xử phạt</span> <i
				class="icon-caret bi bi-caret-left-fill"></i>
		</a>
			<ul class="side-bar__sublist">
				<li class="side-bar__subitem"><a href="admin/add-purnish"
					class="side-bar__subitem-link">Thêm xử phạt</a></li>
				<li class="side-bar__subitem"><a href="admin/list-purnish"
					class="side-bar__subitem-link">Danh sách xử phạt</a></li>
			</ul></li>

		<li class="side-bar__item"><a href="#!"
			class="side-bar__item-link"> <i class="bi bi-cash-stack"></i> <span>Lương</span>
				<i class="icon-caret bi bi-caret-left-fill"></i>
		</a>
			<ul class="side-bar__sublist">
				<li class="side-bar__subitem"><a href="admin/salary"
					class="side-bar__subitem-link">Quản lý lương</a></li>
				<li class="side-bar__subitem"><a href="admin/list-salary"
					class="side-bar__subitem-link">Danh sách lương nhân viên</a></li>

			</ul></li>

		<li class="side-bar__item"><a href="#!"
			class="side-bar__item-link"> <i class="bi bi-collection-fill"></i>
				<span>Quản lý quy định</span> <i
				class="icon-caret bi bi-caret-left-fill"></i>
		</a>
			<ul class="side-bar__sublist">
				<li class="side-bar__subitem"><a href="admin/rules"
					class="side-bar__subitem-link">Quy định</a></li>
			</ul></li>
	</ul>
</aside>