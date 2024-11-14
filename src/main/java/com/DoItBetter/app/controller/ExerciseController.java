package com.DoItBetter.app.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DoItBetter.app.model.Exercise;
import com.DoItBetter.app.response.ResponseVO;
import com.DoItBetter.app.response.ResponseVOBuilder;
import com.DoItBetter.app.service.impl.ExerciseServiceImpl;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

	@Autowired
	ExerciseServiceImpl exerciseServiceImpl;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("")
	public ResponseEntity<ResponseVO<List<Exercise>>> allExercises() {
		List<Exercise> exercise = exerciseServiceImpl.allExercises();

		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON)
				.body(new ResponseVOBuilder<List<Exercise>>().addData(exercise).build());
	}
}
