package com.mohit.security.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mohit.security.model.Customer;
import com.mohit.security.service.CustomerService;

@Controller
public class RegistrationController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/send/otp")
	public ResponseEntity<Integer> sendOtp(@RequestParam String emailId){
		Random random = new Random();
		Integer otp = 100000 + random.nextInt(900000);
		customerService.saveOtp(emailId,otp);
		
//		TODO: SEND THIS OTP ON EMAIL
		return new ResponseEntity<>(otp,HttpStatus.OK);
	}
	
	@GetMapping("/check/otp")
	public ResponseEntity<Boolean> checkOtpExpire(String emailId,Integer otp){
		Boolean otpExpire = customerService.isOtpExpire(emailId);
		
		return new ResponseEntity<>(otpExpire,HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
