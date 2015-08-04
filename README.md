## Chess Project

The problem is to find all unique configurations of a set of normal chess pieces on a chess board with dimensions M×N
where none of the pieces is in a position to take any of the others. Providing the number of results is useful,
but not enough to complete the assignment. Assume the colour of the piece does not matter, and that there are no pawns
among the pieces.

**To build and run**

```
> sbt run
```
    or
```
> sbt -mem 4096 run
```

```
> sbt -mem 4096 test
```
Important Note: I run and test on Java8.

**Sample for usage**
```
Please enter the board size M:
7
Please enter the board size N:
7
Please enter the pieces of type:
KKQQBBN

The play is starting with 7*7 and KKQQBBN

 Solution 1
 - - B - - - N
 Q - - - - - -
 - - - - - - -
 - K - - - - B
 - - - - - - -
 - - - - - Q -
 - - K - - - -

 Solution 2
 Q - - - - - -
 - - K - - - -
 - - - - - - -
 - - N - - - -
 - - - - - - Q
 - - - - - - -
 - B B - - K -

        .
        .
        .


Solution 3063828
 K - - - - - -
 - - - - - Q -
 - - - - - - -
 - - - - - - -
 N - - - K - -
 B - - - - - B
 - - - Q - - -

Total elapsed time: 99124 ms
Total solution: 3063828

```
## About Solution Algorithm

First, the pieces are sorted by their priorities. Because I want to calculate which piece occupy more place.
For example, when I put a Queen to (0, 0) location on a 3*3 board.
6 board item can't be reached by other pieces. ((1, 0, (2, 0), (1, 1), (2, 2), (0, 1), (0, 2))
If I put Knight to (0, 0) location on the 3*3 board. Only one board item can't be reached by other pieces. (1, 2)

So priority order is: Queen, Rook, Bishop, Knight, King

After sorting, I start to calculate all possible boards for the first piece.

The calculation starts with `calculate` method.
`calculate` method takes 2 parameters and returns the solutions (Set of Board)

```
def calculate(board: Board, pieces: List[ChessPiece]): Set[Board] = {`...
```
This method includes a variable which is name is *markedBoard* and it's type is *MarkedBoard*.
Marked board is a kind of board in which you can mark a board item is occupied by a chess piece.

Step 1 - calculate method is called with m*n empty board and something like List(Rook, King, King)
Step 2 - calculate method calls placeBoards method. It's parameters are Set of MarkedBoard and the piece Rook - placeBoards method is called for each piece
Step 3 - placeBoards method calls placeBoard method with a marked board and piece - placeBoard method is called for each marked board
Step 4 - placeBoard method gets the not marked and empty coordinates, and for each possible coordinate calls maybePlace method - maybePlace method is called for the marked board's each not marked and empty coordinates
Step 5 - maybePlace method puts the piece on the coordinate and using the piece's moving strategies calls validatePiecePlacement method - validatePiecePlacement method is called by the maybePlace method for each move strategy of the piece
Step 6 - validatePiecePlacement method takes a start coordinate, and a move strategy of the piece and marked board. Then it checks if there is a conflict or not. If the piece can make more than one movement, the method is called recursively.

