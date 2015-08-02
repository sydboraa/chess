package model.positioning

/**
 * Created by SB on 02/08/15.
 */
case class Coordinate(x: Int, y: Int) {
  //coordinate has move, pieces possible movements for calculation
  def +(move: Move): Coordinate = Coordinate(this.x + move.x, this.y + move.y)
}

