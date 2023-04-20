$("#form-timekeeping").submit(function(event) {
	event.preventDefault();
	const checkinTimer = document.querySelector(".timekeeping__checkin--timer[name='checkin']");
	const checkoutTimer = document.querySelector(".timekeeping__checkout--timer[name='checkout']");
	const checkoutTimeEnd = document.querySelector(".timekeeping__checkout--timer[name='end']").textContent.split(" ")[1];
	const checkoutStatus = document.querySelector(
		".timekeeping__checkout--status-name"
	);
	
	const now = new Date();
	const dateTimeString = formatDate(now) + " " + formatTime(now);

	if (checkinTimer.textContent != "") {
		
		if (checkoutTimeEnd > formatTime(now)) {
			if (confirm("Hiện tại chưa tới giờ tan ca, bạn chắc chắn muốn chấm công không?")) {
				checkoutTimer.textContent = dateTimeString;
				checkoutStatus.textContent = "Về sớm";
				let url = "admin/checkout";
				setTimeout(() => {
					$.ajax({
						url: url,
						beforeSend: function() {
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
				}, 3000);
			}
		}
		else {
			checkoutTimer.textContent = dateTimeString;
			checkoutStatus.textContent = "Đúng giờ";
			let url = "admin/checkout";
			setTimeout(() => {
				$.ajax({
					url: url,
					beforeSend: function() {
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
			}, 3000);
		}
	}
	else {

		let url = "admin/checkin";
		$.ajax({
			url: url,
			beforeSend: function() {
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
});

function formatDate(date) {
	const day = date.getDate().toString().padStart(2, "0");
	const month = (date.getMonth() + 1).toString().padStart(2, "0");
	const year = date.getFullYear();
	return `${year}-${month}-${day}`;
}

function formatTime(date) {
	const hours = date.getHours().toString().padStart(2, "0");
	const minutes = date.getMinutes().toString().padStart(2, "0");
	const seconds = date.getSeconds().toString().padStart(2, "0");
	return `${hours}:${minutes}:${seconds}`;
}

if (document.querySelector(".timekeeping__checkin--timer[name='start']").textContent === "Không có ca làm việc có thể chấm công") {
	const buttonTimekeeping = document.querySelector(".timekeeping__button");
	buttonTimekeeping.disabled = true;
}
