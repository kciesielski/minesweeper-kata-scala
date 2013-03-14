package org.minesweeperkata

import org.specs2.mutable._

class LevelRandomizerSpec extends Specification {


  "Level Randomizer" should {

    "build a simple level" in {

      def randomNumbers: Stream[Int] = Stream(0, 0, 1, 1, 2, 2, 3, 3, 4, 4)

      val expectedMineLayout: Pos => Boolean = {
        pos => {
          (pos == Pos(0, 0) || pos == Pos(1, 1) || pos == Pos(2, 2) || pos == Pos(3, 3) || pos == Pos(4, 4))
        }
      }
      val randomizer = new LevelRandomizer()

      val width = 5
      val height = 6
      //when
      val nextLevel = randomizer.getNextLevel(width, height, 5, randomNumbers)
      // then
      val generatedMineLayout = nextLevel.mine

      for (x <- 0 to width - 1;
           y <- 0 to height - 1) {
        generatedMineLayout(Pos(x, y)) must beEqualTo(expectedMineLayout(Pos(x, y)))
      }
    }

    "not allow repeated mine positions" in {

      def randomNumbers: Stream[Int] = Stream(0, 0, 1, 1, 1, 1, 2, 2, 3, 3, 3)
      val expectedMineLayout: Pos => Boolean = {
        pos => {
          (pos == Pos(0, 0) || pos == Pos(1, 1) || pos == Pos(1, 2) || pos == Pos(2, 3) || pos == Pos(3, 3))
        }
      }
      val randomizer = new LevelRandomizer()

      val width = 5
      val height = 6
      // when
      val nextLevel = randomizer.getNextLevel(width, height, 5, randomNumbers)

      // then
      val generatedMineLayout = nextLevel.mine

      for (x <- 0 to width - 1;
           y <- 0 to height - 1) {
        generatedMineLayout(Pos(x, y)) must beEqualTo(expectedMineLayout(Pos(x, y)))
      }
    }

  }
}
