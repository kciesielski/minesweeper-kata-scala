package org.minesweeperkata

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito

class stepOTileTest extends Specification with Mockito {

  "The stepOnTile function" should {
    "mutate and save state for ordinary tile" in {

      // given
      val fixtureState = new RunningGameState(Set(Pos(0, 0)), TestLevel1)
      val gameStateDaoMock = mock[GameStateDao]
      gameStateDaoMock.getState returns fixtureState
      val expectedState: GameState = new RunningGameState(Set(Pos(0, 0), Pos(1, 0)), TestLevel1)

      // when
      stepOnTile(Pos(1, 0), gameStateDaoMock)

      // then
      there was one(gameStateDaoMock).saveState(expectedState)
    }
  }

  "save dead state when stepped on a mine" in {
    // given
    val fixtureState = new RunningGameState(Set(Pos(0, 0)), TestLevel1)
    val gameStateDaoMock = mock[GameStateDao]
    gameStateDaoMock.getState returns fixtureState

    // when
    stepOnTile(Pos(1, 1), gameStateDaoMock)

    // then
    there was one(gameStateDaoMock).saveState(DeadGameState)
  }

  "save winner state when stepped on last non-mine,non-zero tile" in {
    // given
    val fixtureState = RunningGameState(Set(Pos(0, 0), Pos(1, 0), Pos(2, 0), Pos(0, 1), Pos(2, 1), Pos(0, 2), Pos(1, 2)), TestLevel1)
    val gameStateDaoMock = mock[GameStateDao]
    gameStateDaoMock.getState returns fixtureState

    // when
    stepOnTile(Pos(2, 2), gameStateDaoMock)

    // then
    there was one(gameStateDaoMock).saveState(WinnerGameState)
  }

  "throw an exception when stepped outside game terrain" in {
    // given
    val fixtureState = RunningGameState(Set(Pos(0, 0)), TestLevel1)
    val gameStateDaoMock = mock[GameStateDao]
    gameStateDaoMock.getState returns fixtureState

    // then
    stepOnTile(Pos(8, 8), gameStateDaoMock) should throwA[IllegalArgumentException]
  }

  "throw an exception when tried to step in winner state" in {
    // given
    val gameStateDaoMock = mock[GameStateDao]
    gameStateDaoMock.getState returns WinnerGameState

    // then
    stepOnTile(Pos(1, 0), gameStateDaoMock) should throwA[IllegalStateException]
  }

  "throw an exception when tried to step in dead state" in {
    // given
    val gameStateDaoMock = mock[GameStateDao]
    gameStateDaoMock.getState returns DeadGameState

    // then
    stepOnTile(Pos(1, 0), gameStateDaoMock) should throwA[IllegalStateException]
  }

}