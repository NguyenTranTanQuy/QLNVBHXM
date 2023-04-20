<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
	
<link href="<c:url value='/resources/assets/css/table_account.css' />"
	rel="stylesheet" type="text/css" />

<div class="table-container">
	<div class="table-search-group">
		<input type="text" name="" class="table-search"
			id="account-searchField" placeholder="Tìm kiếm thông tin nhân viên"
			onkeyup="searchTable('account-searchField', 'table-account')" />
	</div>
	<div class="table-container__heading">
		<div class="table-account__checkbox">
			<input type="checkbox" name="only_enabled"
				class="table-input-checkbox" id="table-input-checkbox"
				onclick="fillEnabled('table-input-checkbox', 'table-account')" /> <label
				for="">Only Enabled</label>
		</div>
		<h1 class="table-title">Quản lý danh sách tài khoản</h1>
	</div>
	<div class="table-wrap">
		<table class="table" id="table-account">
			<tr class="table-row">
				<th class="table-heading">Username</th>
				<th class="table-heading">Mã nhân viên</th>
				<th class="table-heading"></th>
				<th class="table-heading">Disabled/Enabled</th>
			</tr>
			<c:forEach var="tk" items="${dstk}">
				<tr class="table-row">
					<td class="table-data">${tk.tentk }</td>
					<td class="table-data">${tk.manv.manv}</td>
					<td class="table-data">
						<button name="Reset_pass"
							onclick="confirmReset('${tk.manv.manv}')"
							class="table-btn__reset-account">Reset password</button>
					</td>
					<td class="table-data">
						<button class="table-btn__change-status"
							name="${tk.trangthai?'disable':'enable'}"
							onclick="confirmChangeStatus('${tk.manv.manv}', '${tk.trangthai}')">
							${tk.trangthai?'Disable':'Enable'}</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>

<script
	src="<c:url value='/resources/assets/javascript/searchTool.js' />"></script>
<script>
	sortTable("table-account");
	
	function confirmReset(manv) {
	  if (confirm("Bạn có chắc muốn reset mật khẩu")) {
	    let url = "admin/resetPassword";
	    console.log(manv);
	    $.ajax({
	      type: "POST",
	      data: {
	        manv,
	      },
	      url: url,
	      beforeSend: function() {
				$('main').append(`
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
			`)
				$('.loader-container').css({
					'display': 'flex'
				});

			},
			complete: function() {
				$('.loader-container').css({
					'display': 'none'
				});
				alert("Đã reset mật khẩu");
			},
	      success: function (data) {
	        $("main").html(data);
	      },
	    });
	  }
	}

	function confirmChangeStatus(manv, status) {
	  if (confirm("Bạn có chắc muốn thay đổi trạng thái tài khoản này")) {
	    let url = "admin/change-status";
	    $.ajax({
	      type: "POST",
	      data: {
	        manv,
	        status,
	      },
	      url: url,
	      beforeSend: function() {
				$('main').append(`
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
			`)
				$('.loader-container').css({
					'display': 'flex'
				});

			},
			complete: function() {
				$('.loader-container').css({
					'display': 'none'
				});
				alert("Thay đổi thành công");
			},
	      success: function (data) {
	        $("main").html(data);
	      },
	    });
	  }
	}
	
	function fillEnabled(inputId, tableId) {
		  let checkBox = document.querySelector("#" + inputId);
		  let table = document.getElementById(tableId);
		  let rows = table.getElementsByTagName("tr");
		  if (checkBox.checked) {
		    for (let i = 1; i < rows.length; i++) {
		      let cells = rows[i].getElementsByTagName("td");
		      let cellText = cells[3].querySelector(".table-btn__change-status").name;
		      if (cellText !== "disable") {
		        rows[i].style.display = "none";
		      } else {
		        rows[i].style.display = "";
		      }
		    }
		  } else {
		    for (let i = 1; i < rows.length; i++) {
		      rows[i].style.display = "";
		    }
		  }
		}
</script>