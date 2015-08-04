package unit

import model.pieces.{King, Rook}
import model.{MarkedBoard, BoardItem, Board}
import model.positioning.Coordinate

/**
 * Created by SB on 03/08/15.
 */
class BoardTest extends ChessSpec {

  "An empty board size" should "be 0" in {
    val board = Board.createEmptyBoard(0, 0)
    assert(board.boardItems.size == 0)
  }

  "An empty board size" should "be 4 and print - - \n - - " in {
    val board = Board.createEmptyBoard(2, 2)
    assert(board.boardItems.keySet.size == 4)
    assert(board.toString == " - -\n - -\n")
  }

  "3*3 board with King(0, 0), King(2, 0), Rook(1, 2)" should "print - - R \n - - -" in {
    val board : Board = Board(Map(Coordinate(0, 0) -> BoardItem(Some(King)), Coordinate(0, 1) -> BoardItem(None), Coordinate(0, 2) -> BoardItem(None),
      Coordinate(1, 0) -> BoardItem(None), Coordinate(1, 1) -> BoardItem(None), Coordinate(1, 2) -> BoardItem(Some(Rook)),
      Coordinate(2, 0) -> BoardItem(Some(King)), Coordinate(2, 1) -> BoardItem(None), Coordinate(2, 2) -> BoardItem(None)))
    val markedBoard : MarkedBoard = new MarkedBoard(baseBoard = board)
    assert(markedBoard.baseBoard.toString == " K - K\n - - -\n - R -\n")
  }
}