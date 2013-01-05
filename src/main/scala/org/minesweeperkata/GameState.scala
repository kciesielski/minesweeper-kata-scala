package org.minesweeperkata

sealed trait GameState {
  def describe: String
}

case class RunningGameState(revealedTiles: Set[Pos], currentLevel: GameDef) extends GameState {

  override def describe: String = "running"
}

case object DeadGameState extends GameState {

  override def describe: String = "dead"
}

case object WinnerGameState extends GameState {

  override def describe: String = "won"
}