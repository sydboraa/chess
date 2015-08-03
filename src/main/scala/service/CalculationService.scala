package service

import model.pieces.ChessPiece
import model.positioning.{Coordinate, Move}
import model.{BoardItem, MarkedBoard, Board}
import scala.annotation.tailrec

/**
 * Created by SB on 02/08/15.
 */
object CalculationService {

//  def calculate(board: Board,pieces: List[ChessPiece]) : Set[Board] = {
//
//    val markedBoard = new MarkedBoard(baseBoard = board)
//
//    val resultsAsOpt: Set[MarkedBoard] = pieces.foldLeft(Set(markedBoard)) {
//      (boards, aPiece) => {
//        boards.flatMap {
//          markedBoard => {
//            markedBoard.notMarkedEmptyCoordinates.flatMap {
//              coordinate => {
//                val boardWPiece = markedBoard.putPiece(coordinate, aPiece)
//                // Mark appropriate fields marked, checking conflicts on the way, if conflict found return None
//                aPiece.moveStrategies.foldLeft(Some(boardWPiece): Option[MarkedBoard]) {
//                  case (Some(board:MarkedBoard), move: Move) =>
//                    validatePiecePlacement(coordinate, board, move, aPiece.moveInfinitely)
//                  case (None, _) => None // Another move failed, just ignore
//                }
//              }
//            }
//          }
//        }
//      }
//    }
//    //convert markedBoard to normal board
//    resultsAsOpt.map {
//      result => {
//        println(result.baseBoard)
//        result.baseBoard
//      }
//    }
//  }

  def calculate(board: Board, pieces: List[ChessPiece]): Set[Board] = {

    val markedBoard = new MarkedBoard(baseBoard = board)

    val results: Set[MarkedBoard] = pieces.foldLeft(Set(markedBoard)) {
      (boards, aPiece) => placeBoards(boards, aPiece)
    }
    //convert markedBoard to normal board
    results.map {
      result => {
        result.baseBoard
      }
    }
  }

  private def placeBoards(markedBoards: Set[MarkedBoard], piece: ChessPiece): Set[MarkedBoard] = {
    markedBoards.flatMap {
      markedBoard =>
        placeBoard(markedBoard, piece)
    }
  }

  private def placeBoard(markedBoard: MarkedBoard, piece: ChessPiece): Set[MarkedBoard] = {
    markedBoard.notMarkedEmptyCoordinates
      .flatMap {
      coordinate =>
        maybePlace(markedBoard, coordinate, piece)
    }
  }

  private def maybePlace(markedBoard: MarkedBoard, coordinate: Coordinate, piece: ChessPiece): Option[MarkedBoard] = {
    // Put piece
    val boardWPiece = markedBoard.putPiece(coordinate, piece)

    // Mark appropriate fields marked, checking conflicts on the way, if conflict found return None
    piece.moveStrategies.foldLeft(Some(boardWPiece): Option[MarkedBoard]) {
      case (Some(board:MarkedBoard), move: Move) =>
        validatePiecePlacement(coordinate, board, move, piece.moveInfinitely)
      case (None, _) => None // Another move failed, just ignore
    }
  }

  @tailrec
  private def validatePiecePlacement(startCoordinate: Coordinate, board: MarkedBoard, move: Move, indefinite: Boolean): Option[MarkedBoard] = {
    // Validates piece placement and returns marked board if possible
    val currentCoordinate = startCoordinate + move

    val (partialResult, coordinateInsideBoard) = board.baseBoard.boardItems.get(currentCoordinate) match {
      case Some(bi: BoardItem) =>
        if (bi.piece.isEmpty) {
          val markedBoard = board.mark(currentCoordinate)
          (Some(markedBoard), true)
        } else {
          (None, true) // Ups, conflict!
        }
      case None => (Some(board), false) // End of board at one direction
    }

    if (partialResult.isDefined && coordinateInsideBoard && indefinite) {
      validatePiecePlacement(currentCoordinate, partialResult.get, move, indefinite)
    } else {
      partialResult
    }
  }
  /*def validatePiecePlacement(startCoordinate: Coordinate, board: MarkedBoard, move: Move, indefinite: Boolean): Option[MarkedBoard] = {
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
  }*/

}
