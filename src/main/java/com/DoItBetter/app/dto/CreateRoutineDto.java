package com.DoItBetter.app.dto;

public class CreateRoutineDto {
  private String name;

  private String description;

  private Long user_id;

  public Long getUser_id() {
    return user_id;
  }

  public String getDescription() {
    return description;
  }

  public String getName() {
    return name;
  }

}
