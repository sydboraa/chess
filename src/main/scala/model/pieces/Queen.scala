package model.pieces

import model.positioning.Move
import utils.Priority

/**
 * Created by SB on 02/08/15.
 */
object Queen extends ChessPiece {

  val characterOfPiece : Char = 'Q'
  val priority : Int = Priority.queenPriority
  val moveInfinitely: Boolean = true
  val moveStrategies: Set[Move] = Set(Move(0, 1), Move(0, -1), Move(1, -1), Move(1, 0), Move(1, 1), Move(-1, 0), Move(-1, 1), Move(-1, -1))

}
