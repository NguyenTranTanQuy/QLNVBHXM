let roleAccount = $(".role-account > span").text();
const aside = $("aside");
const icon = $(".bi-arrow");
const iconClassFrom = "bi-arrow-left";
const iconClassTo = "bi-arrow-right";

const computedStyle = getComputedStyle(document.documentElement);
const widthSidebar = computedStyle.getPropertyValue("--width-sidebar");

// Xử lý khi click vào menu trên Header thì sẽ ẩn đi vài thông tin trên menu.
$(document).ready(function() {
	$(".header__menu-show-sidebar").click(() => {
		// Thay đổi icon:
		if (icon.hasClass(iconClassFrom)) {
			icon.removeClass(iconClassFrom).addClass(iconClassTo);
		} else {
			icon.removeClass(iconClassTo).addClass(iconClassFrom);
		}

		$(".side-bar__item-link > span").toggleClass("collapsed");
		$(".icon-caret").toggleClass("collapsed");

		if (roleAccount === $(".role-account > span").text()) {
			$(".bi-caret-down-fill").click();

			$(".role-account > span").text(
				$(".role-account > span").text().substring(0, 2).toUpperCase()
			);

			$(".side-bar__item-link").css("justify-content", "center");
			aside.css("width", "auto");

			document.documentElement.style.setProperty(
				"--width-sidebar",
				widthSidebar
			);
		} else {
			document.documentElement.style.setProperty(
				"--width-sidebar",
				widthSidebar
			);

			$(".role-account > span").text(roleAccount);
			$(".side-bar__item-link").css("justify-content", "space-between");
			aside.css("width", "var(--width-sidebar)");
		}
		
		document.documentElement.style.setProperty(
				"--width-sidebar",
				$('aside').width() + 1 + "px" 
		);

		$(".header__menu-show-sidebar").css("left", `var(--width-sidebar)`);
		$(".side-bar__sublist").toggleClass("collapsed");
	});
});

$(document).ready(function() {
	$(".side-bar__item").click(() => {
		if (roleAccount != $(".role-account > span").text()) {
			$(".header__menu-show-sidebar").click();
		}
	});
});
