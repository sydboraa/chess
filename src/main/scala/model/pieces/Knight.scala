package model.pieces

import model.positioning.Move
import utils.Priority

/**
 * Created by SB on 02/08/15.
 */
object Knight extends ChessPiece {

  val characterOfPiece : Char = 'N'
  val priority : Int = Priority.knightPriority
  val moveInfinitely: Boolean = false
  val moveStrategies: Set[Move] = Set(Move(1, 2), Move(-1, 2), Move(1, -2), Move(-1, -2), Move(-2, 1), Move(-2, -1), Move(2, 1), Move(2, -1))

}
