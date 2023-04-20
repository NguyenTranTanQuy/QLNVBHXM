changeAvatar(document.querySelector(".form-input-avatar"), document.querySelector(".form-avatar"));

function changeAvatar(inputFile, imageTag) {
	// Bắt sự kiện khi người dùng thay đổi file
	inputFile.addEventListener("change", function() {
		// Kiểm tra xem người dùng đã chọn file ảnh chưa
		if (inputFile.files && inputFile.files[0]) {
			// Tạo đối tượng FileReader để đọc file ảnh
			const reader = new FileReader();

			// Bắt sự kiện khi đọc file thành công
			reader.onload = function(e) {
				// Thay đổi src của thẻ img thành đường dẫn ảnh vừa đọc được
				imageTag.src = e.target.result;
			};
			// Đọc file ảnh
			reader.readAsDataURL(inputFile.files[0]);
		}
	});
}