<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>


<aside>
	<div class="role-account">
		<span>Employee</span>
	</div>

	<ul class="side-bar__list">
		<li class="side-bar__item"><a href="home"
			class="side-bar__item-link" name="home"> <i class="bi bi-house-fill"></i> <span>Trang
					chủ</span> <span></span>
		</a></li>

		<li class="side-bar__item"><a href="#!"
			class="side-bar__item-link"> <i class="bi bi-pencil-fill"></i> <span>Chấm
					công</span> <i class="icon-caret bi bi-caret-left-fill"></i>
		</a>
			<ul class="side-bar__sublist">
				<li class="side-bar__subitem"><a href="employee/timekeeping"
					class="side-bar__subitem-link">Báo danh</a></li>
				<li class="side-bar__subitem"><a href="employee/table-timekeeping"
					class="side-bar__subitem-link">Bảng chấm công</a></li>
			</ul></li>

		<li class="side-bar__item"><a href="#!"
			class="side-bar__item-link"> <i class="bi bi-calendar-day-fill"></i>
				<span>Lịch làm việc</span> <i
				class="icon-caret bi bi-caret-left-fill"></i>
		</a>
			<ul class="side-bar__sublist">
				<li class="side-bar__subitem"><a href="employee/calendar"
					class="side-bar__subitem-link">Xem lịch làm việc</a></li>
			</ul></li>

		<li class="side-bar__item"><a href="#!"
			class="side-bar__item-link"> <i
				class="bi bi-exclamation-triangle-fill"></i> <span>Xử phạt</span> <i
				class="icon-caret bi bi-caret-left-fill"></i>
		</a>
			<ul class="side-bar__sublist">
				<li class="side-bar__subitem"><a href="employee/list-purnish"
					class="side-bar__subitem-link">Danh sách xử phạt</a></li>
			</ul></li>

		<li class="side-bar__item"><a href="#!"
			class="side-bar__item-link"> <i class="bi bi-cash-stack"></i> <span>Lương</span>
				<i class="icon-caret bi bi-caret-left-fill"></i>
		</a>
			<ul class="side-bar__sublist">
				<li class="side-bar__subitem"><a href="employee/list-salary"
					class="side-bar__subitem-link">Danh sách lương của bạn</a></li>

			</ul></li>

		<li class="side-bar__item"><a href="#!"
			class="side-bar__item-link"> <i class="bi bi-collection-fill"></i>
				<span>Quy định phòng ban</span> <i
				class="icon-caret bi bi-caret-left-fill"></i>
		</a>
			<ul class="side-bar__sublist">
				<li class="side-bar__subitem"><a href="employee/rules"
					class="side-bar__subitem-link">Xem quy định</a></li>
			</ul></li>
	</ul>
</aside>
</body>
</html>