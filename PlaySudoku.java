// Julie Aldar
// CS 143
// Sudoku # 1(Board setup)

// This program will test that the sudoku board will display and work correctly

import java.io.FileNotFoundException;


public class PlaySudoku {

// pre: .sdk file exists in same directory as SudokuBoard and PlaySudoku so it can be acessed
// post: prints the soduko board with all the values inside it
   public static void main(String[] args) throws FileNotFoundException {
      SudokuBoard gameBoard = new SudokuBoard("data1.sdk");
      System.out.println(gameBoard);
   }
}

/* Output:
 ----jGRASP exec: java PlaySudoku
  2 . .| 1 . 5| . . 3
  . 5 4| . . .| 7 1 .
  . 1 .| 2 . 3| . 8 .
 ---------------------
  6 . 2| 8 . 7| 3 . 4
  . . .| . . .| . . .
  1 . 5| 3 . 9| 8 . 6
 ---------------------
  . 2 .| 7 . 1| . 6 .
  . 8 1| . . .| 2 4 .
  7 . .| 4 . 2| . . 1
 
 
  ----jGRASP: Operation complete.
 
*/


