package com.DoItBetter.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DoItBetter.app.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
