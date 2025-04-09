package com.utc.model.service;

import com.utc.model.entity.Node;
import com.utc.model.service.base.IService;

public interface IValidatorService extends IService {
  
  void validateInput(int x, int y);
  void validateInput(int value);
  boolean validateRow(int row);
  boolean validateColumn(int col);
  boolean validateZone(int startRow, int startCol);
  boolean validate();
  boolean isBoardFilled();
  void setBoard(Node[][] board);
  void close();
}
