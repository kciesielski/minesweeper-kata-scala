function GameController($scope, levelService) {

    $scope.startNew = function () {
        var cmd = $scope.newGameCommand;
        levelService.reset(cmd, function () {
            $scope.resetBoard();
            $scope.currentState = GameState.RUNNING;
        });
    }

    $scope.applyNewTileValue = function (x, y, tile) {
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

    $scope.step = function (position) {
        if ($scope.currentState != GameState.RUNNING || $scope.board.rows[position.y].fields[position.x].type != FieldType.UNKNOWN) {
            return;
        }
        levelService.step(position.x, position.y, function (response) {
            $scope.applyNewTileValue(position.x, position.y, response.tile);
            if (response.tile == '0') {
                $scope.stepOnNotVisitedNeighbors(position)
            }
        });
    }

    $scope.stepOnNotVisitedNeighbors = function (position) {
        var neighbors = [
            {
                x: position.x,
                y: position.y - 1
            },
            {
                x: position.x,
                y: position.y + 1
            },
            {
                x: position.x - 1,
                y: position.y - 1
            },
            {
                x: position.x + 1,
                y: position.y - 1
            },
            {
                x: position.x - 1,
                y: position.y
            },
            {
                x: position.x + 1,
                y: position.y
            },
            {
                x: position.x + 1,
                y: position.y + 1
            },
            {
                x: position.x - 1,
                y: position.y + 1
            }
        ]
        neighbors.forEach(function (element, index, array) {
            if ($scope.board.rows[element.y].fields[element.x].type == FieldType.UNKNOWN) {
                var position = {
                    x: element.x,
                    y: element.y
                }
                $scope.step(position)
            }
        })
    }

    $scope.toggleFlag = function (x, y) {

        if ($scope.currentState != GameState.RUNNING)
            throw "unexpected flag toggle in non-running state";
        var field = $scope.board.rows[y].fields[x];
        if (field.type != FieldType.FLAGGED && field.type != FieldType.UNKNOWN) {
            return
        }
        if (field.type == FieldType.FLAGGED) {
            field.value = "     ";
            field.type = FieldType.UNKNOWN;
        }
        else if (field.type == FieldType.UNKNOWN) {
            field.type = FieldType.FLAGGED;
            field.value = "F";
        }
    };

    $scope.resetBoard = function () {
        $scope.board = {
            rows: []
        }

        var width = $scope.newGameCommand.width
        var height = $scope.newGameCommand.height
        var fy = 0;
        for (var rowIndex = 0; rowIndex < height; rowIndex++) {
            $scope.board.rows[rowIndex] = []
            var row = $scope.board.rows[rowIndex]
            row.fields = []
            var fx = 0;
            for (var colIndex = 0; colIndex < width; colIndex++) {
                row.fields[colIndex] = {
                    value: "     ",
                    type: FieldType.UNKNOWN,
                    x: fx,
                    y: fy
                }
                fx++;
            }
            fy++;
        }
        $scope.statusText = 'Running';
    }

    $scope.newGameCommand = {
        width: 8,
        height: 8,
        mineCount: 8
    }

    $scope.startNew()
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