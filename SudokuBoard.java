// Julie Aldar
// CS 143
// Sudoku # 1 (Board setup)

// This program will create a sudoku board that reads the data from a .sdk file and displays it in a  grid style
// game board

import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

public class SudokuBoard {

   public char[][] boardDimensions;

// pre: The file that filePath is trying to use has to exist
// post: Stores data from file in the 2D array
public SudokuBoard (String filePath) throws FileNotFoundException {
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