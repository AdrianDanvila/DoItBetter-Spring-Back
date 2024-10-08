package com.DoItBetter.app.response;

import org.springframework.util.ObjectUtils;

public class ResponseErrorVo {
  private int code;
  private String message;
  private String description;

  public ResponseErrorVo(int code, String message) {
    this.code = code;
    this.message = message;
    this.description = message;
  }

  public ResponseErrorVo(int code, String message, String description) {
    this.code = code;
    this.message = message;
    this.description = description;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getDescription() {
    return ObjectUtils.isEmpty(description) ? message : description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}