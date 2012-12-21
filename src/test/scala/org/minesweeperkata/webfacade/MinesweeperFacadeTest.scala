package org.minesweeperkata.webfacade

import org.minesweeperkata._

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

class MinesweeperFacadeTest extends Specification with Mockito {


  "Game facade" should {
    "save randomized level" in {
      // given
      val levelDummy = mock[GameDef]
      val expectedLevel = new RunningGameState(Set.empty[Pos], levelDummy)
      val spiedLevelDao = mock[GameStateDao]

      val mockRandomizer = mock[LevelRandomizer]
      mockRandomizer.getNextLevel(5, 6, 3) returns levelDummy
      // when
      new MinesweeperFacade(mockRandomizer, spiedLevelDao).randomizeLevel(5, 6, 3)
      // then
      there was one(mockRandomizer).getNextLevel(5, 6, 3)
      there was one(spiedLevelDao).saveState(expectedLevel)
    }
  }

}
