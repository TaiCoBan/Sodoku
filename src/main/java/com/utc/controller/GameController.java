package com.utc.controller;

import com.utc.controller.base.Controller;
import com.utc.exception.GameException;
import com.utc.model.validator.IValidator;
import com.utc.model.validator.impl.Validator;
import com.utc.view.ConsoleView;
import com.utc.view.base.IView;

public class GameController implements Controller {
  
  private IValidator validator;
  private IView view;
  
  @Override
  public void create() {
    validator = new Validator();
    view = new ConsoleView();
  }
  
  @Override
  public void launch() {
    int size = view.getInput("Nhập kích cỡ bàn cờ: ");
    validator.create(size);
    while (true) {
      try {
        view.display(validator.getBoard());
        
        int x = view.getInput("Nhập hàng: ");
        int y = view.getInput("Nhập cột: ");
        validator.validateInput(x, y);
        
        int value = view.getInput("Nhập giá trị: ");
        validator.validateInput(value);
        validator.getBoard()[x][y].setValue(value);
        
        if (validator.validate() && validator.isBoardFilled()) {
            view.display(validator.getBoard());
            view.display("WIN !!!");
            break;
        }
        
      } catch (GameException e) {
        view.display(e.getMessage());
        
      } catch (Exception e) {
        view.display("Lỗi không mong muốn gì ấy, không kịp cover");
      }
    }
  }
  
  @Override
  public void close() {
    validator.close();
    view.close();
  }
}