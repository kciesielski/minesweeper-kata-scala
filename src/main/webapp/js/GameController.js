function GameController($scope, $location) {

    $scope.startNew = function () {
        console.log("TODO server call")
        $scope.resetBoard()
    }

    $scope.resetBoard = function () {
        $scope.board = {
            "rows": {}
        }

        var width = $scope.newGame.width
        var height = $scope.newGame.height

        for (var i=0; i<height; i++)
        {
            $scope.board.rows[i] = []
            var row = $scope.board.rows[i]
            row.cols = []
            for (var c=0; c<width; c++)
            {
                row.cols[c] = {
                    value: "?"
                }
            }
        }
    }

    $scope.newGame = {
        "width": 8,
        "height": 8,
        "mineCount": 8
    }

    $scope.resetBoard()
}