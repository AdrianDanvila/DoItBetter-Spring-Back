package com.DoItBetter.app.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DoItBetter.app.model.User;
import com.DoItBetter.app.repository.UserRepository;
import com.DoItBetter.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public ArrayList<User> findAllUser() {
        // TODO Auto-generated method stu b
        throw new UnsupportedOperationException("Unimplemented method 'findAllEmployee'");
    }

    public void saveUser(){
        User tempUser =  new User();
        userRepository.save(tempUser);
    }

}