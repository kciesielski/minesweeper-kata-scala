package org.minesweeperkata

sealed trait GameState {

  def stepOn(pos: Pos): GameState
  def describe:  String
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

  def describe: String = "running"
}
  case object DeadGameState extends GameState  {

    def stepOn(pos: Pos): GameState = {
      null
    }

    def describe: String = "dead"
  }

  case object WinnerGameState extends GameState  {

    def stepOn(pos: Pos): GameState = {
      null
    }

    def describe: String = "won"
  }