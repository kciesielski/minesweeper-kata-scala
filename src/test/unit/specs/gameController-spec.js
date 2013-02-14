'use strict';

describe("Game Controller", function () {

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


    it('should have status from server after starting new game', function () {

        // given
        $httpBackend.expectGET('rest/game/status').respond('{ "status": "Running"  }');

        // when
        scope.startNew();
        $httpBackend.flush();

        // then
        var status = scope.statusText;
        expect(status).toBe('Running');
    });


});