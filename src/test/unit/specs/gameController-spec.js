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
        $httpBackend.expectPOST('rest/game/randomLevel').respond(200);
        ctrl = $controller('GameController', {$scope: scope});
        $httpBackend.flush();
        $routeParams.entryId = 1;

        location = $location;

    }));


    it('should have status from server after starting new game', function () {

        // given
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

    it('should not do anything when toggle flag on a revealed field', function () {

        $httpBackend.expectPUT('rest/game/step').respond({ "tile": "1"});
        scope.step(1, 1);
        $httpBackend.flush();
        scope.toggleFlag(1, 1)

        // then
        expect(scope.board.rows[1].fields[1].type).toBe(FieldType.REVEALED);
    });

    it('should flag an unflagged field', function () {

        $httpBackend.expectPOST('rest/game/randomLevel').respond(200);

        scope.startNew();
        $httpBackend.flush();
        scope.toggleFlag(1, 1)

        expect(scope.board.rows[1].fields[1].type).toBe(FieldType.FLAGGED);
    });

    it('should unflag a flagged field', function () {

        $httpBackend.expectPOST('rest/game/randomLevel').respond(200);

        scope.startNew();
        $httpBackend.flush();
        scope.toggleFlag(1, 1);
        scope.toggleFlag(1, 1);

        expect(scope.board.rows[1].fields[1].type).toBe(FieldType.UNKNOWN);
    });

    it('should call server to step in running state', function () {

        $httpBackend.expectPOST('rest/game/randomLevel').respond(200);

        scope.startNew();
        $httpBackend.flush();
        $httpBackend.expectPUT('rest/game/step').respond({ "tile": "1"});
        scope.step(1, 1);
        $httpBackend.flush();
        // then
        expect(scope.currentState).toBe(GameState.RUNNING);
    });

    it('should change state if step on a mine', function () {

        $httpBackend.expectPOST('rest/game/randomLevel').respond(200);

        scope.startNew();
        $httpBackend.flush();
        $httpBackend.expectPUT('rest/game/step').respond('{ "tile": "*"}');
        scope.step(1, 1);
        $httpBackend.flush();
        // then
        expect(scope.statusText).toBe('Dead');
        expect(scope.currentState).toBe(GameState.DEAD);
    });

    it('should throw exception if try to step on a non-unknown field', function () {

        $httpBackend.expectPOST('rest/game/randomLevel').respond(200);

        scope.startNew();
        $httpBackend.flush();
        $httpBackend.expectPUT('rest/game/step').respond('{ "tile": "1"}');
        scope.step(1, 1);
        $httpBackend.flush();
        scope.step(1, 1)

        expect(scope.board.rows[1].fields[1].value).toBe("1");
    });

});