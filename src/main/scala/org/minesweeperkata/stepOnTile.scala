package org.minesweeperkata

object stepOnTile extends ((Pos, GameStateDao) => Unit) {

  def apply(pos: Pos, stateDao: GameStateDao) {
    val state = stateDao.getState
    val newState: GameState = state.stepOn(pos)
    stateDao.saveState(newState)
  }
}
