package com.DoItBetter.app.dto;

public class RegisterUserDto {
  private String email;

  private String password;

  private String name;

  private Number age;

  private Number weight;

  private Number height;

  // getters and setters

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public Number getAge() {
    return age;
  }

  public Number getHeight() {
    return height;
  }

  public Number getWeight() {
    return weight;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setAge(Number age) {
    this.age = age;
  }

  public void setHeight(Number height) {
    this.height = height;
  }

  public void setWeight(Number weight) {
    this.weight = weight;
  }

}
