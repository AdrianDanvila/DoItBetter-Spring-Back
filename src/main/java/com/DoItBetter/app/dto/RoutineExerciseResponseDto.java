package com.DoItBetter.app.dto;

public class RoutineExerciseResponseDto extends RoutineExerciseDto {
  private String name;
  private String description;

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
