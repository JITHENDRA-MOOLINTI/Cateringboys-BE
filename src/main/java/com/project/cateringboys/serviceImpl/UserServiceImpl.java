package com.project.cateringboys.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.cateringboys.dto.LoginRequest;
import com.project.cateringboys.dto.SignupRequest;
import com.project.cateringboys.exception.InvalidCreditintialsException;
import com.project.cateringboys.model.User;
import com.project.cateringboys.repo.UserRepo;
import com.project.cateringboys.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;

	@Override
	public User registerUser(SignupRequest signupRequest) {
		  User user=new User();
		  user.setEmail(signupRequest.getEmail());
		  user.setName(signupRequest.getName());
		  user.setPassword(signupRequest.getPassword());
		  user.setPhone(signupRequest.getPhone());
          user.setRole(signupRequest.getRole());
		  return userRepo.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		 return userRepo.findAll();
		
	}

	@Override
	public User getUserById(Long id) {
		  return  userRepo.findById(id).orElseThrow(()->new RuntimeException("User is not Found"));
		
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	

	@Override
	public User loginUser(LoginRequest loginRequest) {
	     User user=userRepo.findByEmail(loginRequest.getEmail()).orElseThrow(()->new InvalidCreditintialsException("User is not found"));
	     
	     // use passwordEncoder to compare raw password with stored hash
	     if (!loginRequest.getPassword().equals(user.getPassword())) {
	         throw new InvalidCreditintialsException("Invalid Password");
	     }

	     // compare roles safely (use equals; avoid calling toString unnecessarily)
	     if (!user.getRole().equals(loginRequest.getRole())) {
	         throw new InvalidCreditintialsException("Not a " + loginRequest.getRole());
	     }

	     return user;
	     

	}

	 @Override
	    public void updatePassword(String email, String newPassword) {

	        User user = userRepo.findByEmail(email)
	                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    
	        user.setPassword(newPassword);
	        userRepo.save(user);
	    }	

}
