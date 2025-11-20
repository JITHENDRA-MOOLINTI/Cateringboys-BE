package com.project.cateringboys.service;

import java.util.List;
import java.util.Optional;

import com.project.cateringboys.dto.LoginRequest;
import com.project.cateringboys.dto.SignupRequest;
import com.project.cateringboys.model.User;

public interface UserService {
      
	User registerUser(SignupRequest signupRequest);
    List<User> getAllUsers();
    User getUserById(Long id); 
    Optional<User> findByEmail(String email);
    User loginUser(LoginRequest loginRequest);
    void updatePassword(String email, String newPassword);
  
}
