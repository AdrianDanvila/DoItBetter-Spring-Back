package com.DoItBetter.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class RoutineExercise {
  @ManyToOne
  @JoinColumn(name = "exercise_id", nullable = false)
  private Exercise exercise;

  @Column(nullable = false)
  Long reps = (long) 0;

  @Column(nullable = false)
  Long sets = (long) 0;

  @Column(nullable = true)
  Long weight = (long) 0;

  public void setWeight(Long weight) {
    this.weight = weight;
  }

  public void setSets(Long sets) {
    this.sets = sets;
  }

  public void setReps(Long reps) {
    this.reps = reps;
  }

  public Long getWeight() {
    return weight;
  }

  public Long getSets() {
    return sets;
  }

  public Long getReps() {
    return reps;
  }

  public void setExercise(Exercise exercise) {
    this.exercise = exercise;
  }

  public Exercise getExercise() {
    return exercise;
  }
}
