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
