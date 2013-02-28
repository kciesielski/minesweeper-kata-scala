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
        self.tileResource = $resource('rest/game/tile/:x/:y',{},{});
        self.stepResource = $resource('rest/game/step',{},{
            insert: { method: "PUT" }
        });

        boardService.reset = function(x, y, mineCount, successFunction) {
            var json = {};
            json.x = x;
            json.y = y;
            json.mineCount = mineCount;
            self.resetResource.save(angular.toJson(json), successFunction)

        }

        boardService.step = function(x, y, successFunction) {
            var json = {};
            json.x = x;
            json.y = y;
            self.stepResource.insert(angular.toJson(json), successFunction)
        }

        boardService.checkTile = function(targetX, targetY, successFunction) {
            self.tileResource.get({x: targetX, y: targetY}, successFunction)
        };

        return boardService;
    });