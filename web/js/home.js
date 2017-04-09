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

    /*设置登录和用户信息的显示*/
    $('.login').click(function () {
        $('.user').show();
        $('.login').hide();
    })
    $('.logout').click(function () {
        $('.login').show();
        $('.user').hide();
    })

    /*设置整个内容部分的高度*/
    var allHeight = $(document).height();
    var navTop = $('#nav-top').height();
    var breadHeight = $('#breadcrumbs').height();
    var breadOut = $('#breadcrumbs').outerHeight();
    var pageinner = $('#page-inner').outerHeight();
    var hei = allHeight - navTop - breadHeight - breadOut;
    $('#page-inner').css({'min-height': '650px'})
    $('#page-inner').css({'height': hei});

    var mainApp = {

        initFunction: function () {
            /*MENU
             ------------------------------------*/
            $('#main-menu').metisMenu();

            $(window).bind("load resize", function () {
                if ($(this).width() < 768) {
                    $('div.sidebar-collapse').addClass('collapse')
                } else {
                    $('div.sidebar-collapse').removeClass('collapse')
                }
            });

            /* MORRIS BAR CHART
             -----------------------------------------*/
            Morris.Bar({
                element: 'morris-bar-chart',
                data: [{
                    y: '2006',
                    a: 100,
                    b: 90
                }, {
                    y: '2007',
                    a: 75,
                    b: 65
                }, {
                    y: '2008',
                    a: 50,
                    b: 40
                }, {
                    y: '2009',
                    a: 75,
                    b: 65
                }, {
                    y: '2010',
                    a: 50,
                    b: 40
                }, {
                    y: '2011',
                    a: 75,
                    b: 65
                }, {
                    y: '2012',
                    a: 100,
                    b: 90
                }],
                xkey: 'y',
                ykeys: ['a', 'b'],
                labels: ['Series A', 'Series B'],
                barColors: [
                    '#A6A6A6', '#24C2CE',
                    '#A8E9DC'
                ],
                hideHover: 'auto',
                resize: true
            });


            /* MORRIS DONUT CHART
             ----------------------------------------*/
            Morris.Donut({
                element: 'morris-donut-chart',
                data: [{
                    label: "Download Sales",
                    value: 12
                }, {
                    label: "In-Store Sales",
                    value: 30
                }, {
                    label: "Mail-Order Sales",
                    value: 20
                }],
                colors: [
                    '#A6A6A6', '#24C2CE',
                    '#A8E9DC'
                ],
                resize: true
            });

            /* MORRIS AREA CHART
             ----------------------------------------*/

            Morris.Area({
                element: 'morris-area-chart',
                data: [{
                    period: '2010 Q1',
                    iphone: 2666,
                    ipad: null,
                    itouch: 2647
                }, {
                    period: '2010 Q2',
                    iphone: 2778,
                    ipad: 2294,
                    itouch: 2441
                }, {
                    period: '2010 Q3',
                    iphone: 4912,
                    ipad: 1969,
                    itouch: 2501
                }, {
                    period: '2010 Q4',
                    iphone: 3767,
                    ipad: 3597,
                    itouch: 5689
                }, {
                    period: '2011 Q1',
                    iphone: 6810,
                    ipad: 1914,
                    itouch: 2293
                }, {
                    period: '2011 Q2',
                    iphone: 5670,
                    ipad: 4293,
                    itouch: 1881
                }, {
                    period: '2011 Q3',
                    iphone: 4820,
                    ipad: 3795,
                    itouch: 1588
                }, {
                    period: '2011 Q4',
                    iphone: 15073,
                    ipad: 5967,
                    itouch: 5175
                }, {
                    period: '2012 Q1',
                    iphone: 10687,
                    ipad: 4460,
                    itouch: 2028
                }, {
                    period: '2012 Q2',
                    iphone: 8432,
                    ipad: 5713,
                    itouch: 1791
                }],
                xkey: 'period',
                ykeys: ['iphone', 'ipad', 'itouch'],
                labels: ['iPhone', 'iPad', 'iPod Touch'],
                pointSize: 2,
                hideHover: 'auto',
                pointFillColors: ['#ffffff'],
                pointStrokeColors: ['black'],
                lineColors: ['#A6A6A6', '#24C2CE'],
                resize: true
            });

            /* MORRIS LINE CHART
             ----------------------------------------*/
            Morris.Line({
                element: 'morris-line-chart',
                data: [
                    {y: '2014', a: 50, b: 90},
                    {y: '2015', a: 165, b: 185},
                    {y: '2016', a: 150, b: 130},
                    {y: '2017', a: 175, b: 160},
                    {y: '2018', a: 80, b: 65},
                    {y: '2019', a: 90, b: 70},
                    {y: '2020', a: 100, b: 125},
                    {y: '2021', a: 155, b: 175},
                    {y: '2022', a: 80, b: 85},
                    {y: '2023', a: 145, b: 155},
                    {y: '2024', a: 160, b: 195}
                ],


                xkey: 'y',
                ykeys: ['a', 'b'],
                labels: ['Total Income', 'Total Outcome'],
                fillOpacity: 0.6,
                hideHover: 'auto',
                behaveLikeLine: true,
                resize: true,
                pointFillColors: ['#ffffff'],
                pointStrokeColors: ['black'],
                lineColors: ['gray', '#24C2CE']

            });


            $('.bar-chart').cssCharts({type: "bar"});
            $('.donut-chart').cssCharts({type: "donut"}).trigger('show-donut-chart');
            $('.line-chart').cssCharts({type: "line"});

            $('.pie-thychart').cssCharts({type: "pie"});


        },

        initialization: function () {
            mainApp.initFunction();

        }

    }
    mainApp.initFunction();
    $('#sideNav').click(function () {
        if ($(this).hasClass('closed')) {
            $('.navbar-side').animate({left: '0px'});
            $(this).removeClass('closed');
            $('#page-wrapper').animate({'margin-left': '260px'});
        } else {
            $(this).addClass('closed');
            $('.navbar-side').animate({left: '260px'})
            $('#page-wrapper').animate({'margin-left': '0px'});
        }
    })



})