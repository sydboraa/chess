package unit

import model.pieces.{Rook, King, Knight, ChessPiece}
import model.{MarkedBoard, BoardItem, Board}
import model.positioning.Coordinate

/**
 * Created by SB on 04/08/15.
 */
class MarkedBoardTest extends ChessSpec {

  /* Unit test for put piece method */
  "When piece Knight put on 3*2 empty board, coordinates of piece" should "be (0, 0)" in {
    val board : Board = Board(Map(Coordinate(0, 0) -> BoardItem(None), Coordinate(0, 1) -> BoardItem(None), Coordinate(0, 2) -> BoardItem(None),
      Coordinate(1, 0) -> BoardItem(None), Coordinate(1, 1) -> BoardItem(None), Coordinate(1, 2) -> BoardItem(None)))
    val markedBoard : MarkedBoard = new MarkedBoard(baseBoard = board)
    val coordinate : Coordinate = Coordinate(0, 0)
    val piece : ChessPiece = Knight
    val boardWidthPiece : MarkedBoard = markedBoard.putPiece(coordinate, piece)

    val expected : Board = Board(Map(Coordinate(0, 0) -> BoardItem(Some(Knight)), Coordinate(0, 1) -> BoardItem(None), Coordinate(0, 2) -> BoardItem(None),
      Coordinate(1, 0) -> BoardItem(None), Coordinate(1, 1) -> BoardItem(None), Coordinate(1, 2) -> BoardItem(None)))

    boardWidthPiece.baseBoard should be(expected)
  }

  /* Unit test for isMarked method */
  "2*2 empty markedBoard's all board items" should "return isMarked false" in {
    val board : Board = Board(Map(Coordinate(0, 0) -> BoardItem(None), Coordinate(0, 1) -> BoardItem(None), Coordinate(0, 2) -> BoardItem(None),
      Coordinate(1, 0) -> BoardItem(None), Coordinate(1, 1) -> BoardItem(None), Coordinate(1, 2) -> BoardItem(None)))
    val markedBoard : MarkedBoard = new MarkedBoard(baseBoard = board)

    val coordinate1 : Coordinate = Coordinate(0, 0)
    val coordinate2 : Coordinate = Coordinate(0, 1)
    val coordinate3 : Coordinate = Coordinate(1, 0)
    val coordinate4 : Coordinate = Coordinate(1, 1)
    assert(markedBoard.isMarked(coordinate1) == false)
    assert(markedBoard.isMarked(coordinate2) == false)
    assert(markedBoard.isMarked(coordinate3) == false)
    assert(markedBoard.isMarked(coordinate4) == false)
  }

  /* Unit test for isMarked method */
  "2*2 marked markedBoard" should "return isMarked true" in {
    val board : Board = Board(Map(Coordinate(0, 0) -> BoardItem(Some(King)), Coordinate(0, 1) -> BoardItem(None), Coordinate(0, 2) -> BoardItem(None),
      Coordinate(1, 0) -> BoardItem(None), Coordinate(1, 1) -> BoardItem(None), Coordinate(1, 2) -> BoardItem(None)))
    val markedBoard : MarkedBoard = new MarkedBoard(baseBoard = board)
    val coordinate : Coordinate = Coordinate(0, 0)
    markedBoard.mark(Coordinate(0,0)).isMarked(coordinate) should be(true)
  }

  /* Unit test for notMarkedEmptyCoordinates method */
  "3x3 marked Board include Rook both (0, 0) and (1, 1) " should "not find marked and empty coordinates" in {
    val board : Board = Board(Map(Coordinate(0, 0) -> BoardItem(Some(Rook)), Coordinate(0, 1) -> BoardItem(None), Coordinate(0, 2) -> BoardItem(None),
      Coordinate(1, 0) -> BoardItem(None), Coordinate(1, 1) -> BoardItem(Some(Rook)), Coordinate(1, 2) -> BoardItem(None),
      Coordinate(2, 0) -> BoardItem(None), Coordinate(2, 1) -> BoardItem(None), Coordinate(2, 2) -> BoardItem(None)))
    val markedBoard : MarkedBoard = new MarkedBoard(baseBoard = board)
    val markedWithCoordinates : Set[Coordinate] = markedBoard.mark(Coordinate(0, 0)).mark(Coordinate(1, 1)).notMarkedEmptyCoordinates
    val expected : Set[Coordinate] = Set(Coordinate(0, 0), Coordinate(1, 1))
    markedWithCoordinates should not be(expected)
  }

  /* Unit test for notMarkedEmptyCoordinates method */
  "3x3 marked Board" should "find all not marked and empty coordinates" in {
    val board : Board = Board(Map(Coordinate(0, 0) -> BoardItem(Some(Rook)), Coordinate(0, 1) -> BoardItem(None), Coordinate(0, 2) -> BoardItem(None),
      Coordinate(1, 0) -> BoardItem(None), Coordinate(1, 1) -> BoardItem(Some(Rook)), Coordinate(1, 2) -> BoardItem(None),
      Coordinate(2, 0) -> BoardItem(None), Coordinate(2, 1) -> BoardItem(None), Coordinate(2, 2) -> BoardItem(None)))
    val markedBoard : MarkedBoard = new MarkedBoard(baseBoard = board)
    val markedWithCoordinates : Set[Coordinate] = markedBoard.mark(Coordinate(0, 0)).mark(Coordinate(1, 1)).notMarkedEmptyCoordinates
    val expected : Set[Coordinate] = Set(Coordinate(0, 1), Coordinate(0, 2), Coordinate(1, 0), Coordinate(1, 2), Coordinate(2, 0), Coordinate(2, 1), Coordinate(2, 2))
    markedWithCoordinates should be(expected)
  }
}
