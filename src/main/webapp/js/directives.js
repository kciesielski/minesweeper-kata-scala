"use strict";

var directives = angular.module("msDirectives", []);

directives.directive("tile", function() {
    return {
        restrict: 'E',
        replace: true,
        transclude: true,
        template: '<div><pre>{{fieldlabel}}</pre></div>',
        link: function(scope, element, attrs) {
            attrs.$observe('text', function(newText) {
                if (newText) {
                    scope.fieldlabel = newText;
                }
            });
        }
    }
});

directives.directive('rightClick', function($parse) {
    return function(scope, element, attr) {
        element.bind('contextmenu', function(event) {
            event.preventDefault();
            var fn = $parse(attr['rightClick']);
            scope.$apply(function() {
                fn(scope, {
                    $event: event
                });
            });
            return false;
        });
    }
});