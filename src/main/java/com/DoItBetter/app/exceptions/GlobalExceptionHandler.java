package com.DoItBetter.app.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.DoItBetter.app.response.ResponseErrorVo;
import com.DoItBetter.app.response.ResponseVO;
import com.DoItBetter.app.response.ResponseVOBuilder;

import org.springframework.http.MediaType;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(Exception.class)
  public ProblemDetail handleSecurityException(Exception exception) {
    ProblemDetail errorDetail = null;

    exception.printStackTrace();

    if (exception instanceof BadCredentialsException) {
      errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
      errorDetail.setProperty("description", "The username or password is incorrect");

      return errorDetail;
    }

    if (exception instanceof AccountStatusException) {
      errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
      errorDetail.setProperty("description", "The account is locked");
    }

    if (exception instanceof AccessDeniedException) {
      errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
      errorDetail.setProperty("description", "You are not authorized to access this resource");
    }

    if (exception instanceof SignatureException) {
      errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
      errorDetail.setProperty("description", "The JWT signature is invalid");
    }

    if (exception instanceof ExpiredJwtException) {
      errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
      errorDetail.setProperty("description", "The JWT token has expired");
    }

    if (errorDetail == null) {
      errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.getMessage());
      errorDetail.setProperty("description", "Unknown internal server error.");
    }

    return errorDetail;
  }

  @ExceptionHandler({ NoHandlerFoundException.class })
  public ResponseEntity<ResponseVO> handleNoHandlerFoundException(NoHandlerFoundException ex,
      HttpServletRequest httpServletRequest) {

    return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON)
        .body(new ResponseVOBuilder<>()
            .error(new ResponseErrorVo(HttpStatus.NOT_FOUND.value(), ex.getMessage(), "Resource not found")).build());
  }
}