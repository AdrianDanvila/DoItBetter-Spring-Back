package com.DoItBetter.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoItBetter.app.dto.CreateRoutineDto;
import com.DoItBetter.app.dto.RoutineDto;
import com.DoItBetter.app.model.Routine;
import com.DoItBetter.app.model.User;
import com.DoItBetter.app.repository.RoutineRepository;
import com.DoItBetter.app.repository.UserRepository;
import com.DoItBetter.app.service.UserService;

@Service
public class RoutineServiceImpl implements UserService {
	@Autowired
	RoutineRepository routineRepository;

	@Autowired
	UserRepository userRepository;

	@SuppressWarnings("unused")
	@Autowired
	private ModelMapper modelMapper;

	public void saveRoutine(CreateRoutineDto createRoutineDto) {
		User ownerUser = userRepository.getReferenceById(createRoutineDto.getUser_id());
		Routine tempRoutine = new Routine(createRoutineDto.getName(), createRoutineDto.getDescription(), ownerUser);
		routineRepository.save(tempRoutine);
	}

	public List<RoutineDto> allRoutines() {
		List<RoutineDto> routines = new ArrayList<>();
		routineRepository.findAll().forEach(routine -> {
			RoutineDto tempRoutineDto = modelMapper.map(routine, RoutineDto.class);
			tempRoutineDto.setUser_id(routine.getUser().getId());
			routines.add(tempRoutineDto);
		});

		return routines;
	}

	public List<RoutineDto> getPublishedRoutinesById(Long userId) {
		List<RoutineDto> routines = new ArrayList<>();
		routineRepository.findAllByUserIdAndPublishedTrue(userId).forEach(routine -> {
			RoutineDto tempRoutineDto = modelMapper.map(routine, RoutineDto.class);
			tempRoutineDto.setUser_id(routine.getUser().getId());
			routines.add(tempRoutineDto);
		});
		return routines;
	}

	public List<RoutineDto> getPublishedRoutines() {
		List<RoutineDto> routines = new ArrayList<>();
		routineRepository.findAllByPublishedTrue().forEach(routine -> {
			RoutineDto tempRoutineDto = modelMapper.map(routine, RoutineDto.class);
			tempRoutineDto.setUser_id(routine.getUser().getId());
			routines.add(tempRoutineDto);
		});
		return routines;
	}

	public Routine routineById() {
		Routine routine = routineRepository.getReferenceById(Long.parseLong("1"));
		return routine;
	}

	@Override
	public ArrayList<User> findAllUser() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'findAllUser'");
	}
}