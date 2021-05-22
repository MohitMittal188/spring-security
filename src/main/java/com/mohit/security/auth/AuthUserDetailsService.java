package com.mohit.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mohit.security.model.Customer;
import com.mohit.security.service.CustomerService;


@Service
public class AuthUserDetailsService implements UserDetailsService {
	
	@Autowired
	CustomerService customerService;
	
	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		Customer customer = customerService.getCustomer(arg0);
		if(customer==null)
			throw new UsernameNotFoundException("No user from this username");
		
		return new Principal(customer);
	}

}
