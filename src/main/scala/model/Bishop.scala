package model

import model.positioning.Move
import utils.Priority

/**
 * Created by SB on 02/08/15.
 */
object Bishop extends ChessPiece {

  val characterOfPiece : Char = 'B'
  val priority : Int = Priority.bishopPriority
  val moveInfinitely: Boolean = true
  val moveStrategies: Set[Move] = Set(Move(-1, 1), Move(-1, -1), Move(1, -1), Move(1,1))
}
