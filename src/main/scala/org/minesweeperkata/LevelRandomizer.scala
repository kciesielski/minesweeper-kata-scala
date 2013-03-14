package org.minesweeperkata

import util.Random
import annotation.tailrec

class LevelRandomizer() {

  def randomFunction: Stream[Int] =  Stream.cons(util.Random.nextInt(Int.MaxValue), randomFunction)

  def getNextLevel(newWidth: Int, newHeight: Int, mineCount: Int, fn: Stream[Int] = randomFunction): GameDef = {
    new GameDef {
      val height: Int = newHeight
      val width: Int = newWidth
      val emptyLayout = (pos: Pos) => false
      override val mine = randomPosition(fn, mineCount, newWidth, newHeight, emptyLayout)
    }
  }

  @tailrec
  private def randomPosition(stream: Stream[Int], mineCount: Int, width: Int, height: Int, layout: GameDef#MineLayout): GameDef#MineLayout = {

    val newData = nextNotRepeated(stream, width, height, layout)
    val rest = newData._2
    val newPos = newData._1
    val newLayout = (pos: Pos) => layout(pos) || (pos == newPos)
    if (mineCount == 1)
      newLayout
    else
      randomPosition(rest, mineCount - 1, width, height, newLayout)
  }

  @tailrec
  private def nextNotRepeated(stream: Stream[Int], width: Int, height: Int, currentLayout: GameDef#MineLayout): (Pos, Stream[Int]) = {
    val first = stream.head % width
    val second = stream.tail.head % height
    val rest = stream.tail
    val newPos = Pos(first, second)
    if (currentLayout(newPos)) {
      nextNotRepeated(rest, width, height, currentLayout)
    }
    else
      (newPos, rest.tail)
  }
}
