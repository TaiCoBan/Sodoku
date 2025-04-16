package com.utc.model.service;

import com.utc.model.entity.Node;

public interface ISodokuGamePlay extends IGamePlay {
  
  void move(int x, int y, int value);
  void setBoard(Node[][] board);
}
