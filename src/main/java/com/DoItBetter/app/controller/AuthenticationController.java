package com.DoItBetter.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DoItBetter.app.dto.LoginUserDto;
import com.DoItBetter.app.dto.RegisterUserDto;
import com.DoItBetter.app.model.User;
import com.DoItBetter.app.response.LoginResponse;
import com.DoItBetter.app.response.ResponseErrorVo;
import com.DoItBetter.app.response.ResponseVO;
import com.DoItBetter.app.response.ResponseVOBuilder;
import com.DoItBetter.app.service.AuthenticationService;
import com.DoItBetter.app.service.JwtService;

@CrossOrigin(origins = "*")
@RequestMapping("/auth")
@RestController
public class AuthenticationController {

  @Autowired
  private final JwtService jwtService;

  private final AuthenticationService authenticationService;

  public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
    this.jwtService = jwtService;
    this.authenticationService = authenticationService;
  }

  @PostMapping("/signup")
  public ResponseEntity<ResponseVO<User>> register(@RequestBody RegisterUserDto registerUserDto) {
    User registeredUser = authenticationService.signup(registerUserDto);
    return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON)
        .body(new ResponseVOBuilder<User>().addData(registeredUser).build());

  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
    User authenticatedUser = authenticationService.authenticate(loginUserDto);

    String jwtToken = jwtService.generateToken(authenticatedUser);

    LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

    return ResponseEntity.ok(loginResponse);
  }
}