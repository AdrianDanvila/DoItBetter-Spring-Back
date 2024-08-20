package com.DoItBetter.app.dto;

public class RegisterUserDto {
  private String email;

  private String password;

  private String fullName;

  // getters and setters

  public String getEmail() {
    return email;
  }

  public String getFullName() {
    return fullName;
  }

  public String getPassword() {
    return password;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
