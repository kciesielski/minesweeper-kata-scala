angular.module('msServices', []).
    factory('basket', function() {

        var basket = [];
        var count = 0;

        // Public API here
        return {

            isEmpty: function() {
                return count==0;
            },

            count: function () {
                return count;
            },
            getAll: function() {
                return basket;
            },
            add: function (item) {
                var ind = this.indexOf(item);
                if (ind != -1)
                {
                    basket[ind].quantity += 1;
                }
                else
                {
                    basket.push ({
                        type: item,
                        quantity: 1
                    });
                }
                count += 1;
            },
            clear: function() {
                count = 0;
                basket = [];
            },
            indexOf: function(item) {

                for (var i in basket)
                {
                    if (basket[i].type.id == item.id)
                    {
                        return i;
                    }
                }
                return -1;
            }
        };
    });