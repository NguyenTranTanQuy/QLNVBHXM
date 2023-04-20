function showListDetails(divClass, ulClass) {
  $(divClass).click(function (event) {
    event.stopPropagation();
    $(ulClass).show();
  });

  $("html").click(function () {
    $(ulClass).hide();
  });
}

$(document).ready(function () {
  showListDetails(".header__avatar-info", ".header__avatar-show_details");
});

$(document).ready(function () {
  showListDetails(".header__noti", ".header__noti-details");
});
