package org.minesweeperkata

import org.minesweeperkata.Pos._

object Pos {
  type Move = Pos => Pos
  val moveUp: Move = (pos: Pos) => pos.dy(-1)
  val moveRight: Move = (pos: Pos) => pos.dx(1)
  val moveLeft: Move = (pos: Pos) => pos.dx(-1)
  val moveDown: Move = (pos: Pos) => pos.dy(1)

  lazy val moveRightUp = (p: Pos) => moveRight(moveUp(p))
  lazy val moveRightDown = (p: Pos) => moveRight(moveDown(p))
  lazy val moveLeftUp = (p: Pos) => moveLeft(moveUp(p))
  lazy val moveLeftDown = (p: Pos) => moveLeft(moveDown(p))

}

case class Pos(x: Int, y: Int) {

  def dx(d: Int) = copy(x = x + d, y)
  def dy(d: Int) = copy(x, y = y + d)
}

object GameDef {

  def solution(level: GameDef): IndexedSeq[IndexedSeq[Int]] = {

    for (row <- 0 to level.height - 1) yield
      for ( col <- 0 to level.width - 1) yield
        level.hint((Pos(col, row)))
  }
}

trait GameDef {

  type Terrain = Pos => Boolean
  type MineLayout = Pos => Boolean
  val moves: List[Move] = List(moveRight, moveUp, moveLeft, moveDown, moveRightDown, moveRightUp, moveLeftUp, moveLeftDown)
  val width: Int
  val height: Int
  val mine: MineLayout

  def terrain(position: Pos) : Boolean = {
    position.x >= 0 && position.x < width && position.y >= 0 && position.y < height
  }

  def hint(pos: Pos): Int = {
    moves.foldLeft(0)((count, move) => {
      val isMine = if (mine(move(pos))) 1 else 0
      count + isMine
    })
  }
}

