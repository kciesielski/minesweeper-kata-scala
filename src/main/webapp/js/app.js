var msApp = angular.module('msApp', ['msServices']).
    config(['$routeProvider', function($routeProvider) {

        $routeProvider.
            when('/', {templateUrl: 'index.html', controller: GameController}).
            otherwise({redirectTo: '/'});
    }]);