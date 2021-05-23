package com.mohit.security.auth.providers;

import java.awt.List;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.event.ListSelectionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mohit.security.auth.authentication.UsernamePasswordAutentication;
import com.mohit.security.model.Customer;
import com.mohit.security.service.CustomerService;
import com.mohit.security.service.TokenManagerService;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
		String password = (String)auth.getCredentials();
		
		Customer customer = customerService.getCustomer(username);
		
		if(customer ==null || !passwordEncoder.matches(password, customer.getPassword())) {
			throw new BadCredentialsException("Either username or password incorrect");
		}else {
			java.util.List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(()->"USER");
			return new UsernamePasswordAutentication(username, password,authorities);
		}
		
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return UsernamePasswordAutentication.class.equals(arg0);
	}

}
