package org.minesweeperkata

import org.specs2.mutable.Specification

class InMemoryGameStateDaoSpec extends Specification{

  "in-memory game state DAO" should {
    "load previously saved state" in {

      // given
      val dummyState = DeadGameState
      // when
      InMemoryGameStateDao.saveState(dummyState)
      val loadedState = InMemoryGameStateDao.getState
      // then
      loadedState should beTheSameAs(dummyState)
    }
  }
}
