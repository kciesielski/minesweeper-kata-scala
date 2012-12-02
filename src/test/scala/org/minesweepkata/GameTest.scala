package org.minesweepkata

import org.specs2.mutable._
import org.specs2.matcher.ShouldMatchers

import org.minesweeperkata.{Pos, GameDef, StringParserTerrain}

class GameTest extends Specification with ShouldMatchers {

  object Level1 extends GameDef with StringParserTerrain {
    override val level =
      """...
        |.*.
        |...""".stripMargin
    override val width = 3
    override val height = 3
  }

  object Level2 extends GameDef with StringParserTerrain {


    override val level =
      """.*.
        |...
        |...""".stripMargin
    override val width = 3
    override val height = 3
  }


  "terrain function for level 1" should {
    "correctly determine if position belongs to terrain" in {

      val level = Level1
      assert(level.terrain(Pos(1,1)))
      assert(level.terrain(Pos(2,2)))
      assert(level.terrain(Pos(2,0)))
      assert(level.terrain(Pos(0,0)))
      assert(level.terrain(Pos(1,2)))

      assert(!level.terrain(Pos(1,3)))
      assert(!level.terrain(Pos(-1,1)))
    }
  }

    "mine function for level 1" should {
      "correctly determine if position has a mine" in {

        val level = Level1
        assert(level.mine(Pos(1,1)))
        assert(!level.mine(Pos(0,1)))
        assert(!level.mine(Pos(-1,1)))
      }
    }

  "mine function for level 2" should {
    "correctly determine if position has a mine" in {

      val level = Level2
      assert(level.mine(Pos(1,0)))
      assert(!level.mine(Pos(1,1)))
      assert(!level.mine(Pos(-1,1)))
    }
  }

}


