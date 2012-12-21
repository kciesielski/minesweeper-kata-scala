package org.minesweeperkata.webfacade

import org.minesweeperkata._

class MinesweeperFacade(val levelRandomizer:LevelRandomizer = new LevelRandomizer(), val gameStateDao: GameStateDao = InMemoryGameStateDao) {

  // http post to put a step step on a tile
  def stepOn(pos: Pos) {

  }

  // get for tile value if previously stepped on it
  def tile(pos: Pos): String = {
    "*"
  }

  // get for game status
  def status(): String = {
    "TODO"
  }

  // post for starting a new game
  def randomizeLevel(newWidth: Int, newHeight: Int, mineCount: Int) {
    val gameDef = levelRandomizer.getNextLevel(newWidth, newHeight, mineCount)
    gameStateDao.saveState(new RunningGameState(Set.empty[Pos], gameDef))
  }

}
