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
	
	public Customer getCustomer(String email) {
		return customerDao.getCustomer(email);
	}
}
