package model

import model.pieces.ChessPiece
import model.positioning.Coordinate

/**
 * Created by SB on 02/08/15.
 */

case class BoardItem(piece: Option[ChessPiece] = None) {
  //board consist boardItems and pieces movements and coordinates
  override def toString : String = piece.map(p => p.toString).getOrElse("-")
}

case class Board(boardItems: Map[Coordinate, BoardItem]) {

  override def toString: String = {
    val weight : Int = boardItems.keys.maxBy(coordinate => coordinate.x).x
    val height : Int = boardItems.keys.maxBy(coordinate => coordinate.y).y

    (0 to height).foldLeft("") {
      (str, y) =>
        (0 to weight).foldLeft(str) {
          (str, x) =>
            val boardItem:String = boardItems(Coordinate(x, y)).toString
            s"$str $boardItem"
        } + "\n"
    }
  }
}

object Board {

  def createEmptyBoard(weight: Int, height: Int): Board = {
    //for give coordinate, get item
    val boardsMap : Map[Coordinate, BoardItem] = (0 until weight).foldLeft(Map[Coordinate, BoardItem]()) {
      (boardMap, curX) =>
        (0 until height).foldLeft(boardMap) {
          (boardMap: Map[Coordinate, BoardItem], curY: Int) =>
            boardMap.+(Coordinate(curX, curY) -> BoardItem())
        }
    }
    Board(boardsMap)
  }
}

case class MarkedBoard(baseBoard: Board, markedCoordinates: Set[Coordinate] = Set.empty) {

  def isMarked(coordinate: Coordinate): Boolean = markedCoordinates.contains(coordinate)

  //get available positions for placing piece
  def notMarkedEmptyCoordinates: Set[Coordinate] = {
    baseBoard.boardItems
      .filter {
      case (coordinate: Coordinate, boardItem: BoardItem) =>
        !markedCoordinates.contains(coordinate) && boardItem.piece.isEmpty
    }.keySet
  }

  def putPiece(c: Coordinate, p: ChessPiece): MarkedBoard = {
    this.copy(baseBoard = Board(boardItems = baseBoard.boardItems + (c -> BoardItem(piece = Some(p)))))
  }

  def mark(c: Coordinate): MarkedBoard = {
    this.copy(markedCoordinates = markedCoordinates + c)
  }
}
