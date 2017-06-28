(function (H) {
    var protocol = window.location.protocol;
    var defaultOptionsZhCn = {
        lang: {
            contextButtonTitle: '图表导出菜单',
            decimalPoint: '.',
            downloadJPEG: "下载JPEG图片",
            downloadPDF: "下载PDF文件",
            downloadPNG: "下载PNG文件",
            downloadSVG: "下载SVG文件",
            drillUpText: "返回 {series.name}",
            invalidDate: '无效的时间',
            loading: '加载中...',
            months: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            noData: "没有数据",
            numericSymbols: null,
            printChart: "打印图表",
            resetZoom: '重置缩放比例',
            resetZoomTitle: '重置为原始大小',
            shortMonths: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            thousandsSep: ',',
            weekdays: ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期天'],
            rangeSelectorFrom: '开始时间',
            rangeSelectorTo: '结束时间',
            rangeSelectorZoom: '缩放',
            zoomIn: '缩小',
            zoomOut: '放大'
        },
        global: {
            useUTC: true,
            canvasToolsURL: protocol + '//cdn.hcharts.cn/highcharts/modules/canvas-tools.js',
            VMLRadialGradientURL: protocol + +'//cdn.hcharts.cn/highcharts/gfx/vml-radial-gradient.png'
        },
        title: {text: '图表标题'},
        tooltip: {
            dateTimeLabelFormats: {
                millisecond: '%H:%M:%S.%L',
                second: '%H:%M:%S',
                minute: '%H:%M',
                hour: '%H:%M',
                day: '%Y-%m-%d',
                week: 'Week from %A, %b %e, %Y',
                month: '%Y-%m',
                year: '%Y'
            }
        },
        exporting: {url: protocol + '//export.highcharts.com.cn'},
        credits: {text: 'Highcharts.com.cn', href: 'https://www.highcharts.com.cn'},
        xAxis: {
            dateTimeLabelFormats: {
                millisecond: '%H:%M:%S.%L',
                second: '%H:%M:%S',
                minute: '%H:%M',
                hour: '%H:%M',
                day: '%Y-%m-%d',
                week: '%e. %b',
                month: '%Y-%m',
                year: '%Y'
            }
        }
    };
    H.setOptions(defaultOptionsZhCn)
}(Highcharts));