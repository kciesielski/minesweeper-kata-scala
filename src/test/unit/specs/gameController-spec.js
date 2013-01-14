'use strict';

describe("Dummy test", function () {

    beforeEach(module('msApp'));

    afterEach(inject(function (_$httpBackend_) {
        _$httpBackend_.verifyNoOutstandingExpectation();
        _$httpBackend_.verifyNoOutstandingRequest();
    }));

    var scope, $httpBackend, ctrl, location;

    beforeEach(inject(function (_$httpBackend_, $rootScope, $routeParams, $controller, $location) {
        $httpBackend = _$httpBackend_;

        scope = $rootScope.$new();

        ctrl = $controller('GameController', {$scope: scope});

        $routeParams.entryId = 1;

        location = $location;

    }));


    it('should have running status after starting new game', function () {

        // given
        scope.startNew();

        // when
        var status = scope.statusText();

        // then
        expect(status).toBe("TODO status text");
    });


});