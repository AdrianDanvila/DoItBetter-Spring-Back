package com.DoItBetter.app.response;

import com.DoItBetter.app.dto.UserDto;

public class LoginResponse {
  private UserDto user;
  private String token;

  private long expiresIn;

  public String getToken() {
    return token;
  }

  // Getters and setters...
  public long getExpiresIn() {
    return expiresIn;
  }

  public LoginResponse setExpiresIn(long expiresIn) {
    this.expiresIn = expiresIn;
    return this;
  }

  public LoginResponse setToken(String token) {
    this.token = token;
    return this;
  }

  public LoginResponse setUser(UserDto user) {
    this.user = user;
    return this;
  }

  public UserDto getUser() {
    return user;
  }
}
