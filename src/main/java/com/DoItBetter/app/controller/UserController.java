package com.DoItBetter.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.DoItBetter.app.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;

	@GetMapping("")
	@ResponseBody
	public String getUser() {
		return "HOLA";
	}

	@PostMapping("")
	public String postUser() {
		userServiceImpl.saveUser();
		return "succesful";
	}
}