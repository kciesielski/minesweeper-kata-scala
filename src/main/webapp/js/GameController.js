function GameController($scope, gameStatus, levelService) {

    $scope.startNew = function () {
        var cmd = $scope.newGameCommand;
        levelService.reset(cmd.width, cmd.height, cmd.mineCount, function () {
            $scope.resetBoard();
            $scope.currentState = GameState.RUNNING;
            $scope.refreshStatus();
        });
    }

    function applyNewTileValue(x, y, tile) {
        var field = $scope.board.rows[y].fields[x];
        field.value = tile;
        if (tile == '*') {
            field.type = FieldType.MINED;
            $scope.statusText = 'Dead';
            $scope.currentState = GameState.DEAD;
        }
        else {
            field.type = FieldType.REVEALED;
        }
    }

    $scope.step = function (x, y) {

        if ($scope.currentState != GameState.RUNNING)
            throw "unexpected step in non-running state";
        if ($scope.board.rows[y].fields[x].type != FieldType.UNKNOWN) {
            throw "unexpected step on a non-unknown field";
        }
        levelService.step(x, y, function () {
            levelService.checkTile(x, y, function(response) {
                applyNewTileValue(x, y, response.tile);
            });
        });
    }

    $scope.refreshStatus = function () {
        gameStatus.query(function (response) {
            $scope.statusText = response.status
        });
    }

    $scope.resetBoard = function () {
        $scope.board = {
            rows: []
        }

        var width = $scope.newGameCommand.width
        var height = $scope.newGameCommand.height

        for (var rowIndex = 0; rowIndex < height; rowIndex++) {
            $scope.board.rows[rowIndex] = []
            var row = $scope.board.rows[rowIndex]
            row.fields = []
            for (var colIndex = 0; colIndex < width; colIndex++) {
                row.fields[colIndex] = {
                    value: "     ",
                    type: FieldType.UNKNOWN
                }
            }
        }
    }

    $scope.newGameCommand = {
        width: 8,
        height: 8,
        mineCount: 8
    }

    $scope.resetBoard()
}

GameState = {
    RUNNING: 0,
    WINNER: 1,
    DEAD: 2
}

FieldType = {
    UNKNOWN: 0,
    FLAGGED: 1,
    REVEALED: 2,
    MINED: 3
}