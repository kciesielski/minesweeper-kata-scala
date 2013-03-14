package org.minesweeperkata

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito

class randomizeSpec extends Specification with Mockito {

   "The randomize function" should {
     "get random values and save level to dao" in {

       // given
       val levelDummy = mock[GameDef]
       val expectedLevel = new RunningGameState(Set.empty[Pos], levelDummy)
       val spiedLevelDao = mock[GameStateDao]
       val newWidth: Int = 5
       val newHeight: Int = 6
       val mineCount: Int = 3

       val mockRandomizer = mock[LevelRandomizer]
       mockRandomizer.getNextLevel(newWidth, newHeight, mineCount) returns levelDummy

       // when
       randomize(newWidth, newHeight, mineCount, mockRandomizer, spiedLevelDao)

       // then
       there was one(spiedLevelDao).saveState(expectedLevel)

     }
   }

 }