package com.mohit.security.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider{

	private static final Logger LOGGER=LoggerFactory.getLogger(CustomDaoAuthenticationProvider.class);
	
	CustomDaoAuthenticationProvider(AuthUserDetailsService userDetailsSer){
		super.setUserDetailsService(userDetailsSer);
		super.setPasswordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	public Authentication authenticate(Authentication arg0) throws AuthenticationException {
		LOGGER.info("**********");
		LOGGER.info("Executing Dao authentication provider");
		LOGGER.info("**********");
		return super.authenticate(arg0);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return super.supports(authentication);
	}

	
}
