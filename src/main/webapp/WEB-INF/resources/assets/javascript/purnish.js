$("#form-purnish").on("submit", function(e) {
	e.preventDefault();
	const manv = $(".form-input[name='manv']").val();
	const mucdo = $(".form-input[name='mucdo'").val();
	let date = $(".form-input[name='date'").val();
	const maqd = $(".form-input[name='maqd'").val();

	const now = new Date();
	const inputDate = new Date(date);

	date = date.replace('T', ' ');
	date = date + ':00';


	if (manv == "0" || mucdo == "0" || maqd == "0") {
		$(".text--success").text("Một vài thông tin chưa được lựa chọn!");
		$(".text--success").css({
			'display': 'inline-block',
			'backgroundColor': 'red'
		})
	}
	else if (inputDate > now) {
		console.log(date);
		$(".text--success").text("Ngày xử phạt phải nhỏ hơn hoặc bằng ngày hiện tại!");
		$(".text--success").css({
			'display': 'inline-block',
			'backgroundColor': 'red'
		})
	}
	else {
		let url = $(this).attr("action");

		$.ajax({
			type: "POST",
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
			data: {
				manv,
				mucdo,
				date,
				maqd
			},
			success: function(data) {
				$("main").html(data);
			}
		});
	}
})

function showMucDo() {
	let maqd = $(".form-input[name = 'maqd']").val();
	let manv = $(".form-input[name = 'manv']").val();
	if (maqd != "0") {
		let url = "admin/loadMucdo";
		$.ajax({
			url: url,
			data: {
				maqd,
				manv
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
				$("main").html(data);
			}
		});
	}
}

function formatDate(date) {
	const day = date.getDate().toString().padStart(2, "0");
	const month = (date.getMonth() + 1).toString().padStart(2, "0");
	const year = date.getFullYear();
	return `${year}-${month}-${day}`;
}