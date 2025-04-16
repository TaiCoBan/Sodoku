package com.utc.model.service.impl;

import com.utc.exception.ExceptionType;
import com.utc.exception.GameException;
import com.utc.model.entity.Node;
import com.utc.model.service.IGamePlay;
import com.utc.model.service.ISodokuGamePlay;
import com.utc.model.service.IValidator;

import java.util.Stack;

public class GamePlay implements ISodokuGamePlay {
  
  private IValidator validator;
  private Node[][] board;
  
  private Stack<Node> undoStack;
  private Stack<Node> redoStack;
  
  public GamePlay(IValidator validator) {
    this.validator = validator;
    this.undoStack = new Stack<>();
    this.redoStack = new Stack<>();
  }
  
  @Override
  public void undo() {
    try {
      if (!undoStack.isEmpty()) {
        // Lấy trạng thái cũ từ undoStack
        Node lastState = undoStack.pop();
        int x = lastState.getX();
        int y = lastState.getY();
        
        // Lưu trạng thái hiện tại vào redoStack
        Node current = board[x][y];
        redoStack.push(new Node(x, y, current.getValue()));
        
        // Khôi phục trạng thái cũ
        board[x][y].setValue(lastState.getValue());
      }
    } catch (RuntimeException e) {
      throw new GameException(ExceptionType.UNDO_EXCEPTION);
    }
  }
  
  @Override
  public void redo() {
    try {
      if (!redoStack.isEmpty()) {
        // Lấy trạng thái đã undo từ redoStack
        Node redoState = redoStack.pop();
        int x = redoState.getX();
        int y = redoState.getY();
        
        // Lưu trạng thái hiện tại vào undoStack
        Node current = board[x][y];
        undoStack.push(new Node(x, y, current.getValue()));
        
        // Khôi phục trạng thái redo
        board[x][y].setValue(redoState.getValue());
      }
    } catch (RuntimeException e) {
      throw new GameException(ExceptionType.REDO_EXCEPTION);
    }
  }
  
  @Override
  public void move(int x, int y, int value) {
    Node current = board[x][y];
    
    if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
      throw new GameException(ExceptionType.INVALID_X_Y);
    }
    if (value < 1 || value > board.length) {
      throw new GameException(ExceptionType.INVALID_VALUE);
    }
    undoStack.push(new Node(x, y, current.getValue()));
    redoStack.clear();
    
    board[x][y].setValue(value);
  }
  
  @Override
  public void setBoard(Node[][] board) {
    this.board = board;
  }
}
