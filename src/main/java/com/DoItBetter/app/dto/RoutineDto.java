package com.DoItBetter.app.dto;

import java.util.List;

import com.DoItBetter.app.model.RoutineExercise;

public class RoutineDto {
  private Integer id;

  private String name;

  private String description;

  private Long user_id;

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getUser_id() {
    return user_id;
  }

  public String getName() {
    return name;
  }

  public Integer getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }
}
