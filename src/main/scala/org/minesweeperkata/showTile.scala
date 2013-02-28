package org.minesweeperkata

/**
 * Returns information about actual value of tile on given position. Returned value is either a number 0-9 or
 * a symbol '*' representing a mine.
 * @throws IllegalArgumentException if given position is outside level terrain.
 * @throws IllegalStateException if current game state is not 'running' or 'dead'.
 */
object showTile extends ((Pos, GameStateDao) => TileValue)  {

  def apply(pos: Pos, stateDao: GameStateDao): TileValue = {
    val state = stateDao.getState

    state match {
      case RunningGameState(revealedTiles, level) => {
        if (!level.contains(pos)) throw new IllegalArgumentException("Position " + pos + " exceeds game terrain")
        if (!revealedTiles(pos)) throw new IllegalStateException("Position " + pos + " has not been visited before")
        TileValue(level.hint(pos))
      }
      case _ => throw new IllegalStateException("Cannot reveal any tile in current state: " + state)
    }
  }
}
