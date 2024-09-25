package com.DoItBetter.app.dto;

import com.DoItBetter.app.model.Exercise;

import jakarta.persistence.Column;

public class RoutineExerciseResponseDto extends RoutineExerciseDto {
  private String name;

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
