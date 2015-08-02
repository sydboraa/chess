package model

import model.positioning.Coordinate

/**
 * Created by SB on 02/08/15.
 */

case class BoardItem(piece: Option[ChessPiece] = None) //board consist boardItems and pieces movements and coordinates
case class Board(boardItems: Map[Coordinate, BoardItem])

object Board {

  def createEmptyBoard(weight: Int, height: Int): Board = {
    //for give coordinate, get item
    val boardsMap = (0 until weight).foldLeft(Map[Coordinate, BoardItem]()) {
      (boardMap, curX) =>
        (0 until height).foldLeft(boardMap) {
          (boardMap: Map[Coordinate, BoardItem], curY: Int) =>
            boardMap.+(Coordinate(curX, curY) -> BoardItem())
        }
    }
    Board(boardsMap)
  }

}
