package com.mohit.security.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.mohit.security.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@CrossOrigin
	@GetMapping("/login/otp")
	public ResponseEntity<Integer> sendLoginOtp(@RequestParam String email) {
		
		Random random = new Random();
		Integer otp = 100000 + random.nextInt(900000);
		
		loginService.saveLoginOtp(email, otp);
		
		return new ResponseEntity<>(otp,HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/login")
	public ResponseEntity<Boolean> loginAllowed(@RequestParam String username,
			@RequestParam(required=false) String password,
			@RequestParam(required=false) Integer otp){
		
		return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);
		
	} 
	
}
