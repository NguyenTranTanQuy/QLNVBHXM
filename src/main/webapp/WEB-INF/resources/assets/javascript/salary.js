function loadDataSalary() {
	let manv = $(".form-input[name='manv']").val();
	if (manv != "0") {
		let url = "admin/loadDataSalary";

		$.ajax({
			url: url,
			data: {
				manv
			},
			success: function(data) {
				$('main').html(data);
			}
		});
	}
}