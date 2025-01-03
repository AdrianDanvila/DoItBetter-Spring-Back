package com.DoItBetter.app.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.DoItBetter.app.model.Exercise;
import com.DoItBetter.app.repository.ExerciseRepository;

@Component
public class DataLoader implements CommandLineRunner {

  private final ExerciseRepository exerciseRepository;

  public DataLoader(ExerciseRepository exerciseRepository) {
    this.exerciseRepository = exerciseRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    exerciseRepository
        .save(new Exercise((long) 1, "exercises.squat.name", "exercises.squat.description", "/squat.jpg",
            "/squat.gif"));
    exerciseRepository
        .save(new Exercise((long) 2, "exercises.biceps_curl.name", "exercises.biceps_curl.description",
            "/biceps_curl.jpg", "/biceps_curl.gif"));
    exerciseRepository
        .save(
            new Exercise((long) 3, "exercises.benchpress.name", "exercises.benchpress.description", "/benchpress.png",
                "/benchpress.gif"));
  }
}