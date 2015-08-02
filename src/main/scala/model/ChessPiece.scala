package model

import model.positioning.Move

/**
 * Created by SB on 02/08/15.
 */
abstract class ChessPiece {

  val characterOfPiece: Char
  val priority: Int
  val moveInfinitely: Boolean
  val moveStrategies: Set[Move]

}
