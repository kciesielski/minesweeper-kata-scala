package org.minesweeperkata


trait StringParserTerrain extends GameDef {

  val level: String

  /**
   * Build a function which determines where particular point is a mine or not.
   * @param levelVector vector of level rows (vector of vectors)
   */
  def mineFunction(levelVector: Vector[Vector[Char]]): Pos => Boolean = {

    new Function[Pos, Boolean] {
      override def apply(pos: Pos) = {
        if (pos.x < 0 || pos.y < 0) false
        else {
          if (levelVector.length <= pos.y) false
          else {
            val innerVector = levelVector(pos.y)
            if (innerVector.length <= pos.x) false
            else {
              innerVector(pos.x) == '*'
            }
          }
        }
      }
    }
  }

  def findChar(c: Char, levelVector: Vector[Vector[Char]]): Pos = {
    val row = levelVector.indexWhere(inner => inner.contains(c))
    val col = levelVector(row).indexOf(c)
    Pos(row, col)
  }

  private lazy val vector: Vector[Vector[Char]] =
    Vector(level.split("\n").map(str => Vector(str: _*)): _*)

  lazy val mine: MineLayout = mineFunction(vector)
}
