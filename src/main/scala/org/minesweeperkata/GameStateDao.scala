package org.minesweeperkata

trait GameStateDao {
  def getState: GameState
  def saveState(state: GameState)
}
