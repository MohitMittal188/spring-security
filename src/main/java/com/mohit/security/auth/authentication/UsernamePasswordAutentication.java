package com.mohit.security.auth.authentication;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UsernamePasswordAutentication extends UsernamePasswordAuthenticationToken{

	public UsernamePasswordAutentication(Object principal, Object credentials) {
		super(principal, credentials);
	}
	
	public UsernamePasswordAutentication(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}
	
}
