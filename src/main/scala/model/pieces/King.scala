package model.pieces

import model.positioning.Move
import utils.Priority

/**
 * Created by SB on 02/08/15.
 */
object King extends ChessPiece {

  val characterOfPiece : Char = 'K'
  val priority : Int = Priority.kingPriority
  val moveInfinitely: Boolean = false
  val moveStrategies: Set[Move] = Set(Move(0, 1), Move(0, -1), Move(1, -1), Move(1, 0), Move(1, 1), Move(-1, 0), Move(-1, 1), Move(-1, -1))
}
