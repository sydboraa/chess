package service

import model.pieces.ChessPiece
import model.positioning.{Coordinate, Move}
import model.{BoardItem, MarkedBoard, Board}

/**
 * Created by SB on 02/08/15.
 */
object CalculationService {

  def calculate(board: Board,pieces: List[ChessPiece]) : Set[Board] = {

    val markedBoard = new MarkedBoard(baseBoard = board)

    val resultsAsOpt: Set[MarkedBoard] = pieces.foldLeft(Set(markedBoard)) {
      (boards, aPiece) => {
        boards.flatMap {
          markedBoard => {
            markedBoard.notMarkedEmptyCoordinates.flatMap {
              coordinate => {
                val boardWPiece = markedBoard.putPiece(coordinate, aPiece)
                // Mark appropriate fields marked, checking conflicts on the way, if conflict found return None
                aPiece.moveStrategies.foldLeft(Some(boardWPiece): Option[MarkedBoard]) {
                  case (Some(board:MarkedBoard), move: Move) =>
                    validatePiecePlacement(coordinate, board, move, aPiece.moveInfinitely)
                  case (None, _) => None // Another move failed, just ignore
                }
              }
            }
          }
        }
      }
    }
    //convert markedBoard to normal board
    resultsAsOpt.map {
      result => {
        println(result.baseBoard)
        result.baseBoard
      }
    }
  }

  def validatePiecePlacement(startCoordinate: Coordinate, board: MarkedBoard, move: Move, indefinite: Boolean): Option[MarkedBoard] = {
    val currentCoordinate = startCoordinate + move

    val partialResult = board.baseBoard.boardItems.get(currentCoordinate) match {
      case Some(bi: BoardItem) =>
        if (bi.piece.isEmpty) {
          val markedBoard = board.mark(currentCoordinate)
          if (indefinite) {
            validatePiecePlacement(currentCoordinate, markedBoard, move, indefinite)
          } else {
            Some(markedBoard)
          }
        } else {
          None // Ups, conflict!
        }
      case None => Some(board) // End of board at one direction
    }
    partialResult
  }

}
