package org.minesweeperkata

/**
 * A supplementary object representing simple game level for testing purposes.
 */
object TestLevel1 extends GameDef with StringParserTerrain {
  override val level =
    """...
      |.*.
      |...""".stripMargin

  override val width = 3
  override val height = 3
}