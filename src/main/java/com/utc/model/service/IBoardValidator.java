package com.utc.model.service;

import com.utc.model.entity.Node;

public interface IBoardValidator extends IValidator {
  
  boolean isBoardFilled();
  void setBoard(Node[][] board);
}
