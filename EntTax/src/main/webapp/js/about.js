/**
 * Created by hxmic on 17-6-26.
 */
$(document).ready(function () {

    $('.back').hide();
    $('.back1').hide();
    $('.back2').hide();
    $('.back3').hide();
    $('.back4').hide();

    $('.card').on('click', function () {
        $('.back').show();
        if ($(this).hasClass('open')) {
            $(this).removeClass('open');
            $('.back').hide();
        } else {
            $('.card').removeClass('open');
            $(this).addClass('open');
        }
    });

    $('.card1').on('click', function () {
        $('.back1').show();
        if ($(this).hasClass('open')) {
            $(this).removeClass('open');
            $('.back1').hide();
        } else {
            $('.card1').removeClass('open');
            $(this).addClass('open');
        }
    });

    $('.card2').on('click', function () {
        $('.back2').show();
        if ($(this).hasClass('open')) {
            $(this).removeClass('open');
            $('.back2').hide();
        } else {
            $('.card2').removeClass('open');
            $(this).addClass('open');
        }
    });

    $('.card3').on('click', function () {
        $('.back3').show();
        if ($(this).hasClass('open')) {
            $(this).removeClass('open');
            $('.back3').hide();
        } else {
            $('.card3').removeClass('open');
            $(this).addClass('open');
        }
    });

    $('.card4').on('click', function () {
        $('.back4').show();
        if ($(this).hasClass('open')) {
            $(this).removeClass('open');
            $('.back4').hide();
        } else {
            $('.card4').removeClass('open');
            $(this).addClass('open');
        }
    });

});

// $(document).ready(function () {
//     $('.front-facing').click(function () {
//         $('.front-facing').hide();
//         $('.back-facing').show();
//     })
//     $('.back-facing').click(function () {
//         $('.front-facing').show();
//         $('.back-facing').hide();
//     })
// })