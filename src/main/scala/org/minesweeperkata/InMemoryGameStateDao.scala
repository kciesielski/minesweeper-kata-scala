package org.minesweeperkata

object InMemoryGameStateDao extends GameStateDao  {

  def getState: GameState = null
  def saveState(state: GameState) {}
}
