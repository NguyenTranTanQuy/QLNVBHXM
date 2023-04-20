$("#form-login").on("submit", function(event) {
	var username = $(".form-login__username").val().trim();
	var password = $(".form-login__password").val().trim();

	var usernameError = $(".username__text--error");
	var passwordError = $(".password__text--error");

	var validUsername = validateInput(username, usernameError, "Tên đăng nhập");
	var validPassword = validateInput(password, passwordError, "Mật khẩu");

	if (!validUsername || !validPassword) {
		event.preventDefault();
	}
});

function validateInput(input, errorElement, errorMessage) {
	if (input.length === 0) {
		errorElement.text(errorMessage +" này không được bỏ trống.").toggleClass("display-error", true);
		return false;
	} else if (/[^\u0000-\u007F]/.test(input)) {
		errorElement.text(errorMessage + " không được chứa ký tự tiếng Việt.").toggleClass("display-error", true);
		return false;
	} else if (input.length < 5) {
		errorElement.text(errorMessage + " phải dài ít nhất 5 ký tự.").toggleClass("display-error", true);
		return false;
	} else if (input.length > 20) {
		errorElement.text(errorMessage + " không được dài hơn 20 ký tự.").toggleClass("display-error", true);
		return false;
	} else if (/[ `!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/.test(input)) {
		errorElement.text(errorMessage + " không được chứa ký tự đặc biệt.").toggleClass("display-error", true);
		return false;
	} else {
		errorElement.text("").toggleClass("display-error", false);
		return true;
	}
}