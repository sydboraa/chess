import model.pieces.{Bishop, ChessPiece}
import model.positioning.{Move, Coordinate}

/**
 * Created by SB on 03/08/15.
 */
class CoordinateTest extends ChessSpec {

  "If a move coordinate is (0,0)" should "no movements" in {
    val coordinate : Coordinate = Coordinate(0, 0)
    val newCoordinate : Coordinate = coordinate.+(Move(0, 0))
    assert(coordinate == newCoordinate)
  }

  "If a move coordinate is (2,2)" should "new coordinate must be (2,2) on the board" in {
    val coordinate : Coordinate = Coordinate(0, 0)
    val newCoordinate : Coordinate = coordinate.+(Move(2, 2))
    assert(newCoordinate == Coordinate(2, 2))
  }
}
