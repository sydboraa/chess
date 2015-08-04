package unit

import model.positioning.{Coordinate, Move}

/**
 * Created by SB on 03/08/15.
 */
class CoordinateTest extends ChessSpec {

  "If a move coordinate is (0,0)" should "be no movements" in {
    val coordinate : Coordinate = Coordinate(0, 0)
    val newCoordinate : Coordinate = coordinate.+(Move(0, 0))
    assert(coordinate == newCoordinate)
  }

  "If a move coordinate is (2,2) and the current coordinate is (0, 0) new coordinate " should "be (2,2)" in {
    val coordinate : Coordinate = Coordinate(0, 0)
    val newCoordinate : Coordinate = coordinate.+(Move(2, 2))
    assert(newCoordinate == Coordinate(2, 2))
  }
}
