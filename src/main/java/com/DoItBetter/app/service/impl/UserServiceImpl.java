package com.DoItBetter.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}