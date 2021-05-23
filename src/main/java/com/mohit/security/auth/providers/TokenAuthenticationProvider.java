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

import com.mohit.security.auth.authentication.TokenAuthentication;
import 	com.mohit.security.service.TokenManagerService;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	TokenManagerService tokenManager;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		String token = auth.getName();
	
		if(tokenManager.checkTokenVaild(token)) {
			List<GrantedAuthority> authorities = new LinkedList<>();
			authorities.add(()->"USER");
			
			return new TokenAuthentication(token, null,authorities);
		}
		
		throw new BadCredentialsException("Invalid token");
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return TokenAuthentication.class.equals(arg0);
	}

}
