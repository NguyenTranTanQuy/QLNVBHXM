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
