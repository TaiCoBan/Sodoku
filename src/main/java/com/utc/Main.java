package com.utc;

import com.utc.controller.GameController;
import com.utc.controller.base.Controller;

public class Main {
  public static void main(String[] args) {
    Controller controller = new GameController();
    controller.create();
    controller.launch();
    controller.close();
  }
}