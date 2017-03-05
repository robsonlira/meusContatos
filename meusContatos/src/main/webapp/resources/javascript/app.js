$(function() {
	$('.js-toggle').bind('click', function(event) {
		$('.js-sidebar, .js-content').toggleClass('is-toggled');
		event.preventDefault();
	});	
});

function closeDialogIfSucess(xhr, status, args, dialogWidget, dialogId) {
	if (args.validationFailed || args.KEEP_DIALOG_OPENED) {
		jQuery('#' + dialogId).effect("bounce", {
			times : 3,
			distance : 20
		}, 100);
	} else {
		dialogWidget.hide();
	}
}

