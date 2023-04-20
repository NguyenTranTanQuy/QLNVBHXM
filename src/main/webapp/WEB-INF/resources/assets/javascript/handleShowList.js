function toggleSidebarItem(
  itemClass,
  iconClass,
  childClass,
  iconClassFrom,
  iconClassTo
) {
  $(itemClass).click(function (event) {
    // Ngăn chặn hành động mặc định của liên kết
    event.preventDefault();

    // Lấy các phần tử cần thay đổi
    var icon = $(this).find(iconClass);
    var child = $(this).find(childClass);

    // Thay đổi class của icon
    if (icon.hasClass(iconClassFrom)) {
      icon.removeClass(iconClassFrom).addClass(iconClassTo);
    } else {
      icon.removeClass(iconClassTo).addClass(iconClassFrom);
    }

    // Đóng tất cả các child item khác trước khi mở child item được click
    $(childClass).not(child).slideUp();
    $(iconClass).not(icon).removeClass(iconClassTo).addClass(iconClassFrom);

    // Thay đổi thuộc tính css của ul chứa các items
    child.slideToggle();
  });
}

toggleSidebarItem(
  ".side-bar__item",
  ".icon-caret",
  ".side-bar__sublist",
  "bi-caret-left-fill",
  "bi-caret-down-fill"
);
