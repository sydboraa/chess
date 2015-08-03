import model._
import model.pieces._
import service.CalculationService

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
    println("Please enter the pieces of type")
    val piecesType = in.readLine().trim
    println(s"The play is starting with ${boardSizeM}*${boardSizeN} and ${piecesType}")

    val board = Board.createEmptyBoard(boardSizeM.toInt, boardSizeN.toInt)

    val startTimer : Long = System.nanoTime()
    val solutions : Set[Board] = CalculationService.calculate(board,getOrderedPieces(piecesType))
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

  def getOrderedPieces(pieces : String) : List[ChessPiece] = {
    pieces.nonEmpty match {
      case true => {
        val listPiecesTypes: List[ChessPiece] = pieces.toUpperCase.toList.map {
          piece => piece match {
            case 'Q' => Queen
            case 'R' => Rook
            case 'B' => Bishop
            case 'N' => Knight
            case 'K' => King
            case _ => throw new Exception("Wrong piece type!")
          }
        }
        listPiecesTypes.sortBy(_.priority)
      }
      case false => {
        throw new Exception("Please enter the pieces")
      }
    }

  }
}
