package com.mohit.security.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mohit.security.dao.CustomerDao;
import com.mohit.security.model.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	
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
	
	@Override
	public Customer getCustomer(String email) {
		String query = "select * from customer where email = :emailId";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("emailId", email);
		
		return jdbcTemplate.queryForObject(query, paramSource, new RowMapper<Customer>() {

			@Override
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Customer customer = new Customer();	
				customer.setFirstName(rs.getString("firstname"));
				customer.setLastName(rs.getString("lastname"));
				customer.setEmail(rs.getString("email"));
				customer.setPhoneNumber(rs.getString("phone_number"));
				customer.setPassword(rs.getString("password"));
				return customer;
			}
			
		});
	}
}
