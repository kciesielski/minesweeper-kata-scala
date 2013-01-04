package org.minesweeperkata

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito

class showTileTest extends Specification with Mockito {

  "The showTile function" should {
    "reveal touched field value" in {

      // given
      val dummyState = new RunningGameState(Set.empty[Pos] + Pos(0,0), TestLevel1)
      val gameStateDaoStub = mock[GameStateDao]
      gameStateDaoStub.getState returns dummyState

      // then
      showTile(Pos(0, 0), gameStateDaoStub) === "1"
      showTile(Pos(1, 1), gameStateDaoStub) === "*"

    }
  }

}