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
        $httpBackend.expectPOST('rest/game/randomLevel').respond(200);
        $httpBackend.expectGET('rest/game/status').respond('{ "status": "Running"  }');

        // when
        scope.startNew();
        $httpBackend.flush();

        // then
        var status = scope.statusText;
        expect(status).toBe('Running');
        var rows = scope.board.rows;
        var firstRow  = scope.board.rows[0];
        expect(rows.length).toBe(8);
        expect(firstRow.fields.length).toBe(8);
    });

    it('should reset board with proper sizes of command', function () {

        // given
        $httpBackend.expectPOST('rest/game/randomLevel').respond(200);
        $httpBackend.expectGET('rest/game/status').respond('{ "status": "Running"  }');
        scope.newGameCommand = {
            width: 3,
            height: 4,
            mineCount: 5
        }

        // when
        scope.startNew();
        $httpBackend.flush();

        // then
        var rows = scope.board.rows;
        var firstRow  = scope.board.rows[0];
        expect(rows.length).toBe(4);
        expect(firstRow.fields.length).toBe(3);
    });


});