package org.minesweeperkata.webfacade

import org.minesweeperkata._

class MinesweeperFacade(val levelRandomizer:LevelRandomizer = new LevelRandomizer(), val gameStateDao: GameStateDao = InMemoryGameStateDao) {

  /**
   * Causes the current game state to 'step' on the tile for given position. Stepping on a mine means DEATH ;)
   * @throws IllegalArgumentException if given position is outside level terrain.
   * @throws IllegalStateException if current game state is not 'running' (for example, you are already dead).
   */
  def stepOn(pos: Pos) {

  }

  /**
   * Returns information about actual value of tile on given position. Returned value is either a number 0-9 or
   * a symbol '*' representing a mine.
   * @throws IllegalArgumentException if given position is outside level terrain.
   * @throws IllegalStateException if current game state is not 'running' or 'dead'.
   */
  def tile(pos: Pos): String = {
    "*"
  }

  /**
   * Returns string representation of game status.
   */
  def status(): String = {
    "s"
  }

  /** Post for starting a new game. Modifies the 'current server state' to represent a ne level with given width,
    * height and mine count.
    */
  def randomizeLevel(newWidth: Int, newHeight: Int, mineCount: Int) {
    val gameDef = levelRandomizer.getNextLevel(newWidth, newHeight, mineCount)
    gameStateDao.saveState(new RunningGameState(Set.empty[Pos], gameDef))
  }

}
