package com.mohit.security.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mohit.security.dao.LoginDao;

@Repository
public class LoginDaoImpl implements LoginDao {
	
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public void saveLoginOtp(String email, Integer otp) {
		
		String query = "insert into login_otp values(:emailId,:otp,:datetime)";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("emailId", email);
		paramSource.addValue("otp", otp);
		paramSource.addValue("datetime", LocalDateTime.now());
		
		jdbcTemplate.update(query, paramSource);
		
	}

	@Override
	public Integer getOtp(String email) {
		String query = "select otp from login_otp where email =:emailId";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("emailId", email);
		
		return jdbcTemplate.queryForObject(query, paramSource, Integer.class);
	}

}
