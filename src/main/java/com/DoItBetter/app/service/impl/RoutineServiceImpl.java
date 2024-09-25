package com.DoItBetter.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.DoItBetter.app.dto.CreateRoutineDto;
import com.DoItBetter.app.dto.RoutineDto;
import com.DoItBetter.app.dto.RoutineExerciseResponseDto;
import com.DoItBetter.app.model.Routine;
import com.DoItBetter.app.model.RoutineExercise;
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

	public RoutineDto saveRoutine(CreateRoutineDto createRoutineDto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		User currentUser = (User) authentication.getPrincipal();
		Routine tempRoutine = new Routine(createRoutineDto.getName(), createRoutineDto.getDescription(), currentUser);

		RoutineDto savedRoutine = modelMapper.map(routineRepository.save(tempRoutine), RoutineDto.class);

		return savedRoutine;
	}

	public List<RoutineDto> getRoutinesByToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		User currentUser = (User) authentication.getPrincipal();

		List<RoutineDto> routines = new ArrayList<>();
		routineRepository.findAllByUserId(currentUser.getId()).forEach(routine -> {
			RoutineDto tempRoutineDto = modelMapper.map(routine, RoutineDto.class);
			tempRoutineDto.setUser_id(routine.getUser().getId());
			routines.add(tempRoutineDto);
		});
		return routines;
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

	public List<RoutineDto> getPublishedRoutinesByUserId(Long userId) {
		List<RoutineDto> routines = new ArrayList<>();
		routineRepository.findAllByUserIdAndPublishedTrue(userId).forEach(routine -> {
			RoutineDto tempRoutineDto = modelMapper.map(routine, RoutineDto.class);
			tempRoutineDto.setUser_id(routine.getUser().getId());
			routines.add(tempRoutineDto);
		});
		return routines;
	}

	public List<RoutineDto> getRoutinesByUserId(Long userId) {
		List<RoutineDto> routines = new ArrayList<>();
		routineRepository.findAllByUserId(userId).forEach(routine -> {
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

	public RoutineDto getRoutineById(long id) {
		RoutineDto tempRoutineDto = modelMapper.map(routineRepository.getReferenceById(id), RoutineDto.class);
		return tempRoutineDto;
	}

	public List<RoutineExerciseResponseDto> addExercise(long id, RoutineExercise exercise) {
		Routine tempRoutine = routineRepository.getReferenceById(id);
		List<RoutineExerciseResponseDto> routineExerciseListDto = new ArrayList<>();

		try {
			tempRoutine.addExercise(exercise);
		} catch (Exception e) {
			tempRoutine.getExercises().forEach(routineExercise -> {
				RoutineExerciseResponseDto tempRoutineExercise = modelMapper.map(routineExercise,
						RoutineExerciseResponseDto.class);
				tempRoutineExercise.setId(routineExercise.getExercise().getId());
				tempRoutineExercise.setName(routineExercise.getExercise().getName());
				routineExerciseListDto.add(tempRoutineExercise);
			});
			return routineExerciseListDto;
		}

		routineRepository.save(tempRoutine);

		tempRoutine.getExercises().forEach(routineExercise -> {
			RoutineExerciseResponseDto tempRoutineExercise = modelMapper.map(routineExercise,
					RoutineExerciseResponseDto.class);
			tempRoutineExercise.setId(routineExercise.getExercise().getId());
			tempRoutineExercise.setName(routineExercise.getExercise().getName());
			routineExerciseListDto.add(tempRoutineExercise);
		});

		return routineExerciseListDto;
	}

	public List<RoutineExerciseResponseDto> removeExercise(long id, RoutineExercise exercise) {
		Routine tempRoutine = routineRepository.getReferenceById(id);
		List<RoutineExerciseResponseDto> routineExerciseListDto = new ArrayList<>();

		tempRoutine.deleteExercise(exercise);
		routineRepository.save(tempRoutine);

		tempRoutine.getExercises().forEach(routineExercise -> {
			RoutineExerciseResponseDto tempRoutineExercise = modelMapper.map(routineExercise,
					RoutineExerciseResponseDto.class);
			tempRoutineExercise.setId(routineExercise.getExercise().getId());
			tempRoutineExercise.setName(routineExercise.getExercise().getName());
			routineExerciseListDto.add(tempRoutineExercise);
		});
		return routineExerciseListDto;
	}

	public List<RoutineExerciseResponseDto> getExercises(long id) {
		Routine tempRoutine = routineRepository.getReferenceById(id);
		List<RoutineExerciseResponseDto> routineExerciseListDto = new ArrayList<>();

		tempRoutine.getExercises().forEach(routineExercise -> {
			RoutineExerciseResponseDto tempRoutineExercise = modelMapper.map(routineExercise,
					RoutineExerciseResponseDto.class);
			tempRoutineExercise.setId(routineExercise.getExercise().getId());
			tempRoutineExercise.setName(routineExercise.getExercise().getName());
			routineExerciseListDto.add(tempRoutineExercise);
		});
		return routineExerciseListDto;
	}

	@Override
	public ArrayList<User> findAllUser() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'findAllUser'");
	}

	public void deleteRoutine(Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User) authentication.getPrincipal();
		Routine currentRoutine = routineRepository.getReferenceById(id);
		if (currentUser.getId() == currentRoutine.getUser().getId()) {
			routineRepository.deleteById(id);
		}
	}
}