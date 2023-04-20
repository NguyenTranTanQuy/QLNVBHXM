function handleModalRoles() {
	const addRoleModal = document.getElementById("modal");
	const addRoleBtn = document.getElementById("add-rule-btn");

	// When the user clicks on the button, open the modal
	addRoleBtn.onclick = function() {
		addRoleModal.style.display = "flex";
	};

	// When the user clicks on <span> (x), close the modal
	const closeBtn = document.querySelector(".btn-close[name='modal']");
	closeBtn.onclick = function() {
		addRoleModal.style.display = "none";
	};

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
		if (event.target == addRoleModal) {
			addRoleModal.style.display = "none";
		}
	};
}

function handleSubmitRoles() {
	$("#modal-form").on("submit", function(event) {
		event.preventDefault();
		const name = $(".form-input[name='name'").val();
		const description = $(".form-input[name='description'").val();

		if (name.trim() == "" || description.trim() == "") {
			$(".text--success").text("Tên hoặc mô tả không được bỏ trống!");
			$(".text--success").css({
				'display': 'inline-block',
				'backgroundColor': 'red'
			})
		}
		else {
			let url = $(this).attr("action");
			$.ajax({
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
					name,
					description,
				},
				success: function(data) {
					$("main").html(data);
				},
			});
		}
	});
}

handleModalRoles();
handleSubmitRoles();
