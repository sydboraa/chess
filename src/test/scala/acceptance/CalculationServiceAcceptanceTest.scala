package scala
import model.Board
import model.pieces.{Queen, Knight, Rook, King}
import org.scalatest.{Matchers, FlatSpec}
import service.CalculationService

/**
 * Created by SB on 04/08/15.
 */
class CalculationServiceAcceptanceTest extends FlatSpec with Matchers  {

  /**
   * Acceptance tests
   */

  "3 * 3 board containing 2K and 1R" should "return with 4 solutions " in {
    val board3x3 : Board = Board.createEmptyBoard(3, 3)
    val solutions : Set[Board] = CalculationService.calculate(board3x3, List(King, King, Rook))
    solutions.size should be(4)
  }

  "4 * 4 board containing 2R and 4N" should "return with 8 solutions " in {
    val board4x4 : Board = Board.createEmptyBoard(4, 4)
    val solutions : Set[Board] = CalculationService.calculate(board4x4,List(Rook, Rook, Knight, Knight, Knight, Knight))
    solutions.size should be(8)
  }

  //https://en.wikipedia.org/wiki/Eight_queens_puzzle  Solution count proved on Wikipedia. 8 queen has 92 solution
  "8 * 8 board containing 8Q" should "return with 92 solutions " in {
    val board8x8 : Board = Board.createEmptyBoard(8, 8)
    val solutions : Set[Board] = CalculationService.calculate(board8x8,List(Queen, Queen, Queen, Queen, Queen, Queen, Queen, Queen))
    solutions.size should be(92)
  }
}
