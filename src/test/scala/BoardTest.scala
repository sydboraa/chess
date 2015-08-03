import model.{Board}

/**
 * Created by SB on 03/08/15.
 */
class BoardTest extends ChessSpec {

  "A board" should "have size 0" in {
    val board = Board.createEmptyBoard(0, 0)
    assert(board.boardItems.size == 0)
  }

  "A board" should "have size 4 and print - - \n - - " in {
    val board = Board.createEmptyBoard(2, 2)
    assert(board.boardItems.keySet.size == 4)
    assert(board.toString == " - -\n - -\n")
  }

}