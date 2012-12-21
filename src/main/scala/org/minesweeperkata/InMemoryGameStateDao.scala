package org.minesweeperkata

object InMemoryGameStateDao extends GameStateDao  {

  var state: Option[GameState] = None

  def getState: GameState = { state.getOrElse(throw new IllegalArgumentException("No state saved!")) }

  def saveState(state: GameState) {
    this.state = Some(state)
  }
}
