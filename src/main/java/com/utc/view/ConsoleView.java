package com.utc.view;

import com.utc.model.entity.Node;
import com.utc.view.base.IView;

import java.util.Scanner;

public class ConsoleView implements IView {
  
  private Scanner scanner = new Scanner(System.in);
  
  @Override
  public int getInput(String message) {
    System.out.print(message);
    return scanner.nextInt();
  }
  
  @Override
  public void display(Node[][] board) {
    for (Node[] row : board) {
      for (Node node : row) {
        int value = node.getValue();
        System.out.print((value == 0 ? "." : value) + " ");
      }
      System.out.println();
    }
  }
  
  @Override
  public void display(String message) {
    System.out.println(message);
  }
  
  @Override
  public void close() {
    scanner.close();
  }
}