function formatCurrency() {
	const currencyElements = document.querySelectorAll('.currency');
	currencyElements.forEach((element) => {
		element.textContent = Number(element.textContent).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
	});
}

formatCurrency();