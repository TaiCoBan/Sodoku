package com.utc.model.service;

import com.utc.model.entity.Node;
import com.utc.model.service.base.IService;

public interface IGamePlayService extends IService {
  
  void create(int size);
  void setCellValue(int x, int y, int value);
  void undo();
  void redo();
  Node[][] getBoard();
}
