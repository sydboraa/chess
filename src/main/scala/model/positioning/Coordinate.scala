package model.positioning

/**
 * Created by SB on 02/08/15.
 * class implemented for hold current place
 * coordinate has move, pieces possible movements for calculation
 */
case class Coordinate(x: Int, y: Int) {
  //this method calculate the next possible movement place
  def +(move: Move): Coordinate = Coordinate(this.x + move.x, this.y + move.y)
}

