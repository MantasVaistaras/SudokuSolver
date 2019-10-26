# Sudoku Solver

The program which can solve sudoku. It can also solve sudoku boards which do not have quadratic boxes in it. Note that 64 × 64 is the upper limit for the size of the board. An example of a non-square board is shown below (6 × 6).

![alt text](http://url/to/img.png)

The file format used
To save sudoku tasks, we have defined our own file format. This 6 × 6 board is described by the file above. Note that the file does not contain any blank characters.

The first number (2) is the number of rows in each box, and the next number (3) is the number of columns
in each box. 2 × 3 = 6. The board is then 6 × 6 = 36 regions. 6 is also the number of regions
in column, box and row. Then follows the board itself. Point (.) Means empty region.
If we need more than 9 values, we use the following:

The printout from the example above should be like this (unsolved
to the left):

The solutions are found by going through all the regions on the board and trying all of the
possible (legal) values in each region. This is called the "Brute-force" method.

If there are more solutions than 3500, the program keeps track the number of how many solutions
are found, but do not store more than 3500 solutions.

Example:

