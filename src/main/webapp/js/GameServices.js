var services = angular.module('msServices', ['ngResource']);

    services.factory('gameStatus', function ($resource) {

        return $resource('rest/game/status/', {}, {
            query: {method: 'GET', params: {}, isArray: false}
        });
    });

    services.factory('levelService', function ($resource) {

        var boardService = {};
        var self = this;

        self.resetResource = $resource('rest/game/randomLevel',{},{});

        boardService.reset = function(x, y, mineCount, successFunction) {
            var json = {};
            json.x = x;
            json.y = y;
            json.mineCount = mineCount;
            self.resetResource.save(angular.toJson(json), successFunction)

        }

        return boardService;
    });