/**
 * Created by hxmic on 17-4-1.
 */
$(function () {
    /*设置重新发送验证码等待时间*/
    $('#timer').click(function () {
        var count = 60;
        var countdonw = setInterval(CountDown, 1000);

        function CountDown() {
            $('#timer').attr('disabled', true);
            $('#timer').val(count + 's后重试').css({'color':'gray'});
            if (count == 0) {
                $('#timer').val('获取验证码').removeAttr('disabled');
                clearInterval(countdonw);
            }
            count--;
        }
    })
    /*设置鼠标移入颜色变化按钮 登录....*/
    $().ready(function () {
        $(".btn-an-class").mouseover(function () {
            /* $('.btn-an-class').css({'background':'white'},{'color':'#5182e4'});*/
            this.style.background = 'white';
            this.style.color = '#5182e4';
            this.style.border = '1px solid #5182e4'
        }).mouseout(function () {
            this.style.background = "#5182E4";
            this.style.color = '#fff';
        });

        $('.b-mess').mousemove(function () {
            this.style.color = '#000'
        }).mouseout(function () {
            this.style.color = 'rgba(0,0,0,.5)';
        })
        $('.b-mess').click(function () {
            this.style.border = 'none'
        })
    })

    /*设置同一个页面,点击跳转*/
    $('.forgot-password-link').click(function () {
        $('.login-box').hide();
        $('.forgot-box').show();
    })
    $('.back-to-newpwd').click(function () {
        $('.forgot-box').hide();
        $('.newpwd-box').show();
    })
})