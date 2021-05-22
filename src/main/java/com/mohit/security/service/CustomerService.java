package com.mohit.security.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mohit.security.dao.CustomerDao;
import com.mohit.security.model.Customer;

@Service
public class CustomerService {

	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void saveOtp(String email,Integer otp){
		customerDao.saveOtp(email, otp);
	}
	
	public Boolean isOtpExpire(String email){
		LocalDateTime sendOtpTime = customerDao.getSendOtpTime(email);
		
		return sendOtpTime.plusMinutes(30).isBefore(LocalDateTime.now());
	}
	
	public void saveCustomer(Customer customer){
		String hashedPassword = passwordEncoder.encode(customer.getPassword());
		customer.setPassword(hashedPassword);
		customerDao.saveCustomer(customer);
	}
	
	public Customer getCustomer(String email) {
		return customerDao.getCustomer(email);
	}
}
