package unit

import model.pieces.{Knight, ChessPiece, Rook}
import model.positioning.{Coordinate, Move}
import model.{Board, BoardItem, MarkedBoard}
import org.scalatest.PrivateMethodTester.PrivateMethod
import service.CalculationService

/**
 * Created by SB on 03/08/15.
 */
class CalculationServiceTest extends ChessSpec {

  /*Unit test for validatePiecePlacement method */

  /*
  * when we put R on (0, 0) coordinate
  *
  * R - -
  * - - -
  *
  * For East Direction board will be as follows
  *
  * R * *
  * - - -
  *
  * ((0, 1) and (0,2) will be disabled for other pieces. Because if we put another piece these locations, R will attack
  *
  * */
  "For 3*2 board with marked Rook(0, 0)" should "calculate to disable locations are (0, 1), (0, 2) " in {
    val board : Board = Board(Map(Coordinate(0, 0) -> BoardItem(Some(Rook)), Coordinate(0, 1) -> BoardItem(None), Coordinate(0, 2) -> BoardItem(None),
      Coordinate(1, 0) -> BoardItem(None), Coordinate(1, 1) -> BoardItem(None), Coordinate(1, 2) -> BoardItem(None)))

    val markedBoard : MarkedBoard = new MarkedBoard(baseBoard = board)
    val startCoordinate : Coordinate = Coordinate(0, 0)
    val move : Move = Move(0, 1)
    val indefinite : Boolean = true
    val privateValidatePiecePlacementMethod = PrivateMethod[Option[MarkedBoard]]('validatePiecePlacement)
    val actual = CalculationService invokePrivate privateValidatePiecePlacementMethod(startCoordinate, markedBoard, move, indefinite)
    val expected = Set(Coordinate(0, 1), Coordinate(0, 2))
    actual.get.markedCoordinates should be(expected)
  }


  /*
  * when we put R on (0, 0) coordinate
  *
  * R - -
  * - - -
  *
  * For South Direction board will be as follows
  *
  * R - -
  * * - -
  *
  * ((1, 0) will be disabled for other pieces. Because if we put another piece these locations, R will attack
  *
  * */
  "For 3*2 board with marked Rook(0,0)" should "calculate to disable location is (1, 0) " in {
    val board : Board = Board(Map(Coordinate(0, 0) -> BoardItem(Some(Rook)), Coordinate(0, 1) -> BoardItem(None), Coordinate(0, 2) -> BoardItem(None),
      Coordinate(1, 0) -> BoardItem(None), Coordinate(1, 1) -> BoardItem(None), Coordinate(1, 2) -> BoardItem(None)))
    val markedBoard : MarkedBoard = new MarkedBoard(baseBoard = board)
    val startCoordinate : Coordinate = Coordinate(0, 0)
    val move : Move = Move(1, 0)
    val indefinite : Boolean = true
    val privateValidatePiecePlacementMethod = PrivateMethod[Option[MarkedBoard]]('validatePiecePlacement)
    val actual = CalculationService invokePrivate privateValidatePiecePlacementMethod(startCoordinate, markedBoard, move, indefinite)
    val expected = Set(Coordinate(1, 0))
    actual.get.markedCoordinates should be(expected)
  }

  //isInsideBoard = false
  "For 3*2 board with marked Rook(0,0)" should "calculate to disable locations for (0, -1) - no possible location" in {
    val board : Board = Board(Map(Coordinate(0, 0) -> BoardItem(Some(Rook)), Coordinate(0, 1) -> BoardItem(None), Coordinate(0, 2) -> BoardItem(None),
      Coordinate(1, 0) -> BoardItem(None), Coordinate(1, 1) -> BoardItem(None), Coordinate(1, 2) -> BoardItem(None)))
    val markedBoard : MarkedBoard = new MarkedBoard(baseBoard = board)
    val startCoordinate : Coordinate = Coordinate(0, 0)
    val move : Move = Move(0, -1)
    val indefinite : Boolean = true
    val privateValidatePiecePlacementMethod = PrivateMethod[Option[MarkedBoard]]('validatePiecePlacement)
    val actual = CalculationService invokePrivate privateValidatePiecePlacementMethod(startCoordinate, markedBoard, move, indefinite)
    val expected = Set()
    actual.get.markedCoordinates should be(expected)
  }

  //isInsideBoard = false
  "For 3*2 board with marked Rook(0,0)" should "calculate to disable locations for (-1, 0) - no possible location" in {
    val board : Board = Board(Map(Coordinate(0, 0) -> BoardItem(Some(Rook)), Coordinate(0, 1) -> BoardItem(None), Coordinate(0, 2) -> BoardItem(None),
      Coordinate(1, 0) -> BoardItem(None), Coordinate(1, 1) -> BoardItem(None), Coordinate(1, 2) -> BoardItem(None)))
    val markedBoard : MarkedBoard = new MarkedBoard(baseBoard = board)
    val startCoordinate : Coordinate = Coordinate(0, 0)
    val move : Move = Move(-1, 0)
    val indefinite : Boolean = true
    val privateValidatePiecePlacementMethod = PrivateMethod[Option[MarkedBoard]]('validatePiecePlacement)
    val actual = CalculationService invokePrivate privateValidatePiecePlacementMethod(startCoordinate, markedBoard, move, indefinite)
    val expected = Set()
    actual.get.markedCoordinates should be(expected)
  }

  //this is a conflict test
  "For 3*3 board with marked  Rook(0,0) and other Rook(1,1)" should "no possible location for K" in {
    val board : Board = Board(Map(Coordinate(0, 0) -> BoardItem(Some(Rook)), Coordinate(0, 1) -> BoardItem(None), Coordinate(0, 2) -> BoardItem(None),
      Coordinate(1, 0) -> BoardItem(None), Coordinate(1, 1) -> BoardItem(Some(Rook)), Coordinate(1, 2) -> BoardItem(None),
      Coordinate(2, 0) -> BoardItem(None), Coordinate(2, 1) -> BoardItem(None), Coordinate(2, 2) -> BoardItem(None)))
    val markedBoard : MarkedBoard = new MarkedBoard(baseBoard = board)
    val startCoordinate : Coordinate = Coordinate(2, 2)
    val move : Move = Move(-1, -1)
    val indefinite : Boolean = false
    val privateValidatePiecePlacementMethod = PrivateMethod[Option[MarkedBoard]]('validatePiecePlacement)
    val actual = CalculationService invokePrivate privateValidatePiecePlacementMethod(startCoordinate, markedBoard, move, indefinite)
    actual should be(None)
  }

  "For 0*0 board with marked  Rook(0,0)" should "no possible location" in {
    val board : Board = Board(Map(Coordinate(0, 0) -> BoardItem(Some(Rook))))//, Coordinate(0, 1) -> BoardItem(None)))
    val markedBoard : MarkedBoard = new MarkedBoard(baseBoard = board)
    val startCoordinate : Coordinate = Coordinate(0, 1)  //this coordinate is outside of the board
    val move : Move = Move(0, -1) // i try to put the piece (0, 0)
    val indefinite : Boolean = false
    val privateValidatePiecePlacementMethod = PrivateMethod[Option[MarkedBoard]]('validatePiecePlacement)
    val actual = CalculationService invokePrivate privateValidatePiecePlacementMethod(startCoordinate, markedBoard, move, indefinite)
    actual should be(None)
  }
}
