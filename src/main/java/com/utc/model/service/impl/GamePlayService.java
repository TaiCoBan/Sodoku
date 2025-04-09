package com.utc.model.service.impl;

import com.utc.exception.ExceptionType;
import com.utc.exception.GameException;
import com.utc.model.entity.Node;
import com.utc.model.service.IGamePlayService;
import com.utc.model.service.IValidatorService;

import java.util.Stack;

public class GamePlayService implements IGamePlayService {
  
  private IValidatorService validator;
  private Node[][] board;
  
  private Stack<Node> undoStack;
  private Stack<Node> redoStack;
  
  public GamePlayService(IValidatorService validator) {
    this.validator = validator;
    this.undoStack = new Stack<>();
    this.redoStack = new Stack<>();
  }
  
  @Override
  public void create(int size) {
    board = new Node[size][size];
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        board[row][col] = new Node(row, col, 0);
      }
    }
  }
  
  @Override
  public void setCellValue(int x, int y, int value) {
    Node current = board[x][y];
    undoStack.push(new Node(x, y, current.getValue()));
    redoStack.clear();
    
    validator.validateInput(x, y);
    validator.validateInput(value);
    board[x][y].setValue(value);
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
  public Node[][] getBoard() {
    return board;
  }
}
