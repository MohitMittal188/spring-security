package com.mohit.security.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mohit.security.dao.RegistrationDao;
import com.mohit.security.model.Customer;

@Repository
public class RegistrationDaoImpl implements RegistrationDao{
		
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void saveCustomer(Customer customer) {
		String query = "INSERT INTO customer VALUES(:firstName,:lastName,:email,:phoneNumber,:password)";
		MapSqlParameterSource paramSource  = new MapSqlParameterSource();
		paramSource.addValue("firstName", customer.getFirstName());
		paramSource.addValue("lastName", customer.getLastName());
		paramSource.addValue("email", customer.getEmail());
		paramSource.addValue("phoneNumber", customer.getPhoneNumber());
		paramSource.addValue("password", customer.getPassword());
		jdbcTemplate.update(query, paramSource);
	}

	@Override
	public void saveOtp(String email, Integer otp) {
		String query = "insert into registration_otp values(:email,:otp,:currentTime)";
		LocalDateTime currentTime = LocalDateTime.now();
		
		MapSqlParameterSource paramSource  = new MapSqlParameterSource();
		paramSource.addValue("email", email);
		paramSource.addValue("otp", otp);
		paramSource.addValue("currentTime", currentTime);
		
		jdbcTemplate.update(query,paramSource);
	}

	@Override
	public LocalDateTime getSendOtpTime(String email) {
		String query = "select send_time from registration_otp where email = :emailId";
		MapSqlParameterSource paramSource  = new MapSqlParameterSource();
		paramSource.addValue("emailId", email);
		
		LocalDateTime otpSendtime = jdbcTemplate.queryForObject(query, paramSource, LocalDateTime.class);
		
		return otpSendtime;
	}
	
}
