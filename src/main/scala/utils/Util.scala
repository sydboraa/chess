package utils

import model.pieces._

/**
 * Created by SB on 04/08/15.
 */
object Util {
  def getOrderedPieces(pieces : String) : List[ChessPiece] = {
    pieces.nonEmpty match {
      case true => {
        val listPiecesTypes: List[ChessPiece] = pieces.toUpperCase.toList.map {
          piece => piece match {
            case 'Q' => Queen
            case 'R' => Rook
            case 'B' => Bishop
            case 'N' => Knight
            case 'K' => King
            case _ => throw new Exception("WRONG PIECE TYPE!")
          }
        }
        listPiecesTypes.sortBy(_.priority)
      }
      case false => {
        throw new Exception("PLEASE ENTER THE PIECES!")
      }
    }
  }

  def boardSizeControl(m : String, n: String) : Boolean = {
    if(m.isEmpty || n.isEmpty) {
      toIntControl(m) && toIntControl(n)
    } else {
      toIntControl(m) && toIntControl(n)
    }
  }

  def toIntControl(maybeNumber: String) : Boolean = {
    try {
      maybeNumber.toInt.isInstanceOf[Int]
    } catch {
      case ex: NumberFormatException => {
        false
      }
    }
  }
}
