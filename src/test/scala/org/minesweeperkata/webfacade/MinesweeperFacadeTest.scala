package org.minesweeperkata.webfacade

import org.minesweeperkata._

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

class MinesweeperFacadeTest extends Specification with Mockito {

    "return proper name of current state (class)" in {
      // given
      val gameStateDaoStub = mock[GameStateDao]
      val dummyRandomizer = mock[LevelRandomizer]
      val sutFacade = new MinesweeperFacade(dummyRandomizer, gameStateDaoStub)
      val dummyState = new RunningGameState(Set.empty[Pos], mock[GameDef])
      gameStateDaoStub.getState returns dummyState

      // when
      val status = sutFacade.status()

      // then
      status === "running"
    }

    "return proper name of current state (object)" in {
      // given
      val gameStateDaoStub = mock[GameStateDao]
      val dummyRandomizer = mock[LevelRandomizer]
      val sutFacade = new MinesweeperFacade(dummyRandomizer, gameStateDaoStub)
      val dummyState = DeadGameState
      gameStateDaoStub.getState returns dummyState

      // when
      val status = sutFacade.status()

      // then
      status === "dead"
    }

  }

}
