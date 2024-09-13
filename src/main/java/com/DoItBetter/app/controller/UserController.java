package com.DoItBetter.app.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.DoItBetter.app.dto.UserDto;
import com.DoItBetter.app.model.User;
import com.DoItBetter.app.response.ResponseVO;
import com.DoItBetter.app.response.ResponseVOBuilder;
import com.DoItBetter.app.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	private ModelMapper modelMapper;

	public UserController(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	@GetMapping("/me")
	public ResponseEntity<ResponseVO<UserDto>> authenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		User currentUser = (User) authentication.getPrincipal();

		UserDto user = modelMapper.map(currentUser, UserDto.class);

		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON)
				.body(new ResponseVOBuilder<UserDto>().addData(user).build());
	}

	@GetMapping("")
	public ResponseEntity<ResponseVO<List<UserDto>>> allUsers() {
		List<UserDto> users = userServiceImpl.allUsers();

		return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON)
				.body(new ResponseVOBuilder<List<UserDto>>().addData(users).build());
	}
}
