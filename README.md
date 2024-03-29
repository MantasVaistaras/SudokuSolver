# Sudoku Solver

The program which can solve sudoku. It can also solve sudoku boards which do not have quadratic boxes in it. Note that 64 × 64 is the upper limit for the size of the board. An example of a non-square board is shown below (6 × 6).

![alt text](https://raw.githubusercontent.com/Manteliz/SudokuSolver/master/pictures/sudoku1.png)

The file format used:
The file name is specified as the parameter of the program (on the command line). This 6 × 6 board is described by the file above. Note that the file does not contain any blank characters.

The first number (2) is the number of rows in each box, and the next number (3) is the number of columns
in each box. 2 × 3 = 6. The board is then 6 × 6 = 36 regions. 6 is also the number of regions
in column, box and row. Then follows the board itself. Point (.) Means empty region.
If we need more than 9 values, we use the following:

![alt text](https://raw.githubusercontent.com/Manteliz/SudokuSolver/master/pictures/sudoku2.png)

The solutions are found by going through all the regions on the board and trying all of the
possible (legal) values in each region. This is called the "Brute-force" method.

If there are more solutions than 3500, the program keeps tracking the count of solutions but do not store more than 3500 solutions.

Example 1:

![alt text](https://raw.githubusercontent.com/Manteliz/SudokuSolver/master/pictures/sudoku3.png)

Example 2:

![alt text](https://raw.githubusercontent.com/Manteliz/SudokuSolver/master/pictures/sudoku4.png)

Example 3:

![alt text](https://raw.githubusercontent.com/Manteliz/SudokuSolver/master/pictures/sudoku5.png)

Example 4:

![alt text](https://raw.githubusercontent.com/Manteliz/SudokuSolver/master/pictures/sudoku6.png)

