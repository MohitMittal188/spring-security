package com.mohit.security.dao;

import java.time.LocalDateTime;

import com.mohit.security.model.Customer;

public interface CustomerDao {
	
	Customer getCustomer(String email);
}
