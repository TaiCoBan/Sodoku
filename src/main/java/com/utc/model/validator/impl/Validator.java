package com.utc.model.validator.impl;

import com.utc.exception.ExceptionType;
import com.utc.exception.GameException;
import com.utc.model.validator.IValidator;
import com.utc.model.entity.Node;

public class Validator implements IValidator {
  
  private Node[][] board;
  
  public Validator() {
  }
  
  @Override
  public void create(int size) {
    try {
      board = new Node[size][size];
      for (int row = 0; row < size; row++) {
        for (int col = 0; col < size; col++) {
          board[row][col] = new Node(row, col, 0);
        }
      }
    } catch (RuntimeException e) {
      throw new GameException(ExceptionType.BOARD_INIT_EXCEPTION);
    }
  }
  
  @Override
  public void validateInput(int x, int y) {
//    if (x < 0 || x >= board.length) {
//      throw new GameException(ExceptionType.INVALID_ROW);
//    }
//    if (y < 0 || y >= board[0].length) {
//      throw new GameException(ExceptionType.INVALID_COL);
//    }
    if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
      throw new GameException(ExceptionType.INVALID_X_Y);
    }
  }
  
  @Override
  public void validateInput(int value) {
    if (value < 1 || value > board.length) {
      throw new GameException(ExceptionType.INVALID_VALUE);
    }
  }
  
  @Override
  public boolean validateRow(int row) {
    try {
      int boardSize = board.length;
      boolean[] exists = new boolean[boardSize + 1];
      
      for (int col = 0; col < boardSize; col++) {
        int value = board[row][col].getValue();
        if (value == 0) continue;
        if (value < 1 || value > boardSize || exists[value]) {
          return false;
        }
        exists[value] = true;
      }
      return true;
      
    } catch (RuntimeException e) {
      throw new GameException(ExceptionType.VALIDATE_ROW_EXCEPTION);
    }
  }
  
  @Override
  public boolean validateColumn(int col) {
    try {
      int boardSize = board.length;
      boolean[] exists = new boolean[boardSize + 1];
      
      for (int row = 0; row < boardSize; row++) {
        int value = board[row][col].getValue();
        if (value == 0) continue;
        if (value < 1 || value > boardSize || exists[value]) {
          return false;
        }
        exists[value] = true;
      }
      return true;
      
    } catch (RuntimeException e) {
      throw new GameException(ExceptionType.VALIDATE_COL_EXCEPTION);
    }
  }
  
  @Override
  public boolean validateZone(int startRow, int startCol) {
    try {
      int boardSize = board.length;
      int zoneSize = (int) Math.sqrt(boardSize);
      boolean[] exists = new boolean[boardSize + 1];
      
      for (int row = startRow; row < startRow + zoneSize; row++) {
        for (int col = startCol; col < startCol + zoneSize; col++) {
          int value = board[row][col].getValue();
          if (value == 0) continue;
          if (value < 1 || value > boardSize || exists[value]) {
            return false;
          }
          exists[value] = true;
        }
      }
      return true;
      
    } catch (RuntimeException e) {
      throw new GameException(ExceptionType.VALIDATE_ZONE_EXCEPTION);
    }
  }
  
  @Override
  public boolean validate() {
    try {
      int boardSize = board.length;
      int zoneSize = (int) Math.sqrt(boardSize);
      
      for (int i = 0; i < boardSize; i++) {
        if (!validateRow(i) || !validateColumn(i)) {
          return false;
        }
      }
      
      for (int startRow = 0; startRow < boardSize; startRow += zoneSize) {
        for (int startCol = 0; startCol < boardSize; startCol += zoneSize) {
          if (!validateZone(startRow, startCol)) {
            return false;
          }
        }
      }
      
      return true;
    } catch (Exception e) {
      throw new GameException(ExceptionType.VALIDATE_EXCEPTION);
    }
  }
  
  @Override
  public boolean isBoardFilled() {
    try {
      for (Node[] row : board) {
        for (Node node : row) {
          if (node.getValue() == 0) {
            return false;
          }
        }
      }
      return true;
    } catch (Exception e) {
      throw new GameException(ExceptionType.VALIDATE_EXCEPTION);
    }
  }
  
  @Override
  public Node[][] getBoard() {
    return board;
  }
  
  @Override
  public void close() {
  
  }
}