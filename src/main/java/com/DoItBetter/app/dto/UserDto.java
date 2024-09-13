package com.DoItBetter.app.dto;

public class UserDto {
  private long id;

  private String email;

  private String name;

  private Number age;

  private Number weight;

  private Number height;

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public Number getAge() {
    return age;
  }

  public void setAge(Number age) {
    this.age = age;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setWeight(Number weight) {
    this.weight = weight;
  }

  public void setHeight(Number height) {
    this.height = height;
  }

  public Number getHeight() {
    return height;
  }

  public Number getWeight() {
    return weight;
  }
}
