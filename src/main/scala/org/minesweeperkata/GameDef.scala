package org.minesweeperkata

case class Pos(x: Int, y: Int) {

  def dx(d: Int) = copy(x = x + d, y)
  def dy(d: Int) = copy(x, y = y + d)
}

trait GameDef {

  type Terrain = Pos => Boolean
  type MineLayout = Pos => Boolean

  val width: Int
  val height: Int

  def terrain(position: Pos) : Boolean = {
    position.x >= 0 && position.x < width && position.y >= 0 && position.y < height
  }

  val mine: MineLayout


}