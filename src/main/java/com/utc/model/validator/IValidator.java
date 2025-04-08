package com.utc.model.validator;

import com.utc.model.entity.Node;

public interface IValidator {
  
  void create(int size);
  void validateInput(int x, int y);
  void validateInput(int value);
  boolean validateRow(int row);
  boolean validateColumn(int col);
  boolean validateZone(int startRow, int startCol);
  boolean validate();
  boolean isBoardFilled();
  Node[][] getBoard();
  void close();
}
