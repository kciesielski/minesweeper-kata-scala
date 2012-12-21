package org.minesweeperkata

import org.specs2.mutable._

class LevelRandomizerTest extends Specification {


  "Level Randomizer" should {
    "build a simple level" in {

      val randomNumbers = Stream(1, 1, 2, 2, 3, 3, 4, 4, 5, 5)
      val expectedMineLayout: Pos => Boolean = {
        pos => {
          (pos == Pos(1, 1) || pos == Pos(2, 2) || pos == Pos(3, 3) || pos == Pos(4, 4) || pos == Pos(5, 5))
        }
      }
        val randomizer = new LevelRandomizer(randomNumbers)

        val width = 5
        val height = 6
        val nextLevel = randomizer.getNextLevel(width, height, 5)
        val generatedMineLayout = nextLevel.mine

        for (x <- 0 to width;
             y <- 0 to height) {
          generatedMineLayout(Pos(x, y)) must beEqualTo(expectedMineLayout(Pos(x, y)))
        }
    }
  }
}
