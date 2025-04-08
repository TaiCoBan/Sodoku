package com.utc.view.base;

import com.utc.model.entity.Node;

public interface IView {
  
  int getInput(String message);
  void display(Node[][] board);
  void display(String message);
  void close();
}
