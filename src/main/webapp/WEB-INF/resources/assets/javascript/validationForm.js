document.querySelectorAll(".form-input[type='text']").forEach((input) => {
	input.addEventListener("input", () => {
		let errorElement = $("input[name='" + input.name + "']" + "+ span");
		validateInputText(input.value, errorElement, "Ô này");
	});
});

document.querySelectorAll(".form-input.number").forEach((input) => {
	input.addEventListener("input", () => {
		let errorElement = $("input[name='" + input.name + "']" + "+ span");
		isValidNumber(input.value, errorElement, "Ô này");
	});
});

document.querySelectorAll(".form-input.email").forEach((input) => {
	input.addEventListener("input", () => {
		let errorElement = $("input[name='" + input.name + "']" + "+ span");
		isValidEmail(input.value, errorElement, "Ô này");
	});
});

document.querySelectorAll(".form-input[type='date']").forEach((input) => {
	input.addEventListener("input", () => {
		if (input.value.trim().length == 0) {
			$("input[name='" + input.name + "']" + "+ span")
				.text("Ô này không hợp lệ.")
				.toggleClass("display-error", true);
		} else {
			$("input[name='" + input.name + "']" + "+ span")
				.text("")
				.toggleClass("display-error", false);
		}
	});
});

document.querySelector(".form-input[name='bacluong']").addEventListener("change", function() {
	if (document.querySelector(".form-input[name='bacluong']").value == "0") {
		$(".form-input[name='bacluong']" + "+ span")
			.text("Bạn chưa lựa chọn bậc lương.")
			.toggleClass("display-error", true);
	} else {
		$(".form-input[name='bacluong']" + "+ span")
			.text("")
			.toggleClass("display-error", false);
	}
});

function handleSubmit(idForm) {
	$("#" + idForm).on("submit", function(event) {
		event.preventDefault();
		let inputText = document.querySelectorAll(".form-input[type='text']");
		let inputDate = document.querySelectorAll(".form-input[type='date']");
		let inputNumber = document.querySelectorAll(".form-input.number");
		let inputEmail = document.querySelectorAll(".form-input.email");
		let check = true;

		// Kiểm tra các ô này bắt buộc phải nhập số
		for (let i = 0; i < inputNumber.length; i++) {
			let errorElement = $(
				"input[name='" + inputNumber[i].name + "']" + "+ span"
			);
			let error = isValidNumber(inputNumber[i].value, errorElement, "Ô này");

			if (!error) {
				check = false;
			}
		}

		// Kiểm tra các lỗi về văn bản
		for (let i = 0; i < inputText.length; i++) {
			let errorElement = $("input[name='" + inputText[i].name + "']" + "+ span");

			let error = validateInputText(inputText[i].value, errorElement, "Ô này");

			if (!error) {
				check = false;
			}
		}

		// Kiểm tra các lỗi thời gian không bỏ trống
		for (let i = 0; i < inputDate.length; i++) {
			if (inputDate[i].value.length == 0) {
				$("input[name='" + inputDate[i].name + "']" + "+ span")
					.text("Ô này không hợp lệ.")
					.toggleClass("display-error", true);
				check = false;
			} else {
				$("input[name='" + inputDate[i].name + "']" + "+ span")
					.text("")
					.toggleClass("display-error", false);
			}
		}

		// Kiểm tra ô này bắt buộc nhập email đúng
		for (let i = 0; i < inputEmail.length; i++) {
			let errorElement = $("input[name='" + inputEmail[i].name + "']" + "+ span");
			let error = isValidEmail(inputEmail[i].value, errorElement, "Ô này");

			if (!error) {
				check = false;
			}
		}

		if (document.querySelector(".form-input[name='bacluong']").value == "0") {
			$(".form-input[name='bacluong']" + "+ span")
				.text("Bạn chưa lựa chọn bậc lương.")
				.toggleClass("display-error", true);
			check = false;
		} else {
			$(".form-input[name='bacluong']" + "+ span")
				.text("")
				.toggleClass("display-error", false);
		}

		if (document.querySelector(".form-input[name='cmnd']").value.trim().length == 9 || document.querySelector(".form-input[name='cmnd']").value.trim().length == 12) {
			$(".form-input[name='cmnd']" + "+ span")
				.text("")
				.toggleClass("display-error", false);
		} else {
			$(".form-input[name='cmnd']" + "+ span")
				.text("CMND/CCCD phải 9 hoặc 12 số.")
				.toggleClass("display-error", true);
			check = false;
		}

		if (document.querySelector(".form-input[name='sdt']").value.trim().length == 10) {
			$(".form-input[name='sdt']" + "+ span")
				.text("")
				.toggleClass("display-error", false);
		} else {
			$(".form-input[name='sdt']" + "+ span")
				.text("SDT phải đảm bảo đủ 10 số.")
				.toggleClass("display-error", true);
			check = false;
		}
		let date = new Date();
		let currentYear = date.getFullYear();
		if (document.querySelector(".form-input[name='ngaysinh']").value.length == 0) {
			$(".form-input[name='ngaysinh']" + "+ span")
				.text("Ô này không được bỏ trống.")
				.toggleClass("display-error", true);
			check = false;
		} else if (currentYear - Number(document.querySelector(".form-input[name='ngaysinh']").value.substring(0, 4)) >= 18) {
			$(".form-input[name='ngaysinh']" + "+ span")
				.text("")
				.toggleClass("display-error", false);
		} else {
			$(".form-input[name='ngaysinh']" + "+ span")
				.text("Nhân viên này chưa đủ 18 tuổi.")
				.toggleClass("display-error", true);
			check = false;
		}

		let formattedDate = date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);
		let ngaybatdauhd = $(".form-input[name='ngaybatdauhd']").val();
		let ngayketthuchd = $(".form-input[name='ngayketthuchd']").val();
		if (ngaybatdauhd > ngayketthuchd) {
			$(".form-input[name='ngaybatdauhd']" + "+ span")
				.text("Ngày bắt đầu HDLD phải nhỏ hơn ngày kết thúc")
				.toggleClass("display-error", true);
			check = false;
		} else if (ngayketthuchd < formattedDate) {
			$(".form-input[name='ngayketthuchd']" + "+ span")
				.text("Ngày kết thúc HDLD không được nhỏ hơn ngày hiện tại")
				.toggleClass("display-error", true);
			check = false;
		} else {
			$(".form-input[name='ngaybatdauhd']" + "+ span")
				.text("")
				.toggleClass("display-error", false);
			$(".form-input[name='ngayketthuchd']" + "+ span")
				.text("")
				.toggleClass("display-error", false);
		}
		
		if (ngaybatdauhd.trim() == "") {
			$(".form-input[name='ngaybatdauhd']" + "+ span")
				.text("Ngày bắt đầu HD không được để trống")
				.toggleClass("display-error", true);
			check = false;
		}
		
		if (ngayketthuchd.trim() == "") {
			$(".form-input[name='ngayketthuchd']" + "+ span")
				.text("Ngày kết thúc HD không được để trống")
				.toggleClass("display-error", true);
			check = false;
		}

		if (check) {
			let url = $(this).attr("action");

			let avatar = $(".form-input-avatar[name='avatar']").val();
			let manv = $(".form-input[name='manv']").val();
			let cmnd = $(".form-input[name='cmnd']").val();
			let ho = $(".form-input[name='ho']").val();
			let ten = $(".form-input[name='ten']").val();
			let ngaysinh = $(".form-input[name='ngaysinh']").val();
			let gioitinh = $(".form-input[name='gioitinh']").val();
			let diachi = $(".form-input[name='diachi']").val();
			let sdt = $(".form-input[name='sdt']").val();
			let email = $(".form-input[name='email']").val();
			let mahopdong = $(".form-input[name='mahopdong']").val();
			let bacluong = $(".form-input[name='bacluong']").val();

			$.ajax({
				type: "POST",
				data: {
					avatar,
					manv,
					cmnd,
					ho,
					ten,
					ngaysinh,
					gioitinh,
					diachi,
					sdt,
					email,
					mahopdong,
					ngaybatdauhd,
					ngayketthuchd,
					bacluong
				},
				url: url,
				success: function(data) {
					$("main").html(data);
				},
			});
		}
	});
}

// Hàm kiểm tra lỗi chung của các text
function validateInputText(input, errorElement, errorMessage) {
	if (input.length === 0) {
		errorElement
			.text(errorMessage + " không được bỏ trống.")
			.toggleClass("display-error", true);
		return false;
	} else if (input.length > 20) {
		errorElement
			.text(errorMessage + " không được dài hơn 20 ký tự.")
			.toggleClass("display-error", true);
		return false;
	} else if (/[`!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/.test(input)) {
		errorElement
			.text(errorMessage + " không được chứa ký tự đặc biệt.")
			.toggleClass("display-error", true);
		return false;
	} else {
		errorElement.text("").toggleClass("display-error", false);
		return true;
	}
}

// Hàm kiểm tra email hợp lệ
function isValidEmail(email, errorElement, errorMessage) {
	if (email.length === 0) {
		errorElement
			.text(errorMessage + " không được bỏ trống.")
			.toggleClass("display-error", true);
		return false;
	} else if (!/^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/.test(email)) {
		errorElement
			.text(errorMessage + " nhập email không hợp lệ.")
			.toggleClass("display-error", true);
		return false;
	} else {
		errorElement.text("").toggleClass("display-error", false);
		return true;
	}
}

// Hàm kiểm tra chỉ cho nhập số
function isValidNumber(input, errorElement, errorMessage) {
	if (!/^\d+$/.test(input.trim())) {
		errorElement
			.text(errorMessage + " không được nhập chữ cái.")
			.toggleClass("display-error", true);
		return false;
	} else {
		errorElement.text("").toggleClass("display-error", false);
		return true;
	}
}
