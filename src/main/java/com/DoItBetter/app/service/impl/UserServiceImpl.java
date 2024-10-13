package com.DoItBetter.app.service.impl;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.DoItBetter.app.dto.UserDto;
import com.DoItBetter.app.model.User;
import com.DoItBetter.app.repository.UserRepository;
import com.DoItBetter.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;
	private static final String UPLOAD_DIRECTORY = "uploads/";

	@Override
	public ArrayList<User> findAllUser() {
		throw new UnsupportedOperationException("Unimplemented method 'findAllEmployee'");
	}

	public void saveUser() {
		User tempUser = new User();
		userRepository.save(tempUser);
	}

	public List<UserDto> allUsers() {
		List<UserDto> users = new ArrayList<>();

		userRepository.findAll().forEach(user -> {
			users.add(modelMapper.map(user, UserDto.class));
		});

		return users;
	}

	public void saveProfilePicture(Long userId, MultipartFile file) throws IOException {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) {
			throw new IllegalArgumentException("Usuario no encontrado");
		}
		//

		if (file.isEmpty()) {
			throw new IOException("No se ha subido ninguna imagen");
		}

		//
		String fileName = userId + "_" + file.getOriginalFilename();
		Path path = Paths.get(UPLOAD_DIRECTORY + fileName);
		Files.createDirectories(path.getParent());
		Files.write(path, file.getBytes());

		user.setProfilePictureName(fileName);
		user.setProfilePicturePath(path.toString());
		userRepository.save(user);
	}

}