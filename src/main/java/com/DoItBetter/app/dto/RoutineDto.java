package com.DoItBetter.app.dto;

import java.util.List;

public class RoutineDto {
  private Integer id;

  private String name;

  private String description;

  private List<String> exercises;

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

  public void setExercises(List<String> exercises) {
    this.exercises = exercises;
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

  public List<String> getExercises() {
    return exercises;
  }

  public String getDescription() {
    return description;
  }
}
