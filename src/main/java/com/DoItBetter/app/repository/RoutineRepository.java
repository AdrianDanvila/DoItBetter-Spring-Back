package com.DoItBetter.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoItBetter.app.model.Routine;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
  List<Routine> findAllByPublishedTrue();

  List<Routine> findAllByUserId(Long userId);

  // Método para obtener los posts de un usuario que estén publicados
  List<Routine> findAllByUserIdAndPublishedTrue(Long userId);
}
