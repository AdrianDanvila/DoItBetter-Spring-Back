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

  public RoutineExercise setWeight(Long weight) {
    this.weight = weight;
    return this;

  }

  public RoutineExercise setSets(Long sets) {
    this.sets = sets;
    return this;

  }

  public RoutineExercise setReps(Long reps) {
    this.reps = reps;
    return this;
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

  public RoutineExercise setExercise(Exercise exercise) {
    this.exercise = exercise;
    return this;
  }

  public Exercise getExercise() {
    return exercise;
  }
}
