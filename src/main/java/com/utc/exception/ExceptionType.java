package com.utc.exception;

public enum ExceptionType {
  
  BOARD_INIT_EXCEPTION("Lỗi khởi tạo bảng."),
  VALIDATE_ROW_EXCEPTION("Lỗi kiểm tra hàng."),
  VALIDATE_COL_EXCEPTION("Lỗi kiểm tra cột."),
  VALIDATE_ZONE_EXCEPTION("Lỗi kiểm tra vùng."),
  VALIDATE_EXCEPTION("Lỗi kiểm tra."),
  INVALID_COL("Cột không hợp lệ."),
  INVALID_ROW("Hàng không hợp lệ."),
  INVALID_VALUE("Giá trị không hợp lệ."),
  INVALID_X_Y("Tọa dộ không hợp lệ.")
  ;
  
  private String message;
  
  ExceptionType(String message) {
    this.message = message;
  }
  
  public String getMessage() {
    return message;
  }
}
