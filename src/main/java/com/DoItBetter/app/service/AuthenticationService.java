package com.DoItBetter.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.DoItBetter.app.dto.LoginUserDto;
import com.DoItBetter.app.dto.RegisterUserDto;
import com.DoItBetter.app.model.User;
import com.DoItBetter.app.repository.UserRepository;

@Service
public class AuthenticationService {
  @Autowired
  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final AuthenticationManager authenticationManager;

  public AuthenticationService(
      UserRepository userRepository,
      AuthenticationManager authenticationManager,
      PasswordEncoder passwordEncoder) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User signup(RegisterUserDto input) {
    User user = new User(input.getName(), input.getEmail(), input.getPassword(), input.getAge(), input.getWeight(),
        input.getHeight());
    user.setPassword(passwordEncoder.encode(input.getPassword()));

    return userRepository.save(user);
  }

  public User authenticate(LoginUserDto input) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            input.getEmail(),
            input.getPassword()));

    return userRepository.findByEmail(input.getEmail())
        .orElseThrow();
  }
}
