angular.module('msServices', ['ngResource']).
    factory('gameCore', function ($resource) {

        return $resource('rest/game/status/', {}, {
            query: {method: 'GET', params: {}, isArray: false}
        });
    });