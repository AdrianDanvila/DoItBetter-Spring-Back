package com.DoItBetter.app.dto;

import java.util.List;

import com.DoItBetter.app.model.Comment;
import com.DoItBetter.app.model.RoutineExercise;

public class RoutineDto {
  private Integer id;

  private String name;

  private String description;

  private String routinePicturePath;

  private String routinePictureName;

  private List<RoutineExercise> exercises;

  private List<Comment> comments;

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

  public void setRoutinePictureName(String routinePictureName) {
    this.routinePictureName = routinePictureName;
  }

  public String getRoutinePictureName() {
    return routinePictureName;
  }

  public List<RoutineExercise> getExercises() {
    return exercises;
  }

  public void setExercises(List<RoutineExercise> exercises) {
    this.exercises = exercises;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public List<Comment> getComments() {
    return comments;
  }
}
