package org.minesweeperkata

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito

class showTileTest extends Specification with Mockito {

  object Level1 extends GameDef with StringParserTerrain {
    override val level =
      """...
        |.*.
        |...""".stripMargin

    override val width = 3
    override val height = 3
  }

  "The showTile function" should {
    "reveal touched field value" in {

      // given
      val dummyState = new RunningGameState(Set.empty[Pos] + Pos(0,0), Level1)
      val gameStateDaoStub = mock[GameStateDao]
      gameStateDaoStub.getState returns dummyState

      // then
      showTile(Pos(0, 0), gameStateDaoStub) === "1"
      showTile(Pos(1, 1), gameStateDaoStub) === "*"

    }
  }

}