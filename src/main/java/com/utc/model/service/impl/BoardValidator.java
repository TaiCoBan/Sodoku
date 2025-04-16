package com.utc.model.service.impl;

import com.utc.exception.ExceptionType;
import com.utc.exception.GameException;
import com.utc.model.service.IBoardValidator;
import com.utc.model.entity.Node;

public class BoardValidator implements IBoardValidator {
  
  private Node[][] board;
  
  public BoardValidator() {
  }
  
  public void validateRow(int row) {
    try {
      int boardSize = board.length;
      boolean[] exists = new boolean[boardSize + 1];
      
      for (int col = 0; col < boardSize; col++) {
        int value = board[row][col].getValue();
        if (value == 0) continue;
        if (value < 1 || value > boardSize || exists[value]) {
          throw new GameException(ExceptionType.VALIDATE_ROW_EXCEPTION);
        }
        exists[value] = true;
      }
      
    } catch (RuntimeException e) {
      throw new GameException(ExceptionType.VALIDATE_ROW_EXCEPTION);
    }
  }
  
  public void validateColumn(int col) {
    try {
      int boardSize = board.length;
      boolean[] exists = new boolean[boardSize + 1];
      
      for (int row = 0; row < boardSize; row++) {
        int value = board[row][col].getValue();
        if (value == 0) continue;
        if (value < 1 || value > boardSize || exists[value]) {
          throw new GameException(ExceptionType.VALIDATE_COL_EXCEPTION);
        }
        exists[value] = true;
      }
      
    } catch (RuntimeException e) {
      throw new GameException(ExceptionType.VALIDATE_COL_EXCEPTION);
    }
  }
  
  public void validateZone(int startRow, int startCol) {
    try {
      int boardSize = board.length;
      int zoneSize = (int) Math.sqrt(boardSize);
      boolean[] exists = new boolean[boardSize + 1];
      
      for (int row = startRow; row < startRow + zoneSize; row++) {
        for (int col = startCol; col < startCol + zoneSize; col++) {
          int value = board[row][col].getValue();
          if (value == 0) continue;
          if (value < 1 || value > boardSize || exists[value]) {
            throw new GameException(ExceptionType.VALIDATE_ZONE_EXCEPTION);
          }
          exists[value] = true;
        }
      }
      
    } catch (RuntimeException e) {
      throw new GameException(ExceptionType.VALIDATE_ZONE_EXCEPTION);
    }
  }
  
  @Override
  public void validate() {
    try {
      int boardSize = board.length;
      int zoneSize = (int) Math.sqrt(boardSize);
      
      for (int i = 0; i < boardSize; i++) {
        validateRow(i);
        validateColumn(i);
      }
      
      for (int startRow = 0; startRow < boardSize; startRow += zoneSize) {
        for (int startCol = 0; startCol < boardSize; startCol += zoneSize) {
          validateZone(startRow, startCol);
        }
      }
      
    } catch (RuntimeException e) {
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
    } catch (RuntimeException e) {
      throw new GameException(ExceptionType.VALIDATE_EXCEPTION);
    }
  }
  
  public void setBoard(Node[][] board) {
    this.board = board;
  }
  
  public void close() {
  
  }
}