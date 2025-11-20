package com.project.cateringboys.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.cateringboys.dto.LoginRequest;
import com.project.cateringboys.dto.PasswordUpdateRequest;
import com.project.cateringboys.dto.SignupRequest;
import com.project.cateringboys.model.User;
import com.project.cateringboys.service.UserService;

@RestController
@RequestMapping("/cateringboys")
@CrossOrigin(origins = "*")
public class HomeController {
    
	@Autowired
    private  BookingController bookingController;
	
	@Autowired
	private UserService userService;
	
	
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody SignupRequest signupRequest) {
		return ResponseEntity.ok(userService.registerUser(signupRequest));
	}
	
	@PostMapping("/login")
	public ResponseEntity<?>  login(@RequestBody LoginRequest loginRequest) {
		try {
			  User user=userService.loginUser(loginRequest);
			  return ResponseEntity.ok(user);
		}catch(RuntimeException e) {
			return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
		}
	}
	@GetMapping
	public ResponseEntity<?> getAccount(@RequestParam String email){
		Optional<User> user = userService.findByEmail(email);
	    if (user == null) {
	        return ResponseEntity.badRequest().body(Map.of("error", "Account not found!"));
	    }
	    return ResponseEntity.ok(user);
	}
	
	@PutMapping("/user/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody PasswordUpdateRequest request) {
        try {
        	System.out.println(request.getEmail());
        	System.out.println(request.getNewPassword());
            userService.updatePassword(request.getEmail(), request.getNewPassword());
            return ResponseEntity.ok(Map.of("message", "Password updated successfully!"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
