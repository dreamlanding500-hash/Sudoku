// Julie Aldar
// CS 143
// Sudoku #3 (solve)

// This program will create a sudoku board that reads the data from a .sdk file and displays it in a  grid style
// game board and follows specific game rules

import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

public class SudokuBoard {

   public char[][] boardDimensions;

// pre: The file that filePath is trying to use has to exist
// post: Stores data from file in the 2D array for the board
   public SudokuBoard (String filePath) {
      try {
         Scanner fileInput = new Scanner(new File(filePath));
         boardDimensions = new char [9][9];
         int rows = 0;
         String rowData = "";
      
      while (rows <= 8 && fileInput.hasNextLine()) {
         rowData = fileInput.nextLine();
         for (int x = 0; x <= 8 && x < rowData.length(); x++) {
            boardDimensions[rows][x] = rowData.charAt(x);
            }
            rows++;
         }
         fileInput.close();
      
         } catch (FileNotFoundException e) {
            System.out.println("There is no file");
         }
      }
   
   // pre:boardDimensions are accessed from the file
   // post: returns true is all the sudoku helper methods also return true
   public boolean isValid() {
      return checkValidData() && checkRow() && checkCol() && checkMiniSquares();
   }
   
   // pre: boardDimensions are accessed from the file
   // post: returns false is there is data that isn't 1-9 or a . returns true if data is all valid
   private boolean checkValidData() {
      Set<Character> validData = new HashSet<>();
      validData.add('1');
      validData.add('2');
      validData.add('3');
      validData.add('4');
      validData.add('5');
      validData.add('6');
      validData.add('7');
      validData.add('8');
      validData.add('9');
      validData.add('.');
      
      for (int x = 0; x <= 8; x++) {
         for (int y = 0; y <=8; y++) {
            if (!validData.contains(boardDimensions[x][y])) {
               return false;
            }
         }
      }
      return true;
   }
   
   // pre: boardDimensions are access from the file
   // post: returns false if there are any duplicates in a row. Returns true if there is not
   private boolean checkRow() {
      for (int x = 0; x <= 8; x++) {
         Set<Character> rowChars = new HashSet<>();
         for (int y = 0; y <=8; y++) {
            if (boardDimensions[x][y] != '.') {
               if (rowChars.contains(boardDimensions[x][y])) {
                  return false;
               }
               rowChars.add(boardDimensions[x][y]);
            }
         }
      }
      return true;
   }
   
   
   // pre:boardDimensions are access from the file
   // post: returns false if there are duplicate characters in a column. Returns true if there is not
   private boolean checkCol() {
      for (int x = 0; x <= 8; x++) {
         Set<Character> colChars = new HashSet<>();
         for (int y = 0; y <= 8; y++) {
            if (boardDimensions[y][x] != '.') {
               if (colChars.contains(boardDimensions[y][x])) {
                  return false;
               }
               colChars.add(boardDimensions[y][x]);
            }
         }
      }
      return true;
   }
   
      
   // pre: spot is location between 1-9 on the board
   // post: returns 2D array for the 3x3 mini squares on the board
   private char[][] miniSquare(int spot) {
      char[][] mini = new char[3][3];
      for(int r = 0; r < 3; r++) {
         for(int c = 0; c < 3; c++) {
            // whoa - wild! This took me a solid hour to figure out (at least)
            // This translates between the "spot" in the 9x9 Sudoku board
            // and a new mini square of 3x3
            mini[r][c] = boardDimensions[(spot - 1) / 3 * 3 + r][(spot - 1) % 3 * 3 + c];
         }
      }
      return mini;
   }
   
   // pre: boardDimensions are access from the file
   // post: returns false if any mini squares have duplicates. Returns true if it does not
   private boolean checkMiniSquares() {
      for (int sqNumber = 1; sqNumber <= 9; sqNumber++) {
         char[][] mini = miniSquare(sqNumber);
         Set<Character> miniChars = new HashSet<>();
         for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
               if (mini[x][y] != '.') {
                  if (miniChars.contains(mini[x][y])) {
                     return false;
                  }
                  miniChars.add(mini[x][y]);
               }
            }
         }
      }
      return true;
   }
  
   

   // pre: boardDimensions are access from the file
   // post: returns true when the board is completely filled out, with correct and isValid() is true.
   //Returns false if it is not.
   public boolean isSolved() {
      Map <Character, Integer> occurCount = new HashMap<>();

      for (int x = 0; x <= 8; x++) {
         for (int y = 0; y <= 8; y++) {
            if (boardDimensions[x][y] != '.') {
               if (occurCount.containsKey(boardDimensions[x][y])) {
                  occurCount.put(boardDimensions[x][y], occurCount.get(boardDimensions[x][y]) + 1);
               } else {
                  occurCount.put(boardDimensions[x][y], 1);
               }   
            }
         }
      }
      for (char z = '1'; z <= '9'; z++) {
         if (!occurCount.containsKey(z)) {
            return false;
         }
         if(occurCount.get(z) != 9) {
            return false;
         }
      }
      if (isValid() == false) {
         return false;
      }
         return true;  
   }
   
   // pre: no parameters
   // post: solves board by numbers 1-9 for each empty cell. And goes back if it fails. 
   // also returns true when board is solved and false when board is not valid
   public boolean solve() {
      boolean boardSolved = false;
      
      //Base cases:
      if (!isValid()) {
         return false;
      }
      if (isSolved()) {
         return true;
      }
         for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
            
            // forward and back tracking
               if (boardDimensions[x][y] == '.' ) {
                  for (char z = '1'; z <= '9' && !boardSolved; z++) {
                     boardDimensions[x][y] = z; 
                     boardSolved = solve();
                  }
                  if (boardSolved) {
                     return true;
                     //when attempt fails
                  } else { 
                     boardDimensions[x][y] = '.'; 
                     return false;
                  }
                  
               }
            }
         }
      return boardSolved;
   }
   
   
  
   // pre: boardDimensions has to have data from file stored in it
   // post: Prints grid for the game board using strings
   public String toString() {
         StringBuilder display = new StringBuilder();
      
         for (int x = 0; x <= 8; x++) {
            if (x == 3 || x == 6) {
               display.append("---------------------" + "\n");
            }
            for (int y = 0; y <= 8; y++) {
               display.append(" ");
               display.append(boardDimensions[x][y]);
         
               if (y == 2 || y == 5) {
                  display.append("|");
               } 
            }
            display.append("\n");
         }
      return display.toString();   
   }
}