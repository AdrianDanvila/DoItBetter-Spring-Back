package com.DoItBetter.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.DoItBetter.app.dto.CreateRoutineDto;
import com.DoItBetter.app.dto.RoutineDto;
import com.DoItBetter.app.model.Routine;
import com.DoItBetter.app.response.ResponseVO;
import com.DoItBetter.app.response.ResponseVOBuilder;
import com.DoItBetter.app.service.impl.RoutineServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/routine")
public class RoutineController {

	@Autowired
	RoutineServiceImpl routineServiceImpl;

	@PostMapping("")
	@ResponseBody
	public String postRoutine(@RequestBody CreateRoutineDto createRoutineDto) {
		routineServiceImpl.saveRoutine(createRoutineDto);
		return "succesful";
	}

	@GetMapping("")
	@ResponseBody
	public ResponseEntity<ResponseVO<List<RoutineDto>>> getAllRoutines() {
		List<RoutineDto> routines = routineServiceImpl.allRoutines();
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
		List<RoutineDto> routines = routineServiceImpl.getPublishedRoutinesById(userId);
		ResponseEntity.ok();
		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON)
				.body(new ResponseVOBuilder<List<RoutineDto>>().addData(routines).build());
		// Responde con 200 y la lista de posts
	}
}
