package model.pieces

import model.positioning.Move
import utils.Priority

/**
 * Created by SB on 02/08/15.
 */
object Rook extends ChessPiece {

  val characterOfPiece : Char = 'R'
  val priority : Int = Priority.rookPriority
  val moveInfinitely: Boolean = true
  val moveStrategies: Set[Move] = Set(Move(0, 1), Move(0, -1), Move(1, 0), Move(-1, 0))

}
