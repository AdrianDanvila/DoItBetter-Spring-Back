package com.DoItBetter.app.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.DoItBetter.app.dto.CreateRoutineDto;
import com.DoItBetter.app.dto.RoutineDto;
import com.DoItBetter.app.dto.RoutineExerciseResponseDto;
import com.DoItBetter.app.model.Comment;
import com.DoItBetter.app.model.Routine;
import com.DoItBetter.app.model.RoutineExercise;
import com.DoItBetter.app.model.User;
import com.DoItBetter.app.repository.RoutineRepository;
import com.DoItBetter.app.repository.UserRepository;
import com.DoItBetter.app.service.UserService;
import com.DoItBetter.app.util.Utils;

@Service
public class RoutineServiceImpl implements UserService {
	@Autowired
	RoutineRepository routineRepository;

	private static final String UPLOAD_DIRECTORY = "uploads/";

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

	public RoutineDto copyRoutine(Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		User currentUser = (User) authentication.getPrincipal();
		Routine originalRoutine = routineRepository.getReferenceById(id);
		Routine routine = new Routine()
				.setDescription(originalRoutine.getDescription())
				.setName(originalRoutine.getName())
				.setUser(currentUser)
				.setExercises(new ArrayList<>());
		originalRoutine.getExercises().forEach(routineExercise -> {
			RoutineExercise tempRoutineExercise = new RoutineExercise()
					.setExercise(routineExercise.getExercise())
					.setReps(routineExercise.getReps())
					.setSets(routineExercise.getSets())
					.setWeight(routineExercise.getWeight());
			routine.getExercises().add(tempRoutineExercise);
		});
		RoutineDto savedRoutine = modelMapper.map(routineRepository.save(routine), RoutineDto.class);

		return savedRoutine;
	}

	public List<RoutineDto> getRoutinesByToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		User currentUser = (User) authentication.getPrincipal();

		List<RoutineDto> routines = new ArrayList<>();
		routineRepository.findAllByUserId(currentUser.getId()).forEach(routine -> {
			RoutineDto tempRoutineDto = modelMapper.map(routine, RoutineDto.class);
			tempRoutineDto.setUser_id(routine.getUser().getId());
			tempRoutineDto.setUser_name(routine.getUser().getName());
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
			tempRoutineDto.setUser_name(routine.getUser().getName());
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
			tempRoutineDto.setUser_name(routine.getUser().getName());
			routines.add(tempRoutineDto);
		});
		return routines;
	}

	public Routine routineById() {
		Routine routine = routineRepository.getReferenceById(Long.parseLong("1"));
		return routine;
	}

	public Routine toggleRoutinePublished(long id) {
		Routine routine = routineRepository.getReferenceById(id);
		routine.setPublished(!routine.isPublished());
		routineRepository.save(routine);
		return routine;
	}

	public RoutineDto getRoutineById(long id) {
		Routine routine = routineRepository.getReferenceById(id);
		RoutineDto tempRoutineDto = modelMapper.map(routine, RoutineDto.class);
		tempRoutineDto.setUser_id(routine.getUser().getId());
		tempRoutineDto.setUser_name(routine.getUser().getName());
		return tempRoutineDto;
	}

	public List<RoutineExerciseResponseDto> addExercise(long id, RoutineExercise exercise) {
		Routine tempRoutine = routineRepository.getReferenceById(id);
		List<RoutineExerciseResponseDto> routineExerciseListDto = new ArrayList<>();

		try {
			tempRoutine.addExercise(exercise);
		} catch (Exception e) {
			routineRepository.save(tempRoutine);

			tempRoutine.getExercises().forEach(routineExercise -> {
				RoutineExerciseResponseDto tempRoutineExercise = modelMapper.map(routineExercise,
						RoutineExerciseResponseDto.class);
				tempRoutineExercise.setId(routineExercise.getExercise().getId());
				tempRoutineExercise.setName(routineExercise.getExercise().getName());
				tempRoutineExercise.setDescription(routineExercise.getExercise().getDescription());
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
			tempRoutineExercise.setDescription(routineExercise.getExercise().getDescription());
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
			tempRoutineExercise.setDescription(routineExercise.getExercise().getDescription());
			routineExerciseListDto.add(tempRoutineExercise);
		});
		return routineExerciseListDto;
	}

	public List<RoutineExerciseResponseDto> getExercises(long id) {
		Routine tempRoutine = routineRepository.getReferenceById(id);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<RoutineExerciseResponseDto> routineExerciseListDto = new ArrayList<>();

		User currentUser = (User) authentication.getPrincipal();
		tempRoutine.getExercises().forEach(routineExercise -> {
			RoutineExerciseResponseDto tempRoutineExercise = modelMapper.map(routineExercise,
					RoutineExerciseResponseDto.class);
			tempRoutineExercise.setId(routineExercise.getExercise().getId());
			tempRoutineExercise.setName(routineExercise.getExercise().getName());
			tempRoutineExercise.setDescription(routineExercise.getExercise().getDescription());
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

			Utils.deleteImagesFromUpload(id);
			routineRepository.deleteById(id);
		}
	}

	public RoutineDto saveRoutinePicture(Long id, MultipartFile file) throws IOException {
		Routine routine = routineRepository.findById(id).orElse(null);
		if (routine == null) {
			throw new IllegalArgumentException("Usuario no encontrado");
		}
		//

		if (file.isEmpty()) {
			throw new IOException("No se ha subido ninguna imagen");
		}

		//
		String fileName = id + "_" + file.getOriginalFilename();
		Path path = Paths.get(UPLOAD_DIRECTORY + fileName);
		Files.createDirectories(path.getParent());
		Files.write(path, file.getBytes());

		routine.setRoutinePictureName(fileName);
		routine.setRoutinePicturePath(path.toString());
		return modelMapper.map(routineRepository.save(routine), RoutineDto.class);

	}

	public RoutineDto addComment(Long routineId, String content) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User) authentication.getPrincipal();
		Routine currentRoutine = routineRepository.getReferenceById(routineId);
		Comment newComment = new Comment();
		newComment.setContent(content);
		newComment.setUser_id(currentUser.getId());
		newComment.setUser_name(currentUser.getName());
		currentRoutine.addComment(newComment);
		routineRepository.save(currentRoutine);
		return getRoutineById(routineId);
	}
}