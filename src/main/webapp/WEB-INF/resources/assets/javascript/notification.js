function handleSendNotification() {
	$('#form-notification').on("submit", function(e) {
		e.preventDefault();
		const receiver = $(".form-input[name='receiver'").val();
		const content = $(".form-input[name='content'").val();
		const textError = $(".text--success");

		if (receiver == "0") {
			textError.text("Vui lòng chọn người mà bạn muốn gửi!");
			textError.css({
				'backgroundColor': 'red',
				'display': 'inline-block'
			});
		} else if (content.trim() == "") {
			textError.text("Bạn phải nhập nội dung thông báo trước khi gửi!");
			textError.css({
				'backgroundColor': 'red',
				'display': 'inline-block'
			});
		}
		else {
			if (confirm("Bạn thực sự muốn gửi thông báo này? Hãy kiểm tra thông tin thật kỹ trước khi gửi")) {
				let url = $(this).attr("action");
				$.ajax({
					type: "POST",
					url: url,
					data: {
						receiver,
						content
					},
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
						$('main').html(data);
					}
				})
			}
		}
	})
}

handleSendNotification();