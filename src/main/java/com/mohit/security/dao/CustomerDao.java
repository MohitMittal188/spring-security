package com.mohit.security.dao;

import java.time.LocalDateTime;

import com.mohit.security.model.Customer;

public interface CustomerDao {
	
	void saveCustomer(Customer customer);
	void saveOtp(String email,Integer otp);
	LocalDateTime getSendOtpTime(String email);
	
	Customer getCustomer(String email);
}
