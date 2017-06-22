var Script = function () {

    //morris chart

    $(function () {
      // data stolen from http://howmanyleft.co.uk/vehicle/jaguar_'e'_type
      var tax_data = [
           {"period": "2011 Q3", "licensed": 3407, "sorned": 660},
           {"period": "2011 Q2", "licensed": 3351, "sorned": 629},
           {"period": "2011 Q1", "licensed": 3269, "sorned": 618},
           {"period": "2010 Q4", "licensed": 3246, "sorned": 661},
           {"period": "2009 Q4", "licensed": 3171, "sorned": 676},
           {"period": "2008 Q4", "licensed": 3155, "sorned": 681},
           {"period": "2007 Q4", "licensed": 3226, "sorned": 620},
           {"period": "2006 Q4", "licensed": 3245, "sorned": null},
           {"period": "2005 Q4", "licensed": 3289, "sorned": null}
      ];
      Morris.Line({
        element: 'hero-graph',
        data: tax_data,
        xkey: 'period',
        ykeys: ['licensed', 'sorned'],
        labels: ['Licensed', 'Off the road'],
        lineColors:['#8075c4','#6883a3']
      });

      Morris.Donut({
        element: 'hero-donut',
        data: [
          {label: 'Jam', value: 25 },
          {label: 'Frosted', value: 40 },
          {label: 'Custard', value: 25 },
          {label: 'Sugar', value: 10 }
        ],
          colors: ['#41cac0', '#49e2d7', '#34a39b'],
        formatter: function (y) { return y + "%" }
      });

      Morris.Area({
        element: 'hero-area',
        data: [
          {period: '2010 Q1', one: 2666, two: null, three: 2647},
          {period: '2010 Q2', one: 2778, two: 2294, three: 2441},
          {period: '2010 Q3', one: 4912, two: 1969, three: 2501},
          {period: '2010 Q4', one: 3767, two: 3597, three: 5689},
          {period: '2011 Q1', one: 6810, two: 1914, three: 2293},
          {period: '2011 Q2', one: 5670, two: 4293, three: 1881},
          {period: '2011 Q3', one: 4820, two: 3795, three: 1588},
          {period: '2011 Q4', one: 15073, two: 5967, three: 5175},
          {period: '2012 Q1', one: 10687, two: 4460, three: 2028},
          {period: '2012 Q2', one: 8432, two: 5713, three: 1791}
        ],

          xkey: 'period',
          ykeys: ['one', 'two', 'three'],
          labels: ['one', 'two', 'three'],
          hideHover: 'auto',
          lineWidth: 1,
          pointSize: 5,
          lineColors: ['#4a8bc2', '#ff6c60', '#a9d86e'],
          fillOpacity: 0.5,
          smooth: true
      });

      Morris.Bar({
        element: 'hero-bar',
        data: [
          {device: 'one', geekbench: 136},
          {device: 'one 1', geekbench: 137},
          {device: 'one 2', geekbench: 275},
          {device: 'one 3', geekbench: 380},
          {device: 'one 4', geekbench: 655},
          {device: 'one 5', geekbench: 1571}
        ],
        xkey: 'device',
        ykeys: ['geekbench'],
        labels: ['Geekbench'],
        barRatio: 0.4,
        xLabelAngle: 35,
        hideHover: 'auto',
        barColors: ['#6883a3']
      });

      new Morris.Line({
        element: 'examplefirst',
        xkey: 'year',
        ykeys: ['value'],
        labels: ['Value'],
        data: [
          {year: '2008', value: 20},
          {year: '2009', value: 10},
          {year: '2010', value: 5},
          {year: '2011', value: 5},
          {year: '2012', value: 20}
        ]
      });

      $('.code-example').each(function (index, el) {
        eval($(el).text());
      });
    });

}();




