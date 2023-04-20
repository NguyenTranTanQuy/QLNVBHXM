function showCalendar(role) {
	const calendar = document.getElementById("calendar");
	const eventDetails = document.getElementById("event-details");
	const prevButton = document.querySelector(".calendar-prev-btn");
	const nextButton = document.querySelector(".calendar-next-btn");

	let currentDate = new Date();
	let currentMonth = currentDate.getMonth();
	let currentYear = currentDate.getFullYear();

	document.querySelector(".current-day").textContent =
		currentMonth + 1 + "/" + currentYear;

	function renderCalendar() {
		const firstDayOfMonth = new Date(currentYear, currentMonth, 1).getDay();
		const daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate();
		const dayNames = [
			"Chủ nhật",
			"Thứ hai",
			"Thứ ba",
			"Thứ tư",
			"Thứ năm",
			"Thứ sáu",
			"Thứ bảy",
		];

		calendar.innerHTML = "";

		for (let i = 0; i < dayNames.length; i++) {
			const day = document.createElement("div");
			day.classList.add("day-name");
			day.textContent = dayNames[i];
			calendar.appendChild(day);
		}

		for (let i = 0; i < firstDayOfMonth; i++) {
			const day = document.createElement("div");
			day.classList.add("day", "empty");
			calendar.appendChild(day);
		}

		for (let i = 1; i <= daysInMonth; i++) {
			const day = document.createElement("div");
			day.classList.add("day");
			day.textContent = i;

			const currentDate = new Date();
			if (
				currentYear === currentDate.getFullYear() &&
				currentMonth === currentDate.getMonth() &&
				i === currentDate.getDate()
			) {
				day.classList.add("selected");
			}

			calendar.appendChild(day);

			day.addEventListener("click", () => {
				const date = new Date(currentYear, currentMonth, i);
				const today = new Date();
				
				const yesterday = new Date(today);
				yesterday.setDate(today.getDate() - 1);
				
				const time = date.toTimeString().substring(0, 5);
				
				const selectedDay = document.querySelector(".selected");
				if (selectedDay) {
					selectedDay.classList.remove("selected");
				}
				day.classList.add("selected");

				let year_ = date.getFullYear();
				let month_ = date.getMonth() + 1;
				let day_ = date.getDate();

				if (month_ < 10) {
					month_ = '0' + month_;
				}
				if (day_ < 10) {
					day_ = '0' + day_;
				}

				let dateString = year_ + '-' + month_ + '-' + day_;
				let url = role + '/addworking?date=' + dateString;
				$.ajax({
					url: url,
					success: function(data) {
						$("#modal").html(data);
					},
				});
				openModal();			
			});
		}
	}

	renderCalendar();


	prevButton.addEventListener("click", () => {
		currentMonth--;
		if (currentMonth < 0) {
			currentMonth = 11;
			currentYear--;
		}
		document.querySelector(".current-day").textContent =
			currentMonth + 1 + "/" + currentYear;
		renderCalendar();
	});


	nextButton.addEventListener("click", () => {
		currentMonth++;
		if (currentMonth > 11) {
			currentMonth = 0;
			currentYear++;
		}
		document.querySelector(".current-day").textContent =
			currentMonth + 1 + "/" + currentYear;
		renderCalendar();
	});
}



