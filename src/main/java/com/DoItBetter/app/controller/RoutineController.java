package com.DoItBetter.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.DoItBetter.app.model.Routine;
import com.DoItBetter.app.service.impl.RoutineServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/routine")
public class RoutineController {

	@Autowired
	RoutineServiceImpl routineServiceImpl;

	@PostMapping("")
	@ResponseBody
	public String save() {
		routineServiceImpl.saveRoutine();
		return "succesful";
	}

	@GetMapping("")
	@ResponseBody
	public ResponseEntity<List<Routine>> getAllRoutines() {
		List<Routine> routines = routineServiceImpl.allRoutines();

		return ResponseEntity.ok(routines);
	}
}
