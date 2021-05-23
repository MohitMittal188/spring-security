package com.mohit.security.auth.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mohit.security.auth.authentication.OtpAuthentication;
import com.mohit.security.auth.authentication.UsernamePasswordAutentication;
import com.mohit.security.service.TokenManagerService;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	TokenManagerService tokenManager;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String username = request.getHeader("username");
		String password = request.getHeader("password");
		String otp = request.getHeader("otp");
		
		if(otp == null) {
			UsernamePasswordAutentication usernamePasswordAutentication = new UsernamePasswordAutentication(username, password);
			Authentication result = authenticationManager.authenticate(usernamePasswordAutentication);
			
			if(result.isAuthenticated()) {
				String token = UUID.randomUUID().toString();
				tokenManager.addToken(token);
				response.setHeader("token", token);
				
				SecurityContextHolder.getContext().setAuthentication(result);
				filterChain.doFilter(request, response);
				
			}
			
		}else {
			OtpAuthentication otpAuthentication = new OtpAuthentication(username, otp);
			Authentication result = authenticationManager.authenticate(otpAuthentication);
			
			if(result.isAuthenticated()) {
				String token = UUID.randomUUID().toString();
				tokenManager.addToken(token);
				response.setHeader("token", token);
				
				SecurityContextHolder.getContext().setAuthentication(result);
				filterChain.doFilter(request, response);
			}
		}
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return !request.getServletPath().equals("/login");
	}
}
