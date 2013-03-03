var services = angular.module('msServices', ['ngResource']);

    services.factory('levelService', function ($resource) {

        var boardService = {};
        var self = this;

        self.resetResource = $resource('rest/game/randomLevel',{},{});
        self.tileResource = $resource('rest/game/tile/:x/:y',{},{});
        self.stepResource = $resource('rest/game/step',{},{
            insert: { method: "PUT" }
        });

        boardService.reset = function(command, successFunction) {
            var json = command;
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