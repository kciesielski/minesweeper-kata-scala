package org.minesweeperkata

sealed trait GameState

case class RunningGameState(revealedTiles: Set[Pos], currentLevel: GameDef) extends GameState
case object DeadGameState extends GameState
case object WinnerGameState extends GameState