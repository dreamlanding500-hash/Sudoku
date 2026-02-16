// Julie Aldar
// CS 143
// Sudoku #3 (solve)

// This program will use a file to see if the sudoku board game can be solved. It then shows the game board and the result
public class SudokuSolverEngine {

   public static void main(String[] args) {
      // Here I have called my class `MySudokuBoard` if you named your class
      // differently, modify the line below to use your own class name
      
      //SudokuBoard sudokuBoard = new SudokuBoard("boards/col-violation.sdk");
      //SudokuBoard sudokuBoard = new SudokuBoard("boards/valid-complete.sdk");
      SudokuBoard sudokuBoard = new SudokuBoard("boards/very-fast-solve.sdk");
      System.out.println("Initial board");
      System.out.println(sudokuBoard);
      System.out.println();
      
      if (!sudokuBoard.isValid()) {
         System.out.println("Board can not be solved");
         return;
      }
      if (sudokuBoard.isSolved()) {
         System.out.println("Board is already solved");
         return;
      }
      
      System.out.print("Solving board...");
      long start = System.currentTimeMillis();
      sudokuBoard.solve();
      long stop = System.currentTimeMillis();
      System.out.printf("SOLVED in %.3f seconds.\n", ((stop-start)/1000.0));
      System.out.println();
      System.out.println(sudokuBoard);
   }
}

/* Output when I tested very-fast-solve.sdk:
      ----jGRASP exec: java -ea SudokuSolverEngine
 Initial board
  . 3 4| 6 7 8| 9 1 2
  . 7 2| 1 9 5| 3 4 8
  1 9 8| 3 4 2| 5 6 7
 ---------------------
  . . 9| . 6 1| 4 2 3
  . 2 6| 8 5 3| 7 9 1
  . 1 3| 9 2 4| . 5 6
 ---------------------
  . 6 1| 5 3 7| 2 8 4
  . 8 .| 4 1 9| 6 3 5
  3 4 5| . 8 6| 1 7 9
 
 
 Solving board...SOLVED in 0.004 seconds.
 
  5 3 4| 6 7 8| 9 1 2
  6 7 2| 1 9 5| 3 4 8
  1 9 8| 3 4 2| 5 6 7
 ---------------------
  8 5 9| 7 6 1| 4 2 3
  4 2 6| 8 5 3| 7 9 1
  7 1 3| 9 2 4| 8 5 6
 ---------------------
  9 6 1| 5 3 7| 2 8 4
  2 8 7| 4 1 9| 6 3 5
  3 4 5| 2 8 6| 1 7 9
 
 
  ----jGRASP: Operation complete.
 
 Output when I tested if board was invalid using col-violation.sdk:
     ----jGRASP exec: java -ea SudokuSolverEngine
 Initial board
  9 . .| . . .| . . .
  . . .| . . .| . . .
  6 . .| . . .| . . .
 ---------------------
  . . .| . . .| . . .
  4 . .| . . .| . . .
  3 . .| . . .| . . .
 ---------------------
  . . .| . . .| . . .
  9 . .| . . .| . . .
  . . .| . . .| . . .
 
 
 Board can not be solved
 
  ----jGRASP: Operation complete.
 
   Output when I tested if board is already solved using valid-complete.sdk: 
       ----jGRASP exec: java -ea SudokuSolverEngine
 Initial board
  5 3 4| 6 7 8| 9 1 2
  6 7 2| 1 9 5| 3 4 8
  1 9 8| 3 4 2| 5 6 7
 ---------------------
  8 5 9| 7 6 1| 4 2 3
  4 2 6| 8 5 3| 7 9 1
  7 1 3| 9 2 4| 8 5 6
 ---------------------
  9 6 1| 5 3 7| 2 8 4
  2 8 7| 4 1 9| 6 3 5
  3 4 5| 2 8 6| 1 7 9
 
 
 Board is already solved
 
  ----jGRASP: Operation complete.
 

*/