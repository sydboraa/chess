import model._
import model.pieces._
import service.CalculationService
import utils.Util

import scala.Console._

/**
 * Created by SB on 02/08/15.
 */
object Chess {

  def main(args: Array[String]) {
    //board M*N
    println("Please enter the board size M")
    val boardSizeM = in.readLine()
    println("Please enter the board size N")
    val boardSizeN = in.readLine()

    if(!Util.boardSizeControl(boardSizeM, boardSizeN)){
      println("Please enter valid size for board and piece types! (it must be numeric and not empty)")
    } else {
      println("Please enter the pieces of type")
      val piecesType = in.readLine().trim
      println(s"The play is starting with ${boardSizeM}*${boardSizeN} and ${piecesType}")
      val board = Board.createEmptyBoard(boardSizeM.toInt, boardSizeN.toInt)
      val startTimer : Long = System.nanoTime()
      val solutions : Set[Board] = CalculationService.calculate(board,Util.getOrderedPieces(piecesType))
      val stopTimer : Long = System.nanoTime()

      solutions.toSeq.zipWithIndex.foreach {
        case (r, i) => {
          val ii = i + 1
          println(s"Solution $ii")
          println(r.toString)
          println()
        }
      }

      val elapsedTime : Long = (stopTimer - startTimer) / (1000 * 1000)
      println(s"Total elapsed time: ${elapsedTime} ms")
      println(s"Total solution: ${solutions.size}")
    }
  }


}
