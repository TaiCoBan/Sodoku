package com.utc.controller;

import com.utc.controller.base.Controller;
import com.utc.exception.GameException;
import com.utc.model.service.IGamePlayService;
import com.utc.model.service.IValidatorService;
import com.utc.model.service.impl.GamePlayService;
import com.utc.model.service.impl.ValidatorService;
import com.utc.view.ConsoleView;
import com.utc.view.base.IView;

public class GameController implements Controller {
  
  private IGamePlayService gamePlay;
  private IValidatorService validator;
  private IView view;
  
  @Override
  public void create() {
    validator = new ValidatorService();
    view = new ConsoleView();
    gamePlay = new GamePlayService(validator);
  }
  
  @Override
  public void launch() {
    int size = view.getInput("Nhập kích cỡ bàn cờ: ");
    gamePlay.create(size);
    validator.setBoard(gamePlay.getBoard());
    while (true) {
      try {
        view.display(gamePlay.getBoard());
        
        int action = view.getInput("1 - Điền số \n" +
                                           "2 - Undo \n" +
                                           "3 - Redo \n" +
                                           "4 - Thoát \n" +
                                           "Chọn hành động: ");
        if (action == 1) {
          int x = view.getInput("Nhập hàng: ");
          int y = view.getInput("Nhập cột: ");
          int value = view.getInput("Nhập giá trị: ");
          gamePlay.setCellValue(x, y, value);
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
        
        if (validator.validate() && validator.isBoardFilled()) {
          view.display(gamePlay.getBoard());
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
    validator.close();
    view.close();
  }
}