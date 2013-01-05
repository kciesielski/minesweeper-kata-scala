package org.minesweeperkata

import org.specs2.mutable._
import org.specs2.matcher.ShouldMatchers

import org.minesweeperkata.Pos._

class GameSpec extends Specification with ShouldMatchers {

  object TrivialEmptyLevel extends GameDef with StringParserTerrain {
    override val level =
      """.""".stripMargin

    val solution =
      """0""".stripMargin

    override val width = 1
    override val height = 1
  }

  object Level2 extends GameDef with StringParserTerrain {

    override val level =
      """.*.
        |...
        |...""".stripMargin
    override val width = 3
    override val height = 3
  }

  object Level3 extends GameDef with StringParserTerrain {

    override val level =
      """.*.*
        |..**
        |..*.""".stripMargin

    val solution =
      Vector(
        Vector("1", "*", "4", "*"),
        Vector("1", "3", "*", "*"),
        Vector("0", "2", "*", "3"))

    override val width = 4
    override val height = 3
  }

  "mine function for level 1" should {
    "correctly determine if position has a mine" in {

      val level = TestLevel1
      assert(level.mine(Pos(1, 1)))
      assert(!level.mine(Pos(0, 1)))
      assert(!level.mine(Pos(-1, 1)))
    }
  }

  "mine function for level 2" should {
    "correctly determine if position has a mine" in {

      val level = Level2
      assert(level.mine(Pos(1, 0)))
      assert(!level.mine(Pos(1, 1)))
      assert(!level.mine(Pos(-1, 1)))
    }
  }

  "level 2" should {
    "not contain position outside terrain" in {
      assert(!Level2.contains(Pos(8, 8)))
    }
  }

  "level 2" should {
    "contain position inside terrain" in {
      assert(Level2.contains(Pos(1, 1)))
    }
  }

  "hint for single field" should {
    "be always 0" in {
      val level = TrivialEmptyLevel
      level.hint(Pos(0, 0)) === "0"
    }
  }

  "hint for field" should {
    "be number of surrounding mines" in {
      val level1 = TestLevel1
      val level2 = Level2
      val level3 = Level3

      level1.hint(Pos(1, 0)) === "1"
      level1.hint(Pos(0, 0)) === "1"

      level2.hint(Pos(0, 2)) === "0"

      level3.hint(Pos(0, 2)) === "0"
      level3.hint(Pos(2, 0)) === "4"
      level3.hint(Pos(1, 0)) === "*"
      level3.hint(Pos(1, 1)) === "3"
      level3.hint(Pos(0, -1)) === "1"
    }
  }

  "moving functions" should {
    "be composable" in {
      val pos = Pos(1, 1)
      lazy val rightUp = (p: Pos) => moveRight(moveUp(p))
      rightUp(pos) === Pos(2, 0)
    }
  }

  "solution for trivial level 0" should {
    "0" in {
      GameDef.solution(TrivialEmptyLevel) === Vector(Vector("0"))
    }
  }

  "solution for level 3" should {
    "be as expected" in {
      GameDef.solution(Level3) === Level3.solution
    }
  }

}

