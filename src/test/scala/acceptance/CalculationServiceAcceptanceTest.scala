package scala
import model.Board
import model.pieces._
import org.scalatest.{Matchers, FlatSpec}
import service.CalculationService

/**
 * Created by SB on 04/08/15.
 */
class CalculationServiceAcceptanceTest extends FlatSpec with Matchers  {

  /**
   * Acceptance tests
   */

  "3*3 board containing 2K and 1R" should "return with 4 solutions " in {
    val board3x3 : Board = Board.createEmptyBoard(3, 3)
    val solutions : Set[Board] = CalculationService.calculate(board3x3, List(King, King, Rook))
    solutions.size should be(4)
  }

  "4*4 board containing 2R and 4N" should "return with 8 solutions " in {
    val board4x4 : Board = Board.createEmptyBoard(4, 4)
    val solutions : Set[Board] = CalculationService.calculate(board4x4, List(Rook, Rook, Knight, Knight, Knight, Knight))
    solutions.size should be(8)
  }

  //https://en.wikipedia.org/wiki/Eight_queens_puzzle  Solution count proved on Wikipedia. 8 queen has 92 solution
  "8*8 board containing 8Q" should "return with 92 solutions " in {
    val board8x8 : Board = Board.createEmptyBoard(8, 8)
    val solutions : Set[Board] = CalculationService.calculate(board8x8, List(Queen, Queen, Queen, Queen, Queen, Queen, Queen, Queen))
    solutions.size should be(92)
  }

  "7*7 board containing 2Q, 2K, 2B, 1N and " should "return with 3063828 solutions " in {
    val board7x7 : Board = Board.createEmptyBoard(7, 7)
    val solutions : Set[Board] = CalculationService.calculate(board7x7, List(Queen, Queen, King, King, Bishop, Bishop, Knight))
    solutions.size should be(3063828)
  }

  "3*3 board containing 2K and 1R" should "print 4 correct solutions " in {
    val board3x3 : Board = Board.createEmptyBoard(3, 3)
    val solutions : Set[Board] = CalculationService.calculate(board3x3, List(King, King, Rook))
    val expectedSolutionList : List[String] = List(" K - K\n - - -\n - R -\n", " K - -\n - - R\n K - -\n", " - R -\n - - -\n K - K\n", " - - K\n R - -\n - - K\n")
    solutions.toSeq.zipWithIndex.foreach {
      case (result, i) => {
        assert(expectedSolutionList.contains(result.toString) && solutions.size == 4)
      }
    }
  }

}
