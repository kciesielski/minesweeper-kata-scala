package org.minesweeperkata

import collection.immutable

sealed trait GameState {

  def stepOn(pos: Pos): GameState

  def describe: String
}

case class RunningGameState(revealedTiles: Set[Pos], currentLevel: GameDef) extends GameState {

  def noMoreTiles(revealedTiles: Set[Pos]): Boolean = {

    val allPositions = for (x <- 0 to currentLevel.width - 1;
                            y <- 0 to currentLevel.height - 1) yield Pos(x, y)

    val notVisitedPositions = allPositions.filterNot(revealedTiles.contains(_))

    !notVisitedPositions.exists((pos: Pos) => (currentLevel.hint(pos) != "0" && currentLevel.hint(pos) != "*"))
  }

  def stepOn(pos: Pos): GameState = {
    val alive = (currentLevel.hint(pos) != "*")

    if (!alive)
      DeadGameState
    else
    if (noMoreTiles(revealedTiles + pos))
      WinnerGameState
    else
      RunningGameState(revealedTiles + pos, currentLevel)
  }

  def describe: String = "running"
}

case object DeadGameState extends GameState {

  def stepOn(pos: Pos): GameState = {
    null
  }

  def describe: String = "dead"
}

case object WinnerGameState extends GameState {

  def stepOn(pos: Pos): GameState = {
    null
  }

  def describe: String = "won"
}