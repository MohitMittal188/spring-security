package com.mohit.security.auth.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mohit.security.auth.authentication.TokenAuthentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter{

	AuthenticationManager authenticationManager;
	
	@Autowired
	TokenAuthenticationFilter(@Lazy AuthenticationManager authenticationManager){
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = request.getHeader("authorizationToken");
		if(token!=null) {
			token = token.replace("Bearer ", "");	
		}
		
		if(token!=null) {
			TokenAuthentication tokenAuthentication = new TokenAuthentication(token, null);
			Authentication authenticate = getAuthenticationManager().authenticate(tokenAuthentication);
			
			SecurityContextHolder.getContext().setAuthentication(authenticate);
		}
		
		filterChain.doFilter(request, response);
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}
	
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
}
