package org.minesweeperkata

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import org.specs2.matcher.ShouldMatchers

class showTileSpec extends Specification with ShouldMatchers with Mockito {

  "The showTile function" should {

    "reveal touched field value" in {

      // given
      val dummyState = new RunningGameState(Set(Pos(0, 0), Pos(1, 1)), TestLevel1)
      val gameStateDaoStub = mock[GameStateDao]
      gameStateDaoStub.getState returns dummyState

      // then
      showTile(Pos(0, 0), gameStateDaoStub) === TileValue("1")
      showTile(Pos(1, 1), gameStateDaoStub) === TileValue("*")
    }

    "throw exception if game in 'winner' state" in {
      // given
      val gameStateDaoStub = mock[GameStateDao]
      gameStateDaoStub.getState returns WinnerGameState

      // then
      showTile(Pos(0, 0), gameStateDaoStub) must throwA[IllegalStateException]
    }

    "throw exception if game in 'dead' state" in {
      // given
      val gameStateDaoStub = mock[GameStateDao]
      gameStateDaoStub.getState returns DeadGameState

      // then
      showTile(Pos(0, 0), gameStateDaoStub) must throwA[IllegalStateException]
    }

    "throw exception if position exceeds game terrain" in {
      // given
      val gameStateDaoStub = mock[GameStateDao]
      gameStateDaoStub.getState returns RunningGameState(Set.empty, TestLevel1)

      // then
      showTile(Pos(1, 7), gameStateDaoStub) must throwA[IllegalArgumentException]
    }

  }

}