/**
 * Created by hxmic on 17-6-26.
 */
$(document).ready(function () {

    $('.system-card').on('click', function () {

        if ($(this).hasClass('open')) {
            $(this).removeClass('open');
        } else {
            $('.system-card').removeClass('open');
            $(this).addClass('open');
        }

    });

});