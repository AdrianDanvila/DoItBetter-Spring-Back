package com.DoItBetter.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoItBetter.app.model.Routine;
import com.DoItBetter.app.model.User;
import com.DoItBetter.app.repository.RoutineRepository;
import com.DoItBetter.app.service.UserService;

@Service
public class RoutineServiceImpl implements UserService {
	@Autowired
	RoutineRepository routineRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ArrayList<User> findAllUser() {
		// TODO Auto-generated method stu b
		throw new UnsupportedOperationException("Unimplemented method 'findAllEmployee'");
	}

	public void saveRoutine() {
		Routine tempRoutine = new Routine();
		routineRepository.save(tempRoutine);
	}

	public List<Routine> allRoutines() {
		List<Routine> routines = new ArrayList<>();

		routineRepository.findAll().forEach(routines::add);

		return routines;
	}

}