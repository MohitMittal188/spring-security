package com.mohit.security.auth.providers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.mohit.security.auth.authentication.OtpAuthentication;
import com.mohit.security.service.LoginService;

@Component
public class OtpAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	LoginService loginService;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
		Integer credentials = Integer.valueOf(auth.getCredentials().toString());
		
		Integer otp = loginService.getOtp(username);
		
		if(otp!=null && otp.equals(credentials)) {
			List<GrantedAuthority> list = new LinkedList<>();
			list.add(()->"USER");
			return new OtpAuthentication(username, credentials,list);
		}
		throw new BadCredentialsException("Bad credentials");
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return OtpAuthentication.class.equals(arg0);
	}

}
