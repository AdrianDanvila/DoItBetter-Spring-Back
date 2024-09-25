package com.DoItBetter.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoItBetter.app.model.Exercise;
import com.DoItBetter.app.repository.ExerciseRepository;
import com.DoItBetter.app.service.ExerciseService;

@Service
public class ExerciseServiceImpl implements ExerciseService {
	@Autowired
	ExerciseRepository exerciseRepository;

	public List<Exercise> allExercises() {
		return exerciseRepository.findAll();
	}

}