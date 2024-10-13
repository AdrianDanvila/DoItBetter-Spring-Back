package com.DoItBetter.app.dto;

import java.util.List;

public class RoutineDto {
  private Integer id;

  private String name;

  private String description;

  private String routinePicturePath;

  private boolean published;

  private Long user_id;

  private String user_name;

  public void setPublished(boolean published) {
    this.published = published;
  }

  public boolean isPublished() {

    return published;
  }

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

  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }

  public String getUser_name() {
    return user_name;
  }

  public void setRoutinePicturePath(String routinePicturePath) {
    this.routinePicturePath = routinePicturePath;
  }

  public String getRoutinePicturePath() {
    return routinePicturePath;
  }
}
