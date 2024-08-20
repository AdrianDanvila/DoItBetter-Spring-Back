package com.DoItBetter.app.response;

public class ResponseVO<T> {
  private int status;
  private String result;
  private ResponseErrorVo error;
  private T data;

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public T getData() {
    return data;
  }

  public ResponseErrorVo getError() {
    return error;
  }

  public void setError(ResponseErrorVo error) {
    this.error = error;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public void setData(T data) {
    this.data = data;
  }
}