$(".side-bar__item-link[name='home']").click(function(e) {
	e.preventDefault();

	let url = $(this).attr("href");

	$.ajax({
		url: url,
		success: function(data) {
			$("main").html(data);
		}
	});
});

$(".side-bar__subitem-link").click(function(e) {
	e.preventDefault();

	let url = $(this).attr("href");

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
		success: function(data) {
			$("main").html(data);
		}
	});
});

$(".header__detail-link").click(function(e) {
	e.preventDefault();

	let url = $(this).attr("href");

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
		success: function(data) {
			$("main").html(data);
		}
	});
});

