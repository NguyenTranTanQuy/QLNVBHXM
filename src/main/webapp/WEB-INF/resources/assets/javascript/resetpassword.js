$("#form-rs-pw").on("submit", function(e) {
	e.preventDefault();
	let check = true;
	let password = document.querySelectorAll(".form-input");

	password.forEach(function(o) {
		if (!validateInput(o.value, $(o).siblings(".text--error"), "Ô")) {
			check = false;
		}
	});

	if (password[1].value.trim() != password[2].value.trim()) {
		$(".confirm-pw").text("Xác thực mật khẩu không chính xác!").toggleClass("display-error", true);
		check = false;
	}

	if (check) {
		let url = $(this).attr("action");
		$.ajax({
			type: "POST",
			beforeSend: function() {
				$("main").append(`
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
			`);
				$(".loader-container").css({
					display: "flex",
				});
			},
			complete: function() {
				$(".loader-container").css({
					display: "none",
				});
			},
			data: {
				oldpw: password[0].value,
				newpw: password[1].value,
				confirmpw: password[2].value,
			},
			url: url,
			success: function(data) {
				$("main").html(data);
			},
		});
	}
});

function validateInput(input, errorElement, errorMessage) {
	if (input.length === 0) {
		errorElement
			.text(errorMessage + " này không được bỏ trống.")
			.toggleClass("display-error", true);
		return false;
	} else if (/[^\u0000-\u007F]/.test(input)) {
		errorElement
			.text(errorMessage + " không được chứa ký tự tiếng Việt.")
			.toggleClass("display-error", true);
		return false;
	} else if (input.length < 5) {
		errorElement
			.text(errorMessage + " phải dài ít nhất 5 ký tự.")
			.toggleClass("display-error", true);
		return false;
	} else if (input.length > 20) {
		errorElement
			.text(errorMessage + " không được dài hơn 20 ký tự.")
			.toggleClass("display-error", true);
		return false;
	} else if (/[ `!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/.test(input)) {
		errorElement
			.text(errorMessage + " không được chứa ký tự đặc biệt.")
			.toggleClass("display-error", true);
		return false;
	} else {
		errorElement.text("").toggleClass("display-error", false);
		return true;
	}
}