package org.minesweeperkata

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito

class stepOnTileTest extends Specification with Mockito {

  "The stepOnTile function" should {
      "mutate and save state for ordinary tile" in {

        // given
        val dummyState = new RunningGameState(Set(Pos(0,0)), TestLevel1)
        val gameStateDaoMock = mock[GameStateDao]
        gameStateDaoMock.getState returns dummyState
        val expectedState: GameState = new RunningGameState(Set(Pos(0,0),  Pos(1,0)), TestLevel1)

        // when
        stepOnTile(Pos(1,0), gameStateDaoMock)

        // then
        there was one(gameStateDaoMock).saveState(expectedState)
      }
    }

  }