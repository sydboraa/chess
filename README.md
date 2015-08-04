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

Please enter the board size M:
7
Please enter the board size N:
7
Please enter the pieces of type:
KKQQBBN

2 of 3063828 solutions

Solution 1
```
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
```

The play is starting with 7*7 and KKQQBBN
Total elapsed time: 128220 ms
Total solution: 3063828

**Note** : I printed only two solutions for readme.md

## About Solution Algorithm