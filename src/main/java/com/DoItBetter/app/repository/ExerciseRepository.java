package com.DoItBetter.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoItBetter.app.model.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
  @Override
  default List<Exercise> findAllById(Iterable<Long> ids) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAllById'");
  }
}
