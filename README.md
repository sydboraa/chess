## Chess Project

The problem is to find all unique configurations of a set of normal chess pieces on a chess board with dimensions M×N
where none of the pieces is in a position to take any of the others. Providing the number of results is useful,
but not enough to complete the assignment. Assume the colour of the piece does not matter, and that there are no pawns
among the pieces.

**To build and run**

```
> sbt run
```

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


Printed 2 of 3063828 solutions

Total elapsed time: 128220 ms
Total solution: 3063828

Note : I printed only two solutions for readme.md
```
## About Solution Algorithm

Firstly I calculate all possible boards for a piece.
When I put the Rook on 3*3 board, I got 9 board

```
R - -     - R -     - - R
- - -     - - -     - - -
- - -     - - -     - - -

- - -     - - -     - - -
R - -     - R -     - - R
- - -     - - -     - - -

- - -     - - -     - - -
- - -     - - -     - - -
R - -     - R -     - - -R
```
Pieces are sorted using their priority. Because I want to calculate which piece occupy more place.
So after sorting, firstly I put whose priority is more high.
For example, when I put a Queen to (0, 0) location on the 3*3 board.
6 board item can't be reach by other pieces. ((1, 0, (2, 0), (1, 1), (2, 2), (0, 1), (0, 2))
If I put a piece on these board items, Queen can attack.

Priority order is: Queen, Rook, Bishop, Knight, King

*placeBoards* method get these markedBoards and piece as parameter. I traverse all boards.
Every time I handle one marked board and the piece.

For example; I will handle this board.

```
R - -
- - -
- - -
```

Assume the next piece is King.
I cannot put the King with * marked places. Because they are disabled because of Rook.
```
R * *
* - -
* - -
```

After that I calculate the not marked and empty places.

*Now, the empty and not marked coordinates are (1, 1), (2, 2), (1, 2) and (2, 1)*

I traverse all these possible places. I try everyone of them. I do this in *placeBoard* method.
This method calls *maybePlace* method. Using this method, I put the piece to this empty and not marked place with a temporary board.
But this time, I don't know if my piece attack any other placed piece.
After that I calculate all attacking possibilities using piece's movements.
If there is a conflict, I return None. So I ignore this possibility. If there is no conflict for all reasons,
I return the solution.




