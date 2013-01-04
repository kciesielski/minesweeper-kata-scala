package org.minesweeperkata

import org.specs2.mutable._

class GameStateTest extends Specification {

  "Running Game state" should {

    "still be running with new positions when stepped on non-mine" in {
      // given
      val state1 = RunningGameState(Set(Pos(0,0)), TestLevel1)

      // when
      val newState1 = state1.stepOn(Pos(1, 0))

      // then
      newState1 === RunningGameState(Set(Pos(0,0), Pos(1,0)), TestLevel1)
    }

    "become Dead state when stepped on mine" in {
      // given
      val state = RunningGameState(Set(Pos(0,0)), TestLevel1)

      // when
      val newState = state.stepOn(Pos(1, 1))

      // then
      newState === DeadGameState
    }

    "become Winner state when stepped on last non-mine,non-zero tile" in {
      // given
      val state = RunningGameState(Set(Pos(0,0),Pos(1,0),Pos(2,0),Pos(0,1),Pos(2,1),Pos(0,2),Pos(1,2)), TestLevel1)

      // when
      val newState = state.stepOn(Pos(2, 2))

      // then
      newState === WinnerGameState
    }


  }
}
