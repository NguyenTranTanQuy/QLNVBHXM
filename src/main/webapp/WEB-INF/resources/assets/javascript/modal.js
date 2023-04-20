function handleModal() {
	let modal = document.getElementById("modal");
	let closeBtn = document.getElementsByClassName("btn-close")[0];

	closeBtn.onclick = function() {
		modal.style.display = "none";
	};

	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	};
}

handleModal();
