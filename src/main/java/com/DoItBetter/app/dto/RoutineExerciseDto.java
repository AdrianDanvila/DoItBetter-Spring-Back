package com.DoItBetter.app.dto;

public class RoutineExerciseDto {

  private Long id;
  private Long sets;
  private Long reps;
  private Long weight;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getSets() {
    return sets;
  }

  public void setSets(Long sets) {
    this.sets = sets;
  }

  public Long getReps() {
    return reps;
  }

  public void setReps(Long reps) {
    this.reps = reps;
  }

  public void setWeight(Long weight) {
    this.weight = weight;
  }

  public Long getWeight() {
    return weight;
  }

}
