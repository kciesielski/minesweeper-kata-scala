function GameController($scope) {

    $scope.startNew = function () {
        console.log("TODO server call")
        $scope.resetBoard()
        $scope.currentState = GameState.RUNNING
    }

    $scope.statusText = function() {
        return "TODO status text"
    }

    $scope.resetBoard = function () {
        $scope.board = {
            rows: {}
        }

        var width = $scope.newGameCommand.width
        var height = $scope.newGameCommand.height

        for (var rowIndex = 0; rowIndex < height; rowIndex++) {
            $scope.board.rows[rowIndex] = []
            var row = $scope.board.rows[rowIndex]
            row.fields = []
            for (var colIndex = 0; colIndex < width; colIndex++) {
                row.fields[colIndex] = {
                    x: colIndex,
                    y: rowIndex,
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