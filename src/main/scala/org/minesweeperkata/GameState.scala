package org.minesweeperkata

sealed trait GameState {

  def stepOn(pos: Pos): GameState
}

case class RunningGameState(revealedTiles: Set[Pos], currentLevel: GameDef) extends GameState  {

  def stepOn(pos: Pos): GameState = {
    val alive = (currentLevel.hint(pos) == '*')

    if (!alive)
    {
      DeadGameState
    }
    null
  }
}
  case object DeadGameState extends GameState  {

    def stepOn(pos: Pos): GameState = {
      null
    }
  }

  case object WinnerGameState extends GameState  {

    def stepOn(pos: Pos): GameState = {
      null
    }


  }