package sudoku;

import java.util.HashSet;
import java.util.Set;

public class SudokuSolver {

  public void solve(char[][] board) {}

  public static boolean isValidSudoku(char[][] board) {
    Set<String> sudokuValSet = new HashSet<>();

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        char symbol = board[i][j];
        if (symbol != '.') {
          boolean isValidSudoku =
              sudokuValSet.add(annotateRow(symbol, i))
                  && sudokuValSet.add(annotateCol(symbol, j))
                  && sudokuValSet.add(annotateThreeSquare(symbol, i, j));

          if (!isValidSudoku) {
            return false;
          }
        }
      }
    }

    return true;
  }

  private static String annotateRow(char symbol, int rowIdx) {
    return String.format("%s(%s)", rowIdx, symbol);
  }

  private static String annotateCol(char symbol, int colIdx) {
    return String.format("(%s)%s", symbol, colIdx);
  }

  private static String annotateThreeSquare(char symbol, int rowIdx, int colIdx) {
    return String.format("%s(%s)%s", rowIdx / 3, symbol, colIdx / 3);
  }
}
