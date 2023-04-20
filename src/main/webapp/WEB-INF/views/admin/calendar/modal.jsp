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

			<li class="working-time">
				<button class="btn-add-working-time">Thêm ca</button>
			</li>

		</ul>
	</div>

	<div class="list-employee-working collapsed">
		<h1 class="form-title">Danh sách nhân viên</h1>
		<h2 class="list-employee-time"></h2>
		<div class="list-employee"></div>
		<button class="btn-save-list" ${disabled >= 0 ?'disabled':''}
			onclick="getListEmployee()">Lưu</button>
	</div>

	<form action="admin/addworking" id="form-add-working"
		class="form form-add-working" method="POST">
		<span class="text--success"
			style="display: ${success == ''? 'none': 'inline-block'}">${success}</span>
		<h1 class="form-title">Thêm ca làm việc</h1>
		<div class="form-group width-full">
			<label for="" class="form-label">Ngày làm việc</label><input
				type="text" name="ngaylamviec" class="form-input" value="${dateNow}"
				disabled />
		</div>
		<div class="form-group width-full">
			<label for="" class="form-label">Giờ vào: </label><input type="time"
				name="giovao" class="form-input" />
		</div>
		<div class="form-group width-full">
			<label for="" class="form-label">Giờ ra</label><input type="time"
				name="giora" class="form-input" />
		</div>
		<div class="form-group width-full">
			<button class="btn-create-list-working"
				${disabled >= 0 ?'disabled':''} onclick="handleCreateTimeWorking()">Tạo
				lịch làm việc</button>
		</div>
	</form>
</div>

<script>
function handleCreateTimeWorking() {
	  $("#form-add-working").on("submit", function (e) {
	    e.preventDefault();
	    let url = $(this).attr("action");
	    let giovao = $('.form-input[name="giovao"]').val();
	    let giora = $('.form-input[name="giora"]').val();

	    $.ajax({
	      type: "POST",
	      url: url,
	      data: {
	        giovao,
	        giora,
	      },
	      success: function (data) {
	        $("#modal").html(data);
	      },
	    });
	  });
	}

function getListEmployee() {
	let employees = $(".employee-working-checkbox:checked");
	let lstEmployee = "";
	for (let i = 0; i < employees.length; i++) {
		lstEmployee += employees[i].value + "-";
	}
	if (confirm("Bạn có chắc muốn lưu không?")) {
		alert("Bạn đã cập nhật thành công!");
		let url = "admin/add_employee_working";
	
		$.ajax({
			url: url,
			data: {
				lstEmployee,
			},
			success: function(data) {
				$("main").html(data);
			},
		});
	}
}

</script>

<script src="<c:url value='/resources/assets/javascript/modal.js' />"></script>
<script
	src="<c:url value='/resources/assets/javascript/timeWorking.js' />"></script>

<script>
	handleTimeWorking("admin");
</script>