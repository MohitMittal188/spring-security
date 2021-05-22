package com.mohit.security.auth;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

//@Component
public class CredLoggingAuthenticationProvider implements AuthenticationProvider {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(CredLoggingAuthenticationProvider.class);
	 
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		LOGGER.info("**********");
		LOGGER.info("Executing logging authentication provider");
		LOGGER.info("**********");
		LOGGER.info(auth.getName());
		LOGGER.info(auth.getCredentials().toString());
		return  new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials().toString(), new ArrayList<>());
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}

}
