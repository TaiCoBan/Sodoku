package com.utc.controller;

import com.utc.controller.base.Controller;
import com.utc.exception.GameException;
import com.utc.model.entity.Node;
import com.utc.model.service.IBoardValidator;
import com.utc.model.service.IGamePlay;
import com.utc.model.service.ISodokuGamePlay;
import com.utc.model.service.impl.GamePlay;
import com.utc.model.service.impl.BoardValidator;
import com.utc.view.ConsoleView;
import com.utc.view.base.IView;

public class GameController implements Controller {
  
  private Node[][] board;
  private ISodokuGamePlay gamePlay;
  private IBoardValidator validator;
  private IView view;
  
  @Override
  public void create() {
    validator = new BoardValidator();
    view = new ConsoleView();
    gamePlay = new GamePlay(validator);
  }
  
  @Override
  public void launch() {
    int size = view.getInput("Nhập kích cỡ bàn cờ: ");
    createBoard(size);
    
    gamePlay.setBoard(board);
    validator.setBoard(board);
    
    while (true) {
      try {
        view.display(board);
        
        int action = view.getInput("1 - Điền số \n" +
                                           "2 - Undo \n" +
                                           "3 - Redo \n" +
                                           "4 - Thoát \n" +
                                           "Chọn hành động: ");
        if (action == 1) {
          int x = view.getInput("Nhập hàng: ");
          int y = view.getInput("Nhập cột: ");
          int value = view.getInput("Nhập giá trị: ");
          gamePlay.move(x, y, value);
        } else if (action == 2) {
          gamePlay.undo();
        } else if (action == 3) {
          gamePlay.redo();
        } else if (action == 4) {
          break;
        } else {
          view.display("Hành động không hợp lệ");
          continue;
        }
        
        validator.validate();
        
        if (validator.isBoardFilled()) {
          view.display(board);
          view.display("WIN !!!");
          break;
        }
        
      } catch (GameException e) {
        view.display(e.getMessage());
        
      } catch (Exception e) {
        view.display("Lỗi không mong muốn");
      }
    }
  }
  
  @Override
  public void close() {
    view.close();
  }
  
  private void createBoard(int size) {
    board = new Node[size][size];
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        board[row][col] = new Node(row, col, 0);
      }
    }
  }
}