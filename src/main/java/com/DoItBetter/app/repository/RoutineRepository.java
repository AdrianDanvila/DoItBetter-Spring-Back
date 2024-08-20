package com.DoItBetter.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoItBetter.app.model.Routine;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
}
