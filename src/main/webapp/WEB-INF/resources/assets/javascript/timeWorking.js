function handleTimeWorking(role) {
	const btnAddWorkingTime = $(".btn-add-working-time");
	const formAddWorkingTime = $(".form-add-working");
	const listEmployeeWorking = $(".list-employee-working");
	const listTime = $(".working-time");

	if (
		btnAddWorkingTime.length &&
		formAddWorkingTime.length &&
		listEmployeeWorking.length
	) {
		btnAddWorkingTime.click(() => {
			formAddWorkingTime.removeClass("collapsed");
			listEmployeeWorking.addClass("collapsed");
			return false;
		});
	}

	for (let i = 0; i < listTime.length; i++) {
		listTime[i].addEventListener("click", function() {
			let time = $(this).find(".btn-working-time").text();
			let timeArray = time.split("-");
			let fromTime = timeArray[0];
			let toTime = timeArray[1];

			let url = role + '/timeworking?fromTime=' + fromTime + '&toTime=' + toTime;
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
					$(".list-employee").html(data);
				},
			});

			$(".list-employee-time").text("Ca " + time);
			formAddWorkingTime.addClass("collapsed");
			listEmployeeWorking.removeClass("collapsed");
		});
	}
}
