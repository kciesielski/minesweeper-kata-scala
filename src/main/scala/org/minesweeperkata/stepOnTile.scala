package org.minesweeperkata

object stepOnTile extends ((Pos, GameStateDao) => Unit) {

  def noMoreTiles(revealedTiles: Set[Pos], currentLevel: GameDef): Boolean = {
    val allPositions = for (x <- 0 to currentLevel.width - 1;
                            y <- 0 to currentLevel.height - 1) yield Pos(x, y)

    val notVisitedPositions = allPositions.filterNot(revealedTiles.contains(_))
    !notVisitedPositions.exists((pos: Pos) => (currentLevel.hint(pos) != "0" && currentLevel.hint(pos) != "*"))
  }

  def doStepOn(pos: Pos, state: GameState): GameState = {
    state match {
      case RunningGameState(revealedTiles, currentLevel) => {
        if (!currentLevel.contains(pos)) throw new IllegalArgumentException("Position exceeds level terrain")
        val alive = (currentLevel.hint(pos) != "*")
        if (!alive)
          DeadGameState
        else
        if (noMoreTiles(revealedTiles + pos, currentLevel))
          WinnerGameState
        else
          RunningGameState(revealedTiles + pos, currentLevel)
      }
      case _ => throw new IllegalStateException("Cannot step on field in current state: " + state)
    }
  }

  def apply(pos: Pos, stateDao: GameStateDao) {

    val state = stateDao.getState
    val newState = doStepOn(pos, state)
    stateDao.saveState(newState)
  }
}
