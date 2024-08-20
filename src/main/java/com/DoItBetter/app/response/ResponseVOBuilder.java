package com.DoItBetter.app.response;

import org.springframework.http.HttpStatus;

public class ResponseVOBuilder<T> {
  private final ResponseVO<T> responseVO = new ResponseVO<>();

  public ResponseVOBuilder<T> result(String result) {
    responseVO.setResult(result);
    return this;
  }

  private ResponseVOBuilder<T> status(int status) {
    responseVO.setStatus(status);
    return this;
  }

  public ResponseVOBuilder<T> success() {
    return new ResponseVOBuilder<T>().result("Succeed").status(HttpStatus.ACCEPTED.value());
  }

  public ResponseVOBuilder<T> fail() {
    return new ResponseVOBuilder<T>().result("Failed").status(HttpStatus.INTERNAL_SERVER_ERROR.value());
  }

  public ResponseVOBuilder<T> error(ResponseErrorVo error) {
    responseVO.setError(error);
    responseVO.setResult("Failed");
    responseVO.setStatus(error.getCode());
    return this;
  }

  public ResponseVOBuilder<T> addData(final T body) {
    responseVO.setData(body);
    responseVO.setResult("Succeeded");
    responseVO.setStatus(HttpStatus.ACCEPTED.value());
    return this;
  }

  public ResponseVO<T> build() {
    return responseVO;
  }
}