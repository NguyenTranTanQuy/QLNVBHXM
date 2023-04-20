$("#open-modal").click(function (e) {
	e.preventDefault();
	let url = $(this).attr("href");
	$.ajax({
		url: url,
		success: function (data) {
			$("main").html(data)
		}
	})
})
$("#modal__form").on("submit", function(e) {
	e.preventDefault();
	if (confirm("Bạn thật sự muốn thay đổi thông tin không?")) {
		let url = $(this).attr("action");
		let avatar = $(".form-input-avatar[name='avatar']").val();
		let manv = $(".form-input[name='manv']").val();
		let cmnd = $(".form-input[name='cmnd']").val();
		let ho = $(".form-input[name='ho']").val();
		let ten = $(".form-input[name='ten']").val();
		let ngaysinh = $(".form-input[name='ngaysinh']").val();
		let gioitinh = $(".form-input[name='gioitinh']").val();
		let diachi = $(".form-input[name='diachi']").val();
		let phongban = $(".form-input[name='phongban']").val();
		let sdt = $(".form-input[name='sdt']").val();
		let email = $(".form-input[name='email']").val();

		let date = new Date(); // Get the current date
		let formattedDate = date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);
		let currentYear = date.getFullYear();
		if (currentYear - Number(ngaysinh.substring(0, 4)) <= 18) {
			console.log(formattedDate);

			$('.form-content').animate({
				scrollTop: $('#text--error').offset().top
			}, 500);
			$(".text--success").text("Ngày sinh nhân viên phải đủ 18 tuổi!");

			$(".text--success").css({
				'display': 'inline-block',
				'backgroundColor': 'red'
			})
		}
		else {
			$.ajax({
				type: "POST",
				data: {
					//avatar,
					manv,
					cmnd,
					ho,
					ten,
					ngaysinh,
					gioitinh,
					diachi,
					phongban,
					sdt,
					email
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
				},
				success: function(data) {
					$("main").html(data);
				},
			})
		}
	}
});