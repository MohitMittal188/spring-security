package com.mohit.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.security.model.Customer;
import com.mohit.security.service.CustomerService;

@RestController
public class CustomerServiceController {	
	
	@Autowired
	CustomerService customerService;
	
	@CrossOrigin
	@GetMapping("/get/customer")
	public ResponseEntity<Customer> getCustomer(@RequestParam String email){
		Customer customer = customerService.getCustomer(email);
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
	
}
