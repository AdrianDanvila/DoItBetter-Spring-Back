package com.DoItBetter.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.DoItBetter.app.dto.CreateRoutineDto;
import com.DoItBetter.app.dto.RoutineDto;
import com.DoItBetter.app.dto.RoutineExerciseDto;
import com.DoItBetter.app.dto.RoutineExerciseResponseDto;
import com.DoItBetter.app.model.Exercise;
import com.DoItBetter.app.model.RoutineExercise;
import com.DoItBetter.app.response.ResponseVO;
import com.DoItBetter.app.response.ResponseVOBuilder;
import com.DoItBetter.app.service.impl.RoutineServiceImpl;

@RestController
@RequestMapping("/routine")
public class RoutineController {

	@Autowired
	RoutineServiceImpl routineServiceImpl;

	@PostMapping("")
	@ResponseBody
	public ResponseEntity<ResponseVO<RoutineDto>> postRoutine(@RequestBody CreateRoutineDto createRoutineDto) {
		RoutineDto savedRoutine = routineServiceImpl.saveRoutine(createRoutineDto);
		ResponseEntity.ok();
		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON)
				.body(new ResponseVOBuilder<RoutineDto>().addData(savedRoutine).build());
	}

	@GetMapping("")
	@ResponseBody
	public ResponseEntity<ResponseVO<List<RoutineDto>>> getAllRoutines() {
		List<RoutineDto> routines = routineServiceImpl.allRoutines();
		ResponseEntity.ok();
		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON)
				.body(new ResponseVOBuilder<List<RoutineDto>>().addData(routines).build());
	}

	@GetMapping("/{userId}")
	public ResponseEntity<ResponseVO<List<RoutineDto>>> getRoutinesByUserId(@PathVariable Long userId) {

		List<RoutineDto> routines = routineServiceImpl.getRoutinesByUserId(userId);
		ResponseEntity.ok();
		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON)
				.body(new ResponseVOBuilder<List<RoutineDto>>().addData(routines).build());
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public String deleteRoutine(@PathVariable Long id) {
		routineServiceImpl.deleteRoutine(id);
		return "succesful";
	}

	@GetMapping("/me")
	@ResponseBody
	public ResponseEntity<ResponseVO<List<RoutineDto>>> getAllRoutinesByToken() {
		List<RoutineDto> routines = routineServiceImpl.getRoutinesByToken();
		ResponseEntity.ok();
		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON)
				.body(new ResponseVOBuilder<List<RoutineDto>>().addData(routines).build());
	}

	@GetMapping("/published")
	@ResponseBody
	public ResponseEntity<ResponseVO<List<RoutineDto>>> getPublishedRoutines() {
		List<RoutineDto> routines = routineServiceImpl.getPublishedRoutines();
		ResponseEntity.ok();
		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON)
				.body(new ResponseVOBuilder<List<RoutineDto>>().addData(routines).build());
	}

	@GetMapping("/published/{userId}")
	public ResponseEntity<ResponseVO<List<RoutineDto>>> getPublishedRoutinesByUserId(@PathVariable Long userId) {
		List<RoutineDto> routines = routineServiceImpl.getPublishedRoutinesByUserId(userId);
		ResponseEntity.ok();
		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON)
				.body(new ResponseVOBuilder<List<RoutineDto>>().addData(routines).build());
		// Responde con 200 y la lista de posts
	}

	@GetMapping("/{id}/exercises")
	public ResponseEntity<ResponseVO<List<RoutineExerciseResponseDto>>> getRoutineExercises(@PathVariable Long id) {

		List<RoutineExerciseResponseDto> response = routineServiceImpl.getExercises(id);

		ResponseEntity.ok();
		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON)
				.body(new ResponseVOBuilder<List<RoutineExerciseResponseDto>>().addData(response).build());
	}

	@PostMapping("/{id}/exercises")
	public ResponseEntity<ResponseVO<List<RoutineExerciseResponseDto>>> postRoutineExercise(@PathVariable Long id,
			@RequestBody RoutineExerciseDto routineExerciseDTO) {

		RoutineExercise routineExercise = new RoutineExercise();
		Exercise exercise = new Exercise();
		exercise.setId(routineExerciseDTO.getId());
		routineExercise.setExercise(exercise);
		routineExercise.setSets(routineExerciseDTO.getSets());
		routineExercise.setReps(routineExerciseDTO.getReps());
		List<RoutineExerciseResponseDto> response = routineServiceImpl.addExercise(id, routineExercise);

		ResponseEntity.ok();
		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON)
				.body(new ResponseVOBuilder<List<RoutineExerciseResponseDto>>().addData(response).build());
	}

	@DeleteMapping("/{id}/exercises")
	public ResponseEntity<ResponseVO<List<RoutineExerciseResponseDto>>> deleteRoutineExercise(@PathVariable Long id,
			@RequestBody RoutineExerciseDto routineExerciseDTO) {

		RoutineExercise routineExercise = new RoutineExercise();
		Exercise exercise = new Exercise();
		exercise.setId(routineExerciseDTO.getId());
		routineExercise.setExercise(exercise);
		routineExercise.setSets(routineExerciseDTO.getSets());
		routineExercise.setReps(routineExerciseDTO.getReps());

		List<RoutineExerciseResponseDto> response = routineServiceImpl.removeExercise(id, routineExercise);

		ResponseEntity.ok();
		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON)
				.body(new ResponseVOBuilder<List<RoutineExerciseResponseDto>>().addData(response).build());
	}

}
