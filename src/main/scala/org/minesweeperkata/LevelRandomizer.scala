package org.minesweeperkata

import util.Random

class LevelRandomizer(val randomInts: Stream[Int] = Stream continually(Random.nextInt())) {


  def getNextLevel(newWidth: Int, newHeight: Int, mineCount: Int): GameDef = {
    new GameDef {
      val height: Int = height
      val width: Int = width
      val minePositions = randomInts.take(mineCount)
      val mine: this.type#MineLayout = {


        pos => {
          (pos == Pos(1, 1) || pos == Pos(2, 2) || pos == Pos(3, 3) || pos == Pos(4, 4) || pos == Pos(5, 5))
        }
      }
    }
  }
}
