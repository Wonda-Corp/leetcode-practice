package sudoku;

import java.util.HashSet;
import java.util.Set;

public class SudokuSolver {

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

  public static boolean isValidSudoku2(char[][] board) {
    Set<Character>[] rows = new Set[9];
    Set<Character>[] cols = new Set[9];
    Set<Character>[] boxes = new Set[9];

    for (int i = 0; i < 9; i++) {
      rows[i] = new HashSet<>();
      cols[i] = new HashSet<>();
      boxes[i] = new HashSet();
    }

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        char symbol = board[i][j];
        if (symbol != '.') {
          if (!(rows[i].add(symbol)
              && cols[i].add(symbol)
              && boxes[i / 3 * 3 + j / 3].add(symbol))) {
            return false;
          }
        }
      }
    }

    return true;
  }

  public static boolean isValidSudoku3(char[][] board) {
    boolean[][] rows = new boolean[9][9];
    boolean[][] cols = new boolean[9][9];
    boolean[][] boxes = new boolean[9][9];

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (board[i][j] == '.') {
          continue;
        }
        int offsetDigitValue = board[i][j] - '1';

        int boxIndex = i / 3 * 3 + j / 3;
        if (rows[i][offsetDigitValue]
            || cols[j][offsetDigitValue]
            || boxes[boxIndex][offsetDigitValue]) {
          return false;
        }

        rows[i][offsetDigitValue] =
            cols[j][offsetDigitValue] = boxes[boxIndex][offsetDigitValue] = true;
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

  public void solve(char[][] board) {}
}
