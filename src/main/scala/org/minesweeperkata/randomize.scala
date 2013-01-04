package org.minesweeperkata

/** Post for starting a new game. Modifies the 'current server state' to represent a ne level with given width,
  * height and mine count.
  */
object randomize extends ((Int, Int, Int, LevelRandomizer, GameStateDao) =>  Unit) {

  def apply(newWidth: Int, newHeight: Int, mineCount: Int, levelRandomizer: LevelRandomizer, gameStateDao: GameStateDao) {
    val gameDef = levelRandomizer.getNextLevel(newWidth, newHeight, mineCount)
    gameStateDao.saveState(new RunningGameState(Set.empty[Pos], gameDef))
  }
}
