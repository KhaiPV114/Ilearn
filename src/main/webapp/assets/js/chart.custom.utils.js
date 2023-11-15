'use strict';

window.chartColors = {
    red: 'rgb(255, 99, 132)',
    orange: 'rgb(255, 159, 64)',
    yellow: 'rgb(255, 205, 86)',
    green: 'rgb(75, 192, 192)',
    blue: 'rgb(54, 162, 235)',
    purple: 'rgb(153, 102, 255)',
    grey: 'rgb(201, 203, 207)'
};

(function(global) {
    var Samples = global.Samples || (global.Samples = {});
    var Color = global.Color;
    Samples.utils = {
        // Adapted from http://indiegamr.com/generate-repeatable-random-numbers-in-js/
        srand: function(seed) {
            this._seed = seed;
        },
        rand: function(min, max) {
            var seed = this._seed;
            min = min === undefined ? 0 : min;
            max = max === undefined ? 1 : max;
            this._seed = (seed * 9301 + 49297) % 233280;
            return min + (this._seed / 233280) * (max - min);
        },
        numbers: function(config) {
            var cfg = config || {};
            var count = cfg.count || 31;
            var decimals = cfg.decimals || 8;
            var continuity = cfg.continuity || 1;
            var dfactor = Math.pow(10, decimals) || 0;
            var data = [];
            var i, value;

            for (i = 0; i < count; ++i) {
                data.push(0);
            }
            return data;
        },
        labels: function(config) {
            var cfg = config || {};
            var min = cfg.min || 1;
            var max = cfg.max || 31;    //31 Days
            var step = 1;   //1 days
            var decimals = cfg.decimals || 8;
            var dfactor = Math.pow(10, decimals) || 0;  //Khử số thập phân
            var prefix = cfg.prefix || '';            
            var suffix = cfg.suffix || '';
            var values = [];
            var i;
            for (i = min; i <= max; i += step) {
                values.push(prefix + Math.round(dfactor * i) / dfactor + suffix);
            }

            return values;
        },
        transparentize: function (r, g, b, alpha) {
              const a = (1 - alpha) * 255;
              const calc = x => Math.round((x - a)/alpha);

              return `rgba(${calc(r)}, ${calc(g)}, ${calc(b)}, ${alpha})`;
            }
    };
    // DEPRECATED
    window.randomScalingFactor = function() {
        return Math.round(Samples.utils.rand(-100, 100));
    };
    // INITIALIZATION
    Samples.utils.srand(Date.now());

}(this));