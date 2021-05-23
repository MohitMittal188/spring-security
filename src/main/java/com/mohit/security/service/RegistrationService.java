package com.mohit.security.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mohit.security.dao.RegistrationDao;
import com.mohit.security.model.Customer;

@Service
public class RegistrationService {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	RegistrationDao registrationDao;
	
	public void saveOtp(String email,Integer otp){
		registrationDao.saveOtp(email, otp);
	}
	
	public Boolean isOtpExpire(String email){
		LocalDateTime sendOtpTime = registrationDao.getSendOtpTime(email);
		
		return sendOtpTime.plusMinutes(30).isBefore(LocalDateTime.now());
	}
	
	public void saveCustomer(Customer customer){
		String hashedPassword = passwordEncoder.encode(customer.getPassword());
		customer.setPassword(hashedPassword);
		registrationDao.saveCustomer(customer);
	}
}
